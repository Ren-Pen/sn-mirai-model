package top.bioelectronic.framework.converters.mirai.events.friend;

import net.mamoe.mirai.event.events.FriendRemarkChangeEvent;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.events.friend.SNFriendRemarkChangeEvent;

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
