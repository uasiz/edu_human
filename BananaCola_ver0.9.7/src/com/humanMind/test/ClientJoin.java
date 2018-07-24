package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;

public class ClientJoin extends JFrame {

	private JPanel contentPane;
	private JTextField idTf;
	private JTextField pwTf;
	private JTextField pwCheckTf;
	private JTextField nameTf;
	private JTextField textField_4;
	private JTextField phoneTf1;
	private JTextField eMailTf;
	private JTextField phoneTf2;
	private JSeparator separator;
	private JTextField phoneTf3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientJoin frame = new ClientJoin();
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
	public ClientJoin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JLabel idLabel = new JLabel("¾ÆÀÌµð");
		idLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		idLabel.setBounds(43, 22, 104, 25);
		mainPanel.add(idLabel);
		
		idTf = new JTextField();
		idTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		idTf.setBounds(159, 22, 130, 25);
		mainPanel.add(idTf);
		idTf.setColumns(10);
		
		JButton idCheckBtn = new JButton("Áßº¹ È®ÀÎ");
		idCheckBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 13));
		idCheckBtn.setBounds(300, 23, 92, 23);
		mainPanel.add(idCheckBtn);
		
		JLabel pwLabel = new JLabel("ºñ¹Ð¹øÈ£");
		pwLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		pwLabel.setBounds(43, 57, 104, 25);
		mainPanel.add(pwLabel);
		
		pwTf = new JTextField();
		pwTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		pwTf.setColumns(10);
		pwTf.setBounds(159, 57, 233, 25);
		mainPanel.add(pwTf);
		
		JLabel pwCheckLabel = new JLabel("ºñ¹Ð¹øÈ£ È®ÀÎ");
		pwCheckLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		pwCheckLabel.setBounds(43, 92, 104, 25);
		mainPanel.add(pwCheckLabel);
		
		pwCheckTf = new JTextField();
		pwCheckTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		pwCheckTf.setColumns(10);
		pwCheckTf.setBounds(159, 92, 233, 25);
		mainPanel.add(pwCheckTf);
		
		nameTf = new JTextField();
		nameTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		nameTf.setColumns(10);
		nameTf.setBounds(159, 127, 233, 25);
		mainPanel.add(nameTf);
		
		JLabel nameLabel = new JLabel("ÀÌ¸§");
		nameLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		nameLabel.setBounds(43, 127, 104, 25);
		mainPanel.add(nameLabel);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(159, 162, 233, 25);
		mainPanel.add(textField_4);
		
		JLabel addrLabel = new JLabel("ÁÖ¼Ò");
		addrLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		addrLabel.setBounds(43, 162, 104, 25);
		mainPanel.add(addrLabel);
		
		phoneTf1 = new JTextField();
		phoneTf1.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		phoneTf1.setColumns(10);
		phoneTf1.setBounds(159, 197, 47, 25);
		mainPanel.add(phoneTf1);
		
		JLabel phoneLabel = new JLabel("ÈÞ´ëÆù ¹øÈ£");
		phoneLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		phoneLabel.setBounds(43, 197, 104, 25);
		mainPanel.add(phoneLabel);
		
		eMailTf = new JTextField();
		eMailTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		eMailTf.setColumns(10);
		eMailTf.setBounds(159, 232, 233, 25);
		mainPanel.add(eMailTf);
		
		JLabel eMailLabel = new JLabel("ÀÌ¸ÞÀÏ");
		eMailLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		eMailLabel.setBounds(43, 232, 104, 25);
		mainPanel.add(eMailLabel);
		
		phoneTf2 = new JTextField();
		phoneTf2.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		phoneTf2.setColumns(10);
		phoneTf2.setBounds(232, 197, 64, 25);
		mainPanel.add(phoneTf2);
		
		separator = new JSeparator();
		separator.setBounds(214, 209, 8, 2);
		mainPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(308, 209, 8, 2);
		mainPanel.add(separator_1);
		
		phoneTf3 = new JTextField();
		phoneTf3.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		phoneTf3.setColumns(10);
		phoneTf3.setBounds(328, 197, 64, 25);
		mainPanel.add(phoneTf3);
		
		JButton comBtn = new JButton("ÀÛ¼º ¿Ï·á");
		comBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		comBtn.setBounds(169, 267, 224, 45);
		mainPanel.add(comBtn);
		
		JButton cancelBtn = new JButton("Ãë¼Ò");
		cancelBtn.setBackground(Color.LIGHT_GRAY);
		cancelBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		cancelBtn.setBounds(43, 267, 114, 45);
		mainPanel.add(cancelBtn);
	}
}
