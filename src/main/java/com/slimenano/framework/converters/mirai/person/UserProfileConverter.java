package com.slimenano.framework.converters.mirai.person;

import net.mamoe.mirai.data.UserProfile;
import com.slimenano.sdk.contact.user.MiraiUserProfileImpl;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;

public class UserProfileConverter extends Converter<UserProfile, SNUserProfile, MiraiRobot> {

    @Override
    public SNUserProfile convert(UserProfile userProfile) {

        return new MiraiUserProfileImpl(
                userProfile.getNickname(),
                userProfile.getEmail(),
                userProfile.getAge(),
                userProfile.getQLevel(),
                SNUserProfile.Sex.valueOf(userProfile.getSex().name()),
                userProfile.getSign());
    }


}
