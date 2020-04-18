package view.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.main.Application;
import view.modes.ResizerMode;

public class ReizerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	JLabel lblTitle;

	private ResizerMode resizerMode;

	public ResizerMode getMode() {
		return resizerMode;
	}

	public void setMode(ResizerMode resizerMode) {
		this.resizerMode = resizerMode;

		if (this.resizerMode == ResizerMode.BRUSH) {
			this.lblTitle.setText("Brush Size");
			return;
		} else if (this.resizerMode == ResizerMode.ERASER) {
			this.lblTitle.setText("Eraser Size");
			return;
		}
	}

	/**
	 * Create the frame.
	 */
	private ReizerFrame() {
		setSize(240, 100);
		setUndecorated(true);
		setResizable(false);
		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(220, 220, 220));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBackground(new Color(43, 128, 135));
		titlePanel.setBounds(0, 0, 240, 20);
		contentPane.add(titlePanel);

		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);

				Application.getMainFrame().setEnabled(true);
				Application.getMainFrame().setVisible(true);
				Application.getMainFrame().setOpacity(1f);
			}
		});
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Verdana", Font.BOLD, 12));
		lblExit.setBounds(225, 3, 12, 16);
		titlePanel.add(lblExit);

		lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblTitle.setBounds(0, 0, 240, 20);
		titlePanel.add(lblTitle);

		JPanel eraserSizePanel = new JPanel();
		eraserSizePanel.setBounds(0, 20, 240, 80);
		eraserSizePanel.setBackground(new Color(220, 220, 220));
		contentPane.add(eraserSizePanel);
		eraserSizePanel.setLayout(null);

		int x = 15;
		int y = 15;
		int btnSize = 50;
		for (int i = 0; i < 5; i++) {
			JButton btnEraser = new JButton();
			btnEraser.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if (getMode() == ResizerMode.BRUSH)
						Application.getMainFrame().setBrushSize(btnEraser.getWidth());
					else if (getMode() == ResizerMode.ERASER)
						Application.getMainFrame().setEraserSize(btnEraser.getWidth());
					return;
				}
			});
			btnEraser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			btnEraser.setBackground(Color.WHITE);
			btnEraser.setBounds(x, y, btnSize, btnSize);
			eraserSizePanel.add(btnEraser);

			x += btnSize + 15;
			y += 5;
			btnSize -= 10;
		}
	}

	/**
	 * singleton class
	 */
	private static ReizerFrame instance = null;

	public static ReizerFrame getInstance() {
		synchronized (ReizerFrame.class) {
			if (instance == null)
				instance = new ReizerFrame();
		}
		return instance;
	}
}
