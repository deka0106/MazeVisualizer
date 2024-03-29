package mazeVisualizer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import mazeVisualizer.graphic.ImageManager;
import mazeVisualizer.input.KeyManager;
import mazeVisualizer.input.MouseManager;
import mazeVisualizer.scene.Scene;
import mazeVisualizer.scene.SceneTitle;
import mazeVisualizer.setting.Setting;

public class Main {
	
	/** ウィンドウ */
	private static JFrame window;
	
	/** ダブルバッファリング用 */
	private static BufferedImage buffer;
	
	/** 初期化 */
	private void init() {
		
		/* リソース読み込み */
		loadContents();
		
		/* ウィンドウ初期化 */
		initWindow();
		
		Scene.changeScene(SceneTitle.class);
		Scene.getCurrentScene().init();
	}
	
	/** ウィンドウ初期化 */
	private void initWindow() {
		window = new JFrame(Setting.WINDOW_TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setPreferredSize(new Dimension(Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT));
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		
		window.setLocationRelativeTo(null);
		
		window.addKeyListener(KeyManager.getListener());
		window.addMouseListener(MouseManager.getListener());
		window.addMouseMotionListener(MouseManager.getListener());
		
		buffer = new BufferedImage(Setting.WINDOW_WIDTH, Setting.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	/** リソース読み込み */
	private void loadContents() {
		
		/* 画像読み込み */
		ImageManager.init();
		String[] imgItems = {
				"title", "goal", "player"
		};
		for (int i = 0; i < imgItems.length; i++) {
			ImageManager.loadImage(imgItems[i] + ".png");
		}
		
		ImageManager.loadSplitImages("map.png", 16, 16);
		ImageManager.loadSplitImages("sg.png", 32, 32);
	}
	
	/** 実行 */
	private void run() {
		// 初期化
		init();
		
		// fps設定
		int fps = 60;
		long error = 0;
		long idealSleep = (1000 << 16) / fps;
		long lastTime;
		long currentTime = System.currentTimeMillis() << 16;
		while (true) {
			lastTime = currentTime;
			
			// 更新
			update();
			
			// 描画
			draw();
			
			currentTime = System.currentTimeMillis() << 16;
			long sleepTime = idealSleep - (currentTime - lastTime) - error;
			if (sleepTime < (2 << 16)) sleepTime = (2 << 16);
			lastTime = currentTime;
			try {
				Thread.sleep(sleepTime >> 16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime = System.currentTimeMillis() << 16;
			error = currentTime - lastTime - sleepTime;
		}
	}
	
	/** 更新 */
	private void update() {
		Scene.getCurrentScene().update();
		KeyManager.update();
		MouseManager.update();
	}
	
	/** 描画 */
	private void draw() {
		Graphics2D g = buffer.createGraphics();
		g.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
		
		/* 描画処理 */
		Scene.getCurrentScene().draw(g);
		
		((Graphics2D) window.getContentPane().getGraphics()).drawImage(buffer, 0, 0, null);
		g.dispose();
	}
	
	/** メイン */
	public static void main(String[] args) {
		new Main().run();
	}
	
	public static void setTitle(String title) {
		window.setTitle(title);
	}
	
}
