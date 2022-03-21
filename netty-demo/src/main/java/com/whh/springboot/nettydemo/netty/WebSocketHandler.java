package com.whh.springboot.nettydemo.netty;

import com.whh.springboot.nettydemo.dto.Message;
import com.whh.springboot.nettydemo.dto.MsgType;
import com.whh.springboot.nettydemo.util.JsonUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    /**
     * 客户端发送给服务端的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        try {
            //接受客户端发送的消息
            Message message = JsonUtil.jsonToObject(msg.text(), Message.class);
            if (message == null) {
                log.warn("msg not instanceof Message,{} ", msg.text());
                return;
            }

            if (MsgType.REGISTER.getCode().equals(message.getMsgType())) {
                /*
                  首次注册消息
                  1. TODO：将用户id，channalId 关联关系保存到数据库
                  2. 将当前channel加入缓存中
                 */
                ChannelUtil.addChannel(ctx.channel());
                log.info("连接注册,userId:{}", message.getUserId());
                ctx.writeAndFlush(new TextWebSocketFrame("欢迎使用netty websocket服务，当前时间：" + new Date().toString()));
            } else {
                //处理业务逻辑
            }

        } catch (Exception e) {
            log.error("websocket error：", e);
        }
    }

    /**
     * 客户端连接时候的操作
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("一个客户端连接......" + ctx.channel().remoteAddress() + Thread.currentThread().getName());
    }

    /**
     * 客户端掉线时的操作
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ChannelUtil.removeChannel(ctx.channel());
        log.info("一个客户端移除......" + ctx.channel().remoteAddress());
        ctx.close(); //关闭连接
    }

    /**
     * 发生异常时执行的操作
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //移除连接
        ChannelUtil.removeChannel(ctx.channel());
        //关闭长连接
        ctx.close();
        log.info("连接异常 " + cause.getMessage());
    }
    /**
     * 心跳超时
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    log.info("channel reader_idle channelId:{}", ctx.channel().id().asLongText());
                    ChannelUtil.removeChannel(ctx.channel());
                    ctx.disconnect();
                    break;
                case WRITER_IDLE:
                    log.info("channel writer_idle channelId:{}", ctx.channel().id().asLongText());
                    ChannelUtil.removeChannel(ctx.channel());
                    ctx.disconnect();
                    break;
                case ALL_IDLE:
                    log.info("channel all_idle channelId:{}", ctx.channel().id().asLongText());
                    ChannelUtil.removeChannel(ctx.channel());
                    ctx.disconnect();
                    break;
            }

        }
    }
}