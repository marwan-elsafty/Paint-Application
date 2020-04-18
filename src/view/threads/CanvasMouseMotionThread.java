package view.threads;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.main.Application;

public class CanvasMouseMotionThread extends Thread {

	public void run() {
		Application.getMainFrame().getCanvas().addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent mouseEvent) {
				return;
			}
			
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				Application.getMainFrame().lblHoveredX.setText(mouseEvent.getX() + "");
				Application.getMainFrame().lblHoveredY.setText(mouseEvent.getY() + "");
			}
		});
	}
}
