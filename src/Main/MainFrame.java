package Main;



//Импортируются классы, используемые в приложении
import java.awt.BorderLayout;				
import java.util.AbstractCollection;		
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import javax.swing.ImageIcon;
@SuppressWarnings("serial")	

public class MainFrame extends JFrame {

	// Размеры окна приложения в виде констант
	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;
	// Текстовые поля для считывания значений переменных,
	// как компоненты, совместно используемые в различных методах
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	// Текстовое поле для отображения результата,
	// как компонент, совместно используемый в различных методах
	private JTextField textFieldResult;
	private JTextField textFieldMem;
	// Группа радио-кнопок для обеспечения уникальности выделения в группе
	private ButtonGroup radioButtons = new ButtonGroup();
	// Контейнер для отображения радио-кнопок
	private Box hboxFormulaType = Box.createHorizontalBox();
	private int formulaId = 1;
	//Переменная для памяти
	public int active=1;
	public Double result;
	public Double m1=0.0;
	public Double m2=0.0;
	public Double m3=0.0;
	//
	ImageIcon imageIcon1 = new ImageIcon("C:/Users/Dirov/eclipse-workspace/laba2/src/Formula1.jpg");
	ImageIcon imageIcon2 = new ImageIcon("C:/Users/Dirov/eclipse-workspace/laba2/src/Formula2.jpg");
	JLabel labelForImage = new JLabel(imageIcon1);

	// Формула №1 для рассчѐта
	public Double calculate1(Double x, Double y, Double z) {
	return (Math.sin(Math.PI * Math.pow(y, 2)) + Math.log(Math.pow(y, 2))) /
            (Math.sin(Math.PI * Math.pow(z, 2)) + Math.sin(x) + Math.log(Math.pow(z, 2)) + Math.pow(x, 2) + Math.exp(x));
	}
	// Формула №2 для рассчѐта
	public Double calculate2(Double x, Double y, Double z) {
	return Math.sqrt(Math.exp(y) + Math.exp(Math.pow(x, 2)) + Math.sqrt(1 / x)) /
            Math.pow((Math.cos(Math.PI * Math.pow(z, 3)) + Math.pow(Math.log(1 + z), 2)), Math.sin(y));
	}
	// Вспомогательный метод для добавления кнопок на панель
	private void addRadioButton(String buttonName, final int formulaId) {
	JRadioButton button = new JRadioButton(buttonName);
	button.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
	MainFrame.this.formulaId = formulaId;
	switch(formulaId) { 
	case 1: labelForImage.setIcon(imageIcon1);
	break;
	case 2: labelForImage.setIcon(imageIcon2);
	break;
	}
	
	
	}
	});
	radioButtons.add(button);
	hboxFormulaType.add(button);
	}
	
	// Конструктор класса
	public MainFrame() {
	super("Вычисление формулы");
	setSize(WIDTH, HEIGHT);
	Toolkit kit = Toolkit.getDefaultToolkit();
	// Отцентрировать окно приложения на экране
	setLocation((kit.getScreenSize().width - WIDTH)/2,
	(kit.getScreenSize().height - HEIGHT)/2);
	hboxFormulaType.add(Box.createHorizontalGlue());

	addRadioButton("Формула 1", 1);
	addRadioButton("Формула 2", 2);
	radioButtons.setSelected(
	radioButtons.getElements().nextElement().getModel(), true);
	hboxFormulaType.add(Box.createHorizontalGlue());
	hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
	// Создать область с полями ввода для X и Y
	JLabel labelForX = new JLabel("X:");
	textFieldX = new JTextField("", 10);
	textFieldX.setMaximumSize(textFieldX.getPreferredSize());
	JLabel labelForY = new JLabel("Y:");
	textFieldY = new JTextField("", 10);
	textFieldY.setMaximumSize(textFieldY.getPreferredSize());
	JLabel labelForZ = new JLabel("Z:");
	textFieldZ = new JTextField("", 10);
	textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
	Box hboxVariables = Box.createHorizontalBox();
	hboxVariables.setBorder(
	BorderFactory.createLineBorder(Color.RED));
	hboxVariables.add(labelForX);
	hboxVariables.add(Box.createHorizontalStrut(10));
	hboxVariables.add(textFieldX);
	hboxVariables.add(Box.createHorizontalGlue());
	hboxVariables.add(labelForY);
	hboxVariables.add(Box.createHorizontalStrut(10));
	hboxVariables.add(textFieldY);
	hboxVariables.add(Box.createHorizontalGlue());
	hboxVariables.add(labelForZ);
	hboxVariables.add(Box.createHorizontalStrut(10));
	hboxVariables.add(textFieldZ);
	// Создать область для вывода результата
	JLabel labelForResult = new JLabel("Результат:");
	//labelResult = new JLabel("0");
	textFieldResult = new JTextField("0", 20);
	textFieldResult.setMaximumSize(
	textFieldResult.getPreferredSize());
	Box hboxResult = Box.createHorizontalBox();
	hboxResult.add(Box.createHorizontalGlue());
	hboxResult.add(labelForResult);
	hboxResult.add(Box.createHorizontalStrut(10));
	hboxResult.add(textFieldResult);
	hboxResult.add(Box.createHorizontalGlue());
	hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	//область для памяти
	JLabel labelForMem = new JLabel("mem:");
	textFieldMem = new JTextField("0", 20);
	textFieldMem.setMaximumSize(
	textFieldMem.getPreferredSize());
	textFieldMem.setEditable(false);
	Box hboxMem = Box.createHorizontalBox();
	hboxMem.add(Box.createHorizontalGlue());
	hboxMem.add(labelForMem);
	hboxMem.add(Box.createHorizontalStrut(10));
	hboxMem.add(textFieldMem);
	hboxMem.add(Box.createHorizontalGlue());
	hboxMem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	
	//картинка
	
	Box hboxImage = Box.createHorizontalBox();
	hboxImage.add(Box.createHorizontalGlue());
	hboxImage.add(labelForImage);
	hboxImage.add(Box.createHorizontalGlue());
	hboxImage.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	
	
	
	
	// Создать область для кнопок
	JButton buttonCalc = new JButton("Вычислить");
	buttonCalc.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
	try {Double x = Double.parseDouble(textFieldX.getText());
	Double y = Double.parseDouble(textFieldY.getText());
	Double z = Double.parseDouble(textFieldZ.getText());
	if (formulaId==1)
	result = calculate1(x, y, z);
	else
	result = calculate2(x, y, z);
	textFieldResult.setText(result.toString());
	} catch (NumberFormatException ex) {
	JOptionPane.showMessageDialog(MainFrame.this,
	"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
	JOptionPane.WARNING_MESSAGE);
	}
	}
	});
	JButton buttonReset = new JButton("Очистить поля");
	buttonReset.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
	textFieldX.setText("0");
	textFieldY.setText("0");
	textFieldZ.setText("0");
	textFieldResult.setText("0");
	result=0.0;
	}
	});
	Box hboxButtons = Box.createHorizontalBox();
	hboxButtons.add(Box.createHorizontalGlue());
	hboxButtons.add(buttonCalc);
	hboxButtons.add(Box.createHorizontalStrut(30));
	hboxButtons.add(buttonReset);
	hboxButtons.add(Box.createHorizontalGlue());
	hboxButtons.setBorder(
	BorderFactory.createLineBorder(Color.GREEN));
	//Кнопки MC и M+
	JButton buttonMp = new JButton("M+");
	buttonMp.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		switch (active) {
		case 1:
			m1+=result;
			break;
		case 2:
			m2+=result;
			break;
		case 3:
			m3+= result;
			break;
			
		}
		
		
	}
	});
	
	JButton buttonMem1 = new JButton("mem1");
	buttonMem1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		active=1;
		textFieldMem.setText(m1.toString());
	}
	});
	
	JButton buttonMem2 = new JButton("mem2");
	buttonMem2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		active=2;
		textFieldMem.setText(m2.toString());
	}
	});
	
	JButton buttonMem3 = new JButton("mem3");
	buttonMem3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		active=3;
		textFieldMem.setText(m3.toString());
	}
	});
	
	JButton buttonMC = new JButton("MC");
	buttonMC.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		switch (active) {
		case 1:
			m1= 0.0;
			break;
		case 2:
			m2= 0.0;
			break;
		case 3:
			m3= 0.0;
			break;
			
		}
		
		
	}
	});
	

	Box hboxMemoryButtons = Box.createHorizontalBox();
	hboxMemoryButtons.add(Box.createHorizontalGlue());
	
	hboxMemoryButtons.add(buttonMem1);
	hboxMemoryButtons.add(Box.createHorizontalStrut(10));
	hboxMemoryButtons.add(buttonMem2);
	hboxMemoryButtons.add(Box.createHorizontalStrut(10));
	hboxMemoryButtons.add(buttonMem3);
	hboxMemoryButtons.add(Box.createHorizontalStrut(30));
	hboxMemoryButtons.add(buttonMp);
	hboxMemoryButtons.add(Box.createHorizontalStrut(10));
	hboxMemoryButtons.add(buttonMC);
	hboxMemoryButtons.add(Box.createHorizontalGlue());
	hboxMemoryButtons.setBorder(
	BorderFactory.createLineBorder(Color.PINK));
	
		// Связать области воедино в компоновке BoxLayout
	Box contentBox = Box.createVerticalBox();
	contentBox.add(Box.createVerticalGlue());	
	contentBox.add(hboxFormulaType);
	contentBox.add(hboxImage);
	contentBox.add(hboxVariables);
	contentBox.add(hboxResult);
	contentBox.add(hboxMemoryButtons);
	contentBox.add(hboxMem);
	contentBox.add(hboxButtons);
	contentBox.add(Box.createVerticalGlue());
	getContentPane().add(contentBox, BorderLayout.CENTER);
	}

	
	
	// Главный метод класса
	public static void main(String[] args) {
	MainFrame frame = new MainFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	}

}

