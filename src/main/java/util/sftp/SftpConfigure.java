package util.sftp;

import lombok.Data;

/**
 * sftp服务器的配置
 *
 * @author dzd
 */
@Data
public class SftpConfigure {

    /**
     * SFTP的IP地址
     */
    private String sftpHost;
    /**
     * SFTP的端口
     */
    private Integer sftpPort;
    /**
     * SFTP的用户名
     */
    private String sftpUsername;
    /**
     * SFTP的用户密码
     */
    private String sftpPassword;

    /**
     * SFTP的用户密码
     */
    private String sftpRootPath;

    /**
     * SFTP的路径的前缀
     */
    private String sftpPrefix;

}
