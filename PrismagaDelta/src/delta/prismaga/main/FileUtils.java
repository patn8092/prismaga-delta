package delta.prismaga.main;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {

	public static BufferedImage loadAsImage(String path) {
		try {
			return ImageIO.read(Game.class.getResource(path));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
