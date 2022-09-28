package top.bioelectronic.sdk.contact.user;

import top.bioelectronic.sdk.core.Robot;
import top.bioelectronic.sdk.robot.contact.user.SNStranger;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;
import top.bioelectronic.sdk.robot.messages.meta.SNMessageSource;

public class MiraiStrangerImpl extends MiraiUserImpl implements SNStranger {
    public MiraiStrangerImpl(long id, SNUserProfile profile) {
        super(id, profile);
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        return robot.sendMessage(this, chain);
    }

}
