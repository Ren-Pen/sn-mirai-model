package top.bioelectronic.sdk.contact.user;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MiraiMemberImpl that = (MiraiMemberImpl) o;

        return group.equals(that.group);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + group.hashCode();
        return result;
    }
}
