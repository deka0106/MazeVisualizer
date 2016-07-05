package mazeVisualizer.input;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager {
	
	private static MouseInput mi = new MouseInput();
	
	public static MouseAdapter getListener() {
		return mi;
	}
	
	/**
	 * 更新
	 */
	public static void update() {
		mi.update();
	}
	
	/**
	 *
	 * @return 現在のカーソル位置
	 */
	public static Point getPosition() {
		return mi.getPosition();
	}
	
	/**
	 *
	 * @param button マウスのボタン
	 * @return 今押されているかどうか
	 */
	public static boolean isDown(int button) {
		return mi.isDown(button);
	}
	
	/**
	 *
	 * @param button マウスのボタン
	 * @return たった今押されたかどうか
	 */
	public static boolean isPressed(int button) {
		return mi.isPressed(button);
	}
	
	/**
	 *
	 * @param button マウスのボタン
	 * @return たった今離されたかどうか
	 */
	public static boolean isReleased(int button) {
		return mi.isReleased(button);
	}
	
}

class MouseInput extends MouseAdapter {
	
	private static final int BUTTON_NUMBER = 3;
	
	private Point lastPos;
	private Point currentPos;
	
	private boolean[] lastMouse;
	private boolean[] currentMouse;
	
	MouseInput() {
		lastPos = new Point(0, 0);
		currentPos = new Point(0, 0);
		lastMouse = new boolean[BUTTON_NUMBER];
		currentMouse = new boolean[BUTTON_NUMBER];
	}
	
	void update() {
		lastPos = new Point(currentPos);
		lastMouse = currentMouse.clone();
	}
	
	Point getPosition() {
		return lastPos;
	}
	
	boolean isDown(int button) {
		return currentMouse[button - 1];
	}
	
	boolean isPressed(int button) {
		return !lastMouse[button - 1] && currentMouse[button - 1];
	}
	
	boolean isReleased(int button) {
		return lastMouse[button - 1] && !currentMouse[button - 1];
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		currentMouse[e.getButton() - 1] = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		currentMouse[e.getButton() - 1] = false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		currentPos = e.getPoint();
	}
	
}
