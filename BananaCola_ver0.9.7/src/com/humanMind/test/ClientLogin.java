package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField inIdTf;
	private JTextField inPwTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
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
	public ClientLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel loginP = new JPanel();
		contentPane.add(loginP, BorderLayout.CENTER);
		loginP.setLayout(null);
		
		JLabel loginWelcomeLabel = new JLabel("ÈÞ¸Õ¸¶ÀÎµå °ÔÀÓ¿¡ ¿À½Å°É È¯¿µÇÕ´Ï´Ù!");
		loginWelcomeLabel.setForeground(Color.BLUE);
		loginWelcomeLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD | Font.ITALIC, 24));
		loginWelcomeLabel.setBounds(12, 10, 412, 44);
		loginP.add(loginWelcomeLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 99, 424, 2);
		loginP.add(separator);
		
		JLabel loginIdLabel = new JLabel("ID");
		loginIdLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 18));
		loginIdLabel.setBounds(84, 111, 35, 37);
		loginP.add(loginIdLabel);
		
		JLabel loginPwLabel = new JLabel("PW");
		loginPwLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 18));
		loginPwLabel.setBounds(84, 158, 35, 37);
		loginP.add(loginPwLabel);
		
		inIdTf = new JTextField();
		inIdTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		inIdTf.setBounds(130, 111, 211, 37);
		loginP.add(inIdTf);
		inIdTf.setColumns(10);
		
		inPwTf = new JTextField();
		inPwTf.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 16));
		inPwTf.setColumns(10);
		inPwTf.setBounds(131, 158, 210, 37);
		loginP.add(inPwTf);
		
		JButton loginBtn = new JButton("·Î±×ÀÎ");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 18));
		loginBtn.setBounds(218, 205, 122, 37);
		loginP.add(loginBtn);
		
		JButton joinBtn = new JButton("È¸¿ø °¡ÀÔ");
		joinBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 18));
		joinBtn.setBounds(84, 205, 122, 37);
		loginP.add(joinBtn);
		
		JLabel loginVersionLabel = new JLabel("Version 0.1.0.2");
		loginVersionLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		loginVersionLabel.setForeground(Color.RED);
		loginVersionLabel.setBounds(162, 64, 122, 25);
		loginP.add(loginVersionLabel);
	}
}
