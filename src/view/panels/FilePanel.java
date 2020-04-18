package view.panels;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import model.drawingbuffer.DrawingBuffer;
import model.main.Application;
import view.modes.FileMode;

public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public FilePanel() {
		setSize(950, 100);
		setBackground(new Color(132, 197, 204));
		setLayout(null);

		JLabel lblSave = new JLabel();
		lblSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				System.out.println("X");
				// TODO CODE HERE
				if (DrawingBuffer.hasFileDirectory()) {
					DrawingBuffer.getFileDirectory();

				} else {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Save");
					fileChooser.showSaveDialog(null);

					new DrawingBuffer().save(fileChooser.getSelectedFile().getAbsolutePath().toString());
				}
			}
		});
		lblSave.setToolTipText("Save");
		lblSave.setIcon(new ImageIcon("icons\\saveFile.png"));
		lblSave.setBounds(59, 15, 24, 24);
		add(lblSave);

		JLabel lblOpenFile = new JLabel();
		lblOpenFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				// TODO CODE HERE
			}
		});
		lblOpenFile.setToolTipText("Open File");
		lblOpenFile.setIcon(new ImageIcon("icons\\openFolder.png"));
		lblOpenFile.setBounds(15, 15, 24, 24);
		add(lblOpenFile);

		JLabel lblDelete = new JLabel();
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Toolkit.getDefaultToolkit().beep();

				Application.getFileDialog().setMode(FileMode.DELETE);
				Application.getFileDialog().setVisible(true);

				Application.getMainFrame().setEnabled(false);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});
		lblDelete.setToolTipText("Delete");
		lblDelete.setIcon(new ImageIcon("icons\\delete.png"));
		lblDelete.setBounds(103, 15, 24, 24);
		add(lblDelete);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(147, 10, 1, 80);
		add(separator);
	}

	/**
	 * singleton class
	 */
	private static FilePanel instance = null;

	public static FilePanel getInstance() {
		synchronized (FilePanel.class) {
			if (instance == null)
				instance = new FilePanel();
		}
		return instance;
	}
}
