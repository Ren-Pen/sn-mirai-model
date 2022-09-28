package top.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendDeleteEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.events.friend.SNFriendDeleteEvent;

public class FriendDeleteConverter extends Converter<FriendDeleteEvent, SNFriendDeleteEvent, MiraiRobot> {
    @Override
    public SNFriendDeleteEvent convert(FriendDeleteEvent event) {

        return new SNFriendDeleteEvent(
                converters.convert(event.getFriend(), SNFriend.class)
        );

    }
}
