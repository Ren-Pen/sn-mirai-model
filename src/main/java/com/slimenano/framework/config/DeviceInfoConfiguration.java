package com.slimenano.framework.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import com.slimenano.sdk.config.Configuration;
import com.slimenano.sdk.config.DefaultConfiguration;
import com.slimenano.sdk.framework.SystemInstance;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SystemInstance
@Configuration(prefix = "deviceInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 设备配置项 MIRAI: 版本2
 */
public class DeviceInfoConfiguration implements DefaultConfiguration {

    // 显示屏参数
    private String display = "MIRAI.1.1";
    // 设备名称
    private String product = "mirai";
    // 设备参数
    private String device = "mirai";
    // 设备主板
    private String board = "mirai";
    // 系统定制商
    private String brand = "mamoe";
    // 设备型号
    private String model = "mirai";
    // 系统启动程序版本号
    private String bootloader = "unknown";
    // 设备指纹
    private String fingerprint = "mamoe/mirai/mirai:10/MIRAI.1.1/0000000:user/release-keys";
    // 启动uuid
    private String bootId = UUID.randomUUID().toString();
    // 系统版本 /proc/version 的值
    private String procVersion = "Linux version 3.0.31-9vwwgsl2 (android-build@xxx.xxx.xxx.xxx.com)";
    // 基带 建议为空
    private String baseBand = "";
    // 版本，不需要管
    private Version version = new Version();
    // SIM卡信息
    private String simInfo = "T-Mobile";
    // 系统类型
    private String osType = "android";
    // MAC地址
    private String macAddress = "02:00:00:00:00:00";
    // wifi mac
    private String wifiBSSID = "02:00:00:00:00:00";
    // wifi名称
    private String wifiSSID = "<unknown ssid>";
    // imsi的16位md5，随机就行
    private String imsiMd5 = "f84a324779663fc1fa4b3543e346cd1a";
    // IMEI 序列号，建议自行生成
    private String imei = "864479214305589";
    // apn 填 wifi
    private String apn = "wifi";

    @SneakyThrows
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("{");

        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            builder.append("\"").append(field.getName()).append("\": ");
            if (field.getType() == Version.class)
                builder.append(field.get(this).toString());
            else if ("imei".equals(field.getName()))
                builder.append(field.get(this));
            else if ("imsiMd5".equals(field.getName())) {
                String md5Hex = field.get(this).toString();
                if (md5Hex.length() != 32)
                    throw new RuntimeException("Bad `imsiMd5.size`. Required 32, given " + md5Hex.length() + ".");
                byte[] array = new byte[16];
                for (int k = 0; k < md5Hex.length(); k += 2) {
                    String hex = md5Hex.substring(k, k + 2);
                    array[k / 2] = (byte) Integer.parseInt(hex, 16);
                }
                builder.append(Arrays.toString(array));
            } else
                builder.append(Arrays.toString(field.get(this).toString().getBytes(StandardCharsets.UTF_8)));
            if (i != fields.length - 1) builder.append(",");
        }

        builder.append("}");

        return builder.toString();

    }

    @Data
    public static class Version {
        private String incremental = "5891982";
        private String release = "5";
        private String codename = "REL";

        @SneakyThrows
        @Override
        public String toString() {

            return "{" + "\"incremental\": " + Arrays.toString(this.incremental.getBytes(StandardCharsets.UTF_8)) + "," +
                    "\"release\": " + Arrays.toString(this.release.getBytes(StandardCharsets.UTF_8)) + "," +
                    "\"codename\": " + Arrays.toString(this.codename.getBytes(StandardCharsets.UTF_8)) + "}";
        }

    }

}


