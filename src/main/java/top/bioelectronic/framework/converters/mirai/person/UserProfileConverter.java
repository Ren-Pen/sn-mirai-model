package top.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.data.UserProfile;
import top.bioelectronic.sdk.contact.user.MiraiUserProfileImpl;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;

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
