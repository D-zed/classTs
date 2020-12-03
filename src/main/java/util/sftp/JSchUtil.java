package util.sftp;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import contansts.StringPool;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * SFTP文件上传工具类
 *
 * @author dzd
 */
@Slf4j
public class JSchUtil {

    public static String JPG = "jpg";
    public static String PNG = "png";
    public static String GIF = "gif";
    public static String TIF = "tif";


    private SftpConfigure sftpConfigure;


    public void setSftpConfigure(SftpConfigure basicConfig) {

        this.sftpConfigure = basicConfig;
    }

    public SftpConfigure getSftpConfigure() {

        return sftpConfigure;
    }


    // Session对象
    private Session session = null;

    // Channel对象
    private Channel channel = null;

    /* *//**
     * 上传文件至FTP
     *
     * @param multipartFile 要上传的文件
     * @return String 文件URL地址
     *//*
    public String uploadFileWithName(MultipartFile multipartFile, String path, ChannelSftp sftp) throws Exception {

        if (multipartFile == null) {
            throw new Exception("uploadFileWithName multipartFile is null");
        }
        // 文件类型
        String fileType;
        // 初始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null) {
            throw new Exception("uploadFileWithName originalFilename is null");
        }
        int i = originalFilename.lastIndexOf('.');
        if (i == -1) {
            throw new Exception("uploadFileWithName i==-1 文件类型错误");
        }
        // 获取文件类型
        fileType = originalFilename.substring(i + 1).toLowerCase();
        if (!JPG.equals(fileType) && !PNG.equals(fileType) && !GIF.equals(fileType)) {
            log.error("文件类型有误：" + fileType);
            throw new Exception("uploadFileWithName 文件类型错误 type :" + fileType);
        }

        // 获取输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 上传模式
        int mode = ChannelSftp.OVERWRITE;
        *//*
     * OVERWRITE:完全覆盖模式(如果目标文件已经存在，传输的文件将完全覆盖目标文件，产生新的文件)
     * RESUME:恢复模式(文件已经传输一部分，这时由于网络或其他任何原因导致文件传输中断，如果下一次传输相同的文件)
     * APPEND:追加模式(如果目标文件已存在，传输的文件将在目标文件后追加)
     *//*
        sftp.put(inputStream, path, mode);
        sftp.quit();
        log.info("上传完毕：{}", path);
        return path;
    }
*/

    /**
     * 上传文件至FTP
     */
    public void uploadFileWithName(InputStream inputStream, String path, ChannelSftp sftp) throws Exception {
        // 文件类型
        // checkFileType(path);
        // 上传模式
        int mode = ChannelSftp.OVERWRITE;
        /*
         * OVERWRITE:完全覆盖模式(如果目标文件已经存在，传输的文件将完全覆盖目标文件，产生新的文件)
         * RESUME:恢复模式(文件已经传输一部分，这时由于网络或其他任何原因导致文件传输中断，如果下一次传输相同的文件)
         * APPEND:追加模式(如果目标文件已存在，传输的文件将在目标文件后追加)
         */
        sftp.put(inputStream, path, mode);
        // sftp.quit();
        log.info("上传完毕：{}", path);

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
     * 关闭Channel
     */
    public void closeChannel() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    /**
     * 获取基于SSL协议的ChannelSftp对象
     *
     * @return ChannelSftp SSL协议的ChannelSftp对象
     */
    public ChannelSftp getChannel(SftpConfigure sftpConfigure) throws JSchException {
        setSftpConfigure(sftpConfigure);
        if (this.sftpConfigure == null) {
            log.error("getChannel sftp配置参数为空");
            return null;
        }
        // 创建JSch对象
        JSch jsch = new JSch();
        // 获取session对象
        log.info("getChannel sftpConfig{},{}，{}，{}，{}", this.sftpConfigure.getSftpUsername(), this.sftpConfigure.getSftpHost(), this.sftpConfigure.getSftpPort(), this.sftpConfigure.getSftpRootPath(), this.sftpConfigure.getSftpPassword());
        session = jsch.getSession(this.sftpConfigure.getSftpUsername(), this.sftpConfigure.getSftpHost(), this.sftpConfigure.getSftpPort());
        // 设置密码
        session.setPassword(this.sftpConfigure.getSftpPassword());
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        // 为session对象设置properties
        session.setConfig(config);
        session.setTimeout(30000);
        // 通过session建立链接
        session.connect();
        // 打开SFTP通道
        channel = session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        return (ChannelSftp) channel;
    }


    /**
     * 创建一个文件目录   并且进入到当前目录
     */
    public void createDir(String createpath, ChannelSftp sftp) throws Exception {

        try {
            if (isDirExist(createpath, sftp)) {
                sftp.cd(createpath);
                return;
            }
            String pathArry[] = createpath.split(StringPool.SLASH);
            StringBuffer filePath = new StringBuffer(StringPool.SLASH);
            for (String path : pathArry) {
                if (path.equals(StringPool.EMPTY)) {
                    continue;
                }
                filePath.append(path + StringPool.SLASH);
                if (isDirExist(filePath.toString(), sftp)) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(createpath);
        } catch (Exception e) {
            throw new Exception("创建目录失败 ", e);
        }
    }

    /**
     * 判断目录是否存在
     */
    public boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            // 获取SFTP连接对象
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }


    /**
     * 判断目录或者文件夹是否存在
     *
     * @param directory 路径
     * @return 是否存在
     */
    public static boolean isDirOrFileExist(String directory, ChannelSftp sftp) {
        try {
            Vector<?> vector = sftp.ls(directory);
            if (null == vector) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件 仅仅删除当前文件夹下的文件不包括子文件和本目录
     *
     * @param directory 要删除文件所在目录
     * @param sftp      channel
     */
    public void deleteDir(String directory, ChannelSftp sftp) {
        try {
            Vector<ChannelSftp.LsEntry> vector = listFiles(directory, sftp);
            List<String> fileList = vector.parallelStream().map(ChannelSftp.LsEntry::getFilename).filter(item -> !item.startsWith(StringPool.DOT)).collect(Collectors.toList());
            sftp.cd(directory);
            for (String fileName : fileList) {
                sftp.rm(fileName);
            }
        } catch (Exception e) {
            log.error("删除目录下的图片error", e);
        } finally {
            closeChannel();
        }
    }


    /**
     * 删除文件夹以及子文件子文件夹
     *
     * @param directory 要删除文件所在目录
     * @param sftp      channel
     */
    public void deleteSFTP(String directory, ChannelSftp sftp) {
        try {
            if (isDirOrFileExist(directory, sftp)) {
                Vector<ChannelSftp.LsEntry> vector = sftp.ls(directory);
                if (vector.size() == 1) { // 文件，直接删除
                    sftp.rm(directory);
                } else if (vector.size() == 2) { // 空文件夹，直接删除
                    sftp.rmdir(directory);
                } else {
                    String fileName = "";
                    // 删除文件夹下所有文件
                    for (ChannelSftp.LsEntry en : vector) {
                        fileName = en.getFilename();
                        if (".".equals(fileName) || "..".equals(fileName)) {
                            continue;
                        } else {
                            deleteSFTP(directory + StringPool.SLASH + fileName, sftp);
                        }
                    }
                    // 删除文件夹
                    sftp.rmdir(directory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector<ChannelSftp.LsEntry> listFiles(String directory, ChannelSftp sftp) throws SftpException, SftpException {
        return sftp.ls(directory);
    }

    public static void main(String[] args) throws JSchException, SftpException {
        String fullFileName = "test/autoArticle/html/pc_4399/4399.html";
        String fileName = "4399.html";
        String fileUrl = "https://www.duobaoyu17.com/autoArticle/html/pc_486/486.html";
       JSchUtil jSchUtil = new JSchUtil();
        SftpConfigure sftpConfig = new SftpConfigure();
        sftpConfig.setSftpHost("118.31.19.151");
        sftpConfig.setSftpPort(22);
        sftpConfig.setSftpUsername("semtest");
        sftpConfig.setSftpPassword("semtest");
        sftpConfig.setSftpRootPath("/mnt/semtest/");
        jSchUtil.setSftpConfigure(sftpConfig);


       /* UploadOssParam uploadOssParam=new UploadOssParam();
        uploadOssParam.setFullFileName(fullFileName);
        uploadOssParam.setFileName(fileName);
        InputStream imgStream = HttpUtil.getImgStream(fileUrl);
        uploadOssParam.setAdvArticleInputStream(imgStream);
        ftpParams.add(uploadOssParam);
        advPageService.uploadByFtp(ftpParams,jSchUtil);*/


        String deleteDir = "/mnt/semtest/test/autoArticle/html/pc-pc-397-ub4yzeio";


        ChannelSftp channel = jSchUtil.getChannel(sftpConfig);
        //测试删除文件
        //  jSchUtil.deleteDir(deleteDir,channel);

        jSchUtil.deleteSFTP(deleteDir, channel);
    }
}
