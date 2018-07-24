package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 573);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.desktop));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setToolTipText("");
		panel.setForeground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 400, 400);
		panel.add(panel_1);
		
		textField_3 = new JTextField();
		textField_3.setText("\uB0A8\uC740 \uC2DC\uAC04 - 00:00");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBackground(SystemColor.menu);
		panel_1.add(textField_3);
		
		JButton btnNewButton = new JButton("³ª°¡±â");
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(746, 10, 100, 31);
		panel.add(btnNewButton);
		
		JButton button_1 = new JButton("\uC2E0\uACE0");
		button_1.setBackground(UIManager.getColor("Button.background"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(746, 51, 100, 31);
		panel.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(424, 420, 229, 39);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\uC785\uB825");
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(665, 420, 69, 39);
		panel.add(btnNewButton_1);
		
		JButton button_2 = new JButton("\uC900\uBE44");
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setBounds(424, 469, 148, 40);
		panel.add(button_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uAD75\uC740 \uD39C");
		rdbtnNewRadioButton.setBackground(SystemColor.inactiveCaption);
		rdbtnNewRadioButton.setBounds(320, 416, 69, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\uC587\uC740 \uD39C");
		radioButton_1.setBackground(SystemColor.inactiveCaption);
		radioButton_1.setBounds(320, 436, 69, 23);
		panel.add(radioButton_1);
		
		JButton btnNewButton_2 = new JButton("\uBAA8\uB450 \uC9C0\uC6B0\uAE30");
		btnNewButton_2.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setBounds(302, 469, 106, 40);
		panel.add(btnNewButton_2);
		
		JButton button = new JButton("\uC2DC\uC791");
		button.setBackground(UIManager.getColor("Button.background"));
		button.setBounds(584, 469, 150, 40);
		panel.add(button);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(424, 10, 310, 203);
		panel.add(list);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(424, 224, 310, 186);
		panel.add(textArea);
		
		JButton colorBlackBtn = new JButton("");
		colorBlackBtn.setBackground(Color.BLACK);
		colorBlackBtn.setBounds(12, 420, 39, 39);
		panel.add(colorBlackBtn);
		
		JButton colorRedBtn = new JButton("");
		colorRedBtn.setBackground(Color.RED);
		colorRedBtn.setBounds(63, 420, 39, 39);
		panel.add(colorRedBtn);
		
		JButton colorGreenBtn = new JButton("");
		colorGreenBtn.setBackground(Color.GREEN);
		colorGreenBtn.setBounds(114, 420, 39, 39);
		panel.add(colorGreenBtn);
		
		JButton colorBlueBtn = new JButton("");
		colorBlueBtn.setBackground(Color.BLUE);
		colorBlueBtn.setBounds(165, 420, 39, 39);
		panel.add(colorBlueBtn);
		
		JButton colorYellowBtn = new JButton("");
		colorYellowBtn.setBackground(Color.YELLOW);
		colorYellowBtn.setBounds(220, 420, 39, 39);
		panel.add(colorYellowBtn);
		
		JButton colotWhiteBtn = new JButton("");
		colotWhiteBtn.setBackground(Color.WHITE);
		colotWhiteBtn.setBounds(271, 420, 39, 39);
		panel.add(colotWhiteBtn);
		
		JButton answerL = new JButton("New button");
		answerL.setBackground(Color.CYAN);
		answerL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		answerL.setBounds(12, 469, 278, 40);
		panel.add(answerL);
	}
}
