package com.slimenano.sdk.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static org.apache.tika.utils.StringUtils.isBlank;

/**
 * 由 Mirai 内部字符画函数转换成的java类
 *
 * @author NaturalHG
 * @see net.mamoe.mirai.utils.LoginSolver
 */
public class CharacterImage {

    private final int outputWidth;
    private final double ignoreRate;
    private final BufferedImage img;

    public CharacterImage(BufferedImage image, int outputWidth, double ignoreRate) {
        this.img = image;
        this.outputWidth = outputWidth;
        this.ignoreRate = ignoreRate;
    }

    public CharacterImage(BufferedImage image) {
        this.img = image;
        outputWidth = 100;
        ignoreRate = 0.95;
    }

    private int gray(int rgb) {
        int r = (rgb & 0xff0000) >> 16;
        int g = (rgb & 0x00ff00) >> 8;
        int b = rgb & 0x0000ff;
        return (r * 30 + g * 59 + b * 11 + 50) / 100;
    }

    private boolean grayCompare(int g1, int g2) {
        return ((double) Math.min(g1, g2) / Math.max(g1, g2)) >= ignoreRate;
    }


    public String createCharImg() {
        int newHeight = (int) (img.getHeight() * ((double) outputWidth / img.getWidth()));
        Image tmp = img.getScaledInstance(outputWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage image = new BufferedImage(outputWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        int background = gray(image.getRGB(0, 0));
        StringBuilder sb = new StringBuilder();
        LinkedList<StringBuilder> lines = new LinkedList<>();
        int minXPos = outputWidth;
        int maxXPos = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            StringBuilder builderLine = new StringBuilder();
            for (int x = 0; x < image.getWidth(); x++) {
                int gray = gray(image.getRGB(x, y));
                if (grayCompare(gray, background)) {
                    builderLine.append(" ");
                } else {
                    builderLine.append("#");
                    if (x < minXPos) {
                        minXPos = x;
                    }
                    if (x > maxXPos) {
                        maxXPos = x;
                    }
                }
            }
            if (isBlank(builderLine.toString())) {
                continue;
            }
            lines.add(builderLine);
        }
        for (StringBuilder line : lines) {
            sb.append(line.substring(minXPos, maxXPos)).append("\n");
        }
        g2d.dispose();
        return sb.toString();
    }

}
