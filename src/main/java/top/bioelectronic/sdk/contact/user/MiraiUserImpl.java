package top.bioelectronic.sdk.contact.user;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiraiUserImpl miraiUser = (MiraiUserImpl) o;

        return id == miraiUser.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
