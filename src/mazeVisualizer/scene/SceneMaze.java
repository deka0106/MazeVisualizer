package mazeVisualizer.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mazeVisualizer.Main;
import mazeVisualizer.graphic.ImageManager;
import mazeVisualizer.input.KeyManager;
import mazeVisualizer.maze.Maze;
import mazeVisualizer.maze.MazeSolver;
import mazeVisualizer.setting.Setting;

public class SceneMaze extends Scene {
	
	/** 背景色 */
	private static Color backColor = Color.WHITE;
	
	/** ゴール */
	private static BufferedImage goalImg = ImageManager.getImage("goal.png");
	
	/** 現在の迷路 */
	private Maze currentMaze;
	
	/** 迷路Solver */
	private MazeSolver solver;
	
	/** 迷路ゴール */
	private boolean goalFlag = false;
	
	private int frameCount = 0;
	
	private boolean autoMode = false;
	
	/**
	 * 初期化
	 */
	@Override
	public void init() {
		Main.setTitle("迷路" + " [Enter:ゴール後次マップ, L:視界切り替え, A:自動, N:スキップ]");
		setMaze(39, 21);
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
		setMaze(39, 21);
	}
	
	/**
	 * 更新
	 */
	@Override
	public void update() {
		if (goalFlag) {
			if ((autoMode && frameCount % 100 == 0) || KeyManager.isPressed(KeyManager.Key.DECIDE)) {
				goalFlag = false;
				nextMaze();
				frameCount = 0;
			}
		} else {
			if (KeyManager.isPressed(KeyManager.Key.LIGHT)) {
				Maze.changeShadowFlag();
			}
			if (KeyManager.isPressed(KeyManager.Key.AUTO)) {
				autoMode = !autoMode;
			}
			if (KeyManager.isDown(KeyManager.Key.NEXT)) {
				if (solver.nextStep() == 1) {
					goalFlag = true;
					frameCount = 0;
				}
			} else if (autoMode && frameCount % 10 == 0) {
				if (solver.nextStep() == 1) {
					goalFlag = true;
				}
				frameCount = 0;
			}
		}
		
		frameCount++;
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
			g.drawImage(goalImg, 0, 0, null);
		}
	}
	
}
