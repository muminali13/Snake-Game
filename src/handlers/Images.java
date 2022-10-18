package handlers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Images {

    public static BufferedImage logo = loadImage("/Images/m.png");

    public static BufferedImage snake_up_mouth = loadImage("/Images/Snake/upmouth.png");
    public static BufferedImage snake_down_mouth = loadImage("/Images/Snake/downmouth.png");
    public static BufferedImage snake_right_mouth = loadImage("/Images/Snake/rightmouth.png");
    public static BufferedImage snake_left_mouth = loadImage("/Images/Snake/leftmouth.png");
    public static BufferedImage snake_snake_image = loadImage("/Images/Snake/snakeimage.png");
    public static BufferedImage snake_point_image = loadImage("/Images/point.png");


    private static BufferedImage loadImage(String s) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Images.class.getResourceAsStream(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
