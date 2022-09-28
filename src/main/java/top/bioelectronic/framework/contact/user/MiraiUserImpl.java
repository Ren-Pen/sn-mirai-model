package top.bioelectronic.framework.contact.user;

import lombok.Getter;
import top.bioelectronic.sdk.robot.contact.user.SNUser;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;

@Getter
public abstract class MiraiUserImpl implements SNUser {

    protected final SNUserProfile profile;
    protected final long id;


    public MiraiUserImpl(long id, SNUserProfile profile) {
        this.id = id;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "[用户][" + getId() + "]";
    }
}
