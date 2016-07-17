package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import mazeVisualizer.input.KeyManager;
import mazeVisualizer.maze.Maze;
import mazeVisualizer.maze.MazeSolver;
import mazeVisualizer.setting.Setting;

public class SceneMaze extends Scene {
	
	/** 背景色 */
	private static Color backColor = Color.WHITE;
	
	/** ゴール */
	private static Font goalFont = new Font(Font.MONOSPACED, Font.PLAIN, 120);
	
	/** 現在の迷路 */
	private Maze currentMaze;
	
	/** 迷路Solver */
	private MazeSolver solver;
	
	/** 迷路ゴール */
	private boolean goalFlag = false;
	
	/**
	 * 初期化
	 */
	@Override
	public void init() {
		setMaze(91, 71);
	}
	
	/**
	 * 迷路を設定する
	 *
	 * @param w 幅
	 * @param h 高
	 */
	public void setMaze(int w, int h) {
		currentMaze = new Maze(w, h);
		solver = new MazeSolver(currentMaze);
	}
	
	/**
	 * 次の迷路に進む
	 */
	public void nextMaze() {
		setMaze(91, 71);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void update() {
		if (KeyManager.isPressed(KeyManager.Key.DECIDE)) {
			if (goalFlag) {
				goalFlag = false;
				nextMaze();
			}
			
			if (solver.nextStep() == 1) {
				goalFlag = true;
			}
		}
		if (KeyManager.isPressed(KeyManager.Key.OPTION)) {
			Maze.changeShadowFlag();
		}
	}
	
	/**
	 * 描画
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(backColor);
		g.fillRect(0, 0, Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT);
		
		currentMaze.draw(g);
		
		if (goalFlag) {
			g.setColor(Color.RED);
			g.setFont(goalFont);
			g.drawString("ゴール", 220, 300);
		}
	}
	
}
