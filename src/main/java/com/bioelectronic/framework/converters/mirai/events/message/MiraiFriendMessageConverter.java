package com.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.FriendMessageEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNFriend;
import com.bioelectronic.sdk.robot.events.messages.SNFriendMessageEvent;
import com.bioelectronic.sdk.robot.messages.SNMessageChain;

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
