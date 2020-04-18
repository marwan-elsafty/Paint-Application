package model.main;

import view.frames.MainFrame;
import view.frames.FileDialog;
import controller.Controller;
import view.frames.ColorChooserFrame;
import view.frames.ReizerFrame;
import view.frames.LoadingFrame;

public final class Application extends Thread {

	private static MainFrame mainFrame;
	private static ColorChooserFrame colorChooserFrame;
	private static ReizerFrame reizerFrame;
	private static FileDialog fileDialog;

	private static Controller controller;

	public final static MainFrame getMainFrame() {
		return mainFrame;
	}

	public final static ColorChooserFrame getColorChooserFrame() {
		return colorChooserFrame;
	}

	public final static ReizerFrame getEraserSizeFrame() {
		return reizerFrame;
	}

	public final static FileDialog getFileDialog() {
		return fileDialog;
	}
	
	public final static Controller getController() {
		return controller;
	}

	public void run() {
		LoadingFrame loadingFrame = LoadingFrame.getInstance();
		loadingFrame.setVisible(true);

		mainFrame = MainFrame.getInstance();
		fileDialog = FileDialog.getInstance();
		colorChooserFrame = ColorChooserFrame.getInstance();
		reizerFrame = ReizerFrame.getInstance();
		
		controller = Controller.getInstance();

		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {

			}
			loadingFrame.getLblLoadedPercent().setText(Integer.toString(i));
		}

		loadingFrame.dispose();

		mainFrame.setVisible(true);
	}
}