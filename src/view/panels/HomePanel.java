package view.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import controller.commander.ChangeMode;
import model.ShapesConstants;
import model.main.Application;
import view.CustomCanvas;
import view.modes.CanvasMode;
import view.modes.ColorChooserMode;
import view.modes.ResizerMode;

public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CanvasMode canvasMode = CanvasMode.SELECTOR;
	private CustomCanvas canvas = CustomCanvas.getInstance();

	private Controller controller = Controller.getInstance();

	private int brushSize = ShapesConstants.NODE_RADIUS;
	private int eraserSize = ShapesConstants.NODE_RADIUS;

	private float strokeWidth;
	private Color strokeColor = Color.BLACK;
	private Color fillColor = null;
	private int colorTransparency = 1;

	private JButton fillColorPreview;
	private JButton strokeColorPreview;

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

	public Controller getController() {
		return controller;
	}

	public JButton getStrokeColorPreview() {
		return strokeColorPreview;
	}

	public JButton getFillColorPreview() {
		return fillColorPreview;
	}

	/**
	 * Create the frame.
	 */
	private HomePanel() {
		setSize(950, 100);
		setBackground(new Color(132, 197, 204));
		setLayout(null);

		JLabel lblRedo = new JLabel();
		lblRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getController().redo();
			}
		});
		lblRedo.setToolTipText("Redo");
		lblRedo.setIcon(new ImageIcon("icons\\redo.png"));
		lblRedo.setBounds(59, 15, 24, 24);
		add(lblRedo);

		JLabel lblUndo = new JLabel();
		lblUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getController().undo();
			}
		});
		lblUndo.setToolTipText("Undo");
		lblUndo.setIcon(new ImageIcon("icons\\undo.png"));
		lblUndo.setBounds(15, 15, 24, 24);
		add(lblUndo);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(103, 10, 1, 80);
		add(separator);

		JLabel lblLine = new JLabel();
		lblLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.LINE);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblLine.setIcon(new ImageIcon("icons\\line.png"));
		lblLine.setBounds(124, 61, 24, 24);
		add(lblLine);

		JLabel lblSquare = new JLabel();
		lblSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.SQUARE);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblSquare.setIcon(new ImageIcon("icons\\square.png"));
		lblSquare.setBounds(168, 15, 24, 24);
		add(lblSquare);

		JLabel lblCircle = new JLabel();
		lblCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.CIRCLE);
				canvasMode = CanvasMode.CIRCLE;
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblCircle.setIcon(new ImageIcon("icons\\circle.png"));
		lblCircle.setBounds(212, 15, 24, 24);
		add(lblCircle);

		JLabel lblTriangle = new JLabel();
		lblTriangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.TRIANGLE);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblTriangle.setIcon(new ImageIcon("icons\\triangle.png"));
		lblTriangle.setBounds(124, 15, 24, 24);
		add(lblTriangle);

		JLabel lblEllipse = new JLabel();
		lblEllipse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.ELLIPSE);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblEllipse.setIcon(new ImageIcon("icons\\ellipse.png"));
		lblEllipse.setBounds(212, 61, 24, 24);
		add(lblEllipse);

		JLabel lblRectangle = new JLabel();
		lblRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.RECTANGLE);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblRectangle.setIcon(new ImageIcon("icons\\rectangle.png"));
		lblRectangle.setBounds(168, 61, 24, 24);
		add(lblRectangle);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(256, 10, 1, 80);
		add(separator_1);

		JLabel lblPencil = new JLabel();
		lblPencil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.BRUSH);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		lblPencil.setIcon(new ImageIcon("icons\\pencil.png"));
		lblPencil.setBounds(277, 15, 24, 24);
		add(lblPencil);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(787, 10, 1, 80);
		add(separator_2);

		JLabel lblMove = new JLabel();
		lblMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.MOVER);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		lblMove.setIcon(new ImageIcon("icons\\move.png"));
		lblMove.setBounds(852, 61, 24, 24);
		lblMove.setToolTipText("Move Shape");
		add(lblMove);

		JLabel lblEraser = new JLabel();
		lblEraser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Application.getMainFrame().setCanvasMode(CanvasMode.ERASER);
				// canvas.setCursor(new Cursor());
			}
		});
		lblEraser.setIcon(new ImageIcon("icons\\eraser.png"));
		lblEraser.setBounds(277, 61, 24, 24);
		add(lblEraser);

		JSlider sliderStrokeWidth = new JSlider();
		sliderStrokeWidth.setValue(0);
		sliderStrokeWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Application.getMainFrame().setStrokeWidth(sliderStrokeWidth.getValue());
			}
		});

		sliderStrokeWidth.setPaintTicks(true);
		sliderStrokeWidth.setMinorTickSpacing(5);
		sliderStrokeWidth.setBounds(667, 55, 100, 30);
		sliderStrokeWidth.setBackground(new Color(132, 197, 204));
		sliderStrokeWidth.setForeground(Color.WHITE);
		add(sliderStrokeWidth);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(357, 10, 1, 80);
		add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setForeground(Color.WHITE);
		separator_4.setBounds(646, 10, 1, 80);
		add(separator_4);

		JLabel lblStroke = new JLabel("Stroke");
		lblStroke.setBackground(new Color(132, 197, 204));
		lblStroke.setForeground(Color.WHITE);
		lblStroke.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblStroke.setHorizontalAlignment(SwingConstants.LEFT);
		lblStroke.setVerticalAlignment(SwingConstants.TOP);
		lblStroke.setBounds(384, 65, 63, 23);
		add(lblStroke);

		JLabel lblFill = new JLabel("Fill");
		lblFill.setHorizontalAlignment(SwingConstants.LEFT);
		lblFill.setVerticalAlignment(SwingConstants.TOP);
		lblFill.setForeground(Color.WHITE);
		lblFill.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblFill.setBounds(384, 44, 63, 23);
		add(lblFill);

		JLabel lblPalette = new JLabel();
		lblPalette.setIcon(new ImageIcon("icons\\palette.png"));
		lblPalette.setBounds(414, 15, 24, 24);
		add(lblPalette);

		fillColorPreview = new JButton();
		fillColorPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				CanvasMode canvasMode = Application.getMainFrame().getCanvasMode();
				if (canvasMode == CanvasMode.BRUSH || canvasMode == CanvasMode.ERASER
						|| canvasMode == CanvasMode.RESIZER || canvasMode == CanvasMode.MOVER) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				Application.getColorChooserFrame().setMode(ColorChooserMode.FILL_COLOR_CHOOSER);
				Application.getColorChooserFrame().setVisible(true);

				Application.getMainFrame().setEnabled(false);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});
		fillColorPreview.setBorder(null);
		fillColorPreview.setBackground(Color.WHITE);
		fillColorPreview.setEnabled(false);
		fillColorPreview.setBounds(469, 44, 16, 16);
		add(fillColorPreview);

		strokeColorPreview = new JButton();
		strokeColorPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				CanvasMode canvasMode = Application.getMainFrame().getCanvasMode();
				if (canvasMode == CanvasMode.ERASER || canvasMode == CanvasMode.RESIZER
						|| canvasMode == CanvasMode.MOVER) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				Application.getColorChooserFrame().setMode(ColorChooserMode.STROKE_COLOR_CHOOSER);
				Application.getColorChooserFrame().setVisible(true);

				Application.getMainFrame().setEnabled(false);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});
		strokeColorPreview.setBorder(null);
		strokeColorPreview.setBackground(strokeColor);
		strokeColorPreview.setEnabled(false);
		strokeColorPreview.setBounds(469, 65, 16, 16);
		add(strokeColorPreview);

		JLabel lblStrokeWidth = new JLabel("Stroke Width");
		lblStrokeWidth.setVerticalAlignment(SwingConstants.TOP);
		lblStrokeWidth.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrokeWidth.setForeground(Color.WHITE);
		lblStrokeWidth.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblStrokeWidth.setBounds(667, 25, 100, 20);
		add(lblStrokeWidth);

		JSlider sliderTransparency = new JSlider();
		sliderTransparency.setValue(100);
		sliderTransparency.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int transparencyPercentage = (int) sliderStrokeWidth.getValue() / 100;
				Application.getMainFrame().setColorTransparency((int) 255 * transparencyPercentage);
			}
		});
		sliderTransparency.setPaintTicks(true);
		sliderTransparency.setMinorTickSpacing(5);
		sliderTransparency.setForeground(Color.WHITE);
		sliderTransparency.setBackground(new Color(132, 197, 204));
		sliderTransparency.setBounds(526, 55, 100, 30);
		add(sliderTransparency);

		JLabel lblTransparency = new JLabel("Transparency");
		lblTransparency.setVerticalAlignment(SwingConstants.TOP);
		lblTransparency.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransparency.setForeground(Color.WHITE);
		lblTransparency.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblTransparency.setBounds(526, 25, 100, 20);
		add(lblTransparency);

		JLabel lblResize = new JLabel();
		lblResize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.RESIZER);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			}
		});
		lblResize.setIcon(new ImageIcon("icons\\resize.png"));
		lblResize.setBounds(808, 61, 24, 24);
		lblResize.setToolTipText("Resize Shape");
		add(lblResize);

		JLabel lblSelect = new JLabel();
		lblSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.SELECTOR);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblSelect.setIcon(new ImageIcon("icons\\cursorSelect.png"));
		lblSelect.setToolTipText("Select Shape");
		lblSelect.setBounds(808, 15, 24, 24);
		add(lblSelect);

		JLabel lblClear = new JLabel();
		lblClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Application.getController().getShapesArrayList().clear();
				Application.getController().setChangeMode(ChangeMode.NO_CHANGE);
				
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblClear.setIcon(new ImageIcon("icons\\clear.png"));
		lblClear.setBounds(15, 61, 24, 24);
		add(lblClear);

		JButton btnEraserSizePrivew = new JButton();
		btnEraserSizePrivew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getEraserSizeFrame().setMode(ResizerMode.ERASER);
				Application.getEraserSizeFrame().setVisible(true);

				Application.getMainFrame().setEnabled(false);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});
		btnEraserSizePrivew.setBounds(321, 65, 16, 16);
		btnEraserSizePrivew.setBorder(null);
		btnEraserSizePrivew.setBackground(Color.WHITE);
		add(btnEraserSizePrivew);

		JButton btnBrushSizePrivew = new JButton();
		btnBrushSizePrivew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getEraserSizeFrame().setMode(ResizerMode.BRUSH);
				Application.getEraserSizeFrame().setVisible(true);

				Application.getMainFrame().setEnabled(false);
				Application.getMainFrame().setOpacity(0.75f);
			}
		});
		btnBrushSizePrivew.setBorder(null);
		btnBrushSizePrivew.setBackground(Color.WHITE);
		btnBrushSizePrivew.setBounds(321, 21, 16, 16);
		add(btnBrushSizePrivew);
		
		JLabel lblRemove = new JLabel();
		lblRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.REMOVER);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));				
			}
		});
		lblRemove.setIcon(new ImageIcon("icons\\cursorRemove.png"));
		lblRemove.setToolTipText("Remove Shape");
		lblRemove.setBounds(852, 15, 24, 24);
		add(lblRemove);
		
		JLabel lblCopy = new JLabel();
		lblCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				Application.getMainFrame().setCanvasMode(CanvasMode.DUPLICATOR);
				Application.getMainFrame().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));				
			}
		});
		lblCopy.setIcon(new ImageIcon("icons\\cursorAdd.png"));
		lblCopy.setToolTipText("Duplicate Shape");
		lblCopy.setBounds(896, 15, 24, 24);
		add(lblCopy);
	}

	/**
	 * singleton class
	 */
	private static HomePanel instance = null;

	public static HomePanel getInstance() {
		synchronized (HomePanel.class) {
			if (instance == null)
				instance = new HomePanel();
		}
		return instance;
	}
}
