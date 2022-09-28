package top.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.data.UserProfile;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfileImpl;

public class UserProfileConverter extends Converter<UserProfile, SNUserProfile, MiraiRobot> {

    @Override
    public SNUserProfile convert(UserProfile userProfile) {

        return new SNUserProfileImpl(
                userProfile.getNickname(),
                userProfile.getEmail(),
                userProfile.getAge(),
                userProfile.getQLevel(),
                SNUserProfile.Sex.valueOf(userProfile.getSex().name()),
                userProfile.getSign());
    }


}
