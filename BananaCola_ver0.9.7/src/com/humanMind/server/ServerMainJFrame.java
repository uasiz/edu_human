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

	// ���� ���� GUI�� �̱��� ������� �ѹ��� ����˴ϴ�.

	public static ServerMainJFrame getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final ServerMainJFrame INSTANCE = new ServerMainJFrame();
	}

	private ServerMainJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 400, 450, 300);
		setTitle("HumanMind ���� ���� ���α׷�");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);

		serverMain(); // ���� ���� GUI �α��� ȭ��

		setVisible(true);
	}

	private void serverMain() {
		JPanel serverMainP = new JPanel();
		contentPane.add(serverMainP, BorderLayout.CENTER);
		serverMainP.setLayout(null);

		JLabel serverMainLabel = new JLabel("HumanMind ���� ���� ���α׷�");
		serverMainLabel.setForeground(Color.BLUE);
		serverMainLabel.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 25));
		serverMainLabel.setBounds(32, 10, 368, 61);
		serverMainP.add(serverMainLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 104, 424, 2);
		serverMainP.add(separator);

		JLabel serverMainLabel2 = new JLabel("������ �����Ͻ÷��� ���� �α����� �ϼž� �մϴ�.");
		serverMainLabel2.setForeground(Color.RED);
		serverMainLabel2.setFont(new Font("�������", Font.BOLD, 13));
		serverMainLabel2.setBounds(85, 70, 294, 15);
		serverMainP.add(serverMainLabel2);

		JLabel adminIdL = new JLabel("������ ID");
		adminIdL.setFont(new Font("�������", Font.BOLD, 16));
		adminIdL.setBounds(103, 122, 76, 27);
		serverMainP.add(adminIdL);

		JLabel adminPwL = new JLabel("������ PW");
		adminPwL.setFont(new Font("�������", Font.BOLD, 16));
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
						normalMsg("ȯ���մϴ�!", "<html><b>�����ڴ� ȯ���մϴ�.</b></html>");
						ServerStartJFrame.getInstance();
						dispose();
					} else {
						alertMsg("�߸��� �����Դϴ�!",
								"<html><b>������ ������ ��ġ���� �ʽ��ϴ�!!</b><br><br>�ٽ� Ȯ�� �� �α��� �Ͽ� �ּ���.<br></html>");
						adminIdTf.setText("");
						adminPwTf.setText("");
						adminIdTf.requestFocus();
					}
				}
			}

		});
		serverMainP.add(adminPwTf);

		JButton adminLoginBtn = new JButton("������ �α���");
		adminLoginBtn.setFont(new Font("�������", Font.BOLD, 18));
		adminLoginBtn.setBounds(138, 201, 167, 40);
		adminLoginBtn.addActionListener(e -> {

			if (adminLoginCheck(adminIdTf.getText(), adminPwTf.getText())) {
				normalMsg("ȯ���մϴ�!", "<html><b>�����ڴ� ȯ���մϴ�.</b></html>");
				dispose();
				ServerStartJFrame.getInstance();
			} else {
				alertMsg("�߸��� �����Դϴ�!", "<html><b>������ ������ ��ġ���� �ʽ��ϴ�!!</b><br><br>�ٽ� Ȯ�� �� �α��� �Ͽ� �ּ���.<br></html>");
				adminIdTf.setText("");
				adminPwTf.setText("");
				adminIdTf.requestFocus();
			}

		});
		serverMainP.add(adminLoginBtn);

	}

	// ������ �α��� üũ �޼ҵ�
	public boolean adminLoginCheck(String id, String pw) {
		if (id.equals(adminId) && pw.equals(adminPw)) {
			return true;
		} else {
			return false;
		}
	}

	// �˸� �޼��� â ��� �� �ҵ�
	private void normalMsg(String title, String msg) {
		JLabel startMessage = new JLabel(msg);
		startMessage.setFont(new Font("�������", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(this, startMessage, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// ��� �޼��� â ��� �� �ҵ�
	private void alertMsg(String title, String msg) {
		JLabel startMessage = new JLabel(msg);
		startMessage.setFont(new Font("�������", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(this, startMessage, title, JOptionPane.WARNING_MESSAGE);
	}

}
