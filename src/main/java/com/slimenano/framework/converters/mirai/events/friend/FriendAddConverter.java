package com.slimenano.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendAddEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.events.friend.SNFriendAddEvent;

public class FriendAddConverter extends Converter<FriendAddEvent, SNFriendAddEvent, MiraiRobot> {

    @Override
    public SNFriendAddEvent convert(FriendAddEvent friendAddEvent) {
        return new SNFriendAddEvent(
                converters.convert(friendAddEvent.getFriend(), SNFriend.class)
        );

    }
}
