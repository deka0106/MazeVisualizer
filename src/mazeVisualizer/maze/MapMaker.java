package mazeVisualizer.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapMaker {
	
	/** 乱数用 */
	private static Random rand = new Random();
	
	/**
	 * マップを生成する
	 *
	 * @param w マップ幅
	 * @param h マップ高
	 * @return マップデータ
	 */
	static int[][] makeMap(int w, int h) {
		
		// マップデータ
		int[][] map = new int[h][w];
		
		// 壁の始点になりうる点のリスト
		List<Point> list = new ArrayList<>();
		
		// マップ初期化
		// 上下の壁
		for (int x = 0; x < w; x++) {
			map[0][x] = Maze.WALL;
			map[h - 1][x] = Maze.WALL;
			if (0 < x && x < w - 1 && x % 2 == 0) {
				// 始点になりうる点追加
				list.add(new Point(x, 0));
				list.add(new Point(x, h - 1));
			}
		}
		
		// 左右の壁
		for (int y = 0; y < h; y++) {
			map[y][0] = Maze.WALL;
			map[y][w - 1] = Maze.WALL;
			if (0 < y && y < h - 1 && y % 2 == 0) {
				// 始点になりうる点追加
				list.add(new Point(0, y));
				list.add(new Point(w - 1, y));
			}
		}
		
		// 迷路生成(壁のばし法)
		while (!list.isEmpty()) { // 始点になりうる点が存在しない場合終了
			
			// リストから乱択した番号
			int index = rand.nextInt(list.size());
			
			// 現在見ている場所
			Point point = list.get(index);
			int x = point.x;
			int y = point.y;
			
			// 選択された場所をリストから削除
			list.remove(index);
			
			boolean endFlag = false;
			
			// 壁を伸ばせる所まで伸ばす
			while (!endFlag) { // 全方向伸ばせなかった場合終了
				
				endFlag = true;
				
				// 伸ばす方向
				int direction = rand.nextInt(4);
				int tmp = direction;
				
				while (direction != (++tmp) % 4) {
					
					if (tmp % 4 == 0) { // 上
						if (2 < y && map[y - 2][x] != Maze.WALL) {
							map[y - 1][x] = Maze.WALL;
							map[y - 2][x] = Maze.WALL;
							list.add(new Point(x, y - 2));
							y -= 2;
							endFlag = false;
							break;
						}
					} else if (tmp % 4 == 1) { // 右
						if (x < w - 3 && map[y][x + 2] != Maze.WALL) {
							map[y][x + 1] = Maze.WALL;
							map[y][x + 2] = Maze.WALL;
							list.add(new Point(x + 2, y));
							x += 2;
							endFlag = false;
							break;
						}
					} else if (tmp % 4 == 2) { // 下
						if (y < h - 3 && map[y + 2][x] != Maze.WALL) {
							map[y + 1][x] = Maze.WALL;
							map[y + 2][x] = Maze.WALL;
							list.add(new Point(x, y + 2));
							y += 2;
							endFlag = false;
							break;
						}
					} else if (tmp % 4 == 3) { // 左
						if (2 < x && map[y][x - 2] != Maze.WALL) {
							map[y][x - 1] = Maze.WALL;
							map[y][x - 2] = Maze.WALL;
							list.add(new Point(x - 2, y));
							x -= 2;
							endFlag = false;
							break;
						}
					}
					
				}
				
			}
		}
		
		// スタート地点
		map[0][1] = Maze.START;
		
		// ゴール地点
		map[h - 1][w - 2] = Maze.GOAL;
		
		return map;
	}
}
