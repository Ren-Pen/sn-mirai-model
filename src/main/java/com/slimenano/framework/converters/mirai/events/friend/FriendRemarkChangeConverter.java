package com.slimenano.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendRemarkChangeEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.events.friend.SNFriendRemarkChangeEvent;

public class FriendRemarkChangeConverter extends Converter<FriendRemarkChangeEvent, SNFriendRemarkChangeEvent, MiraiRobot> {

    @Override
    public SNFriendRemarkChangeEvent convert(FriendRemarkChangeEvent event) {

        return new SNFriendRemarkChangeEvent(
                converters.convert(event.getFriend(), SNFriend.class),
                event.getOldRemark(),
                event.getNewRemark()
        );

    }

}
