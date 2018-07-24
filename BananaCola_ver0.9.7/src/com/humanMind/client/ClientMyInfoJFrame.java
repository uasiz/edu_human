package com.humanMind.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.humanMind.database.MemberDao;
import com.humanMind.database.MemberDto;

public class ClientMyInfoJFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField idTf, nameTf, addrTf, phoneTf, eMailTf;
	private String id, name, addr, phone, eMail;
	
	private MemberDao dao = MemberDao.getInstance();
	private boolean isEdit = false;
	
	private Map<String, MemberSerial> myMap = new HashMap<>();
	
	ClientMyInfoJFrame(String inId, Map<String, MemberSerial> map) {
		this.id = inId;
		this.myMap = map;
		
		name = map.get(inId).getName();
		addr = map.get(inId).getAddr();
		phone = map.get(inId).getPhone();
		eMail = map.get(inId).getEmail();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 293, 313);
		setTitle("HumanMind - ³» Á¤º¸");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);
		
		myInfo();
		
		setVisible(true);
	}
	
	private void myInfo() {

		JPanel mainP = new JPanel();
		contentPane.add(mainP, BorderLayout.CENTER);
		mainP.setLayout(null);
		
		JLabel idL = new JLabel("¾ÆÀÌµð");
		idL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		idL.setBounds(24, 10, 68, 28);
		mainP.add(idL);
		
		JLabel nameL = new JLabel("ÀÌ¸§");
		nameL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		nameL.setBounds(24, 48, 68, 28);
		mainP.add(nameL);
		
		JLabel addrL = new JLabel("ÁÖ¼Ò");
		addrL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		addrL.setBounds(24, 86, 68, 28);
		mainP.add(addrL);
		
		JLabel phoneL = new JLabel("ÀüÈ­¹øÈ£");
		phoneL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		phoneL.setBounds(24, 124, 68, 28);
		mainP.add(phoneL);
		
		JLabel eMailL = new JLabel("ÀÌ¸ÞÀÏ");
		eMailL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		eMailL.setBounds(24, 162, 68, 28);
		mainP.add(eMailL);
		
		idTf = new JTextField();
		idTf.setEditable(false);
		idTf.setBounds(92, 14, 163, 21);
		idTf.setText(id);
		mainP.add(idTf);
		idTf.setColumns(10);
		
		nameTf = new JTextField();
		nameTf.setEditable(false);
		nameTf.setColumns(10);
		nameTf.setBounds(92, 52, 163, 21);
		nameTf.setText(name);
		mainP.add(nameTf);
		
		addrTf = new JTextField();
		addrTf.setEditable(false);
		addrTf.setColumns(10);
		addrTf.setBounds(92, 90, 163, 21);
		addrTf.setText(addr);
		mainP.add(addrTf);
		
		phoneTf = new JTextField();
		phoneTf.setEditable(false);
		phoneTf.setColumns(10);
		phoneTf.setBounds(92, 128, 163, 21);
		phoneTf.setText(phone);
		mainP.add(phoneTf);
		
		eMailTf = new JTextField();
		eMailTf.setEditable(false);
		eMailTf.setColumns(10);
		eMailTf.setBounds(92, 166, 163, 21);
		eMailTf.setText(eMail);
		mainP.add(eMailTf);
		
		JButton editBtn = new JButton("Á¤º¸ ¼öÁ¤");
		editBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		editBtn.setBounds(24, 205, 104, 49);
		editBtn.setEnabled(false);
//		editBtn.addActionListener(e -> {
//			
//			nameTf.setEditable(true);
//			addrTf.setEditable(true);
//			phoneTf.setEditable(true);
//			eMailTf.setEditable(true);
//			editBtn.setEnabled(false);
//			isEdit = true;
//			
//		});
		mainP.add(editBtn);
		
		JButton applyBtn = new JButton("È®ÀÎ");
		applyBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		applyBtn.setBounds(151, 205, 104, 49);
		mainP.add(applyBtn);
		applyBtn.addActionListener(e -> {
			
			if(isEdit) {
				MemberDto dto = new MemberDto();
				dto.setId(id);
				dto.setName(nameTf.getText());
				dto.setAddr(addrTf.getText());
				dto.setPhone(phoneTf.getText());
				dto.setEmail(eMailTf.getText());
				dao.update(dto);
				normalMsg("Á¤º¸°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù!", "<html><b>Á¤º¸°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù!</b></html>");
			} 
			dispose();
		});
	}
	
	private void normalMsg(String title, String msg) {
		JLabel startMessage = new JLabel(msg);
		startMessage.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(this, startMessage, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
}
