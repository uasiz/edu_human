package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.humanMind.database.ServerTimeDto;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ServerManager extends JFrame {

	private JPanel contentPane1;
	private JTable table;

	private JButton startBtn, memberBtn, endBtn; 
	private JTextField mainChatTf;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerManager frame = new ServerManager();
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
	public ServerManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 400, 518, 563);
		setTitle("HumanMind 서버 관리 프로그램");
		setResizable(false);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane1);
		
		JPanel serverStartP = new JPanel();
		contentPane1.add(serverStartP, BorderLayout.CENTER);
		serverStartP.setLayout(null);

		startBtn = new JButton("서버 시작");
		startBtn.setForeground(Color.BLUE);
		startBtn.setFont(new Font("나눔고딕", Font.BOLD, 22));
		startBtn.setBounds(12, 10, 136, 41);
		serverStartP.add(startBtn);

		memberBtn = new JButton("회원 관리 메뉴");
		memberBtn.setForeground(Color.BLACK);
		memberBtn.setFont(new Font("나눔고딕", Font.BOLD, 22));
		memberBtn.setBounds(158, 10, 184, 41);
		serverStartP.add(memberBtn);

		endBtn = new JButton("서버 종료");
		endBtn.setBackground(Color.DARK_GRAY);
		endBtn.setForeground(Color.RED);
		endBtn.setFont(new Font("나눔고딕", Font.BOLD, 22));
		endBtn.setBounds(354, 10, 136, 41);
		endBtn.setEnabled(false);
		endBtn.addActionListener(e -> {

		});
		serverStartP.add(endBtn);
		
		JPanel mainP = new JPanel();
		mainP.setBounds(12, 61, 478, 453);
		serverStartP.add(mainP);
		mainP.setLayout(null);
		
		JLabel serverLogL = new JLabel("서버 로그");
		serverLogL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		serverLogL.setBounds(12, 10, 123, 27);
		mainP.add(serverLogL);
		
		JScrollPane serverLogSp = new JScrollPane();
		serverLogSp.setBounds(12, 33, 256, 247);
		mainP.add(serverLogSp);
		
		JTextArea serverLogTa = new JTextArea();
		serverLogSp.setViewportView(serverLogTa);
		serverLogTa.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
		serverLogTa.setEditable(false);
		
		JLabel inUserL = new JLabel("접속 유저 목록");
		inUserL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		inUserL.setBounds(280, 10, 123, 27);
		mainP.add(inUserL);
		
		JLabel mainChatL = new JLabel("서버 메인 채팅");
		mainChatL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		mainChatL.setBounds(12, 290, 123, 27);
		mainP.add(mainChatL);
		
		JScrollPane mainChatSp = new JScrollPane();
		mainChatSp.setBounds(12, 311, 256, 104);
		mainP.add(mainChatSp);
		
		JTextArea mainChatTa = new JTextArea();
		mainChatSp.setViewportView(mainChatTa);
		mainChatTa.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
		mainChatTa.setEditable(false);
		
		mainChatTf = new JTextField();
		mainChatTf.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
		mainChatTf.setBounds(12, 425, 256, 21);
		mainP.add(mainChatTf);
		mainChatTf.setColumns(10);
		
		table_1 = new JTable();
		table_1.setBounds(284, 33, 182, 410);
		mainP.add(table_1);
	}
}
