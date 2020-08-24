package hanshunping.netty.netty.protobuf;

/**
 * netty自带的序列化工具 StringEncoder StringDecoder 性能很低
 * 编码之后的大小是二进制的五倍
 * protobuf是Google发布的开源项目，全程Google Protocol Buffer
 * 这个是一个用于结构化数据串行化很适合做rpc
 * 目前很多公司 http json ---> tcp protobuf
 * protobuf以message的方式传出 可以跨平台的
 * @author dzd
 */
public class ProtabufDemo {
}
