package mazeVisualizer.maze;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

public class MazeSolver {
	
	/** 移動用定数 */
	private static final int[] dx = { -1, 0, 1, 0 };
	private static final int[] dy = { 0, -1, 0, 1 };
	
	/** 迷路 */
	private Maze maze;
	
	/** スタック */
	private Deque<Point> stack;
	
	/**
	 * コンストラクタ
	 *
	 * @param maze 迷路
	 */
	public MazeSolver(Maze maze) {
		this.maze = maze;
		
		stack = new ArrayDeque<>();
		stack.push(maze.p);
	}
	
	/**
	 * 次のステップに進む
	 *
	 * @return 通常:0, ゴール:1, ゴール不可:-1
	 */
	public int nextStep() {
		if (!stack.isEmpty()) {
			maze.p = stack.pop();
			maze.passed[maze.p.y][maze.p.x] = true;
			
			if (maze.map[maze.p.y][maze.p.x] == Maze.GOAL) return 1;
			
			for (int i = 0; i < 4; i++) {
				int nx = maze.p.x + dx[i];
				int ny = maze.p.y + dy[i];
				if (0 <= nx && nx < maze.w && 0 <= ny && ny < maze.h
						&& maze.map[ny][nx] != Maze.WALL
						&& !maze.passed[ny][nx]) {
					stack.push(new Point(nx, ny));
				}
			}
			return 0;
		}
		// stackが空→もう行ける場所がない
		return -1;
	}
	
}
