package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.main.Application;
import view.modes.FileMode;

public class TitleBar extends JPanel {

	private static final long serialVersionUID = 1L;

	private int xMouse;
	private int yMouse;

	/**
	 * Create the panel.
	 */
	public TitleBar() {
		setSize(950, 20);
		setBackground(new Color(43, 128, 135));
		setLayout(null);

		JLabel btnExit = new JLabel("X");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnExit.setBounds(931, 6, 13, 13);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getFileDialog().setMode(FileMode.SAVE);
				Application.getFileDialog().setVisible(true);

				Application.getMainFrame().setEnabled(false);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});
		add(btnExit);

		JLabel lblMinimize = new JLabel("-");
		lblMinimize.setForeground(Color.WHITE);
		lblMinimize.setFont(new Font("Century Gothic", Font.PLAIN, 33));
		lblMinimize.setBounds(908, 5, 13, 10);
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				Application.getMainFrame().setState(Frame.ICONIFIED);
			}
		});
		add(lblMinimize);
		
		JLabel lblTitle = new JLabel("Paint - V5");
		lblTitle.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setVerticalTextPosition(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(425, 0, 100, 20);
		add(lblTitle);
		
		/*
		 * listeners
		 */
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				xMouse = event.getX();
				yMouse = event.getY();
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				Application.getMainFrame().setOpacity(1f);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				int xDim = event.getXOnScreen() - xMouse;
				int yDim = event.getYOnScreen() - yMouse;

				Application.getMainFrame().setLocation(xDim, yDim);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});

	}

	/**
	 * singleton class
	 */
	private static TitleBar instance = null;

	public static TitleBar getInstance() {
		synchronized (TitleBar.class) {
			if (instance == null)
				instance = new TitleBar();
		}
		return instance;
	}
}
