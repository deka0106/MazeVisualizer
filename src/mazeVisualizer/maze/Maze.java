package mazeVisualizer.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Scanner;

import mazeVisualizer.Main;

public class Maze {
	
	private static final int size = 32;
	
	private int w, h;
	private char[][] map;
	
	private boolean[][] passed;
	
	public Maze(String path) {
		Scanner sc = new Scanner(Main.class.getClassLoader().getResourceAsStream("res/maze/" + path));
		
		h = sc.nextInt();
		w = sc.nextInt();
		
		map = new char[h][w];
		for (int y = 0; y < h; y++) {
			map[y] = sc.next().toCharArray();
		}
		
		sc.close();
		
		passed = new boolean[h][w];
		
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		int leftUpX = (Main.WINDOW_WIDTH - w * size) / 2;
		int leftUpY = (Main.WINDOW_HEIGHT - h * size) / 2;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				switch (map[y][x]) {
					case '0':
						g.setColor(Color.LIGHT_GRAY);
						break;
					case '1':
						g.setColor(Color.BLACK);
						break;
					case 's':
						g.setColor(Color.PINK);
						break;
					case 't':
						g.setColor(Color.CYAN);
						break;
				}
				g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
				
				g.setColor(new Color(255, 0, 0, 50));
				if (passed[y][x]) g.fillRect(leftUpX + size * x, leftUpY + size * y, size, size);
			}
		}
	}
	
}
