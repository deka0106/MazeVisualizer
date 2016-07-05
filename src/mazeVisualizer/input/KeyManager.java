package mazeVisualizer.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyManager {
	
	private static KeyInput ki = new KeyInput();
	
	public static KeyAdapter getListener() {
		return ki;
	}
	
	public static enum Key {
		DECIDE, CANCEL
	}
	
	private static HashMap<Key, Integer> keyMap = new HashMap<>();
	static {
		keyMap.put(Key.DECIDE, KeyEvent.VK_ENTER);
		keyMap.put(Key.CANCEL, KeyEvent.VK_BACK_SPACE);
	}
	
	public static void update() {
		ki.update();
	}
	
	public static boolean isDown(Key key) {
		return ki.isDown(keyMap.get(key));
	}
	
	public static boolean isPressed(Key key) {
		return ki.isPressed(keyMap.get(key));
	}
	
	public static boolean isReleased(Key key) {
		return ki.isReleased(keyMap.get(key));
	}
	
	public static boolean isDown(int keyCode) {
		return ki.isDown(keyCode);
	}
	
	public static boolean isPressed(int keyCode) {
		return ki.isPressed(keyCode);
	}
	
	public static boolean isReleased(int keyCode) {
		return ki.isReleased(keyCode);
	}
	
}

class KeyInput extends KeyAdapter {
	
	private static final int KEY_NUMBER = 256;
	
	private boolean[] lastKeys, currentKeys;
	
	KeyInput() {
		lastKeys = new boolean[KEY_NUMBER];
		currentKeys = new boolean[KEY_NUMBER];
	}
	
	void update() {
		lastKeys = currentKeys.clone();
	}
	
	boolean isDown(int keyCode) {
		return currentKeys[keyCode];
	}
	
	boolean isPressed(int keyCode) {
		return !lastKeys[keyCode] && currentKeys[keyCode];
	}
	
	boolean isReleased(int keyCode) {
		return lastKeys[keyCode] && !currentKeys[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		currentKeys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		currentKeys[e.getKeyCode()] = false;
	}
}
