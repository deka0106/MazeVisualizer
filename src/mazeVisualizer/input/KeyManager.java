package mazeVisualizer.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import mazeVisualizer.setting.Setting;

public class KeyManager {
	
	private static KeyInput ki = new KeyInput();
	
	/**
	 *
	 * @return KeyAdapter
	 */
	public static KeyAdapter getListener() {
		return ki;
	}
	
	/**
	 * 使用するキーの種類
	 */
	public static enum Key {
		DECIDE, CANCEL, OPTION, UP, DOWN, LEFT, RIGHT
	}
	
	private static HashMap<Key, Integer> keyMap = new HashMap<>();
	static {
		keyMap.put(Key.DECIDE, Setting.DEFAULT_KEY_DECIDE);
		keyMap.put(Key.CANCEL, Setting.DEFAULT_KEY_CANCEL);
		keyMap.put(Key.OPTION, Setting.DEFAULT_KEY_OPTION);
		keyMap.put(Key.UP, Setting.DEFAULT_KEY_UP);
		keyMap.put(Key.DOWN, Setting.DEFAULT_KEY_DOWN);
		keyMap.put(Key.LEFT, Setting.DEFAULT_KEY_LEFT);
		keyMap.put(Key.RIGHT, Setting.DEFAULT_KEY_RIGHT);
	}
	
	/**
	 * 更新
	 */
	public static void update() {
		ki.update();
	}
	
	/**
	 *
	 * @param key Key
	 * @return 今押されているかどうか
	 */
	public static boolean isDown(Key key) {
		return ki.isDown(keyMap.get(key));
	}
	
	/**
	 *
	 * @param key Key
	 * @return たった今押されたかどうか
	 */
	public static boolean isPressed(Key key) {
		return ki.isPressed(keyMap.get(key));
	}
	
	/**
	 *
	 * @param key Key
	 * @return たった今離されたかどうか
	 */
	public static boolean isReleased(Key key) {
		return ki.isReleased(keyMap.get(key));
	}
	
	/**
	 *
	 * @param keyCode キーコード
	 * @return 今押されているかどうか
	 */
	public static boolean isDown(int keyCode) {
		return ki.isDown(keyCode);
	}
	
	/**
	 *
	 * @param keyCode キーコード
	 * @return たった今押されたかどうか
	 */
	public static boolean isPressed(int keyCode) {
		return ki.isPressed(keyCode);
	}
	
	/**
	 *
	 * @param keyCode キーコード
	 * @return たった今離されたかどうか
	 */
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
