package mazeVisualizer.graphic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import mazeVisualizer.Main;

public class ImageManager {
	
	private static HashMap<String, BufferedImage> imageMap;
	private static HashMap<String, BufferedImage[]> splitImagesMap;
	
	public static void init() {
		imageMap = new HashMap<>();
		splitImagesMap = new HashMap<>();
	}
	
	public static BufferedImage loadImage(String path) {
		if (imageMap.containsKey(path)) return imageMap.get(path);
		System.out.println("New loading image: " + path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(Main.class.getClassLoader().getResource("res/image/" + path));
			setImage(path, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getImage(String path) {
		return imageMap.get(path);
	}
	
	public static BufferedImage[] loadSplitImages(String path, int width, int height) {
		if (splitImagesMap.containsKey(path)) return splitImagesMap.get(path);
		BufferedImage image = loadImage(path);
		System.out.println("New loading split image: " + path);
		int img_w = image.getWidth();
		int img_h = image.getHeight();
		BufferedImage[] images = new BufferedImage[(img_w / width) * (img_h / height)];
		for (int i = 0; i < (img_h / height); i++) {
			for (int j = 0; j < (img_w / width); j++) {
				images[i * (img_w / width) + j] = image.getSubimage(j * width, i * height, width, height);
			}
		}
		setSplitImages(path, images);
		return images;
	}
	
	public static BufferedImage[] getSplitImages(String path) {
		return splitImagesMap.get(path);
	}
	
	public static void setImage(String path, BufferedImage image) {
		imageMap.put(path, image);
	}
	
	public static void setSplitImages(String path, BufferedImage[] images) {
		splitImagesMap.put(path, images);
	}
}
