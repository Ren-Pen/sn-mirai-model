package top.bioelectronic.framework.contact.user;

import lombok.Getter;
import top.bioelectronic.framework.exceptions.NoImplementException;
import top.bioelectronic.sdk.core.Robot;
import top.bioelectronic.sdk.robot.contact.SNGroup;
import top.bioelectronic.sdk.robot.contact.SNMemberPermission;
import top.bioelectronic.sdk.robot.contact.user.SNAnonymousMember;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;
import top.bioelectronic.sdk.robot.messages.meta.SNMessageSource;

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
}
