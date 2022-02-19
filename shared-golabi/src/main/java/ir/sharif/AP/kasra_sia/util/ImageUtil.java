package ir.sharif.AP.kasra_sia.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageUtil {
    public static BufferedImage convert(String string) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(string);
        InputStream stream = new ByteArrayInputStream(bytes);
        return ImageIO.read(stream);
    }
    public static String convert(BufferedImage bufferedImage,String format) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,format,outputStream);
        byte[] bytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
