package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientGameRoom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGameRoom frame = new ClientGameRoom();
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
	public ClientGameRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 31, 255, 179);
		panel.add(textArea);
		
		JLabel lblNewLabel = new JLabel("접속 인원");
		lblNewLabel.setBounds(296, 14, 57, 15);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("채팅창");
		label.setBounds(12, 14, 57, 15);
		panel.add(label);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(291, 31, 121, 179);
		panel.add(textArea_1);
		
		textField = new JTextField();
		textField.setBounds(12, 220, 255, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("나가기");
		btnNewButton.setBounds(291, 219, 121, 23);
		panel.add(btnNewButton);
	}
}
