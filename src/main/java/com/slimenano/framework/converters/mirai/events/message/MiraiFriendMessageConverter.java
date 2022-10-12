package com.slimenano.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.FriendMessageEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.events.messages.SNFriendMessageEvent;
import com.slimenano.sdk.robot.messages.SNMessageChain;

public class MiraiFriendMessageConverter extends Converter<FriendMessageEvent, SNFriendMessageEvent, MiraiRobot> {
    @Override
    public SNFriendMessageEvent convert(FriendMessageEvent friendMessageEvent) {

        return new SNFriendMessageEvent(
                converters.convert(friendMessageEvent.getMessage(), SNMessageChain.class),
                converters.convert(friendMessageEvent.getSender(), SNFriend.class),
                friendMessageEvent.getTime()
        );
    }
}
