package com.humanMind.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.humanMind.database.MemberDao;
import com.humanMind.database.MemberDto;

public class ClientJoinJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idTf, nameTf, addrTf, phoneTf1, eMailTf, phoneTf2, phoneTf3;
	private JSeparator separator1, separator2;
	private JPasswordField pwTf, pwCheckTf;
	private JButton idCheckBtn;

	private boolean isIdChek, isPwOver4, isPwMatch, isPhoneNum, isJoinOk;

	private Socket socket;
	private ClientJoinThread cjt;
	private MemberDao mDao = MemberDao.getInstance();
	private MemberDto mDto = new MemberDto();

	ClientJoinJFrame(Socket s, ClientJoinThread c) {
		this.socket = s;
		this.cjt = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 450, 386);
		setTitle("HumanMind - ȸ�� ����");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);
		setVisible(true);

		joinMenu();

	}

	private void joinMenu() {
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JLabel idLabel = new JLabel("���̵�");
		idLabel.setFont(new Font("�������", Font.BOLD, 15));
		idLabel.setBounds(43, 22, 104, 25);
		mainPanel.add(idLabel);

		idTf = new JTextField();
		idTf.setFont(new Font("�������", Font.PLAIN, 15));
		idTf.setBounds(159, 22, 130, 25);
		mainPanel.add(idTf);
		idTf.setColumns(10);

		idCheckBtn = new JButton("�ߺ� Ȯ��");
		idCheckBtn.setFont(new Font("�������", Font.BOLD, 13));
		idCheckBtn.setBounds(300, 23, 92, 23);
		idCheckBtn.addActionListener(e -> {

			idDupleCheck();

		});
		mainPanel.add(idCheckBtn);

		JLabel pwLabel = new JLabel("��й�ȣ");
		pwLabel.setFont(new Font("�������", Font.BOLD, 15));
		pwLabel.setBounds(43, 57, 104, 25);
		mainPanel.add(pwLabel);

		pwTf = new JPasswordField();
		pwTf.setFont(new Font("�������", Font.PLAIN, 15));
		pwTf.setColumns(10);
		pwTf.setBounds(159, 57, 233, 25);
		mainPanel.add(pwTf);

		JLabel pwCheckLabel = new JLabel("��й�ȣ Ȯ��");
		pwCheckLabel.setFont(new Font("�������", Font.BOLD, 15));
		pwCheckLabel.setBounds(43, 92, 104, 25);
		mainPanel.add(pwCheckLabel);

		pwCheckTf = new JPasswordField();
		pwCheckTf.setFont(new Font("�������", Font.PLAIN, 15));
		pwCheckTf.setColumns(10);
		pwCheckTf.setBounds(159, 92, 233, 25);
		mainPanel.add(pwCheckTf);

		nameTf = new JTextField();
		nameTf.setFont(new Font("�������", Font.PLAIN, 15));
		nameTf.setColumns(10);
		nameTf.setBounds(159, 127, 233, 25);
		mainPanel.add(nameTf);

		JLabel nameLabel = new JLabel("�̸�");
		nameLabel.setFont(new Font("�������", Font.BOLD, 15));
		nameLabel.setBounds(43, 127, 104, 25);
		mainPanel.add(nameLabel);

		addrTf = new JTextField();
		addrTf.setFont(new Font("�������", Font.PLAIN, 15));
		addrTf.setColumns(10);
		addrTf.setBounds(159, 162, 233, 25);
		mainPanel.add(addrTf);

		JLabel addrLabel = new JLabel("�ּ�");
		addrLabel.setFont(new Font("�������", Font.BOLD, 15));
		addrLabel.setBounds(43, 162, 104, 25);
		mainPanel.add(addrLabel);

		phoneTf1 = new JTextField();
		phoneTf1.setFont(new Font("�������", Font.PLAIN, 15));
		phoneTf1.setColumns(10);
		phoneTf1.setBounds(159, 197, 47, 25);
		mainPanel.add(phoneTf1);

		JLabel phoneLabel = new JLabel("�޴��� ��ȣ");
		phoneLabel.setFont(new Font("�������", Font.BOLD, 15));
		phoneLabel.setBounds(43, 197, 104, 25);
		mainPanel.add(phoneLabel);

		eMailTf = new JTextField();
		eMailTf.setFont(new Font("�������", Font.PLAIN, 15));
		eMailTf.setColumns(10);
		eMailTf.setBounds(159, 232, 233, 25);
		mainPanel.add(eMailTf);

		JLabel eMailLabel = new JLabel("�̸���");
		eMailLabel.setFont(new Font("�������", Font.BOLD, 15));
		eMailLabel.setBounds(43, 232, 104, 25);
		mainPanel.add(eMailLabel);

		phoneTf2 = new JTextField();
		phoneTf2.setFont(new Font("�������", Font.PLAIN, 15));
		phoneTf2.setColumns(10);
		phoneTf2.setBounds(232, 197, 64, 25);
		mainPanel.add(phoneTf2);

		separator1 = new JSeparator();
		separator1.setBounds(214, 209, 8, 2);
		mainPanel.add(separator1);

		separator2 = new JSeparator();
		separator2.setBounds(308, 209, 8, 2);
		mainPanel.add(separator2);

		phoneTf3 = new JTextField();
		phoneTf3.setFont(new Font("�������", Font.PLAIN, 15));
		phoneTf3.setColumns(10);
		phoneTf3.setBounds(328, 197, 64, 25);
		mainPanel.add(phoneTf3);

		JButton comBtn = new JButton("�ۼ� �Ϸ�");
		comBtn.setFont(new Font("�������", Font.BOLD, 15));
		comBtn.setBounds(169, 267, 224, 45);
		comBtn.addActionListener(e -> {

			checkJoin();

		});
		mainPanel.add(comBtn);

		JButton cancelBtn = new JButton("���");
		cancelBtn.setBackground(Color.LIGHT_GRAY);
		cancelBtn.setFont(new Font("�������", Font.BOLD, 15));
		cancelBtn.setBounds(43, 267, 114, 45);
		cancelBtn.addActionListener(e -> {
			alertMsg("����ϼ̽��ϴ�!", "<html><b>ȸ�������� ����ϰ� ���θ޴��� ���ư��ϴ�</b></html>");
			dispose();

		});
		mainPanel.add(cancelBtn);

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

	private boolean isNum(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void idDupleCheck() {

		String tempId = idTf.getText();
		isIdChek = false;
		cjt.idSend(tempId);
		boolean innerFlag = cjt.getFlag();

		if (innerFlag) {
			normalMsg("���̵� �ߺ����� �ʽ��ϴ�!", "<html><b>��� ������ ���̵��Դϴ�!</b></html>");
			isIdChek = true;
			idTf.setEditable(false);
			idCheckBtn.setEnabled(false);
			pwTf.requestFocus();
		} else {
			alertMsg("���̵� �ߺ��˴ϴ�.!", "<html><b>�̹� ������� ���̵��Դϴ�.</b></html>");
			idTf.setText("");
		}
	}

	private void checkJoin() {
		isPwOver4 = false;
		isPwMatch = false;
		isPhoneNum = false;
		isJoinOk = false;

		if (!isIdChek) {
			alertMsg("���̵� �ߺ� üũ�� �ʿ��մϴ�!", "<html><b>���̵� �ߺ�üũ�� ���ּ���.</b></html>");
		}

		if (pwTf.getText().length() > 3) {
			isPwOver4 = true;
		} else {
			alertMsg("��й�ȣ�� �ʹ� ª���ϴ�!", "<html><b>��й�ȣ�� 4�ڸ� �̻����� �����ϼ���!</b></html>");
			pwTf.setText("");
			pwCheckTf.setText("");
			pwTf.requestFocus();
		}

		if (pwTf.getText().equals(pwCheckTf.getText())) {
			isPwMatch = true;
		} else {
			alertMsg("��й�ȣ�� ��ġ���� �ʽ��ϴ�!", "<html><b>��й�ȣ�� ��ġ���� �ʽ��ϴ�!</b><br>��й�ȣ�� �ٽ� Ȯ���ϼ���.</html>");
			pwTf.setText("");
			pwCheckTf.setText("");
			pwTf.requestFocus();
		}

		if (isNum(phoneTf1.getText()) && isNum(phoneTf2.getText()) && isNum(phoneTf3.getText())) {
			isPhoneNum = true;
		} else {
			alertMsg("��ȭ��ȣ�� ���ڸ� �Է��ϼ���!", "<html><b>��ȭ��ȣ�� ���ڸ� �Է� �����մϴ�</b></html>");
			phoneTf1.setText("");
			phoneTf2.setText("");
			phoneTf2.setText("");
			phoneTf1.requestFocus();
		}

		if (isPwOver4 && isPwMatch && isPhoneNum && isIdChek) {
			isJoinOk = true;
		} else {
			isJoinOk = false;
		}

		if (isJoinOk) {
			String id = idTf.getText();
			String pw = pwTf.getText();
			String name = nameTf.getText();
			String add = addrTf.getText();
			String phone = phoneTf1.getText() + "-" + phoneTf2.getText() + "-" + phoneTf3.getText();
			String eMail = eMailTf.getText();

			cjt.sendNewClient(id, pw, name, add, phone, eMail);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			normalMsg("ȯ���մϴ�!", "<html><b>ȸ�������� �Ϸ�Ǿ����ϴ�!</b></html>");
			dispose();
		}
	}
}
