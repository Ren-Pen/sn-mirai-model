package com.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Friend;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNFriend;
import com.bioelectronic.sdk.robot.contact.user.SNFriendImpl;
import com.bioelectronic.sdk.robot.contact.user.SNUserProfile;

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
