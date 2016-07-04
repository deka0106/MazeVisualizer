package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import mazeVisualizer.Main;
import mazeVisualizer.graphic.ImageManager;
import mazeVisualizer.input.MouseManager;

public class SceneTitle extends Scene {
	
	private static Color backColor = new Color(255, 255, 255);
	private static Font titleFont = new Font(Font.MONOSPACED, Font.PLAIN, 80);
	
	private static BufferedImage buri = ImageManager.getImage("buri.png");
	
	/**
	 * 更新
	 */
	@Override
	public void update() {
		if (MouseManager.isPressed(MouseEvent.BUTTON1)) {
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
		g.setColor(backColor);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		// 鰤
		g.drawImage(buri, (Main.WINDOW_WIDTH - buri.getWidth()) / 2, 100, null);
		
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawString("迷路可視化", 180, 400);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	
}
