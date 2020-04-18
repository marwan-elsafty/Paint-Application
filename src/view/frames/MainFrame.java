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

import model.ShapesConstants;
import view.CustomCanvas;
import view.modes.CanvasMode;
import view.panels.FilePanel;
import view.panels.HomePanel;
import view.panels.PropertiesPanel;
import view.panels.TitleBar;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TitleBar titleBar;
	
	private FilePanel filePanel;
	private HomePanel homePanel;
	private PropertiesPanel propertiesPanel;
	
	private CanvasMode canvasMode = CanvasMode.SELECTOR;
	private CustomCanvas canvas;

	private int brushSize = ShapesConstants.NODE_RADIUS;
	private int eraserSize = ShapesConstants.NODE_RADIUS;

	private float strokeWidth;
	private Color strokeColor = Color.BLACK;
	private Color fillColor = null;
	private int colorTransparency = 1;

	public JLabel lblHoveredX;
	public JLabel lblHoveredY;
	
	public FilePanel getFilePanel() {
		return filePanel;
	}
	
	public HomePanel getHomePanel() {
		return homePanel;
	}

	public PropertiesPanel getPropertiesPanel() {
		return propertiesPanel;
	}
	
	public CanvasMode getCanvasMode() {
		return canvasMode;
	}

	public void setCanvasMode(CanvasMode canvasMode) {
		this.canvasMode = canvasMode;
	}

	public int getBrushSize() {
		return brushSize;
	}

	public void setBrushSize(int brushSize) {
		this.brushSize = brushSize;
	}

	public int getEraserSize() {
		return eraserSize;
	}

	public void setEraserSize(int eraserSize) {
		this.eraserSize = eraserSize;
	}

	public float getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = (float) 0.5 * strokeWidth;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public int getColorTransparency() {
		return colorTransparency;
	}

	public void setColorTransparency(int colorTransparency) {
		this.colorTransparency = colorTransparency;
	}

	public CustomCanvas getCanvas() {
		return canvas;
	}

	public JLabel getLblHoveredX() {
		return lblHoveredX;
	}

	public JLabel getLblHoveredY() {
		return lblHoveredY;
	}

	/**
	 * Create the frame.
	 */
	private MainFrame() {
		setSize(950, 650);
		setUndecorated(true);
		setResizable(false);
		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		setBackground(new Color(220, 220, 220));

		/*
		 * title bar
		 */
		titleBar = TitleBar.getInstance();
		titleBar.setLocation(0, 0);
		titleBar.setVisible(true);
		getContentPane().add(titleBar);
		
		/*
		 * file panel
		 */
		filePanel = FilePanel.getInstance();
		filePanel.setLocation(0, 40);
		filePanel.setVisible(false);
		getContentPane().add(filePanel);
		
		/*
		 * home panel
		 * starting panel
		 */
		homePanel = HomePanel.getInstance();
		homePanel.setLocation(0, 40);
		homePanel.setVisible(true);
		getContentPane().add(homePanel);

		/*
		 * properties panel
		 */
		propertiesPanel = PropertiesPanel.getInstance();
		propertiesPanel.setLocation(0, 40);
		propertiesPanel.setVisible(false);
		getContentPane().add(propertiesPanel);

		/*
		 * canvas
		 */
		canvas = CustomCanvas.getInstance();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(20, 160, 910, 470);
		getContentPane().add(canvas);

		/*
		 * menu bar
		 */
		JPanel menuBar = new JPanel();
		menuBar.setLayout(null);
		menuBar.setBackground(new Color(43, 128, 135));
		menuBar.setBounds(0, 20, 950, 20);
		getContentPane().add(menuBar);

		/*
		 * menu bar shuffle buttons
		 */
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(132, 197, 204));
		btnHome.setHorizontalAlignment(SwingConstants.CENTER);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnHome.setBounds(0, 0, 85, 20);
		btnHome.setBorder(null);
		menuBar.add(btnHome);

		JButton btnPropeties = new JButton("Propeties");
		btnPropeties.setBackground(new Color(43, 128, 135));
		btnPropeties.setHorizontalAlignment(SwingConstants.CENTER);
		btnPropeties.setForeground(Color.WHITE);
		btnPropeties.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnPropeties.setBounds(85, 0, 85, 20);
		btnPropeties.setBorder(null);
		menuBar.add(btnPropeties);
		
		JButton btnFile = new JButton("File");
		btnFile.setBackground(new Color(43, 128, 135));
		btnFile.setHorizontalAlignment(SwingConstants.CENTER);
		btnFile.setForeground(Color.WHITE);
		btnFile.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnFile.setBounds(170, 0, 85, 20);
		btnFile.setBorder(null);
		menuBar.add(btnFile);

		/*
		 * hovering coordinates
		 */
		JLabel lblX = new JLabel("x :");
		lblX.setBounds(20, 632, 16, 14);
		getContentPane().add(lblX);

		lblHoveredX = new JLabel("0");
		lblHoveredX.setBounds(36, 632, 60, 14);
		getContentPane().add(lblHoveredX);

		JLabel lblY = new JLabel("y :");
		lblY.setBounds(100, 632, 16, 14);
		getContentPane().add(lblY);

		lblHoveredY = new JLabel("0");
		lblHoveredY.setBounds(116, 632, 60, 14);
		getContentPane().add(lblHoveredY);

		/*
		 * activates home panel
		 */
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnHome.setBackground(new Color(132, 197, 204));
				btnPropeties.setBackground(new Color(43, 128, 135));
				btnFile.setBackground(new Color(43, 128, 135));

				homePanel.setVisible(true);
				propertiesPanel.setVisible(false);
				filePanel.setVisible(false);
			}
		});

		/*
		 * activates properties panel
		 */
		btnPropeties.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnHome.setBackground(new Color(43, 128, 135));
				btnPropeties.setBackground(new Color(132, 197, 204));
				btnFile.setBackground(new Color(43, 128, 135));
				
				homePanel.setVisible(false);
				propertiesPanel.setVisible(true);
				filePanel.setVisible(false);
			}
		});
		
		/*
		 * activates file panel
		 */
		btnFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnHome.setBackground(new Color(43, 128, 135));
				btnPropeties.setBackground(new Color(43, 128, 135));
				btnFile.setBackground(new Color(132, 197, 204));
				
				homePanel.setVisible(false);
				propertiesPanel.setVisible(false);
				filePanel.setVisible(true);
			}
		});
	}

	/**
	 * singleton class
	 */
	private static MainFrame instance = null;

	public static MainFrame getInstance() {
		synchronized (MainFrame.class) {
			if (instance == null)
				instance = new MainFrame();
		}
		return instance;
	}
}
