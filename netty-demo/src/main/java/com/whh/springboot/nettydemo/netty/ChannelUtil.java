package com.whh.springboot.nettydemo.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUtil {
    /**
     * 用户id和客户端channel的映射
     */
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static void addChannel(Channel channel) {
        channelMap.put(channel.id().asLongText(), channel);
    }

    public static void removeChannel(Channel channel) {
        channelMap.remove(channel.id());
    }
}
