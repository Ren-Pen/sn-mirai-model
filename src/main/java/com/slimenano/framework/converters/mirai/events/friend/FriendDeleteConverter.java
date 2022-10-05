package com.slimenano.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendDeleteEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.events.friend.SNFriendDeleteEvent;

public class FriendDeleteConverter extends Converter<FriendDeleteEvent, SNFriendDeleteEvent, MiraiRobot> {
    @Override
    public SNFriendDeleteEvent convert(FriendDeleteEvent event) {

        return new SNFriendDeleteEvent(
                converters.convert(event.getFriend(), SNFriend.class)
        );

    }
}
