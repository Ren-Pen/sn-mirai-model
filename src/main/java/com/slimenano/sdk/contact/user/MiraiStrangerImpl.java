package com.slimenano.sdk.contact.user;

import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.user.SNStranger;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;
import com.slimenano.sdk.robot.messages.SNMessageChain;
import com.slimenano.sdk.robot.messages.meta.SNMessageSource;

public class MiraiStrangerImpl extends MiraiUserImpl implements SNStranger {
    public MiraiStrangerImpl(long id, SNUserProfile profile) {
        super(id, profile);
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        return robot.sendMessage(this, chain);
    }

    @Override
    public void nudge(Robot robot) {
        robot.nudge(this, this);
    }
}
