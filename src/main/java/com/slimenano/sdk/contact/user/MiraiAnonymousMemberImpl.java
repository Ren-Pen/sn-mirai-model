package com.slimenano.sdk.contact.user;

import lombok.Getter;
import com.slimenano.sdk.exceptions.NoImplementException;
import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.SNGroup;
import com.slimenano.sdk.robot.contact.SNMemberPermission;
import com.slimenano.sdk.robot.contact.user.SNAnonymousMember;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;
import com.slimenano.sdk.robot.messages.SNMessageChain;
import com.slimenano.sdk.robot.messages.meta.SNMessageSource;

@Getter
public class MiraiAnonymousMemberImpl extends MiraiMemberImpl implements SNAnonymousMember {

    private final String anonymousId;

    public MiraiAnonymousMemberImpl(long id, SNUserProfile profile, SNGroup group, String specialTitle, String nameCard, SNMemberPermission permission, String anonymousId) {
        super(id, profile, group, specialTitle, nameCard, permission);
        this.anonymousId = anonymousId;
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        throw new NoImplementException("不能向匿名群成员发送临时会话！");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MiraiAnonymousMemberImpl that = (MiraiAnonymousMemberImpl) o;

        return anonymousId.equals(that.anonymousId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + anonymousId.hashCode();
        return result;
    }

    @Override
    public void nudge(Robot robot) {
        throw new NoImplementException("不能向匿名群成员发送戳一戳！");
    }
}
