package top.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendInputStatusChangedEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.events.friend.SNFriendInputStatusChangedEvent;

public class FriendInputStatusChangedConverter extends Converter<FriendInputStatusChangedEvent, SNFriendInputStatusChangedEvent, MiraiRobot> {

    @Override
    public SNFriendInputStatusChangedEvent convert(FriendInputStatusChangedEvent event) {

        return new SNFriendInputStatusChangedEvent(
                converters.convert(event.getFriend(), SNFriend.class),
                event.getInputting());


    }


}
