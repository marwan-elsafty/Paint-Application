package view.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.main.Application;
import view.modes.FileMode;

public class FileDialog extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	JLabel lblMessage;

	private FileMode colorChooserMode;

	public FileMode getMode() {
		return colorChooserMode;
	}

	public void setMode(FileMode colorChooserMode) {
		this.colorChooserMode = colorChooserMode;

		if (this.colorChooserMode == FileMode.SAVE) {
			this.lblMessage.setText("<HTML><center>Do you want to save your<br>canvas progress ?<center/><HTML/>");
			return;
		} else if (this.colorChooserMode == FileMode.DELETE) {
			this.lblMessage
					.setText("<HTML><center>Are you sure you want to<br>delete all your progress ?<center/><HTML/>");
			return;
		}
	}

	/**
	 * Create the frame.
	 */
	private FileDialog() {
		setSize(300, 150);
		setUndecorated(true);
		setResizable(false);
		setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(43, 128, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("X");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				setVisible(false);

				Application.getMainFrame().setEnabled(true);
				Application.getMainFrame().setVisible(true);
				Application.getMainFrame().setOpacity(1f);
			}
		});
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tunga", Font.PLAIN, 22));
		label.setBounds(278, 10, 12, 16);
		contentPane.add(label);

		JButton btnYes = new JButton("YES");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				if (getMode() == FileMode.DELETE) {
					// File file = new File(DrawingBuffer.getFileDirectory().toString());
					//
					// if (file.delete()) {
					// System.out.println("file :: deleted successfully");
					// } else {
					// System.out.println("file :: failed to delete");
					// }

					// File file = new File("E:\\XX.txt");
					//
					// if (file.delete()) {
					// System.out.println("File deleted successfully");
					// } else {
					// System.out.println("Failed to delete the file");
					// }
					
					setVisible(false);

					Application.getMainFrame().setEnabled(true);
					Application.getMainFrame().setVisible(true);
					Application.getMainFrame().setOpacity(1f);

				} else if (getMode() == FileMode.SAVE) {
					// TODO CODE HERE
					// SAVE
					
					System.exit(0);
				}
			}
		});
		btnYes.setHorizontalAlignment(SwingConstants.CENTER);
		btnYes.setForeground(Color.WHITE);
		btnYes.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnYes.setBorder(null);
		btnYes.setBackground(new Color(132, 197, 204));
		btnYes.setBounds(50, 100, 75, 20);
		contentPane.add(btnYes);

		JButton btnNo = new JButton("NO");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				if (getMode() == FileMode.DELETE) {
					setVisible(false);

					Application.getMainFrame().setEnabled(true);
					Application.getMainFrame().setVisible(true);
					Application.getMainFrame().setOpacity(1f);

				} else if (getMode() == FileMode.SAVE) {
					System.exit(0);
				}
			}
		});
		btnNo.setHorizontalAlignment(SwingConstants.CENTER);
		btnNo.setForeground(Color.WHITE);
		btnNo.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnNo.setBorder(null);
		btnNo.setBackground(new Color(132, 197, 204));
		btnNo.setBounds(175, 100, 75, 20);
		contentPane.add(btnNo);

		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblMessage.setBounds(50, 25, 200, 50);
		contentPane.add(lblMessage);
	}

	/**
	 * singleton class
	 */
	private static FileDialog instance = null;

	public static FileDialog getInstance() {
		synchronized (FileDialog.class) {
			if (instance == null)
				instance = new FileDialog();
		}
		return instance;
	}
}
