package hanshunping.netty.netty.protocolmsg;

/**
 * 协议包
 * @author dzd
 */
public class MessageProtocol {
    private int len;

    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
