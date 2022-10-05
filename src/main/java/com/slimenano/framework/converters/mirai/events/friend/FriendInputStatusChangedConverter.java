package com.slimenano.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendInputStatusChangedEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.events.friend.SNFriendInputStatusChangedEvent;

public class FriendInputStatusChangedConverter extends Converter<FriendInputStatusChangedEvent, SNFriendInputStatusChangedEvent, MiraiRobot> {

    @Override
    public SNFriendInputStatusChangedEvent convert(FriendInputStatusChangedEvent event) {

        return new SNFriendInputStatusChangedEvent(
                converters.convert(event.getFriend(), SNFriend.class),
                event.getInputting());


    }


}
