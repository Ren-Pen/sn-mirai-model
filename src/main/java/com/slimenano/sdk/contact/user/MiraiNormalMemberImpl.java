package com.slimenano.sdk.contact.user;

import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.SNGroup;
import com.slimenano.sdk.robot.contact.SNMemberPermission;
import com.slimenano.sdk.robot.contact.user.SNNormalMember;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;
import com.slimenano.sdk.robot.messages.SNMessageChain;
import com.slimenano.sdk.robot.messages.meta.SNMessageSource;
import lombok.Getter;

@Getter
public class MiraiNormalMemberImpl extends MiraiMemberImpl implements SNNormalMember {
    private final int muteTimeRemaining;
    private final int lastSpeakTimestamp;
    private final int joinTimestamp;
    private final boolean isMute;

    public MiraiNormalMemberImpl(long id, SNUserProfile profile, SNGroup group, String specialTitle, String nameCard, SNMemberPermission permission, int joinTimestamp, int lastSpeakTimestamp, int muteTimeRemaining, boolean isMute) {
        super(id, profile, group, specialTitle, nameCard, permission);
        this.joinTimestamp = joinTimestamp;
        this.lastSpeakTimestamp = lastSpeakTimestamp;
        this.muteTimeRemaining = muteTimeRemaining;
        this.isMute = isMute;
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        return robot.sendMessage(this, chain);
    }

    @Override
    public void nudge(Robot robot) {
        robot.nudge(this, this.getGroup());
    }
}
