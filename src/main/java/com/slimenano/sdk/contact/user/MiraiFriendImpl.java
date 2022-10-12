package com.slimenano.sdk.contact.user;

import com.slimenano.sdk.robot.exception.file.OverFileSizeMaxException;
import com.slimenano.sdk.robot.exception.permission.NoOperationPermissionException;
import com.slimenano.sdk.robot.exception.unsupported.UnsupportedRobotOperationException;
import com.slimenano.sdk.robot.messages.content.SNAudio;
import lombok.Getter;
import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.user.SNFriend;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;
import com.slimenano.sdk.robot.messages.SNMessageChain;
import com.slimenano.sdk.robot.messages.meta.SNMessageSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Getter
public class MiraiFriendImpl extends MiraiUserImpl implements SNFriend {

    private final String nickname;
    private final String remark;

    public MiraiFriendImpl(long id, SNUserProfile profile, String nickname, String remark) {
        super(id, profile);
        this.nickname = nickname;
        this.remark = remark;
    }

    public MiraiFriendImpl(long id, String nickname, String remark) {
        super(id, null);
        this.nickname = nickname;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "[好友][" + getId() + "]" + "[" + (getRemark().isEmpty() ? getNickname() : getRemark()) + "]";
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        return robot.sendMessage(this, chain);
    }

    @Override
    public void nudge(Robot robot) {
        robot.nudge(this, this);
    }

    @Override
    public SNAudio uploadAudio(Robot robot, File file) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        return robot.uploadAudio(this, file);
    }

    @Override
    public SNAudio uploadAudio(Robot robot, URL url) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        return robot.uploadAudio(this, url);
    }
}
