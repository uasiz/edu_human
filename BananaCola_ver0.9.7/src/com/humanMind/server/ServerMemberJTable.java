package com.humanMind.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.humanMind.database.DbConn;
import com.humanMind.database.MemberDao;
import com.humanMind.database.MemberDto;

public class ServerMemberJTable extends JFrame implements ActionListener {
	
	Connection conn;
	Statement st;
	ResultSet rs;
	
	int maxNum = -1;
	int intRow = -1;
	Date date = new Date();
	String fileName = String.valueOf(date.getTime()) + "_member.txt";
	
	String[] header;
	String[][] contents;
	
	JPanel tab_1 = new JPanel();
	JPanel tab_1_inputP = new JPanel();

	JTextField[] inData = new JTextField[6];
	
	JPopupMenu popup;
	JMenuItem m_del, m_mod;
	
	
	// JTable의 컬럼 전체를 가져오고, 전체를 집어넣기 쉽다.
	DefaultTableModel tableModel;
	
	JTable table;
	
	JScrollPane tableScroll;

	MemberDao dao = MemberDao.getInstance();
	
	
	ServerMemberJTable() {
		
		conn = DbConn.getConnection();
		Dimension size = new Dimension(800, 400);  // 가로와 세로의 값을 저장하는 객체
		setLocation(300, 300);
		setTitle("맴버 관리 테이블");
		setSize(size);
		add(tab_1);
		tableSetting();
		createInputP();
		createTabbedP();
		menuLayout();  // 팝업 메뉴 (마우스 오른쪽 클릭시 뜨는 메뉴)
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public void createInputP() {
		tab_1_inputP.setLayout(new BoxLayout(tab_1_inputP, BoxLayout.X_AXIS));
		for (int i = 0; i < inData.length; i++) {
			tab_1_inputP.add(inData[i] = new JTextField());
		}
		JButton addB = new JButton("Add");
		JButton modB = new JButton("Mod");
		JButton delB = new JButton("Del");
		JButton savB = new JButton("Save");
		
		tab_1_inputP.add(addB);
		addB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				MemberDto dto = new MemberDto();
				
				dto.setId(inData[0].getText());
				dto.setPw(inData[1].getText());
				dto.setName(inData[2].getText());
				dto.setAddr(inData[3].getText());
				dto.setPhone(inData[4].getText());
				dto.setEmail(inData[5].getText());
				dao.insertMember(dto);
				
				reset();
			}
		});
		
		tab_1_inputP.add(modB);
		modB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				MemberDto dto = new MemberDto();
				
				dto.setId(inData[0].getText());
				dto.setPw(inData[1].getText());
				dto.setName(inData[2].getText());
				dto.setAddr(inData[3].getText());
				dto.setPhone(inData[4].getText());
				dto.setEmail(inData[5].getText());
				dao.update(dto);
				
				reset();
			}
		});
		
		tab_1_inputP.add(delB);
		delB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				dao.delete(inData[0].getText());
				
				reset();
				
			}
		});
		
		tab_1_inputP.add(savB);
		savB.addActionListener(e -> saveToText());
	}
	
	public void saveToText() {
		
		try {
			File file = new File(fileName);
			BufferedWriter fw = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0; i < table.getRowCount(); i++) {
				String data = "";
				for(int j = 0; j < table.getColumnCount(); j++) {
					 data += table.getValueAt(i, j) + "/";
				}
				fw.write(data+"\n");
			}
			fw.flush();
			fw.close();
			JOptionPane.showMessageDialog(this, "저장 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void createTabbedP() {
		tab_1.setLayout(new BorderLayout());
		tab_1.add(tableScroll, "Center");
		tab_1.add(tab_1_inputP, "South");
	}
	
	public void menuLayout() {
	
		popup = new JPopupMenu();
		m_del = new JMenuItem("Del");
		m_mod = new JMenuItem("Mod");
		popup.add(m_del);
		popup.add(m_mod);
		
		m_del.addActionListener(e -> {
			if (table.getSelectedRow() == -1) {
				return;
			} else {
				intRow = table.getSelectedRow();
				
				dao.delete((String) table.getValueAt(intRow, 0));
				intRow = -1;
				reset();

			}
		});
		
		m_mod.addActionListener(e -> {
			if (table.getSelectedRow() == -1) {
				return;
			} else {
				intRow = table.getSelectedRow();
				
				for(int i = 0; i < inData.length; i++) {
					inData[i].setText((String) table.getValueAt(intRow, i));
				}

				intRow = -1;

			}
		});
	}
	
	public void tableSetting() {
		
		ArrayList<MemberDto> list = dao.selectToArrayList();
		maxNum = list.size();
		header = new String[]{"ID", "PW", "이름", "주소", "전화번호", "이메일주소"};
		contents = new String[maxNum][6];
		for (int i = 0; i < list.size(); i++) {
			contents[i][0] = list.get(i).getId();
			contents[i][1] = list.get(i).getPw();
			contents[i][2] = list.get(i).getName();
			contents[i][3] = list.get(i).getAddr();
			contents[i][4] = list.get(i).getPhone();
			contents[i][5] = list.get(i).getEmail();
		}
		
		
		tableModel = new DefaultTableModel(contents,header);
		table = new JTable(tableModel);
		tableScroll = new JScrollPane(table);
		
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0);
		table.setShowVerticalLines(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// JTable 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 테이블셀 렌더러 객체를 생성.
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);              // 생성한 렌더러의 가로정렬을 CENTER로
		TableColumnModel tc = table.getColumnModel();
		for(int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(dtcr);
		}
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {  // 오른쪽 클릭
					int column = table.columnAtPoint(e.getPoint());
					int row = table.rowAtPoint(e.getPoint());
					table.changeSelection(row, column, false, false);
					popup.show(table, e.getX(), e.getY());
				}
			}
			
		});
	}
	
	public void delTableRow(int row) {
		tableModel.removeRow(row);
	}

	public void reset() {
		
		tableModel.setNumRows(0);
		
		ArrayList<MemberDto> list = dao.selectToArrayList();
		maxNum = list.size();
		header = new String[]{"ID", "PW", "이름", "주소", "전화번호", "이메일주소"};
		contents = new String[maxNum][6];
		for (int i = 0; i < list.size(); i++) {
			contents[i][0] = list.get(i).getId();
			contents[i][1] = list.get(i).getPw();
			contents[i][2] = list.get(i).getName();
			contents[i][3] = list.get(i).getAddr();
			contents[i][4] = list.get(i).getPhone();
			contents[i][5] = list.get(i).getEmail();
			tableModel.addRow(contents[i]);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
