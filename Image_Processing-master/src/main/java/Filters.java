import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
public class Filters {

    private static final int mostValueInRGB = 255;

    public Filters(BufferedImage image, int number, File output) throws IOException {
        Color color1;
        int width = image.getWidth();
        int height = image.getHeight();
        Random random = new Random();
        int num = random.nextInt(3);

        File file = new File("src/main/java/Image1.jpg");
        BufferedImage image1 = ImageIO.read(file);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel);
                switch (number) {
                    case 1 -> {
                        color1 = blackAndWhite(color);
                        image1.setRGB(x, y, color1.getRGB());
                    }
                    case 2 -> image1.setRGB(width - x - 1, y, color.getRGB());

                    case 3 -> {
                        color1 = colorShiftRight(color);
                        image1.setRGB(x, y, color1.getRGB());
                    }
                    case 4 -> {
                        color1 = colorShiftLeft(color);
                        image1.setRGB(x, y, color1.getRGB());
                    }
                    case 5 -> {
                        color1 = eliminate(color, num);
                        image1.setRGB(x, y, color1.getRGB());
                    }
                    default -> {
                        color1 = lighter(color);
                        image1.setRGB(x, y, color1.getRGB());
                    }
                }
            }
        }
        ImageIO.write(image1, "png", output);

    }

    public Color blackAndWhite(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int average = (red + green + blue) / 3;
        return new Color(average, average, average);
    }


    public Color colorShiftRight(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        return new Color(green, blue, red);
    }

    public Color colorShiftLeft(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        return new Color(blue, red, green);
    }

    public Color eliminate(Color color, int num) {

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        return switch (num) {
            case 0 -> new Color(0, green, blue);
            case 1 -> new Color(red, 0, blue);
            default -> new Color(red, green, 0);
        };
    }


    public Color lighter(Color color) {
        int red = intenseColor(color.getRed(), 1.2);
        int green = intenseColor(color.getGreen(), 1.2);
        int blue = intenseColor(color.getBlue(), 1.2);

        return new Color(red, green, blue);
    }
    public static int intenseColor(int original, double by) {
        original *= by;
        if (original > mostValueInRGB) {
            original = mostValueInRGB;
        }
        return original;
    }

}
