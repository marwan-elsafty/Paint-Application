package view.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblShapeName;
	
	private JTextField txtFieldLength;
	private JTextField txtFieldWidth;
	private JTextField txtFieldMajorAxe;
	private JTextField txtFieldMinorAxe;
	private JTextField txtFieldRadius;

	private JButton strokeColorPreview;
	private JButton fillColorPreview;

	public JLabel getLabelShapeName() {
		return lblShapeName;
	}
	
	public JTextField getTxtFieldLength() {
		return txtFieldLength;
	}

	public JTextField getTxtFieldWidth() {
		return txtFieldWidth;
	}

	public JTextField getTxtFieldMajorAxe() {
		return txtFieldMajorAxe;
	}

	public JTextField getTxtFieldMinorAxe() {
		return txtFieldMinorAxe;
	}

	public JTextField getTxtFieldRadius() {
		return txtFieldRadius;
	}

	public JButton getStrokeColorPreview() {
		return strokeColorPreview;
	}

	public JButton getFillColorPreview() {
		return fillColorPreview;
	}

	/**
	 * Create the panel.
	 */
	private PropertiesPanel() {
		setSize(950, 100);
		setBackground(new Color(132, 197, 204));
		setLayout(null);

		lblShapeName = new JLabel("Shape.");
		lblShapeName.setVerticalAlignment(SwingConstants.TOP);
		lblShapeName.setHorizontalAlignment(SwingConstants.LEFT);
		lblShapeName.setForeground(Color.WHITE);
		lblShapeName.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblShapeName.setBounds(15, 10, 100, 20);
		add(lblShapeName);

		JLabel lblLength = new JLabel("Length : ");
		lblLength.setVerticalAlignment(SwingConstants.TOP);
		lblLength.setHorizontalAlignment(SwingConstants.LEFT);
		lblLength.setForeground(Color.WHITE);
		lblLength.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblLength.setBounds(15, 40, 100, 20);
		add(lblLength);

		JLabel lblWidth = new JLabel("Width :");
		lblWidth.setVerticalAlignment(SwingConstants.TOP);
		lblWidth.setHorizontalAlignment(SwingConstants.LEFT);
		lblWidth.setForeground(Color.WHITE);
		lblWidth.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblWidth.setBounds(15, 65, 100, 20);
		add(lblWidth);

		txtFieldLength = new JTextField();
		txtFieldLength.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		txtFieldLength.setEnabled(false);
		txtFieldLength.setColumns(10);
		txtFieldLength.setBounds(115, 40, 100, 20);
		add(txtFieldLength);

		txtFieldWidth = new JTextField();
		txtFieldWidth.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		txtFieldWidth.setEnabled(false);
		txtFieldWidth.setColumns(10);
		txtFieldWidth.setBounds(115, 65, 100, 20);
		add(txtFieldWidth);

		JLabel lblMajorAxe = new JLabel("Major Axe :");
		lblMajorAxe.setVerticalAlignment(SwingConstants.TOP);
		lblMajorAxe.setHorizontalAlignment(SwingConstants.LEFT);
		lblMajorAxe.setForeground(Color.WHITE);
		lblMajorAxe.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblMajorAxe.setBounds(250, 40, 100, 20);
		add(lblMajorAxe);

		JLabel lblMinorAxe = new JLabel("Minor Axe :");
		lblMinorAxe.setVerticalAlignment(SwingConstants.TOP);
		lblMinorAxe.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinorAxe.setForeground(Color.WHITE);
		lblMinorAxe.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblMinorAxe.setBounds(250, 65, 100, 20);
		add(lblMinorAxe);

		txtFieldMajorAxe = new JTextField();
		txtFieldMajorAxe.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		txtFieldMajorAxe.setEnabled(false);
		txtFieldMajorAxe.setColumns(10);
		txtFieldMajorAxe.setBounds(350, 40, 100, 20);
		add(txtFieldMajorAxe);

		txtFieldMinorAxe = new JTextField();
		txtFieldMinorAxe.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		txtFieldMinorAxe.setEnabled(false);
		txtFieldMinorAxe.setColumns(10);
		txtFieldMinorAxe.setBounds(350, 65, 100, 20);
		add(txtFieldMinorAxe);

		JLabel lblRadius = new JLabel("Radius : ");
		lblRadius.setVerticalAlignment(SwingConstants.TOP);
		lblRadius.setHorizontalAlignment(SwingConstants.LEFT);
		lblRadius.setForeground(Color.WHITE);
		lblRadius.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblRadius.setBounds(485, 40, 100, 20);
		add(lblRadius);

		txtFieldRadius = new JTextField();
		txtFieldRadius.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		txtFieldRadius.setEnabled(false);
		txtFieldRadius.setColumns(10);
		txtFieldRadius.setBounds(585, 40, 100, 20);
		add(txtFieldRadius);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(720, 10, 1, 80);
		add(separator);

		JLabel lblFillColor = new JLabel("Fill Color :");
		lblFillColor.setVerticalAlignment(SwingConstants.TOP);
		lblFillColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblFillColor.setForeground(Color.WHITE);
		lblFillColor.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblFillColor.setBounds(771, 40, 100, 20);
		add(lblFillColor);

		JLabel lblStrokeColor = new JLabel("Stroke Color :");
		lblStrokeColor.setVerticalAlignment(SwingConstants.TOP);
		lblStrokeColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblStrokeColor.setForeground(Color.WHITE);
		lblStrokeColor.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblStrokeColor.setBounds(771, 65, 100, 20);
		add(lblStrokeColor);

		fillColorPreview = new JButton();
		fillColorPreview.setEnabled(false);
		fillColorPreview.setBorder(null);
		fillColorPreview.setBackground(Color.WHITE);
		fillColorPreview.setBounds(881, 40, 16, 16);
		add(fillColorPreview);

		strokeColorPreview = new JButton();
		strokeColorPreview.setEnabled(false);
		strokeColorPreview.setBorder(null);
		strokeColorPreview.setBackground(Color.BLACK);
		strokeColorPreview.setBounds(881, 65, 16, 16);
		add(strokeColorPreview);
	}

	/**
	 * singleton class
	 */
	private static PropertiesPanel instance = null;

	public static PropertiesPanel getInstance() {
		synchronized (PropertiesPanel.class) {
			if (instance == null)
				instance = new PropertiesPanel();
		}
		return instance;
	}
}
