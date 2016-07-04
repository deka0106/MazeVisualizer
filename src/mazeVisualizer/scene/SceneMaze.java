package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import mazeVisualizer.Main;
import mazeVisualizer.input.MouseManager;
import mazeVisualizer.maze.Maze;
import mazeVisualizer.maze.MazeSolver;

public class SceneMaze extends Scene {
	
	/** 背景色 */
	private static Color backColor = Color.WHITE;
	
	/** 迷路の数 */
	private static int mazeNum = 2;
	
	/** 現在の迷路のID */
	private int mazeID = 0;
	
	/** 現在の迷路 */
	private Maze currentMaze;
	
	/** 迷路Solver */
	private MazeSolver solver;
	
	/**
	 * 初期化
	 */
	@Override
	public void init() {
		setMaze(0);
	}
	
	/**
	 * 迷路を設定する
	 *
	 * @param id 設定する迷路のID
	 */
	public void setMaze(int id) {
		mazeID = id;
		currentMaze = new Maze("maze" + (id + 1) + ".txt");
		solver = new MazeSolver(currentMaze);
	}
	
	/**
	 * 次の迷路に進む
	 */
	public void nextMaze() {
		setMaze((mazeID + 1) % mazeNum);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void update() {
		if (MouseManager.isPressed(MouseEvent.BUTTON1)) {
			if (solver.nextStep() == 1) {
				nextMaze();
			}
		}
	}
	
	/**
	 * 描画
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(backColor);
		g.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		
		currentMaze.draw(g);
	}
	
}
