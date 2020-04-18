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
import model.util.ColorGenerator;
import view.modes.ColorChooserMode;

public class ColorChooserFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private int numColors = 70;
	private Color[] colorPalette = ColorGenerator.createGradient(numColors);

	private JLabel lblTitle;

	private ColorChooserMode colorChooserMode;

	public ColorChooserMode getMode() {
		return colorChooserMode;
	}

	public void setMode(ColorChooserMode colorChooserMode) {
		this.colorChooserMode = colorChooserMode;

		if (this.colorChooserMode == ColorChooserMode.STROKE_COLOR_CHOOSER) {
			this.lblTitle.setText("Stroke Color");
			return;
		} else if (this.colorChooserMode == ColorChooserMode.FILL_COLOR_CHOOSER) {
			this.lblTitle.setText("Fill Color");
			return;
		}
	}

	/**
	 * Create the frame.
	 */
	private ColorChooserFrame() {
		setSize(200, 180);
		setUndecorated(true);
		setResizable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(220, 220, 220));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(43, 128, 135));
		panel.setBounds(0, 0, 200, 20);
		contentPane.add(panel);

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
		lblExit.setBounds(185, 3, 12, 16);
		panel.add(lblExit);

		lblTitle = new JLabel();
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 200, 20);
		panel.add(lblTitle);

		JPanel colorPalettePanel = new JPanel();
		colorPalettePanel.setBounds(0, 20, 200, 160);
		colorPalettePanel.setBackground(new Color(220, 220, 220));
		contentPane.add(colorPalettePanel);
		colorPalettePanel.setLayout(null);

		int NEW_ROW;
		int x = NEW_ROW = 10;
		int y = 10;

		/*
		 *  colors gradient
		 */
		for (int i = 0; i < numColors; i++) {
			JButton btnColor = new JButton();
			btnColor.setBorder(null);
			btnColor.setBounds(x, y, 16, 16);
			btnColor.setBackground(colorPalette[i]);

			x += 18;
			if (i != 0 && (i + 1) % 10 == 0) {
				y += 18;
				x = NEW_ROW;
			}

			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if (getMode() == ColorChooserMode.STROKE_COLOR_CHOOSER) {
						Application.getMainFrame().setStrokeColor(btnColor.getBackground());
						Application.getMainFrame().getHomePanel().getStrokeColorPreview().setBackground(btnColor.getBackground());
					} else if (getMode() == ColorChooserMode.FILL_COLOR_CHOOSER) {
						Application.getMainFrame().setFillColor(btnColor.getBackground());
						Application.getMainFrame().getHomePanel().getFillColorPreview().setBackground(btnColor.getBackground());
					}
					return;
				}

				@Override
				public void mouseExited(MouseEvent mouseEvent) {
					btnColor.setSize(16, 16);
					btnColor.setBorder(null);
				}
			});

			btnColor.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent mouseEvent) {
					btnColor.setSize(24, 24);

					btnColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			});

			colorPalettePanel.add(btnColor);
			repaint();
		}

		/*
		 *  black to white gradient
		 */
		int RGB = 0;
		for (int i = 0; i < 10; i++) {
			final JButton btnColor = new JButton();
			btnColor.setBorder(null);
			btnColor.setBounds(x, y, 16, 16);
			btnColor.setBackground(new Color(RGB, RGB, RGB));

			if (i == 8)
				btnColor.setBackground(Color.WHITE);

			if (i == 9)
				btnColor.setBackground(new Color(1f, 1f, 1f));

			x += 18;
			RGB += 26;

			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if (getMode() == ColorChooserMode.STROKE_COLOR_CHOOSER) {
						Application.getMainFrame().setStrokeColor(btnColor.getBackground());
						Application.getMainFrame().getHomePanel().getStrokeColorPreview().setBackground(btnColor.getBackground());
					} else if (getMode() == ColorChooserMode.FILL_COLOR_CHOOSER) {
						Application.getMainFrame().setFillColor(btnColor.getBackground());
						Application.getMainFrame().getHomePanel().getFillColorPreview().setBackground(btnColor.getBackground());
					}
					return;
				}

				@Override
				public void mouseExited(MouseEvent mouseEvent) {
					btnColor.setSize(16, 16);
					btnColor.setBorder(null);
				}
			});

			btnColor.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent mouseEvent) {
					btnColor.setSize(24, 24);
					btnColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			});

			colorPalettePanel.add(btnColor);
			repaint();
		}
	}

	/**
	 * singleton class
	 */
	private static ColorChooserFrame instance = null;

	public static ColorChooserFrame getInstance() {
		synchronized (ColorChooserFrame.class) {
			if (instance == null)
				instance = new ColorChooserFrame();
		}
		return instance;
	}
}
