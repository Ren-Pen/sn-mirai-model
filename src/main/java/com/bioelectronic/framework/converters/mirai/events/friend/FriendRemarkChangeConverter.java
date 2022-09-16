package com.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendRemarkChangeEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNFriend;
import com.bioelectronic.sdk.robot.events.friend.SNFriendRemarkChangeEvent;

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
