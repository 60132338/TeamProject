package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constants.CSConstants;
import database.SearchData;
import database.SelectFromCoffee;

// 메인페이지에서 브랜드를 검색하였을때 나타나는 패널.
public class CSBrandSearchPanel extends JPanel {
	private JLabel title; 
	private JButton mainBtn;
	
	private ArrayList<SearchData> list;
	private SelectFromCoffee selectCoffee;
	
	private JScrollPane scrollpane;
	private JTable table;

	private Vector<String> tableColumn;
	private DefaultTableModel model;
	private Vector<Object> tablerow;
	
	private CSMainPanel mainpanel;
	private CSDetailPanel detailPanel;
	
	private ButtonHandler buttonhandler;
	
	private MouseListener mouselistener;
	private CSDetailPanel detailpanel;
	
	public CSBrandSearchPanel(String word) {
		super();
		list = new ArrayList<SearchData>();
		selectCoffee = new SelectFromCoffee();
		buttonhandler = new ButtonHandler();
		
		this.setBackground(CSConstants.BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
		
		mouselistener = new MouseListener();
		
		title = new JLabel("브랜드 검색 페이지");
		mainBtn = new JButton("Main");
		
		setTable(word);
		
		this.add("North", title);
		this.add("Center",scrollpane);
		this.add("South", mainBtn);
		
		mainBtn.addActionListener(buttonhandler);
		table.addMouseListener(mouselistener);
	}
	
	// 검색된 브랜드스트링을 통해 데이터베이스에서 검색되어져온 데이터들을 ArrayList에 저장하는 메소드
	public void getList(String brand){
		SearchData data;
		ResultSet result = selectCoffee.searchToBrand(brand);
		try {
			while (result.next()) {
				data = new SearchData(result);
				list.add(data);
			}
		} catch (SQLException e) {
			System.err.println("Select 오류: " + e.getMessage());
		}
	}
	
	// ArrayList에 저장된 데이터를 table로 만들어주는 메소드
	public void setTable(String brand){
		tableColumn = new Vector<String>();
		tableColumn.addElement("Brand");
		tableColumn.addElement("Menu");
		tableColumn.addElement("Calorie");
		tableColumn.addElement("Price");
		
		model = new DefaultTableModel(tableColumn, 0);
		table = new JTable(model);
		
		table.getColumn("Brand").setPreferredWidth(15);
		table.getColumn("Calorie").setPreferredWidth(4);
		table.getColumn("Price").setPreferredWidth(6);
		
		getList(brand);
		Collections.sort(list, new CalAscCompare());
		
		for(int i =0;i<list.size();i++){
			tablerow = new Vector<Object>();
			tablerow.addElement(list.get(i).getCoffeeBrand());
			tablerow.addElement(list.get(i).getCoffeeMenu());
			tablerow.addElement(list.get(i).getCoffeeCal());
			tablerow.addElement(list.get(i).getCoffeePrice());
			model.addRow(tablerow);
		}
		
		scrollpane = new JScrollPane(table);
	}
	
	// Collections.sort를 사용하기 위하여 만든 비교메소드
	private static class CalAscCompare implements Comparator<SearchData>{

		@Override
		public int compare(SearchData arg0, SearchData arg1) {
			// TODO Auto-generated method stub
			return arg0.getCoffeeCal() < arg1.getCoffeeCal() ? -1 : arg0.getCoffeeCal() > arg1.getCoffeeCal() ? 1:0;
		}
		
	}
	
	// main버튼의 이벤트를 담당하는 핸들러
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainpanel = new CSMainPanel();
			CSMainFrame.getInstance().changePanel(mainpanel);
		}
		
	}
	
	// table을 선택하여 상세페이지를 보여주는 마우스 리스너
	private class MouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getButton() == 1){
				JTable target = (JTable)e.getSource();
				int row = target.getSelectedRow();
				String menu = (String)target.getValueAt(row, 1);
				String brand = (String)target.getValueAt(row, 0);
				detailpanel = new CSDetailPanel(brand, menu);
				CSMainFrame.getInstance().changePanel(detailpanel);
			}
		}
		
	}
}
