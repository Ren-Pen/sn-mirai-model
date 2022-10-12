package com.slimenano.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Friend;
import com.slimenano.sdk.contact.user.MiraiFriendImpl;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;

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

        return new MiraiFriendImpl(
                friend.getId(),
                profile,
                friend.getNick(),
                friend.getRemark());
    }
}
