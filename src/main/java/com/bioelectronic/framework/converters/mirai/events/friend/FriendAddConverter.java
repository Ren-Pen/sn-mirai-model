package com.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendAddEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNFriend;
import com.bioelectronic.sdk.robot.events.friend.SNFriendAddEvent;

public class FriendAddConverter extends Converter<FriendAddEvent, SNFriendAddEvent, MiraiRobot> {

    @Override
    public SNFriendAddEvent convert(FriendAddEvent friendAddEvent) {
        return new SNFriendAddEvent(
                converters.convert(friendAddEvent.getFriend(), SNFriend.class)
        );

    }
}
