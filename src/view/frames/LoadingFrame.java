package view.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.CustomCanvas;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblLoadedPercent;

	public JLabel getLblLoadedPercent() {
		return lblLoadedPercent;
	}

	/**
	 * Create the frame.
	 */
	private LoadingFrame() {
		setSize(400, 250);
		setUndecorated(true);
		setResizable(false);
		setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(43, 128, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * to be dynamically changed
		 */
		lblLoadedPercent = new JLabel("99");
		lblLoadedPercent.setForeground(Color.WHITE);
		lblLoadedPercent.setFont(new Font("Forte", Font.PLAIN, 16));
		lblLoadedPercent.setBounds(211, 200, 20, 30);
		contentPane.add(lblLoadedPercent);

		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tunga", Font.PLAIN, 22));
		lblExit.setBounds(374, 11, 16, 16);
		contentPane.add(lblExit);

		JLabel lblTitle = new JLabel("<html>Paint<br>&<br>Draw</html>");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Forte", Font.PLAIN, 35));
		lblTitle.setBounds(15, 0, 120, 150);
		contentPane.add(lblTitle);

		JLabel lblLoading = new JLabel("Loading        %");
		lblLoading.setForeground(Color.WHITE);
		lblLoading.setFont(new Font("Forte", Font.PLAIN, 16));
		lblLoading.setBounds(150, 200, 100, 30);
		contentPane.add(lblLoading);

		JLabel lblBackground = new JLabel();
		lblBackground.setIcon(new ImageIcon("icons\\loading.jpg"));
		lblBackground.setBounds(0, 0, 400, 250);
		contentPane.add(lblBackground);
	}

	/*
	 * singleton class
	 */
	private static LoadingFrame instance = null;

	public static LoadingFrame getInstance() {
		synchronized (CustomCanvas.class) {
			if (instance == null)
				instance = new LoadingFrame();
		}
		return instance;
	}
}
