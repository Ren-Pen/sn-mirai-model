package com.slimenano.framework.converters.mirai;

import com.slimenano.sdk.framework.SystemInstance;
import com.slimenano.sdk.framework.annotations.Mount;
import com.slimenano.sdk.framework.ui.IGUIBridge;
import com.slimenano.sdk.logger.Marker;
import com.slimenano.sdk.utils.CharacterImage;
import kotlin.coroutines.Continuation;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.network.LoginFailedException;
import net.mamoe.mirai.network.UnsupportedSliderCaptchaException;
import net.mamoe.mirai.utils.LoginSolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@SystemInstance
@Marker("登录验证器")
@Slf4j
public class MyLoginSolver extends LoginSolver {

    @Mount
    private IGUIBridge bridge;

    @Nullable
    @Override
    public Object onSolvePicCaptcha(@NotNull Bot bot, @NotNull byte[] bytes, @NotNull Continuation<? super String> continuation) {
        log.info("MIRAI需要图片验证码验证，请根据下面的图片输入对应的字母，验证码长度为4个字母");
        File file = new File("tmp" + File.separator + "captcha.png");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)){
            fos.write(bytes);
            log.info("图片验证码临时文件已保存，若无法看清字符图片，请查看文件 {}", file.getAbsolutePath());
        } catch (Exception e) {
            log.warn("无法写出验证码临时文件，请尝试查看字符图片", e);
        }

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)){
            CharacterImage image = new CharacterImage(ImageIO.read(bis));
            log.info("[图片验证码]\n" + image.createCharImg());
        }catch (Throwable e){
            log.warn("创建字符图片时出错", e);
        }
        log.info("请输入 4 位字母验证码. 若要更换验证码, 请直接回车，若要退出，请输入：[:q]");
        String s = bridge.prompt("", "验证码", "");
        if (":q".equals(s)) throw new UnsupportedSliderCaptchaException("用户主动取消验证码流程");
        if (s.length() != 4) return null;
        else{
            log.info("正在提交 {}...", s);
            return s;
        }
    }

    @Nullable
    @Override
    public Object onSolveSliderCaptcha(@NotNull Bot bot, @NotNull String s, @NotNull Continuation<? super String> continuation) {

        log.info("MIRAI需要滑动验证码验证, 请按照以下链接的步骤完成滑动验证码, 然后输入获取到的 ticket");
        log.info("@see https://github.com/project-mirai/mirai-login-solver-selenium");
        log.info("@see https://docs.mirai.mamoe.net/mirai-login-solver-selenium/");
        log.info("若要退出请输入：[:q]");
        log.info("验证码连接：{}", s);
        String ticket = bridge.prompt("", "ticket", null);
        if (":q".equals(ticket)) throw new UnsupportedSliderCaptchaException("用户主动取消验证码流程");
        log.info("正在提交...");
        return ticket;

    }

    @Nullable
    @Override
    public Object onSolveUnsafeDeviceLoginVerify(@NotNull Bot bot, @NotNull String s, @NotNull Continuation<? super String> continuation) {
        log.info("当前登录环境不安全，服务器要求账户认证。");
        log.info("认证链接：{}", s);
        log.info("若要退出请输入：[:q]");
        String s1 = bridge.prompt("", "输入任意字符后按回车继续", "");
        if (":q".equals(s1)) throw new UnsupportedSliderCaptchaException("用户主动取消验证码流程");
        log.info("正在提交...");
        return s1;
    }
}
