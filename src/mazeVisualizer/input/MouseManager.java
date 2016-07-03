package mazeVisualizer.input;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager {
	
	private static MouseInput mi = new MouseInput();
	
	public static MouseAdapter getListener() {
		return mi;
	}
	
	public static void update() {
		mi.update();
	}
	
	public static Point getPosition() {
		return mi.getPosition();
	}
	
	public static boolean isDown(int button) {
		return mi.isDown(button);
	}
	
	public static boolean isPressed(int button) {
		return mi.isPressed(button);
	}
	
	public static boolean isReleased(int button) {
		return mi.isReleased(button);
	}
	
}

class MouseInput extends MouseAdapter {
	
	private static final int BUTTON_NUMBER = 3;
	
	private Point currentPos;
	private Point nextPos;
	
	private boolean[] lastMouse;
	private boolean[] currentMouse;
	private boolean[] nextMouse;
	
	public MouseInput() {
		currentPos = new Point(0, 0);
		nextPos = new Point(0, 0);
		lastMouse = new boolean[BUTTON_NUMBER];
		currentMouse = new boolean[BUTTON_NUMBER];
		nextMouse = new boolean[BUTTON_NUMBER];
	}
	
	public void update() {
		currentPos = new Point(nextPos);
		for (int i = 0; i < BUTTON_NUMBER; i++) {
			lastMouse[i] = currentMouse[i];
			currentMouse[i] = nextMouse[i];
		}
	}
	
	public Point getPosition() {
		return currentPos;
	}
	
	public boolean isDown(int button) {
		return currentMouse[button - 1];
	}
	
	public boolean isPressed(int button) {
		return !lastMouse[button - 1] && currentMouse[button - 1];
	}
	
	public boolean isReleased(int button) {
		return lastMouse[button - 1] && !currentMouse[button - 1];
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		nextMouse[e.getButton() - 1] = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		nextMouse[e.getButton() - 1] = false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		nextPos = e.getPoint();
	}
	
}
