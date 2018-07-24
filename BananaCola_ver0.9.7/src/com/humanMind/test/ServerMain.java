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

public class ServerMain extends JFrame {

	private JPanel contentPane;
	private JTextField adminIdTf;
	private JTextField adminPwTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMain frame = new ServerMain();
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
	public ServerMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel serverMainP = new JPanel();
		contentPane.add(serverMainP, BorderLayout.CENTER);
		serverMainP.setLayout(null);
		
		JLabel serverMainLabel = new JLabel("HumanMind 서버 관리 프로그램");
		serverMainLabel.setForeground(Color.BLUE);
		serverMainLabel.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 25));
		serverMainLabel.setBounds(32, 10, 368, 61);
		serverMainP.add(serverMainLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 104, 424, 2);
		serverMainP.add(separator);
		
		JLabel serverMainLabel2 = new JLabel("서버를 시작하시려면 먼저 로그인을 하셔야 합니다.");
		serverMainLabel2.setForeground(Color.RED);
		serverMainLabel2.setFont(new Font("나눔고딕", Font.BOLD, 13));
		serverMainLabel2.setBounds(85, 70, 294, 15);
		serverMainP.add(serverMainLabel2);
		
		JLabel adminIdL = new JLabel("관리자 ID");
		adminIdL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		adminIdL.setBounds(103, 122, 76, 27);
		serverMainP.add(adminIdL);
		
		JLabel adminPwL = new JLabel("관리자 PW");
		adminPwL.setFont(new Font("나눔고딕", Font.BOLD, 16));
		adminPwL.setBounds(103, 158, 76, 27);
		serverMainP.add(adminPwL);
		
		adminIdTf = new JTextField();
		adminIdTf.setBounds(191, 122, 136, 29);
		serverMainP.add(adminIdTf);
		adminIdTf.setColumns(10);
		
		adminPwTf = new JTextField();
		adminPwTf.setColumns(10);
		adminPwTf.setBounds(191, 162, 136, 29);
		serverMainP.add(adminPwTf);
		
		JButton adminLoginBtn = new JButton("관리자 로그인");
		adminLoginBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		adminLoginBtn.setBounds(138, 201, 167, 40);
		serverMainP.add(adminLoginBtn);
	}
}
