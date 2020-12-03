package util.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import util.http.HttpUtil;
import util.sftp.SftpConfigure;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dzd
 */
@Slf4j
public class FtpUtil {

    public static String JPG = "jpg";
    public static String PNG = "png";
    public static String GIF = "gif";
    public static String TIF = "tif";

    private SftpConfigure ftpConfig;

    public void setStpConfig(SftpConfigure basicConfig) {

        this.ftpConfig = basicConfig;
    }


    /**
     * 获取FTPClient对象
     *
     * @return FTPClient
     */
    public FTPClient getFTPClient() {

        if (ftpConfig == null) {
            log.error("getFTPClient ftp配置未初始化");
            return null;
        }
        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(ftpConfig.getSftpHost(), ftpConfig.getSftpPort());
            // 设置用户名和密码
            ftp.login(ftpConfig.getSftpUsername(), ftpConfig.getSftpPassword());
            // 设置连接超时时间,5000毫秒
            ftp.setConnectTimeout(50000);
            // 设置中文编码集，防止中文乱码
            ftp.setControlEncoding("UTF-8");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                log.info("未连接到FTP，用户名或密码错误");
                ftp.disconnect();
            } else {
                log.info("FTP连接成功");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            log.info("FTP的IP地址可能错误，请正确配置");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP的端口错误,请正确配置");
        }
        return ftp;
    }

    /**
     * 关闭FTP方法
     *
     * @param ftp
     * @return
     */
    public boolean closeFTP(FTPClient ftp) {

        try {
            ftp.logout();
        } catch (Exception e) {
            log.error("FTP关闭失败");
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    log.error("FTP关闭失败");
                }
            }
        }

        return false;

    }


    /**
     * 下载FTP下指定文件
     *
     * @param ftp      FTPClient对象
     * @param filePath FTP文件路径
     * @param fileName 文件名
     * @param downPath 下载保存的目录
     * @return
     */
    public boolean downLoad(FTPClient ftp, String filePath, String fileName,
                            String downPath) {
        // 默认失败
        boolean flag = false;
        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(filePath);
            // 获取目录下文件集合
            ftp.enterLocalPassiveMode();
            ftp.setControlEncoding("GBK");
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {
                    File path = new File(downPath);
                    if (!path.exists()) {
                        boolean b = path.mkdirs();
                        log.warn("创建文件夹{}，{}" + downPath, b);
                    }
                    File downFile = new File(downPath + File.separator
                            + file.getName());
                    OutputStream out = new FileOutputStream(downFile);
                    // 绑定输出流下载文件,需要设置编码集，不然可能出现文件为空的情况
                    flag = ftp.retrieveFile(new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), out);
                    out.flush();
                    out.close();
                    if (flag) {
                        log.warn("下载成功");
                    } else {
                        log.error("下载失败");
                    }
                }
            }
        } catch (Exception e) {
            log.error("下载失败", e);
        }
        return flag;
    }


    /**
     * FTP文件上传工具类 ，默认现在已经处于对应的目录下了
     *
     * @param in       in
     * @param filePath filePath
     * @param ftp      ftp
     */
    public Boolean uploadFileWithName(InputStream in, String filePath, FTPClient ftp) {
        boolean flag = false;
        try {
            // 设置PassiveMode传输
            ftp.enterLocalPassiveMode();
            //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            //判断FPT目标文件夹时候存在不存在则创建
            //上传文件
            flag = ftp.storeFile(new String(filePath.getBytes("UTF-8"), "ISO-8859-1"), in);
            if (flag) {
                log.info("上传成功");
            } else {
                log.error("上传失败");
            }
        } catch (Exception e) {
            log.error("ftp 上传失败", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                log.error("uploadFileWithName ftp error", e);
            }
        }
        return flag;
    }


    /**
     * 进入到对应的目录中如果不存在则创建目录后进入
     *
     * @param ftp     ftp
     * @param ftpPaths ftpPaths
     * @throws IOException IOException
     */
    public void createDir(FTPClient ftp,  List<String> ftpPaths) throws IOException {
        for (String ftpPath : ftpPaths) {
            if (!ftp.changeWorkingDirectory(ftpPath)) {
                ftp.makeDirectory(ftpPath);
                //如果不存在就先创建在进入内层
                ftp.changeWorkingDirectory(ftpPath);
            }
        }
    }

    private void checkFileType(String path) throws Exception {
        String fileType;
        int i = path.lastIndexOf('.');
        if (i == -1) {
            throw new Exception("uploadFileWithName i==-1 文件类型错误");
        }
        // 获取文件类型
        fileType = path.substring(i + 1).toLowerCase();
        if (!JPG.equals(fileType) && !PNG.equals(fileType) && !GIF.equals(fileType) && !TIF.equals(fileType)) {
            log.error("文件类型有误：" + fileType);
            throw new Exception("文件类型有误：" + fileType);
        }
    }


    /**
     * FPT上文件的复制
     *
     * @param ftp      FTPClient对象
     * @param olePath  原文件地址
     * @param newPath  新保存地址
     * @param fileName 文件名
     * @return
     */
    public boolean copyFile(FTPClient ftp, String olePath, String newPath, String fileName) {
        boolean flag = false;

        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(olePath);
            //设置连接模式，不设置会获取为空
            ftp.enterLocalPassiveMode();
            // 获取目录下文件集合
            FTPFile[] files = ftp.listFiles();
            ByteArrayInputStream in = null;
            ByteArrayOutputStream out = null;
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {

                    //读取文件，使用下载文件的方法把文件写入内存,绑定到out流上
                    out = new ByteArrayOutputStream();
                    ftp.retrieveFile(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"), out);
                    in = new ByteArrayInputStream(out.toByteArray());
                    //创建新目录
                    ftp.makeDirectory(newPath);
                    //文件复制，先读，再写
                    //二进制
                    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                    flag = ftp.storeFile(newPath + File.separator + (new String(file.getName().getBytes("UTF-8"), "ISO-8859-1")), in);
                    out.flush();
                    out.close();
                    in.close();
                    if (flag) {
                        log.info("转存成功");
                    } else {
                        log.error("复制失败");
                    }


                }
            }
        } catch (Exception e) {
            log.error("复制失败");
        }
        return flag;
    }

    /**
     * 实现文件的移动，这里做的是一个文件夹下的所有内容移动到新的文件，
     * 如果要做指定文件移动，加个判断判断文件名
     * 如果不需要移动，只是需要文件重命名，可以使用ftp.rename(oleName,newName)
     *
     * @param ftp
     * @param oldPath
     * @param newPath
     * @return
     */
    public boolean moveFile(FTPClient ftp, String oldPath, String newPath) {
        boolean flag = false;

        try {
            ftp.changeWorkingDirectory(oldPath);
            ftp.enterLocalPassiveMode();
            //获取文件数组
            FTPFile[] files = ftp.listFiles();
            //新文件夹不存在则创建
            if (!ftp.changeWorkingDirectory(newPath)) {
                ftp.makeDirectory(newPath);
            }
            //回到原有工作目录
            ftp.changeWorkingDirectory(oldPath);
            for (FTPFile file : files) {

                //转存目录
                flag = ftp.rename(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"), newPath + File.separator + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                if (flag) {
                    log.info(file.getName() + "移动成功");
                } else {
                    log.error(file.getName() + "移动失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("移动文件失败");
        }
        return flag;
    }

    /**
     * 遍历解析文件夹下所有文件
     *
     * @param folderPath 需要解析的的文件夹
     * @param ftp        FTPClient对象
     * @return
     */
    public boolean readFileByFolder(FTPClient ftp, String folderPath) {
        boolean flage = false;
        try {
            ftp.changeWorkingDirectory(new String(folderPath.getBytes("UTF-8"), "ISO-8859-1"));
            //设置FTP连接模式
            ftp.enterLocalPassiveMode();
            //获取指定目录下文件文件对象集合
            FTPFile files[] = ftp.listFiles();
            InputStream in = null;
            BufferedReader reader = null;
            for (FTPFile file : files) {
                //判断为txt文件则解析
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        in = ftp.retrieveFileStream(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                        reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                        String temp;
                        StringBuffer buffer = new StringBuffer();
                        while ((temp = reader.readLine()) != null) {
                            buffer.append(temp);
                        }
                        if (reader != null) {
                            reader.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                        //ftp.retrieveFileStream使用了流，需要释放一下，不然会返回空指针
                        ftp.completePendingCommand();
                        //这里就把一个txt文件完整解析成了个字符串，就可以调用实际需要操作的方法
                        System.out.println(buffer.toString());
                    }
                }
                //判断为文件夹，递归
                if (file.isDirectory()) {
                    String path = folderPath + File.separator + file.getName();
                    readFileByFolder(ftp, path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件解析失败");
        }
        return flage;
    }

    public static void main(String[] args) throws IOException {
        SftpConfigure sftpConfig=new SftpConfigure();
        sftpConfig.setSftpHost("106.38.29.220");
        sftpConfig.setSftpPort(21);
        sftpConfig.setSftpUsername("fanhuarw_test");
        sftpConfig.setSftpPassword("9Phpn5Sru_9q35#8");
        FtpUtil  ftpUtil = new FtpUtil();
        ftpUtil.setStpConfig(sftpConfig);

        FTPClient ftp = ftpUtil.getFTPClient();

        List<String> filePath=new ArrayList<>();
        filePath.add("86110000");
        filePath.add("2020");
        filePath.add("08");
        filePath.add("11");
        String fileName="F34567.jpg";
        log.info("filepathDir 1{}",filePath);
        ftpUtil.createDir(ftp, filePath);
        InputStream inputStream = HttpUtil.readImg("https://dby-sem-1.oss-cn-hangzhou.aliyuncs.com/excel/%E5%90%B4%E6%94%B9.jpg");
        log.info("filepathName 1{}",filePath);
        ftpUtil.uploadFileWithName(inputStream,fileName,ftp);
        ftpUtil.closeFTP(ftp);
    }
}

