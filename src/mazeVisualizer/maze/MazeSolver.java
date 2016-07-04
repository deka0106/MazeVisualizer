package mazeVisualizer.maze;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

public class MazeSolver {
	
	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, 1, -1 };
	
	private Maze maze;
	
	private Deque<Point> stack;
	
	public MazeSolver(Maze maze) {
		this.maze = maze;
		
		maze.p = new Point(maze.s);
		
		stack = new ArrayDeque<>();
		stack.push(maze.p);
	}
	
	public int nextStep() {
		if (!stack.isEmpty()) {
			maze.p = stack.pop();
			maze.passed[maze.p.y][maze.p.x] = true;
			
			if (maze.p.equals(maze.t)) return 1;
			
			for (int i = 0; i < 4; i++) {
				int nx = maze.p.x + dx[i];
				int ny = maze.p.y + dy[i];
				if (0 <= nx && nx < maze.w && 0 <= ny && ny < maze.h
						&& maze.map[ny][nx] != '1'
						&& !maze.passed[ny][nx]) {
					stack.push(new Point(nx, ny));
				}
			}
			return 0;
		}
		return -1;
	}
	
}
