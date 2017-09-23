package paintClone;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class Tools extends JPanel {

	/**
	 * Create the panel.
	 */
	
	protected JButton btnDraw;
	protected JButton btnShapes;
	protected boolean shapesOrDraw;
	private JRadioButton rdbtnCircle;
	private JRadioButton rdbtnSquare;
	private JRadioButton rdbtnTriangle;
	private JLabel lblShapes;
	private JButton btnColors;
	private JPanel pnlCurrentColor;
	private JSpinner spinner;
	private JButton btnEraser;
	public Tools() {
		setLayout(null);
		setBounds(0,0,800,100);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 800, 100);
		add(tabbedPane);
		
		/**
		 * Home Tab Starts
		 */
		JPanel pnlHome = new JPanel();
		tabbedPane.addTab("Home", null, pnlHome, null);
		pnlHome.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		pnlCurrentColor = new JPanel();
		pnlCurrentColor.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlCurrentColor.setBackground(Canvas.penAndShapeColor);
		pnlHome.add(pnlCurrentColor);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(5, 5, 20, 1));
		pnlHome.add(spinner);
		spinner.addChangeListener((e) -> {
			Canvas.strokeSize = (Integer)spinner.getValue();
		});
		
		btnEraser = new JButton("Eraser");
		btnEraser.addActionListener((event) -> {
			btnEraser.setEnabled(false);
			btnDraw.setEnabled(true);
			btnShapes.setEnabled(true);
			Canvas.shapeOn = false;
			Canvas.penAndShapeColor = Color.WHITE;
		});
		pnlHome.add(btnEraser);
		
		btnDraw = new JButton("Pencil");
		btnDraw.setEnabled(false);
		pnlHome.add(btnDraw);
		btnDraw.addActionListener((event) -> {
			btnDraw.setEnabled(false);
			btnShapes.setEnabled(true);
			btnEraser.setEnabled(true);
			Canvas.shapeOn = false;
			Canvas.strokeSize = (Integer)spinner.getValue();
			Canvas.penAndShapeColor = pnlCurrentColor.getBackground();
		});
		
		btnColors = new JButton("Colors");
		btnColors.addActionListener((event) -> {
			Color prev = pnlCurrentColor.getBackground();
			Canvas.penAndShapeColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
			if(Canvas.penAndShapeColor == null) Canvas.penAndShapeColor = prev;
			pnlCurrentColor.setBackground(Canvas.penAndShapeColor);
		});
		pnlHome.add(btnColors);
		
		btnShapes = new JButton("Shapes");
		pnlHome.add(btnShapes);
		btnShapes.addActionListener((event) -> {
			btnShapes.setEnabled(false);
			btnDraw.setEnabled(true);
			btnEraser.setEnabled(true);
			Canvas.shapeOn = true;
		});
		
		/*
		 * Button Group for shapes starts
		 */
		
		ButtonGroup shapes = new ButtonGroup();
		
		lblShapes = new JLabel("Shapes: ");
		lblShapes.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlHome.add(lblShapes);
		
		rdbtnCircle = new JRadioButton("Circle");
		rdbtnCircle.addActionListener((event) -> {
			JRadioButton rb = (JRadioButton) event.getSource();
			Canvas.shapeType = rb.getText();
		});
		rdbtnCircle.setSelected(true);
		shapes.add(rdbtnCircle);
		pnlHome.add(rdbtnCircle);
		
		rdbtnSquare = new JRadioButton("Square");
		rdbtnSquare.addActionListener((event) -> {
			JRadioButton rb = (JRadioButton) event.getSource();
			Canvas.shapeType = rb.getText();
		});
		shapes.add(rdbtnSquare);
		pnlHome.add(rdbtnSquare);
		
		rdbtnTriangle = new JRadioButton("Triangle");
		rdbtnTriangle.addActionListener((event) -> {
			JRadioButton rb = (JRadioButton) event.getSource();
			Canvas.shapeType = rb.getText();
		});
		shapes.add(rdbtnTriangle);
		pnlHome.add(rdbtnTriangle);
		

	}
}
