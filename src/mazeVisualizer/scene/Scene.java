package mazeVisualizer.scene;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public abstract class Scene {
	
	/** 現在のシーン */
	private static Scene currentScene = null;
	
	/** 初期化 */
	public void init() {
	}
	
	/** 描画 */
	public abstract void update();
	
	/** 更新 */
	public abstract void draw(Graphics2D g);
	
	/**
	 * Scene変更
	 *
	 * @param oclass Scene継承クラス
	 */
	public static void changeScene(Class<? extends Scene> oclass) {
		currentScene = getInstance(oclass);
	}
	
	/**
	 *
	 * @return 現在のシーン
	 */
	public static Scene getCurrentScene() {
		return currentScene;
	}
	
	/*
	 * サブクラス対応 シングルトーンパターン
	 */
	private static Map<Class<? extends Scene>, Scene> classToInstance = new HashMap<>();
	private final static Object lock = new Object();
	
	protected Scene() {
		synchronized (lock) {
			Class<? extends Scene> oclass = this.getClass();
			if (classToInstance.get(oclass) != null) {
				throw new RuntimeException("Already created: " + oclass);
			}
			classToInstance.put(oclass, this);
		}
	}
	
	/**
	 *
	 * @param oclass Scene継承クラス
	 * @return Sceneインスタンス
	 */
	public static Scene getInstance(Class<? extends Scene> oclass) {
		synchronized (lock) {
			Scene obj = classToInstance.get(oclass);
			if (obj == null) {
				try {
					obj = oclass.newInstance();
				} catch (IllegalAccessException e) {
					throw new RuntimeException(oclass + " cannot be accessed.");
				} catch (InstantiationException e) {
					throw new RuntimeException(oclass + " cannot be instantiated.");
				}
			}
			return obj;
		}
	}
}
