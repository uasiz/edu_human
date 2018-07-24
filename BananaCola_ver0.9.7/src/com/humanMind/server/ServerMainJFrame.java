package com.humanMind.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ServerMainJFrame extends JFrame {

	private final String adminId = "human";
	private final String adminPw = "1111";

	private JPanel contentPane;
	private JTextField adminIdTf;
	private JPasswordField adminPwTf;

	// 서버 메인 GUI는 싱글톤 기법으로 한번만 실행됩니다.

	public static ServerMainJFrame getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final ServerMainJFrame INSTANCE = new ServerMainJFrame();
	}

	private ServerMainJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 400, 450, 300);
		setTitle("HumanMind 서버 관리 프로그램");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);

		serverMain(); // 서버 메인 GUI 로그인 화면

		setVisible(true);
	}

	private void serverMain() {
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
		adminIdTf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					adminPwTf.requestFocus();
				}
			}

		});
		adminIdTf.setColumns(10);

		adminPwTf = new JPasswordField();
		adminPwTf.setColumns(10);
		adminPwTf.setBounds(191, 162, 136, 29);
		adminPwTf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (adminLoginCheck(adminIdTf.getText(), adminPwTf.getText())) {
						normalMsg("환영합니다!", "<html><b>관리자님 환영합니다.</b></html>");
						ServerStartJFrame.getInstance();
						dispose();
					} else {
						alertMsg("잘못된 접근입니다!",
								"<html><b>관리자 계정이 일치하지 않습니다!!</b><br><br>다시 확인 후 로그인 하여 주세요.<br></html>");
						adminIdTf.setText("");
						adminPwTf.setText("");
						adminIdTf.requestFocus();
					}
				}
			}

		});
		serverMainP.add(adminPwTf);

		JButton adminLoginBtn = new JButton("관리자 로그인");
		adminLoginBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		adminLoginBtn.setBounds(138, 201, 167, 40);
		adminLoginBtn.addActionListener(e -> {

			if (adminLoginCheck(adminIdTf.getText(), adminPwTf.getText())) {
				normalMsg("환영합니다!", "<html><b>관리자님 환영합니다.</b></html>");
				dispose();
				ServerStartJFrame.getInstance();
			} else {
				alertMsg("잘못된 접근입니다!", "<html><b>관리자 계정이 일치하지 않습니다!!</b><br><br>다시 확인 후 로그인 하여 주세요.<br></html>");
				adminIdTf.setText("");
				adminPwTf.setText("");
				adminIdTf.requestFocus();
			}

		});
		serverMainP.add(adminLoginBtn);

	}

	// 관리자 로그인 체크 메소드
	public boolean adminLoginCheck(String id, String pw) {
		if (id.equals(adminId) && pw.equals(adminPw)) {
			return true;
		} else {
			return false;
		}
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

}
