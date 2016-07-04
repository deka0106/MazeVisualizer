package mazeVisualizer.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Scanner;

import mazeVisualizer.Main;

public class Maze {

	int w, h, leftUpX, leftUpY, size;
	char[][] map;
	Point s, t, p;

	boolean[][] passed;

	public Maze(String path) {
		Scanner sc = new Scanner(Main.class.getClassLoader().getResourceAsStream("res/maze/" + path));

		h = sc.nextInt();
		w = sc.nextInt();

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

		// チップサイズ
		size = Math.min(Main.WINDOW_WIDTH / w / 8 * 8, Main.WINDOW_HEIGHT / h / 8 * 8);

		leftUpX = (Main.WINDOW_WIDTH - w * size) / 2;
		leftUpY = (Main.WINDOW_HEIGHT - h * size) / 2;

		passed = new boolean[h][w];

		p = new Point(s);
	}

	/** 描画 */
	public void draw(Graphics2D g) {

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				switch (map[y][x]) {
					case '0': // 道
						g.setColor(Color.LIGHT_GRAY);
						break;
					case '1': // 壁
						g.setColor(Color.BLACK);
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

		g.setColor(Color.ORANGE);
		g.fillOval(leftUpX + size * p.x + 2, leftUpY + size * p.y + 2, size - 4, size - 4);
	}

}
