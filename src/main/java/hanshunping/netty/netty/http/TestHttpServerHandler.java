package hanshunping.netty.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 *
 * 仅仅做个演示毕竟谁没事闲的用netty做http啊
 * SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter
 * 客户端和服务端通讯的数据的类型封装成了 HttpObject
 * 所以这个也是一个handler
 * 使用netty作为http服务器
 * @author dzd
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //一开始请求会出现两次 由于有一次请求 favicon.ico
        if (msg instanceof HttpRequest){
            System.out.println("pipeline的hashcode ："+ctx.pipeline().hashCode());
            System.out.println("msg 类型 ="+msg.getClass());
            System.out.println("客户端地址 "+ctx.channel().remoteAddress());

            HttpRequest httpRequest= (HttpRequest) msg;

            //获取uri
            URI uri = new URI(httpRequest.uri());

            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了 favicon.ico 这里不做处理");
                return;
            }
            //回复信息给浏览器
            ByteBuf content= Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //将构建好的response返回
            ctx.writeAndFlush(response);
        }
    }
}
