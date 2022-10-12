package com.slimenano.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Group;
import com.slimenano.sdk.contact.MiraiGroupImpl;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.SNGroup;
import com.slimenano.sdk.robot.contact.SNMemberPermission;

public class GroupConverter extends Converter<Group, SNGroup, MiraiRobot> {
    @Override
    public SNGroup convert(Group group) {
        if (group == null) return null;

        return new MiraiGroupImpl(
                group.getId(),
                group.getName(),
                group.getAvatarUrl(),
                SNMemberPermission.valueOf(group.getBotPermission().toString()),
                group.getOwner().getId()
        );

    }

    @Override
    public Group reverse_convert(SNGroup group) {
        return robot.getBot().getGroup(group.getId());
    }
}
