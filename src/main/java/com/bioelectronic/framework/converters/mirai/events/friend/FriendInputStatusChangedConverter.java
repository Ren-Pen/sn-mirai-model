package com.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendInputStatusChangedEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNFriend;
import com.bioelectronic.sdk.robot.events.friend.SNFriendInputStatusChangedEvent;

public class FriendInputStatusChangedConverter extends Converter<FriendInputStatusChangedEvent, SNFriendInputStatusChangedEvent, MiraiRobot> {

    @Override
    public SNFriendInputStatusChangedEvent convert(FriendInputStatusChangedEvent event) {

        return new SNFriendInputStatusChangedEvent(
                converters.convert(event.getFriend(), SNFriend.class),
                event.getInputting());


    }


}
