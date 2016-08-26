package mazeVisualizer.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mazeVisualizer.Main;
import mazeVisualizer.graphic.ImageManager;
import mazeVisualizer.input.KeyManager;

public class SceneTitle extends Scene {
	
	private static BufferedImage titleImg = ImageManager.getImage("title.png");
	
	@Override
	public void init() {
		Main.setTitle("迷路" + " [Enter:スタート]");
	}
	
	/**
	 * 更新
	 */
	@Override
	public void update() {
		if (KeyManager.isPressed(KeyManager.Key.DECIDE)) {
			Scene.changeScene(SceneMaze.class);
			Scene.getCurrentScene().init();
		}
	}
	
	/**
	 * 描画
	 */
	@Override
	public void draw(Graphics2D g) {
		// 背景
		g.drawImage(titleImg, 0, 0, null);
	}
	
}
