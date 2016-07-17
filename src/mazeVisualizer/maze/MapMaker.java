package mazeVisualizer.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapMaker {
	
	/** 乱数用 */
	private static Random rand = new Random();
	
	static int[][] makeMap(int w, int h) {
		int[][] map = new int[h][w];
		
		List<Point> canPutWallList = new ArrayList<>();
		
		for (int x = 0; x < w; x++) {
			map[0][x] = Maze.WALL;
			map[h - 1][x] = Maze.WALL;
			if (0 < x && x < w - 1 && x % 2 == 0) {
				canPutWallList.add(new Point(x, 0));
				canPutWallList.add(new Point(x, h - 1));
			}
		}
		
		for (int y = 0; y < h; y++) {
			map[y][0] = Maze.WALL;
			map[y][w - 1] = Maze.WALL;
			if (0 < y && y < h - 1 && y % 2 == 0) {
				canPutWallList.add(new Point(0, y));
				canPutWallList.add(new Point(w - 1, y));
			}
		}
		
		while (!canPutWallList.isEmpty()) {
			int index = rand.nextInt(canPutWallList.size());
			Point point = canPutWallList.get(index);
			canPutWallList.remove(index);
			
			int x = point.x;
			int y = point.y;
			
			boolean endFlag = false;
			do {
				endFlag = true;
				
				int direction = rand.nextInt(4);
				int tmp = direction;
				boolean extend = false;
				
				do {
					
					switch (tmp % 4) {
						case 0: // 上
							if (2 < y && map[y - 2][x] != Maze.WALL) {
								map[y - 1][x] = Maze.WALL;
								map[y - 2][x] = Maze.WALL;
								canPutWallList.add(new Point(x, y - 2));
								y -= 2;
								endFlag = false;
								extend = true;
							}
							break;
						case 1: // 右
							if (x < w - 3 && map[y][x + 2] != Maze.WALL) {
								map[y][x + 1] = Maze.WALL;
								map[y][x + 2] = Maze.WALL;
								canPutWallList.add(new Point(x + 2, y));
								x += 2;
								endFlag = false;
								extend = true;
							}
							break;
						case 2: // 下
							if (y < h - 3 && map[y + 2][x] != Maze.WALL) {
								map[y + 1][x] = Maze.WALL;
								map[y + 2][x] = Maze.WALL;
								canPutWallList.add(new Point(x, y + 2));
								y += 2;
								endFlag = false;
								extend = true;
							}
							break;
						case 3: // 左
							if (2 < x && map[y][x - 2] != Maze.WALL) {
								map[y][x - 1] = Maze.WALL;
								map[y][x - 2] = Maze.WALL;
								canPutWallList.add(new Point(x - 2, y));
								x -= 2;
								endFlag = false;
								extend = true;
							}
							break;
					}
					
				} while (direction != (++tmp) % 4 && !extend);
				
			} while (!endFlag);
		}
		
		map[0][1] = Maze.START;
		
		map[h - 1][w - 2] = Maze.GOAL;
		
		return map;
	}
}
