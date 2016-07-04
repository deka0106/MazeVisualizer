package mazeVisualizer.maze;

public class MazeSolver {

	private int w, h;
	private char[][] map;

	public MazeSolver(Maze maze) {
		this.w = maze.getW();
		this.h = maze.getH();
		this.map = maze.getMap();
	}



}
