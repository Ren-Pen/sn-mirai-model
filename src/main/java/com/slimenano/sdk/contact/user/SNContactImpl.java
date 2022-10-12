package com.slimenano.sdk.contact.user;

import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.SNContact;
import com.slimenano.sdk.robot.exception.file.OverFileSizeMaxException;
import com.slimenano.sdk.robot.exception.permission.NoOperationPermissionException;
import com.slimenano.sdk.robot.exception.unsupported.UnsupportedRobotOperationException;
import com.slimenano.sdk.robot.messages.content.SNAudio;
import com.slimenano.sdk.robot.messages.content.SNImage;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Getter
public abstract class SNContactImpl implements SNContact {

    protected final long id;

    protected SNContactImpl(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SNContactImpl snContact = (SNContactImpl) o;

        return id == snContact.id;
    }

    @Override
    public SNImage uploadImg(Robot robot, File file) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        return robot.uploadImg(this, file);
    }

    @Override
    public SNImage uploadImg(Robot robot, URL url, boolean forceUpdate) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        return robot.uploadImg(this, url, forceUpdate);
    }

    @Override
    public SNImage uploadImg(Robot robot, URL url) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        return robot.uploadImg(this, url);
    }

    @Override
    public SNAudio uploadAudio(Robot robot, File file) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        throw new UnsupportedRobotOperationException();
    }

    @Override
    public SNAudio uploadAudio(Robot robot, URL url) throws IOException, UnsupportedRobotOperationException, NoOperationPermissionException, OverFileSizeMaxException {
        throw new UnsupportedRobotOperationException();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
