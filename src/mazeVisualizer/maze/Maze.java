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
	
	/** プレイヤーの視界の半径 */
	float viewRadius;
	
	/** マップデータ */
	char[][] map;
	
	/** 通過済みどうか */
	boolean[][] passed;
	
	/** スタート地点 */
	Point s;
	
	/** ゴール地点 */
	Point t;
	
	/** プレイヤー地点 */
	Point p;
	
	/** マップ全体の画像 */
	BufferedImage mapImage;
	
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
		map = new char[h][w];
		for (int y = 0; y < h; y++) {
			map[y] = sc.next().toCharArray();
			for (int x = 0; x < w; x++) {
				switch (map[y][x]) {
					case 's':
						s = new Point(x, y);
						break;
					case 't':
						t = new Point(x, y);
						break;
				}
			}
		}
		sc.close();
		
		// 初期化
		passed = new boolean[h][w];
		
		// プレイヤー位置
		p = new Point(s);
		
		// チップサイズ
		size = Math.min(Setting.WINDOW_WIDTH / w / 4 * 4, Setting.WINDOW_HEIGHT / h / 4 * 4);
		
		// 視界
		viewRadius = size * 2;
		
		// 左上座標
		leftUpX = (Setting.WINDOW_WIDTH - w * size) / 2;
		leftUpY = (Setting.WINDOW_HEIGHT - h * size) / 2;
		
	}
	
	/** 描画 */
	public void draw(Graphics2D g) {
		
		drawMap(g);
		
		drawPlayer(g);
		
		drawShadow(g);
		
	}
	
	private void drawMap(Graphics2D g) {
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				switch (map[y][x]) {
					case '0': // 道
						g.setColor(Color.LIGHT_GRAY);
						break;
					case '1': // 壁
						g.setColor(Color.DARK_GRAY);
						break;
					case 's': // スタート
						g.setColor(Color.GREEN);
						break;
					case 't': // ゴール
						g.setColor(Color.CYAN);
						break;
				}
				g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
				
				g.setColor(new Color(255, 0, 0, 50));
				if (passed[y][x]) g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
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
