package com.slimenano.sdk.contact.user;

import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.SNContact;
import com.slimenano.sdk.robot.exception.file.OverFileSizeMaxException;
import com.slimenano.sdk.robot.exception.permission.NoOperationPermissionException;
import com.slimenano.sdk.robot.exception.unsupported.UnsupportedRobotOperationException;
import com.slimenano.sdk.robot.messages.content.SNAudio;
import com.slimenano.sdk.robot.messages.content.SNImage;
import lombok.Getter;
import com.slimenano.sdk.robot.contact.user.SNUser;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Getter
public abstract class MiraiUserImpl extends SNContactImpl implements SNUser {

    protected final SNUserProfile profile;

    public MiraiUserImpl(long id, SNUserProfile profile) {
        super(id);
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
