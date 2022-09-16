package com.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Group;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.SNGroup;
import com.bioelectronic.sdk.robot.contact.SNGroupImpl;
import com.bioelectronic.sdk.robot.contact.SNMemberPermission;

public class GroupConverter extends Converter<Group, SNGroup, MiraiRobot> {
    @Override
    public SNGroup convert(Group group) {
        if (group == null) return null;

        return new SNGroupImpl(
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
