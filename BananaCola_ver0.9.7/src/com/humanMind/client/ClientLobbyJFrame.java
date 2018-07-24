package com.humanMind.client;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ClientLobbyJFrame extends JFrame {

	private Socket socket;
	private Socket myInfoSock;
	private JPanel contentPane, mainPanel, inGamePanel;
	private JTextField chatTf, myIdTf, myCashTf, quizTf;
	private JTextArea roomListTa, chatTa, inGameChatTa;
	private JScrollPane roomListSp, chatSp, inGameChatSp;
	private JLabel answerL;

	private JFrame inGame;
	private JTextArea inMemberTa = new JTextArea();
	private String[] inUserListHeader = new String[] { "회원 아이디" };
	private String[][] inUserListCon;
	private DefaultTableModel tableModel = new DefaultTableModel(inUserListCon, inUserListHeader);
	private JTable inUserListT = new JTable(tableModel);

	private String[] inUserListHeader2 = new String[] { "방 번호", "방 이름" };
	private String[][] inUserListCon2;
	private DefaultTableModel tableModel2 = new DefaultTableModel(inUserListCon2, inUserListHeader2);
	private JTable gameRoomT = new JTable(tableModel2);

	private String id;

	private ClientMyInfoThread myInfo;
	private ClientChatThread chat = null;

	private ArrayList<String> inUserList;
	private HashMap<Integer, String> roomMap = new HashMap<>();
	private Vector<String> inRoomId = new Vector<>(); 

	private JPopupMenu popup, popupInRoomList;
	private JMenuItem m_to, roomEnter;

	private int intRow = -1;
	private String tempTo;

	private int x = 0;
	private int y = 0;
	private int size = 7;
	
	private Color color = Color.BLACK;
	
	private MyCan inGameCan;
	
	private String[] quizArray = new String[] { "사과", "수박", "컴퓨터", "마우스", "모니터", "포도", "자바", "책상", "의자", "가방", 
			"필통", "연필", "노트북", "칠판", "학교", "커피", "막대사탕", "배", "비행기", "기차",
			"자동차", "토끼", "거북이", "강아지", "다람쥐", "건물", "물건"};
	private String quiz = "";
	
	ClientLobbyJFrame(Socket s, Socket m, ClientChatThread chat, String id, Map<String, MemberSerial> myMap) {
		this.socket = s;
		this.myInfoSock = m;
		this.chat = chat;
		this.id = id;

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(200, 100, 593, 458);
		setTitle("HumanMind - 로비");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String logOutMsg = id + "님이 로그아웃하였습니다." + "\n";
				chat.setMsg(logOutMsg);
				chat.sendData();
				exit();
			}

			private void exit() {
				System.exit(0);
			}
		});

		setRobby();
		setVisible(true);

	}

	private void setRobby() {
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JLabel roomListL = new JLabel("방 목록 - Room List");
		roomListL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		roomListL.setBounds(12, 10, 152, 15);
		mainPanel.add(roomListL);

		gameRoomT.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		gameRoomT.setBounds(12, 27, 334, 149);

		gameRoomT.setRowMargin(0);
		gameRoomT.getColumnModel().setColumnMargin(0);
		gameRoomT.setShowVerticalLines(true);
		gameRoomT.getTableHeader().setReorderingAllowed(false);
		gameRoomT.getTableHeader().setResizingAllowed(false);
		gameRoomT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc2 = inUserListT.getColumnModel();
		for (int i = 0; i < tc2.getColumnCount(); i++) {
			tc2.getColumn(i).setCellRenderer(dtcr2);
		}

		mainPanel.add(gameRoomT);
		
		JButton createRoomBtn = new JButton("방 만들기");
		createRoomBtn.setFont(new Font("나눔고딕", Font.BOLD, 13));
		createRoomBtn.setBounds(70, 186, 222, 23);
		mainPanel.add(createRoomBtn);

		createRoomBtn.addActionListener(e -> {
			String roomName = JOptionPane.showInputDialog("방 이름을 입력하세요");
			if (roomName != null) {
				String msg = "CreateRoom/" + roomName;
				chat.setMsg(msg);
				chat.sendData();
			}
			
			clientGameRoomHostJFrame(roomName);
			chatTf.setEnabled(false);
			inGameChatTa.append("[ " + id + " ] 님께서 '" + roomName + "'방에 입장하셨습니다." + "\n");
			inGameChatTa.append("[ 메너 게임, 메너 채팅 해 주세요. ]" + "\n");
			inRoomIdWrite();
		});
		
		gameRoomT.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					int column = gameRoomT.columnAtPoint(e.getPoint());
					int row = gameRoomT.rowAtPoint(e.getPoint());
					gameRoomT.changeSelection(row, column, false, false);
					popupInRoomList.show(gameRoomT, e.getX(), e.getY());
				}
			}

		});
		
		popupInRoomList = new JPopupMenu();
		roomEnter = new JMenuItem("방 입장하기");
		popupInRoomList.add(roomEnter);
		
		roomEnter.addActionListener(e -> {
			
			if (gameRoomT.getSelectedRow() == -1) {
				return;
			} else {
				intRow = gameRoomT.getSelectedRow();
				int inSelNum = ((String) gameRoomT.getValueAt(intRow, 0)).charAt(0)-48;
				String inRoomName = (String) gameRoomT.getValueAt(intRow, 1);
				intRow = -1;
				chat.setRoomNum(inSelNum);
				clientGameRoomJFrame(inRoomName);
				inGameChatTa.append("[ " + id + " ] 님께서 " + inRoomName + "방에 입장하셨습니다." + "\n");
				String msg = "[" + id + "] 님께서 " + inRoomName + "방에 입장하셨습니다.";
				chat.setMsg(msg);
				chat.sendData();
				inGameChatTa.append("메너 게임, 메너 채팅 해 주세요." + "\n");
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				String protocol = "EnterRoomm/"+inSelNum+"/"+id;
				chat.setMsg(protocol);
				chat.sendData();
				chatTf.setEnabled(false);
			}
			
		});

		JLabel userListL = new JLabel("사용자 목록 - User List");
		userListL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		userListL.setBounds(370, 10, 152, 15);
		mainPanel.add(userListL);

		JLabel chatL = new JLabel("대화창 - Chatting Room");
		chatL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		chatL.setBounds(12, 238, 152, 15);
		mainPanel.add(chatL);

		chatSp = new JScrollPane();
		chatSp.setBounds(12, 254, 334, 107);
		mainPanel.add(chatSp);

		chatTa = new JTextArea();
		chatSp.setViewportView(chatTa);
		chatTa.setEditable(false);

		chatTf = new JTextField();
		chatTf.setBounds(12, 366, 334, 21);
		mainPanel.add(chatTf);
		chatTf.setColumns(10);
		chatTf.addActionListener(e -> {

			String msg = chatTf.getText();
			chat.setMsg(msg);
			chat.sendData();
			
			int start = -1;
			start = msg.indexOf("/");
			
			if (start == 0) {
				int end = msg.indexOf(" ", 4);
				String to = msg.substring(start + 4, end);
				String sendMsg = msg.substring(end + 1);
				chatTa.append("[ " + to + "에게 보내는 귓말 ] " + sendMsg + "\n");
				chatSp.getVerticalScrollBar().setValue(chatSp.getVerticalScrollBar().getMaximum());
				chatTf.setText("");
			} else {
				chatTa.append("[ " + id + " ] : " + msg + "\n");
				chatSp.getVerticalScrollBar().setValue(chatSp.getVerticalScrollBar().getMaximum());
				chatTf.setText("");
			}

		});

		JLabel imageL = new JLabel("My Image");
		imageL.setBackground(Color.BLUE);
		imageL.setBounds(370, 258, 89, 90);
		mainPanel.add(imageL);

		JLabel idL = new JLabel("아이디");
		idL.setBounds(471, 254, 57, 15);
		mainPanel.add(idL);

		myIdTf = new JTextField();
		myIdTf.setEditable(false);
		myIdTf.setBounds(470, 271, 85, 21);
		mainPanel.add(myIdTf);
		myIdTf.setText(id);
		myIdTf.setColumns(10);

		JLabel cashL = new JLabel("보유 캐쉬");
		cashL.setBounds(471, 306, 57, 15);
		mainPanel.add(cashL);

		myCashTf = new JTextField();
		myCashTf.setEditable(false);
		myCashTf.setColumns(10);
		myCashTf.setBounds(470, 323, 85, 21);
		mainPanel.add(myCashTf);

		JButton myIntroBtn = new JButton("내 정보");
		myIntroBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		myIntroBtn.setBounds(368, 365, 73, 23);
		myIntroBtn.addActionListener(e -> {
			myInfo = new ClientMyInfoThread(id, myInfoSock);
		});
		mainPanel.add(myIntroBtn);

		JButton btnItemShop = new JButton("Item Shop");
		btnItemShop.setBackground(Color.CYAN);
		btnItemShop.setFont(new Font("나눔고딕", Font.BOLD, 12));
		btnItemShop.setBounds(444, 365, 111, 23);
		mainPanel.add(btnItemShop);

		inUserListT.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		inUserListT.setBounds(370, 27, 185, 182);

		inUserListT.setRowMargin(0);
		inUserListT.getColumnModel().setColumnMargin(0);
		inUserListT.setShowVerticalLines(true);
		inUserListT.getTableHeader().setReorderingAllowed(false);
		inUserListT.getTableHeader().setResizingAllowed(false);
		inUserListT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = inUserListT.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(dtcr);
		}

		mainPanel.add(inUserListT);

		inUserAndGameList();

		inUserListT.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					int column = inUserListT.columnAtPoint(e.getPoint());
					int row = inUserListT.rowAtPoint(e.getPoint());
					inUserListT.changeSelection(row, column, false, false);
					popup.show(inUserListT, e.getX(), e.getY());
				}
			}

		});

		popup = new JPopupMenu();
		m_to = new JMenuItem("귓말보내기");
		popup.add(m_to);

		m_to.addActionListener(e -> {
			if (inUserListT.getSelectedRow() == -1) {
				return;
			} else {
				intRow = inUserListT.getSelectedRow();
				tempTo = "/to " + ((String) inUserListT.getValueAt(intRow, 0)) + " ";
				chatTf.setText(tempTo);
				chatTf.requestFocus();
				intRow = -1;
			}
		});
	}

	public void inRoomIdWrite() {
		inMemberTa.setText("");
		inRoomId = chat.getInRoomIdList();
		
		
		for (int i = 0; i < inRoomId.size(); i++) {
			if (inRoomId.get(i).equals(id)) {
				inMemberTa.append((i+1) + "번 : " + inRoomId.get(i) + "(나)" + "\n");
			} else {
				inMemberTa.append((i+1) + "번 : " + inRoomId.get(i) + "\n");
			}
		}
		
		
	}

	public void setMsg(String msg) {
		chatTa.append(msg + "\n");
		chatSp.getVerticalScrollBar().setValue(chatSp.getVerticalScrollBar().getMaximum());
	}
	
	public void setRoomMsg(String msg) {
		inGameChatTa.append(msg + "\n");
		inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
	}


	public void inUserAndGameList() {

		Runnable task1 = () -> {
			while (true) {

				inUserList = chat.getInGameId();
				tableModel.setNumRows(0);

				int maxNum = inUserList.size();
				inUserListCon = new String[maxNum][1];
				int tmpIndex = 0;
				for (int i = 0; i < inUserList.size(); i++) {
					if (id.equals(inUserList.get(i))) {
						inUserListCon[i][0] = inUserList.get(i) + "(나)";
						tableModel.addRow(inUserListCon[i]);
						tmpIndex = i;
						break;
					}
				}

				inUserList.remove(tmpIndex);

				for (int i = 0; i < inUserList.size(); i++) {
					inUserListCon[i][0] = inUserList.get(i);
					tableModel.addRow(inUserListCon[i]);

				}

				roomMap = chat.getRoomMap();
				tableModel2.setNumRows(0);

				if(roomMap != null) {
					int maxRoomNum = roomMap.size();
					inUserListCon2 = new String[maxRoomNum][2];
					int roomIndex = 0;
					for (int i : roomMap.keySet()) {
						inUserListCon2[roomIndex][0] = String.valueOf(roomIndex + 1) + "번 방";
						inUserListCon2[roomIndex][1] = roomMap.get(i);
						tableModel2.addRow(inUserListCon2[roomIndex]);
						roomIndex++;
					}
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}

		};

		Thread t1 = new Thread(task1);
		t1.start();

	}
	
	public void clientGameRoomHostJFrame(String roomName) {
	    inGame = new JFrame(roomName);
	    		
		JPanel inGameContentPane;
		JTextField inChatTf;
			
		inGame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		inGame.setBounds(100, 100, 890, 573);
		inGameContentPane = new JPanel();
		inGameContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		inGameContentPane.setLayout(new BorderLayout(0, 0));
		inGame.add(inGameContentPane);
		inGame.setTitle(" (게임 방 접속 중 - Host) " + roomName + " - " + id); 
		inGame.setResizable(false);
		inGame.setVisible(true);
		
		inGamePanel = new JPanel();
		inGameContentPane.add(inGamePanel, BorderLayout.CENTER);
		inGamePanel.setLayout(null);
		
		inMemberTa.setBounds(424, 10, 310, 187);
		inMemberTa.setEditable(false);
		inGamePanel.add(inMemberTa);

		inGameChatSp = new JScrollPane();
		inGameChatSp.setBounds(424, 211, 310, 199);
		inGamePanel.add(inGameChatSp);

		inGameChatTa = new JTextArea();
		inGameChatTa.setEditable(false);
		inGamePanel.add(inGameChatTa);

		inGameChatSp.setViewportView(inGameChatTa);

		
		inChatTf = new JTextField();
		inChatTf.setBounds(424, 420, 229, 39);
		inGamePanel.add(inChatTf);
		inChatTf.addActionListener(e -> {
			
			String msg = inChatTf.getText();
			chat.setMsg(msg);
			chat.sendData();
			
			int start = -1;
			start = msg.indexOf("/");
			
			if (start == 0) {
				int end = msg.indexOf(" ", 4);
				String to = msg.substring(start + 4, end);
				String sendMsg = msg.substring(end + 1);
				inGameChatTa.append("[ " + to + "에게 보내는 귓말 ] " + sendMsg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			} else {
				inGameChatTa.append("[ " + id + " ] : " + msg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			}
			
		
		});
		inChatTf.setColumns(10);
		
		JButton exitBtn = new JButton("나가기");
		exitBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		exitBtn.setBounds(746, 10, 100, 31);
		exitBtn.addActionListener(e -> {
			chatTf.setEnabled(true);
			String msg = "[" + id + "] 님께서 방을 나가셨습니다" + "\n";
			chat.setMsg(msg);
			chat.sendData();
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} 
			
			String protocol = "LeaveRoomm/"+id;
			chat.setMsg(protocol);
			chat.sendData();
			
			chat.setRoomNum(0);
			inGame.dispose();
		});
		inGamePanel.add(exitBtn);
		
		JButton policeBtn = new JButton("신고");
		policeBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		policeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		policeBtn.setBounds(746, 51, 100, 31);
		inGamePanel.add(policeBtn);
		
		JButton inGameChatBtn = new JButton("입력");
		inGameChatBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		inGameChatBtn.setBackground(UIManager.getColor("Button.background"));
		inGameChatBtn.addActionListener(e -> {
			
			String msg = inChatTf.getText();
			chat.setMsg(msg);
			chat.sendData();
			
			int start = -1;
			start = msg.indexOf("/");
			
			if (start == 0) {
				int end = msg.indexOf(" ", 4);
				String to = msg.substring(start + 4, end);
				String sendMsg = msg.substring(end + 1);
				inGameChatTa.append("[ " + to + "에게 보내는 귓말 ] " + sendMsg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			} else {
				inGameChatTa.append("[ " + id + " ] : " + msg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			}
			
		
		});
		inGameChatBtn.setBounds(665, 420, 69, 39);
		inGamePanel.add(inGameChatBtn);
		
		JButton readyBtn = new JButton("준비");
		readyBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		readyBtn.setBackground(UIManager.getColor("Button.background"));
		readyBtn.setBounds(424, 469, 148, 40);
		inGamePanel.add(readyBtn);
		
		JRadioButton normalRadio = new JRadioButton("보통 펜");
		normalRadio.setBackground(SystemColor.inactiveCaption);
		normalRadio.setBounds(320, 416, 69, 23);
		normalRadio.setSelected(true);
		normalRadio.addActionListener(e -> {
			size = 7;
		});
		inGamePanel.add(normalRadio);
		
		JRadioButton boldRadio = new JRadioButton("굵은 펜");
		boldRadio.setBackground(SystemColor.inactiveCaption);
		boldRadio.setBounds(320, 436, 69, 23);
		boldRadio.addActionListener(e -> {
			size = 15;
		});
		inGamePanel.add(boldRadio);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(normalRadio);
		radioGroup.add(boldRadio);
		
		JButton clearBtn = new JButton("모두 지우기");
		clearBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		clearBtn.setBackground(UIManager.getColor("Button.background"));
		clearBtn.addActionListener(e -> {
			inGameCan.clear();
			String protocol = "ClearPaint/1";
			chat.setMsg(protocol);
			chat.sendData();
		});
		clearBtn.setBounds(302, 469, 106, 40);
		inGamePanel.add(clearBtn);
		
		answerL = new JLabel("");
		answerL.setBackground(Color.CYAN);
		answerL.setFont(new Font("나눔고딕", Font.BOLD, 14));
		answerL.setBounds(12, 469, 278, 40);
		answerL.setOpaque(true);
		answerL.setHorizontalAlignment(SwingConstants.CENTER);
		inGamePanel.add(answerL);
		setQuiz();
		
		
		JButton startBtn = new JButton("시작");
		startBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		startBtn.setBackground(UIManager.getColor("Button.background"));
		startBtn.setBounds(584, 469, 150, 40);
		inGamePanel.add(startBtn);
		
		// 그림판 추가
		addCanvasHost();
		
		JButton colorBlackBtn = new JButton("");
		colorBlackBtn.setBackground(Color.BLACK);
		colorBlackBtn.setBounds(12, 420, 39, 39);
		colorBlackBtn.addActionListener(e -> {
			color = Color.BLACK;
			String protocol = "ColorPaint/Color.BLACK";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorBlackBtn);
		
		JButton colorRedBtn = new JButton("");
		colorRedBtn.setBackground(Color.RED);
		colorRedBtn.setBounds(63, 420, 39, 39);
		colorRedBtn.addActionListener(e -> {
			color = Color.RED;
			String protocol = "ColorPaint/Color.RED";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorRedBtn);
		
		JButton colorGreenBtn = new JButton("");
		colorGreenBtn.setBackground(Color.GREEN);
		colorGreenBtn.setBounds(114, 420, 39, 39);
		colorGreenBtn.addActionListener(e -> {
			color = Color.GREEN;
			String protocol = "ColorPaint/Color.GREEN";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorGreenBtn);
		
		JButton colorBlueBtn = new JButton("");
		colorBlueBtn.setBackground(Color.BLUE);
		colorBlueBtn.setBounds(165, 420, 39, 39);
		colorBlueBtn.addActionListener(e -> {
			color = Color.BLUE;
			String protocol = "ColorPaint/Color.BLUE";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorBlueBtn);
		
		JButton colorYellowBtn = new JButton("");
		colorYellowBtn.setBackground(Color.YELLOW);
		colorYellowBtn.setBounds(220, 420, 39, 39);
		colorYellowBtn.addActionListener(e -> {
			color = Color.YELLOW;
			String protocol = "ColorPaint/Color.YELLOW";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorYellowBtn);
		
		JButton colorWhiteBtn = new JButton("");
		colorWhiteBtn.setBackground(Color.WHITE);
		colorWhiteBtn.setBounds(271, 420, 39, 39);
		colorWhiteBtn.addActionListener(e -> {
			color = Color.WHITE;
			String protocol = "ColorPaint/Color.WHITE";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorWhiteBtn);
		
	}
	
	public void setQuiz() {
		Random ran = new Random();
		quiz = quizArray[ran.nextInt(quizArray.length)];
		answerL.setText("");
		answerL.setText(quiz);
		
	}

	// 방장 이외의 게임 참여자 게임방화면
	public void clientGameRoomJFrame(String roomName) {
	    inGame = new JFrame(roomName);
	    		
		JPanel inGameContentPane;
		JTextField inChatTf;
			
		inGame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		inGame.setBounds(100, 100, 890, 573);
		inGameContentPane = new JPanel();
		inGameContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		inGameContentPane.setLayout(new BorderLayout(0, 0));
		inGame.add(inGameContentPane);
		inGame.setTitle(" (게임 방 접속 중) " + roomName + " - " + id); 
		inGame.setResizable(false);
		inGame.setVisible(true);
		
		inGamePanel = new JPanel();
		inGameContentPane.add(inGamePanel, BorderLayout.CENTER);
		inGamePanel.setLayout(null);
		
		inMemberTa.setBounds(424, 10, 310, 187);
		inMemberTa.setEditable(false);
		inGamePanel.add(inMemberTa);

		inGameChatSp = new JScrollPane();
		inGameChatSp.setBounds(424, 211, 310, 199);
		inGamePanel.add(inGameChatSp);

		inGameChatTa = new JTextArea();
		inGameChatTa.setEditable(false);
		inGamePanel.add(inGameChatTa);

		inGameChatSp.setViewportView(inGameChatTa);

		
		inChatTf = new JTextField();
		inChatTf.setBounds(424, 420, 229, 39);
		inGamePanel.add(inChatTf);
		inChatTf.addActionListener(e -> {
			
			String msg = inChatTf.getText();
			chat.setMsg(msg);
			chat.sendData();
			
			int start = -1;
			start = msg.indexOf("/");
			
			if (start == 0) {
				int end = msg.indexOf(" ", 4);
				String to = msg.substring(start + 4, end);
				String sendMsg = msg.substring(end + 1);
				inGameChatTa.append("[ " + to + "에게 보내는 귓말 ] " + sendMsg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			} else {
				inGameChatTa.append("[ " + id + " ] : " + msg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			}
			
		});
		inChatTf.setColumns(10);
		
		JButton exitBtn = new JButton("나가기");
		exitBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		exitBtn.setBounds(746, 10, 100, 31);
		exitBtn.addActionListener(e -> {
			chatTf.setEnabled(true);
			String msg = "[" + id + "] 님께서 방을 나가셨습니다" + "\n";
			chat.setMsg(msg);
			chat.sendData();
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} 
			
			String protocol = "LeaveRoomm/"+id;
			chat.setMsg(protocol);
			chat.sendData();
			
			chat.setRoomNum(0);
			inGame.dispose();
		});
		inGamePanel.add(exitBtn);
		
		JButton policeBtn = new JButton("신고");
		policeBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		policeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		policeBtn.setBounds(746, 51, 100, 31);
		inGamePanel.add(policeBtn);
		
		JButton inGameChatBtn = new JButton("입력");
		inGameChatBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		inGameChatBtn.setBackground(UIManager.getColor("Button.background"));
		inGameChatBtn.addActionListener(e -> {
			
			String msg = inChatTf.getText();
			chat.setMsg(msg);
			chat.sendData();
			
			int start = -1;
			start = msg.indexOf("/");
			
			
			if (start == 0) {
				int end = msg.indexOf(" ", 4);
				String to = msg.substring(start + 4, end);
				String sendMsg = msg.substring(end + 1);
				inGameChatTa.append("[ " + to + "에게 보내는 귓말 ] " + sendMsg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			} else {
				inGameChatTa.append("[ " + id + " ] : " + msg + "\n");
				inGameChatSp.getVerticalScrollBar().setValue(inGameChatSp.getVerticalScrollBar().getMaximum());
				inChatTf.setText("");
			}
			
		});
		inGameChatBtn.setBounds(665, 420, 69, 39);
		inGamePanel.add(inGameChatBtn);
		
		JButton readyBtn = new JButton("준비");
		readyBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		readyBtn.setBackground(UIManager.getColor("Button.background"));
		readyBtn.setBounds(424, 469, 148, 40);
		inGamePanel.add(readyBtn);
		
		JRadioButton normalRadio = new JRadioButton("보통 펜");
		normalRadio.setBackground(SystemColor.inactiveCaption);
		normalRadio.setBounds(320, 416, 69, 23);
		normalRadio.setSelected(true);
		normalRadio.addActionListener(e -> {
			size = 7;
		});
		inGamePanel.add(normalRadio);
		normalRadio.setEnabled(false);
		
		JRadioButton boldRadio = new JRadioButton("굵은 펜");
		boldRadio.setBackground(SystemColor.inactiveCaption);
		boldRadio.setBounds(320, 436, 69, 23);
		boldRadio.addActionListener(e -> {
			size = 15;
		});
		inGamePanel.add(boldRadio);
		boldRadio.setEnabled(false);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(normalRadio);
		radioGroup.add(boldRadio);
		
		JButton clearBtn = new JButton("모두 지우기");
		clearBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		clearBtn.setBackground(UIManager.getColor("Button.background"));
		clearBtn.addActionListener(e -> {
			inGameCan.clear();
			String protocol = "ClearPaint/1";
			chat.setMsg(protocol);
			chat.sendData();
		});
		clearBtn.setBounds(302, 469, 106, 40);
		inGamePanel.add(clearBtn);
		clearBtn.setEnabled(false);
		
		JTextField answerTf = new JTextField();
		answerTf.setText("여기에 정답을 입력하세요");
		answerTf.setBounds(12, 469, 278, 40);
		answerTf.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	answerTf.setText("");
	        }

			@Override
			public void focusLost(FocusEvent arg0) {
				answerTf.setText("여기에 정답을 입력하세요");				
			}
	    });
		
		answerTf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					String hostId = inRoomId.get(0);
					String protocol = "SendAnswer/"+ hostId + "/" + id + "/"+answerTf.getText();
					chat.setMsg(protocol);
					chat.sendData();
				} 
			}
			
		});
		
		inGamePanel.add(answerTf);
		answerTf.setColumns(10);
		
		
		JButton startBtn = new JButton("시작");
		startBtn.setFont(new Font("나눔고딕", Font.BOLD, 14));
		startBtn.setBackground(UIManager.getColor("Button.background"));
		startBtn.setBounds(584, 469, 150, 40);
		inGamePanel.add(startBtn);
		
		// 그림판 추가
		addCanvas();
		
		JButton colorBlackBtn = new JButton("");
		colorBlackBtn.setBackground(Color.BLACK);
		colorBlackBtn.setBounds(12, 420, 39, 39);
		colorBlackBtn.addActionListener(e -> {
			color = Color.BLACK;
			String protocol = "ColorPaint/Color.BLACK";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorBlackBtn);
		colorBlackBtn.setEnabled(false);
		
		JButton colorRedBtn = new JButton("");
		colorRedBtn.setBackground(Color.RED);
		colorRedBtn.setBounds(63, 420, 39, 39);
		colorRedBtn.addActionListener(e -> {
			color = Color.RED;
			String protocol = "ColorPaint/Color.RED";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorRedBtn);
		colorRedBtn.setEnabled(false);
		
		JButton colorGreenBtn = new JButton("");
		colorGreenBtn.setBackground(Color.GREEN);
		colorGreenBtn.setBounds(114, 420, 39, 39);
		colorGreenBtn.addActionListener(e -> {
			color = Color.GREEN;
			String protocol = "ColorPaint/Color.GREEN";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorGreenBtn);
		colorGreenBtn.setEnabled(false);
		
		JButton colorBlueBtn = new JButton("");
		colorBlueBtn.setBackground(Color.BLUE);
		colorBlueBtn.setBounds(165, 420, 39, 39);
		colorBlueBtn.addActionListener(e -> {
			color = Color.BLUE;
			String protocol = "ColorPaint/Color.BLUE";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorBlueBtn);
		colorBlueBtn.setEnabled(false);
		
		JButton colorYellowBtn = new JButton("");
		colorYellowBtn.setBackground(Color.YELLOW);
		colorYellowBtn.setBounds(220, 420, 39, 39);
		colorYellowBtn.addActionListener(e -> {
			color = Color.YELLOW;
			String protocol = "ColorPaint/Color.YELLOW";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorYellowBtn);
		colorYellowBtn.setEnabled(false);
		
		JButton colorWhiteBtn = new JButton("");
		colorWhiteBtn.setBackground(Color.WHITE);
		colorWhiteBtn.setBounds(271, 420, 39, 39);
		colorWhiteBtn.addActionListener(e -> {
			color = Color.WHITE;
			String protocol = "ColorPaint/Color.WHITE";
			chat.setMsg(protocol);
			chat.sendData();
		});
		inGamePanel.add(colorWhiteBtn);
		colorWhiteBtn.setEnabled(false);
		
		answerL = new JLabel("");
		answerL.setBounds(0,0,0,0);
		inGamePanel.add(answerL);
		
		
	}
	
	public void clearCan() {
		inGameCan.clear();
	}
	
	public void setPaint(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void setColor(String c) {

		if (c.equals("Color.BLACK")) {
			color = Color.BLACK;
		} else if (c.equals("Color.RED")) {
			color = Color.RED;
		} else if (c.equals("Color.GREEN")) {
			color = Color.GREEN;
		} else if (c.equals("Color.BLUE")) {
			color = Color.BLUE;
		} else if (c.equals("Color.YELLOW")) {
			color = Color.YELLOW;
		} else if (c.equals("Color.WHITE")) {
			color = Color.WHITE;
		} 
	}

	private void addCanvasHost() {
		inGameCan = new MyCan();

		inGameCan.setBounds(12, 10, 377, 390);
		inGameCan.setBackground(Color.WHITE);
		inGameCan.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				inGameCan.repaint();
				String protocol = x+"/"+y+"/"+size;
				chat.sendPaintXY(protocol);
			}
		});

		inGamePanel.add(inGameCan);
		
		inPaintT();
	}
	
	private void addCanvas() {
		inGameCan = new MyCan();

		inGameCan.setBounds(12, 10, 377, 390);
		inGameCan.setBackground(Color.WHITE);
		inGamePanel.add(inGameCan);
		chat.receivePaintXY();
		inPaintT();
	}


	public void inPaintT() {
		Runnable task = () -> {
			
			while(true) {
				inGameCan.repaint();
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		Thread t = new Thread(task);  
		t.start();
	}


	class MyCan extends Canvas {
		@Override
		public void paint(Graphics g) {
			g.setColor(color);
			g.fillOval(x, y, size, size);
		}

		@Override
		public void update(Graphics g) {
			paint(g);
		}
		
		public void clear() {
			setBounds(12, 10, 377, 391);
			setBounds(12, 10, 377, 390);
		}
	}


	public Boolean answerCheck(String answer) {
		boolean inAnswer = false;
		
		if (quiz.equals(answer)) {
			inAnswer = true;
		} else {
			inAnswer = false;
		}
		
		return inAnswer;
	}
	
	public void setPopup(String t, String s) {
		JLabel startMessage = new JLabel(s);
		startMessage.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		JOptionPane.showMessageDialog(inGame, startMessage, t, JOptionPane.INFORMATION_MESSAGE);

	}
}