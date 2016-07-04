package mazeVisualizer.graphic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import mazeVisualizer.Main;

public class ImageManager {
	
	/** 画像マップ */
	private static HashMap<String, BufferedImage> imageMap;
	
	/** 分割画像マップ */
	private static HashMap<String, BufferedImage[]> splitImagesMap;
	
	/**
	 * 初期化
	 */
	public static void init() {
		imageMap = new HashMap<>();
		splitImagesMap = new HashMap<>();
	}
	
	/**
	 * 画像を読み込み
	 *
	 * @param path 画像のパス
	 * @return 画像
	 */
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
	
	/**
	 * 画像を取得
	 *
	 * @param path 画像のパス
	 * @return 画像
	 */
	public static BufferedImage getImage(String path) {
		return imageMap.get(path);
	}
	
	/**
	 * 分割画像読み込み
	 *
	 * @param path 画像のパス
	 * @param width 分割幅
	 * @param height 分割高
	 * @return 分割画像
	 */
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
	
	/**
	 * 分割画像を取得
	 *
	 * @param path 画像のパス
	 * @return 分割画像
	 */
	public static BufferedImage[] getSplitImages(String path) {
		return splitImagesMap.get(path);
	}
	
	/**
	 *
	 * @param path key(パス)
	 * @param image value(画像)
	 */
	public static void setImage(String path, BufferedImage image) {
		imageMap.put(path, image);
	}
	
	/**
	 *
	 * @param path key(パス)
	 * @param image value(分割画像)
	 */
	public static void setSplitImages(String path, BufferedImage[] images) {
		splitImagesMap.put(path, images);
	}
}
