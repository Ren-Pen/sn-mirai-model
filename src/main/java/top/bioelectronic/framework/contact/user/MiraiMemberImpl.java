package top.bioelectronic.framework.contact.user;

import lombok.Getter;
import top.bioelectronic.sdk.robot.contact.SNGroup;
import top.bioelectronic.sdk.robot.contact.SNMemberPermission;
import top.bioelectronic.sdk.robot.contact.user.SNMember;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;

@Getter
public abstract class MiraiMemberImpl extends MiraiUserImpl implements SNMember {

    private final SNGroup group;
    private final String specialTitle;
    private final String nameCard;
    private final SNMemberPermission permission;

    protected MiraiMemberImpl(long id, SNUserProfile profile, SNGroup group, String specialTitle, String nameCard, SNMemberPermission permission) {
        super(id, profile);
        this.group = group;
        this.specialTitle = specialTitle;
        this.nameCard = nameCard;
        this.permission = permission;
    }


}
