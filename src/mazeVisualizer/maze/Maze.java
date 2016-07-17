package mazeVisualizer.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import mazeVisualizer.setting.Setting;

public class Maze {
	
	/* マップ要素 */
	static final int ROAD = 0;
	static final int WALL = 1;
	static final int START = 2;
	static final int GOAL = 3;
	
	/** マップの幅 */
	int w;
	
	/** マップの高 */
	int h;
	
	/** マップの左上x座標 */
	int leftUpX;
	
	/** マップの左上y座標 */
	int leftUpY;
	
	/** マップチップの大きさ */
	int size;
	
	/** 視界を狭めるかどうか */
	
	/** プレイヤーの視界の半径 */
	float viewRadius;
	
	/** マップデータ */
	int[][] map;
	
	/** 通過済みどうか */
	boolean[][] passed;
	
	/** プレイヤー地点 */
	Point p;
	
	/** マップ全体の画像 */
	BufferedImage mapImage;
	
	/**
	 * 迷路生成
	 *
	 * @param mapW マップ幅
	 * @param mapH マップ高
	 */
	public Maze(int mapW, int mapH) {
		
		// 大きさ調整
		if (mapW < 5) {
			System.out.println("幅5以上でない場合生成不可");
			mapW = 5;
		} else if (mapW % 2 == 0) {
			System.out.println("奇数幅でない場合生成不可");
			mapW++;
		}
		if (mapH < 5) {
			System.out.println("高さ5以上でない場合生成不可");
			mapH = 5;
		} else if (mapH % 2 == 0) {
			System.out.println("奇数高でない場合生成不可");
			mapH++;
		}
		
		// マップサイズ
		w = mapW;
		h = mapH;
		
		// マップ生成
		map = MapMaker.makeMap(w, h);
		
		// 初期化
		passed = new boolean[h][w];
		
		// プレイヤー
		p = new Point(1, 0);
		
		// チップサイズ
		size = Math.min(Setting.WINDOW_WIDTH / w / 4 * 4, Setting.WINDOW_HEIGHT / h / 4 * 4);
		
		// 視界
		viewRadius = size * 2;
		
		// 左上座標
		leftUpX = (Setting.WINDOW_WIDTH - w * size) / 2;
		leftUpY = (Setting.WINDOW_HEIGHT - h * size) / 2;
		
		mapImage = new BufferedImage(Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) mapImage.getGraphics();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				switch (map[y][x]) {
					case ROAD: // 道
						break;
					case WALL: // 壁
						g.setColor(Color.DARK_GRAY);
						g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
						break;
					case START: // スタート
						g.setColor(Color.GREEN);
						g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
						break;
					case GOAL: // ゴール
						g.setColor(Color.CYAN);
						g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
						break;
				}
			}
		}
		g.dispose();
	}
	
	/**
	 * 迷路生成(ファイル入力)
	 *
	 * @param path マップデータのパス
	 */
	public Maze(String path) {
		Scanner sc = new Scanner(Setting.class.getClassLoader().getResourceAsStream("res/maze/" + path));
		
		// マップサイズ
		h = sc.nextInt();
		w = sc.nextInt();
		
		// マップ初期化
		map = new int[h][w];
		for (int y = 0; y < h; y++) {
			char[] charArray = sc.next().toCharArray();
			for (int x = 0; x < w; x++) {
				switch (charArray[x]) {
					case '0':
						map[y][x] = ROAD;
						break;
					case '1':
						map[y][x] = WALL;
						break;
					case 's':
						map[y][x] = START;
						p = new Point(x, y);
						break;
					case 't':
						map[y][x] = GOAL;
						break;
				}
			}
		}
		sc.close();
		
		// 初期化
		passed = new boolean[h][w];
		
		// チップサイズ
		size = Math.min(Setting.WINDOW_WIDTH / w / 4 * 4, Setting.WINDOW_HEIGHT / h / 4 * 4);
		
		// 視界
		viewRadius = size * 2;
		
		// 左上座標
		leftUpX = (Setting.WINDOW_WIDTH - w * size) / 2;
		leftUpY = (Setting.WINDOW_HEIGHT - h * size) / 2;
		
		mapImage = new BufferedImage(Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) mapImage.getGraphics();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				switch (map[y][x]) {
					case ROAD: // 道
						break;
					case WALL: // 壁
						g.setColor(Color.DARK_GRAY);
						g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
						break;
					case START: // スタート
						g.setColor(Color.GREEN);
						g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
						break;
					case GOAL: // ゴール
						g.setColor(Color.CYAN);
						g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
						break;
				}
			}
		}
		g.dispose();
	}
	
	/** 描画 */
	public void draw(Graphics2D g) {
		
		drawMap(g);
		
		drawPlayer(g);
		
		// drawShadow(g);
		
	}
	
	private void drawMap(Graphics2D g) {
		// 背景
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());
		
		g.drawImage(mapImage, 0, 0, null);
		g.setColor(new Color(255, 0, 0, 50));
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (passed[y][x]) {
					g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
				}
			}
		}
	}
	
	private void drawPlayer(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillOval(leftUpX + size * p.x, leftUpY + size * p.y, size, size);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	
	private void drawShadow(Graphics2D g) {
		Point2D center = new Point2D.Float(leftUpX + size * p.x + size / 2, leftUpY + size * p.y + size / 2);
		float[] dist = { 0.0f, 0.9f, 1.0f };
		Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 0), new Color(0, 0, 0, 255) };
		RadialGradientPaint rgp = new RadialGradientPaint(center, viewRadius, dist, colors);
		g.setPaint(rgp);
		g.fillRect(0, 0, Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT);
		
	}
}
