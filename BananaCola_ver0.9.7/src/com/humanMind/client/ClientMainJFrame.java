package com.humanMind.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientMainJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField inIdTf;
	private JPasswordField inPwTf;

	private Socket joinSock;
	private Socket loginSock;
	private Socket chatSock;
	private Socket myInfoSock;
	private Socket inGameListSock;
	private DatagramSocket dsock;
	
	private ClientChatThread chat;
	private ClientLoginThread cltioc;
	private Map<String, MemberSerial> myMap; 
	
	ClientMainJFrame(Socket j, Socket l, Socket cs, Socket ms, Socket i, DatagramSocket d, ClientLoginThread c) {
		this.joinSock = j;
		this.loginSock = l;
		this.chatSock = cs;
		this.myInfoSock = ms;
		this.inGameListSock = i;
		this.cltioc = c;
		this.dsock = d;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 450, 300);
		setTitle("HumanMind - 로그인 화면");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);

		setLogin();
		setVisible(true);
	}

	private void setLogin() {

		JPanel loginP = new JPanel();
		contentPane.add(loginP, BorderLayout.CENTER);
		loginP.setLayout(null);

		JLabel loginWelcomeLabel = new JLabel("휴먼마인드 게임에 오신걸 환영합니다!");
		loginWelcomeLabel.setForeground(Color.BLUE);
		loginWelcomeLabel.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 24));
		loginWelcomeLabel.setBounds(12, 10, 412, 44);
		loginP.add(loginWelcomeLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 99, 424, 2);
		loginP.add(separator);

		JLabel loginIdLabel = new JLabel("ID");
		loginIdLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		loginIdLabel.setBounds(84, 111, 35, 37);
		loginP.add(loginIdLabel);

		JLabel loginPwLabel = new JLabel("PW");
		loginPwLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		loginPwLabel.setBounds(84, 158, 35, 37);
		loginP.add(loginPwLabel);

		inIdTf = new JTextField();
		inIdTf.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		inIdTf.setBounds(130, 111, 211, 37);
		loginP.add(inIdTf);
		inIdTf.setColumns(10);
		inIdTf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					inPwTf.requestFocus();
				} 
			}
			
		});

		inPwTf = new JPasswordField();
		inPwTf.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		inPwTf.setColumns(10);
		inPwTf.setBounds(131, 158, 210, 37);
		inPwTf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					loginCheck();
				} 
			}
			
		});
		loginP.add(inPwTf);

		JButton loginBtn = new JButton("로그인");
		loginBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		loginBtn.setBounds(218, 205, 122, 37);
		loginBtn.addActionListener(e -> {
			
			 loginCheck();
			 
		});
		loginP.add(loginBtn);

		JButton joinBtn = new JButton("회원 가입");
		joinBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		joinBtn.setBounds(84, 205, 122, 37);
		joinBtn.addActionListener(e -> {
			
			normalMsg("환영합니다!", "<html><b>회원가입 페이지로 이동합니다!</b></html>");
			new ClientJoinThread(joinSock);

		});
		loginP.add(joinBtn);

		JLabel loginVersionLabel = new JLabel("Banana Cola - Version 0.9.7.0");
		loginVersionLabel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		loginVersionLabel.setForeground(Color.RED);
		loginVersionLabel.setBounds(102, 64, 225, 25);
		loginP.add(loginVersionLabel);

	}

	// 알림 메세지 창 출력 메 소드
	private void normalMsg(String title, String msg) {
		JLabel startMessage = new JLabel(msg);
		startMessage.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(this, startMessage, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// 경고 메세지 창 출력 메 소드
	private void alertMsg(String title, String msg) {
		JLabel startMessage = new JLabel(msg);
		startMessage.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(this, startMessage, title, JOptionPane.WARNING_MESSAGE);
	}

	private void loginCheck() {

		boolean loginOk = false;

		String tempId = inIdTf.getText();
		String tempPw = inPwTf.getText();
		
		cltioc.checkLoginAccount(tempId, tempPw);
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myMap = cltioc.getMyMap();

		if(!cltioc.getId().equals("fail") && (cltioc.getId() != "")) {
			loginOk = true;
		}
		
		if(loginOk) {
			String tempName = myMap.get(tempId).getName();
			normalMsg("환영합니다!", "<html><b>"+tempName+"님 환영합니다!</b></html>");
			inIdTf.setText("");
			inPwTf.setText("");
			inIdTf.requestFocus();
			chat = new ClientChatThread(chatSock, myInfoSock, myMap, inGameListSock, dsock);
			
			try {
				joinSock.close();
				loginSock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			dispose();
		} else {
			loginFail();
		}
	}
	
	public void loginFail() {
		alertMsg("로그인 실패!", "<html><b>계정을 다시 확인하세요.</b></html>");
		inIdTf.setText("");
		inPwTf.setText("");
		inIdTf.requestFocus();
		dispose();
	}
}