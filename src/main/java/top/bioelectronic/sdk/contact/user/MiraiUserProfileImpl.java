package top.bioelectronic.sdk.contact.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;

@Getter
@AllArgsConstructor
public class MiraiUserProfileImpl implements SNUserProfile {

    private final String nickname;
    private final String email;
    private final int age;
    private final int qLevel;
    private final Sex sex;

    /**
     * 个性签名
     */
    private String sign;

    @Override
    public String toString() {
        return "[用户资料][昵称] " + nickname + "\n" +
               "         [邮箱] " + email + "\n" +
                "         [年龄] " + age + "\n" +
                "         [QQ等级] " + qLevel + "\n" +
                "         [性别] " + sex + "\n" +
                "         [个性签名] " + sign + "\n";
    }
}
