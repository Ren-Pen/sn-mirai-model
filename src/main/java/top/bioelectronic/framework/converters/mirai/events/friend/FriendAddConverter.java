package top.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendAddEvent;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.events.friend.SNFriendAddEvent;

public class FriendAddConverter extends Converter<FriendAddEvent, SNFriendAddEvent, MiraiRobot> {

    @Override
    public SNFriendAddEvent convert(FriendAddEvent friendAddEvent) {
        return new SNFriendAddEvent(
                converters.convert(friendAddEvent.getFriend(), SNFriend.class)
        );

    }
}
