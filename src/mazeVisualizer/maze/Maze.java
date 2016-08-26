package mazeVisualizer.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import mazeVisualizer.graphic.ImageManager;
import mazeVisualizer.setting.Setting;

public class Maze {
	
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { -1, 0, 1, 0 };
	
	/* マップ要素 */
	static final int ROAD = 0;
	static final int WALL = 1;
	static final int START = 2;
	static final int GOAL = 3;
	
	static BufferedImage[] mapImg = ImageManager.getSplitImages("map.png");
	static BufferedImage[] sgImg = ImageManager.getSplitImages("sg.png");
	static BufferedImage playerImg = ImageManager.getImage("player.png");
	
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
	
	/** プレイヤー周りを暗くするかどうか */
	private static boolean shadowFlag = true;
	
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
		size = Math.min(Setting.WINDOW_WIDTH / w / 16 * 16, Setting.WINDOW_HEIGHT / h / 16 * 16);
		
		// 視界
		viewRadius = size * 4;
		
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
						int imgIndex = 0;
						for (int i = 0; i < 4; i++) {
							int xx = x + dx[i];
							int yy = y + dy[i];
							if (0 <= xx && xx < w && 0 <= yy && yy < h && map[yy][xx] == WALL) {
								imgIndex += (1 << i);
							}
						}
						g.drawImage(mapImg[imgIndex], leftUpX + size * x, leftUpY + size * y, size, size, null);
						break;
					case START: // スタート
						g.drawImage(sgImg[0], leftUpX + size * x, leftUpY + size * y, size, size, null);
						break;
					case GOAL: // ゴール
						g.drawImage(sgImg[1], leftUpX + size * x, leftUpY + size * y, size, size, null);
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
		
		if (shadowFlag) drawShadow(g);
		
	}
	
	private void drawMap(Graphics2D g) {
		// 背景
		g.setColor(new Color(120, 180, 40));
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
		g.drawImage(playerImg, leftUpX + size * p.x, leftUpY + size * p.y, size, size, null);
	}
	
	private void drawShadow(Graphics2D g) {
		Point2D center = new Point2D.Float(leftUpX + size * p.x + size / 2, leftUpY + size * p.y + size / 2);
		float[] dist = { 0.0f, 0.9f, 1.0f };
		Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 0), new Color(0, 0, 0, 255) };
		RadialGradientPaint rgp = new RadialGradientPaint(center, viewRadius, dist, colors);
		g.setPaint(rgp);
		g.fillRect(0, 0, Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT);
		
	}
	
	/**
	 * プレイヤーの周りを暗くするかを変更
	 */
	public static void changeShadowFlag() {
		shadowFlag = !shadowFlag;
	}
}
