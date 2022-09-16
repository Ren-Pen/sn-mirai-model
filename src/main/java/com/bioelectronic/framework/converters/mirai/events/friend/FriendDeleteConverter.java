package com.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendDeleteEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNFriend;
import com.bioelectronic.sdk.robot.events.friend.SNFriendDeleteEvent;

public class FriendDeleteConverter extends Converter<FriendDeleteEvent, SNFriendDeleteEvent, MiraiRobot> {
    @Override
    public SNFriendDeleteEvent convert(FriendDeleteEvent event) {

        return new SNFriendDeleteEvent(
                converters.convert(event.getFriend(), SNFriend.class)
        );

    }
}
