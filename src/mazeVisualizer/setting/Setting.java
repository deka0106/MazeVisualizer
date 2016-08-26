package mazeVisualizer.setting;

import java.awt.event.KeyEvent;

public class Setting {
	
	private Setting() {
	}
	
	/** タイトル */
	public static final String WINDOW_TITLE = "迷路";
	
	/** ウィンドウ幅 */
	public static final int WINDOW_WIDTH = 1280;
	
	/** ウィンドウ高 */
	public static final int WINDOW_HEIGHT = 720;
	
	// デフォルトキー
	public static final int DEFAULT_KEY_DECIDE = KeyEvent.VK_ENTER;
	public static final int DEFAULT_KEY_CANCEL = KeyEvent.VK_BACK_SPACE;
	public static final int DEFAULT_KEY_LIGHT = KeyEvent.VK_L;
	public static final int DEFAULT_KEY_NEXT = KeyEvent.VK_N;
	public static final int DEFAULT_KEY_AUTO = KeyEvent.VK_A;
	public static final int DEFAULT_KEY_UP = KeyEvent.VK_UP;
	public static final int DEFAULT_KEY_DOWN = KeyEvent.VK_DOWN;
	public static final int DEFAULT_KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int DEFAULT_KEY_RIGHT = KeyEvent.VK_RIGHT;
}
