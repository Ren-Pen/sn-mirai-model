package com.slimenano.sdk.contact;

import com.slimenano.sdk.core.Robot;
import com.slimenano.sdk.robot.contact.SNGroup;
import com.slimenano.sdk.robot.contact.SNMemberPermission;
import com.slimenano.sdk.robot.messages.SNMessageChain;
import com.slimenano.sdk.robot.messages.meta.SNMessageSource;
import lombok.Getter;

@Getter
public class MiraiGroupImpl implements SNGroup {

    private final long id;
    private final String name;
    private final String avatarUrl;
    private final SNMemberPermission botPermission;
    private final long owner;


    public MiraiGroupImpl(long id, String name, String avatarUrl, SNMemberPermission botPermission, long owner) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.botPermission = botPermission;
        this.owner = owner;
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        return robot.sendMessage(this, chain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiraiGroupImpl that = (MiraiGroupImpl) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
