package top.bioelectronic.sdk.contact.user;

import lombok.Getter;
import top.bioelectronic.sdk.core.Robot;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;
import top.bioelectronic.sdk.robot.messages.meta.SNMessageSource;

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
}
