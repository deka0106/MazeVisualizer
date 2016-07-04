package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import mazeVisualizer.Main;
import mazeVisualizer.input.MouseManager;
import mazeVisualizer.maze.Maze;
import mazeVisualizer.maze.MazeSolver;

public class SceneMaze extends Scene {
	
	private static Color backColor = Color.WHITE;
	
	private static int mazeNum = 2;
	private int mazeID = 0;
	
	private Maze currentMaze;
	private MazeSolver solver;
	
	@Override
	public void init() {
		setMaze(0);
	}
	
	public void setMaze(int number) {
		mazeID = number;
		currentMaze = new Maze("maze" + (number + 1) + ".txt");
		solver = new MazeSolver(currentMaze);
	}
	
	public void nextMaze() {
		setMaze((mazeID + 1) % mazeNum);
	}
	
	@Override
	public void update() {
		if (MouseManager.isPressed(MouseEvent.BUTTON1)) {
			if (solver.nextStep() == 1) {
				nextMaze();
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(backColor);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		currentMaze.draw(g);
	}
	
}
