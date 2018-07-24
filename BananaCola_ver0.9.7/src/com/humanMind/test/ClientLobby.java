package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientLobby extends JFrame {

	private JPanel contentPane;
	private JTextField chatTf;
	private JTextField myIdTf;
	private JTextField myCashTf;
	private JTable inUserTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLobby frame = new ClientLobby();
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
	public ClientLobby() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JLabel roomListL = new JLabel("¹æ ¸ñ·Ï - Room List");
		roomListL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 14));
		roomListL.setBounds(12, 10, 152, 15);
		mainPanel.add(roomListL);
		
		JLabel userListL = new JLabel("»ç¿ëÀÚ ¸ñ·Ï - User List");
		userListL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 14));
		userListL.setBounds(370, 10, 152, 15);
		mainPanel.add(userListL);
		
		JLabel chatL = new JLabel("´ëÈ­Ã¢ - Chatting Room");
		chatL.setFont(new Font("³ª´®°íµñ", Font.BOLD, 14));
		chatL.setBounds(12, 238, 152, 15);
		mainPanel.add(chatL);
		
		JScrollPane chatSp = new JScrollPane();
		chatSp.setBounds(12, 254, 334, 107);
		mainPanel.add(chatSp);
		
		JTextArea chatTa = new JTextArea();
		chatSp.setViewportView(chatTa);
		chatTa.setEditable(false);
		
		chatTf = new JTextField();
		chatTf.setBounds(12, 366, 334, 21);
		mainPanel.add(chatTf);
		chatTf.setColumns(10);
		
		JLabel imageL = new JLabel("My Image");
		imageL.setBackground(Color.BLUE);
		imageL.setBounds(370, 258, 89, 90);
		mainPanel.add(imageL);
		
		JLabel idL = new JLabel("¾ÆÀÌµð");
		idL.setBounds(471, 254, 57, 15);
		mainPanel.add(idL);
		
		myIdTf = new JTextField();
		myIdTf.setEditable(false);
		myIdTf.setBounds(470, 271, 85, 21);
		mainPanel.add(myIdTf);
		myIdTf.setColumns(10);
		
		JLabel cashL = new JLabel("º¸À¯ Ä³½¬");
		cashL.setBounds(471, 306, 57, 15);
		mainPanel.add(cashL);
		
		myCashTf = new JTextField();
		myCashTf.setEditable(false);
		myCashTf.setColumns(10);
		myCashTf.setBounds(470, 323, 85, 21);
		mainPanel.add(myCashTf);
		
		JButton myIntroBtn = new JButton("³» Á¤º¸");
		myIntroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		myIntroBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 13));
		myIntroBtn.setBounds(368, 365, 73, 23);
		mainPanel.add(myIntroBtn);
		
		JButton btnItemShop = new JButton("Item Shop");
		btnItemShop.setBackground(Color.CYAN);
		btnItemShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnItemShop.setFont(new Font("³ª´®°íµñ", Font.BOLD, 13));
		btnItemShop.setBounds(444, 365, 111, 23);
		mainPanel.add(btnItemShop);
		
		inUserTable = new JTable();
		inUserTable.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 13));
		inUserTable.setBounds(370, 27, 185, 182);
		mainPanel.add(inUserTable);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 27, 334, 149);
		mainPanel.add(textArea);
		
		JButton createRoomBtn = new JButton("¹æ ¸¸µé±â");
		createRoomBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 13));
		createRoomBtn.setBounds(70, 186, 222, 23);
		mainPanel.add(createRoomBtn);
		
	}
}
