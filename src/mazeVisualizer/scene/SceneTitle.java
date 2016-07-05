package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import mazeVisualizer.Main;
import mazeVisualizer.input.KeyManager;

public class SceneTitle extends Scene {
	
	private static Color backColor = new Color(255, 255, 255);
	private static Font titleFont = new Font(Font.MONOSPACED, Font.PLAIN, 80);
	
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
		g.setColor(backColor);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawString("深さ優先探索", 160, 300);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	
}
