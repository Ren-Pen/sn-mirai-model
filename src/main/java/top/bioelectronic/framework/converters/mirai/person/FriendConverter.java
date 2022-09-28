package top.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Friend;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.contact.user.SNFriendImpl;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;

public class FriendConverter extends Converter<Friend, SNFriend, MiraiRobot> {

    @Override
    public Friend reverse_convert(SNFriend friend) {
        return robot.getBot().getFriend(friend.getId());
    }

    @Override
    public SNFriend convert(Friend friend) {
        if (friend == null) return null;

        SNUserProfile profile = converters.convert(
                friend.queryProfile(),
                SNUserProfile.class);

        return new SNFriendImpl(
                friend.getId(),
                profile,
                friend.getNick(),
                friend.getRemark());
    }
}
