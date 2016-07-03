package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Graphics2D;

import mazeVisualizer.Main;

public class SceneMaze extends Scene {
	
	private static Color backColor = Color.WHITE;
	
	@Override
	public void init() {
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(backColor);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
	}
	
}
