package com.humanMind.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.humanMind.client.InGameMemberId;
import com.humanMind.database.ServerTimeDao;
import com.humanMind.database.ServerTimeDto;

public class ServerStartJFrame extends JFrame {

	private SimpleDateFormat dayTime = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	private long serverStartTimeLong = 0;
	private String serverStartTime = "";
	private long serverEndTimeLong = 0;
	private String serverEndTime = "";
	private long totalTimeLong = 0;
	private String totalTime = "";

	private JPanel contentPane, contentPane1, mainP;
	private JButton startBtn, memberBtn, endBtn;
	private JTextArea serverLogTa, mainChatTa;

	private JScrollPane serverLogSp, mainChatSp;

	private String[] inUserListHeader = new String[] { "회원 아이디" };
	private String[][] inUserListCon;
	private DefaultTableModel tableModel = new DefaultTableModel(inUserListCon, inUserListHeader);
	private JTable inUserListT = new JTable(tableModel);

	private JTextField mainChatTf;
	private ServerTimeDao sTimeDao = ServerTimeDao.getInstance();
	private ServerTimeDto sTimeDto;

	private ServerLobbyThreadIOController chat = null;

	private String chatMsg = "";

	private ServerInUser sInUser;
	private Map<String, ServerLobbyThreadIOController> inUserMap;
	private ArrayList<InGameMemberId> inUserList = new ArrayList<>();

	private int roomNum = 0;
	private HashMap<Integer, String> roomMap = new HashMap<>();

	// 서버 메인 GUI는 싱글톤 기법으로 한번만 실행됩니다.
	static private ServerStartJFrame serverStartJFrame = new ServerStartJFrame();

	public static ServerStartJFrame getInstance() {
		return serverStartJFrame;
	}

	private ServerStartJFrame() {
		sInUser = ServerInUser.getInstance();
		inUserMap = sInUser.getInUser();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 400, 450, 300);
		setTitle("HumanMind 서버 관리 프로그램");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);
		setVisible(true);

		startBtnMenu();
	}

	private void startBtnMenu() {

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
		startBtn.addActionListener(e -> {
			serverStartTimeLong = System.currentTimeMillis();
			serverStartTime = dayTime.format(new Date(serverStartTimeLong));
			normalMsg("서버를 시작합니다!", "<html><b>서버를 시작합니다!</b><br>서버 시작 시간은 <b>" + serverStartTime + "</b> 입니다.</html>");

			Runnable task1 = () -> {
				new ServerAcceptClass();
			};

			Thread t1 = new Thread(task1);
			t1.start();

			startBtn.setEnabled(false);
			memberBtn.setEnabled(false);
			endBtn.setEnabled(true);
			mainChatTf.setEditable(true);

			inLog("서버 시작" + "\n");
		});
		serverStartP.add(startBtn);

		memberBtn = new JButton("회원 관리 메뉴");
		memberBtn.setForeground(Color.BLACK);
		memberBtn.setFont(new Font("나눔고딕", Font.BOLD, 22));
		memberBtn.setBounds(158, 10, 184, 41);
		memberBtn.addActionListener(e -> {

			new ServerMemberJTable();

		});
		serverStartP.add(memberBtn);

		endBtn = new JButton("서버 종료");
		endBtn.setBackground(Color.DARK_GRAY);
		endBtn.setForeground(Color.RED);
		endBtn.setFont(new Font("나눔고딕", Font.BOLD, 22));
		endBtn.setBounds(354, 10, 136, 41);
		endBtn.setEnabled(false);
		endBtn.addActionListener(e -> {

			String s = "<html>정말 서버를 종료하시겠습니까? <br>서버를 종료하시려면 다음을 입력하세요 : <br><b>Stop Server</b></html>";
			JLabel endMessage = new JLabel(s);
			endMessage.setFont(new Font("나눔고딕", Font.PLAIN, 18));

			try {
				if (JOptionPane.showInputDialog(endMessage).equals("Stop Server")) {
					serverEndTimeLong = System.currentTimeMillis();
					serverEndTime = dayTime.format(new Date(serverEndTimeLong));
					totalTimeLong = serverEndTimeLong - serverStartTimeLong;
					int timeHour = (int) ((totalTimeLong / (1000 * 60 * 60)) % 24);
					int timeMinute = (int) ((totalTimeLong / (1000 * 60)) % 60);
					int timeSecond = (int) ((totalTimeLong / (1000)) % 60);
					totalTime = timeHour + "시간 " + timeMinute + "분" + timeSecond + "초";

					alertMsg("서버를 종료합니다!",
							"<html>서버 시작 시간은 <b>" + serverStartTime + "</b> 입니다.<br><br>" + "서버 종료 시간은 <b>"
									+ serverEndTime + "</b>입니다.<br><br> 총 서버 운영 시간은 <b>" + totalTime + "</b> 입니다.");
					sTimeDto = new ServerTimeDto();
					sTimeDto.setStartTime(serverStartTime);
					sTimeDto.setEndTime(serverEndTime);
					sTimeDto.setTotalTime(totalTime);
					if (sTimeDao.insertTime(sTimeDto)) {
						System.out.println("서버 시간 데이터베이스 저장 완료");
					} else {
						System.out.println("서버 시간 데이터베이스 저장 실패!! 관리자에게 문의하세요.");
					}
					inLog("서버 종료" + "\n");
					System.exit(0);
				} else {
					alertMsg("올바르지 않은 문자 입력!", "<html><b>문자 입력이 올바르지 않습니다.</b><br><br>서버를 종료하시려면 정확한 문자를 입력하세요.<br>");
				}
			} catch (Exception e2) {

			}

		});
		serverStartP.add(endBtn);

		mainP = new JPanel();
		mainP.setBounds(12, 61, 478, 453);
		serverStartP.add(mainP);
		mainP.setLayout(null);

		JLabel serverLogL = new JLabel("서버 로그");
		serverLogL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		serverLogL.setBounds(12, 10, 123, 27);
		mainP.add(serverLogL);

		serverLogSp = new JScrollPane();
		serverLogSp.setBounds(12, 33, 256, 247);
		mainP.add(serverLogSp);

		serverLogTa = new JTextArea();
		serverLogSp.setViewportView(serverLogTa);
		serverLogTa.setFont(new Font("함초롬돋움", Font.PLAIN, 11));
		serverLogTa.setEditable(false);

		JLabel inUserL = new JLabel("접속 유저 목록");
		inUserL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		inUserL.setBounds(280, 10, 123, 27);
		mainP.add(inUserL);

		JLabel mainChatL = new JLabel("서버 메인 채팅");
		mainChatL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		mainChatL.setBounds(12, 290, 123, 27);
		mainP.add(mainChatL);

		mainChatSp = new JScrollPane();
		mainChatSp.setBounds(12, 311, 256, 104);
		mainP.add(mainChatSp);

		mainChatTa = new JTextArea();
		mainChatSp.setViewportView(mainChatTa);
		mainChatTa.setFont(new Font("함초롬돋움", Font.PLAIN, 11));
		mainChatTa.setEditable(false);

		inUserListT.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		inUserListT.setBounds(284, 33, 182, 410);

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

		mainP.add(inUserListT);

		inUserList();

		mainChatTf = new JTextField();
		mainChatTf.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
		mainChatTf.setBounds(12, 425, 256, 21);
		mainP.add(mainChatTf);
		mainChatTf.setEditable(false);
		mainChatTf.setColumns(10);
		mainChatTf.addActionListener(e -> {
			chatMsg = mainChatTf.getText();
			mainChatTa.append("[ 관리자 ] : " + chatMsg + "\n");
			mainChatSp.getVerticalScrollBar().setValue(mainChatSp.getVerticalScrollBar().getMaximum());
			inLog("[ 관리자 ] : " + chatMsg + "\n");
			sendAll("", "[ 관리자 ] : 9 " + chatMsg);
			mainChatTf.setText("");
		});

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

	public void inLog(String s) {
		long logTimeLong = System.currentTimeMillis();
		String logTimeString = dayTime.format(new Date(logTimeLong));

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("log.txt", true));
			String log = "[ " + logTimeString + " ]  " + s;
			serverLogTa.append(log);
			serverLogSp.getVerticalScrollBar().setValue(serverLogSp.getVerticalScrollBar().getMaximum());
			out.write(log);
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inUserList() {

		Runnable task1 = () -> {
			while (true) {
				inUserMap = sInUser.getInUser();
				tableModel.setNumRows(0);

				int maxNum = inUserMap.size();
				int i = 0;
				inUserListCon = new String[maxNum][1];
				for (String id : inUserMap.keySet()) {
					inUserListCon[i][0] = id;
					tableModel.addRow(inUserListCon[i]);
					i++;
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

	public void mainChatTa(String id, String memo) {

		if (memo.indexOf("/to") == 0) {
			sendTo(id, memo);
		} else {
			String allMsg = "[ " + id + " ] : " + memo;
			mainChatTa.append(allMsg);
			mainChatSp.getVerticalScrollBar().setValue(mainChatSp.getVerticalScrollBar().getMaximum());
			sendAll("", allMsg);
		}

	}

	public void sendAll(String id, String msg) {
		for (String key : inUserMap.keySet()) {
			if (!key.equals(id)) {
				inUserMap.get(key).setMsg(msg);
				inUserMap.get(key).sendData();
			}
		}
	}

	public void sendTo(String id, String memo) { // /to aaa 메세지가 memo로 들어옴
		inUserMap.get(id).setMsg(memo);
		inUserMap.get(id).sendData();

	}

	public void inChat(String s) {
		mainChatTa.append("[ 관리자 ] : " + s + "\n");
		mainChatSp.getVerticalScrollBar().setValue(mainChatSp.getVerticalScrollBar().getMaximum());
	}

	public void inChat(String id, String s) {
		int roomNum = Character.getNumericValue(s.charAt(0));
		String outMsg = s.substring(1);
		if (roomNum == 0) {
			mainChatTa.append("[ " + id + " ] : " + "(lobby) " + outMsg);
			mainChatSp.getVerticalScrollBar().setValue(mainChatSp.getVerticalScrollBar().getMaximum());
			inLog("[ " + id + " ] : " + "(lobby) " + outMsg);
		} else {
			mainChatTa.append("[ " + id + " ] : " + "( " + roomNum + " 번 방) " + outMsg);
			mainChatSp.getVerticalScrollBar().setValue(mainChatSp.getVerticalScrollBar().getMaximum());
			inLog("[ " + id + " ] : " + "( " + roomNum + " 번 방) " + outMsg);
		}

	}

	public ArrayList<String> getInUserList() {

		ArrayList<String> inUserList = new ArrayList<>();

		for (String id : inUserMap.keySet()) {
			inUserList.add(id);
		}

		return inUserList;

	}

	public ArrayList<InetAddress> getIp() {
		ArrayList<InetAddress> inIp = new ArrayList<>();

		for (String key : inUserMap.keySet()) {
			inIp.add(inUserMap.get(key).getIp());
		}

		return inIp;
	}

	public void setInUserMap(String id, ServerLobbyThreadIOController s) {
		inUserMap.put(id, s);
	}
	
	public void setInUserList(String id, String ia, int port) throws UnknownHostException {
		InGameMemberId inMem = new InGameMemberId(id, ia, port);
		inUserList.add(inMem);
	}
	
	public ArrayList<InGameMemberId> getUserList() {
		
		return inUserList;
	}
	

	public synchronized int makeRoom(String roomName) {
		
		roomNum += 1;
		
		roomMap.put(roomNum, roomName);
		
		
		return this.roomNum;
	}
	
	public synchronized HashMap<Integer, String> getRoomMap() {
		return this.roomMap;
	}
	

}
