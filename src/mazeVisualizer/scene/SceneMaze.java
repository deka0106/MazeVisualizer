package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Graphics2D;

import mazeVisualizer.Main;
import mazeVisualizer.maze.Maze;

public class SceneMaze extends Scene {

	private static Color backColor = Color.WHITE;

	private int mazeNum = 0;

	private Maze currentMaze;

	@Override
	public void init() {
		setMaze(0);
	}

	public void setMaze(int number) {
		mazeNum = number;
		currentMaze = new Maze("maze" + (number + 1) + ".txt");
	}

	public void nextMaze() {
		setMaze(mazeNum + 1);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(backColor);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

		currentMaze.draw(g);
	}

}
