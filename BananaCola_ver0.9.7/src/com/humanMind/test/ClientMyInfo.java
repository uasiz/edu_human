package com.humanMind.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientMyInfo extends JFrame {

	private JPanel contentPane;
	private JTextField idTf;
	private JTextField nameTf;
	private JTextField addrTf;
	private JTextField phoneTf;
	private JTextField eMailTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMyInfo frame = new ClientMyInfo();
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
	public ClientMyInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
		mainP.add(idTf);
		idTf.setColumns(10);
		
		nameTf = new JTextField();
		nameTf.setEditable(false);
		nameTf.setColumns(10);
		nameTf.setBounds(92, 52, 163, 21);
		mainP.add(nameTf);
		
		addrTf = new JTextField();
		addrTf.setEditable(false);
		addrTf.setColumns(10);
		addrTf.setBounds(92, 90, 163, 21);
		mainP.add(addrTf);
		
		phoneTf = new JTextField();
		phoneTf.setEditable(false);
		phoneTf.setColumns(10);
		phoneTf.setBounds(92, 128, 163, 21);
		mainP.add(phoneTf);
		
		eMailTf = new JTextField();
		eMailTf.setEditable(false);
		eMailTf.setColumns(10);
		eMailTf.setBounds(92, 166, 163, 21);
		mainP.add(eMailTf);
		
		JButton editBtn = new JButton("\uC815\uBCF4 \uC218\uC815");
		editBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		editBtn.setBounds(24, 205, 104, 49);
		mainP.add(editBtn);
		
		JButton applyBtn = new JButton("\uD655\uC778");
		applyBtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		applyBtn.setBounds(151, 205, 104, 49);
		mainP.add(applyBtn);
	}

}
