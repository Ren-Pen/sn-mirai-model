package top.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Group;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.SNGroup;
import top.bioelectronic.sdk.robot.contact.SNGroupImpl;
import top.bioelectronic.sdk.robot.contact.SNMemberPermission;

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
