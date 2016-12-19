package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import constants.CSConstants;
import database.SearchData;
import database.SelectFromCoffee;

public class CSMainPanel extends JPanel {
	private BorderLayout mainlayout = new BorderLayout();
	private BorderLayout sublayout = new BorderLayout();
	
	private CSBrandSearchPanel brandpanel;
	private CSCoffeeSearchPanel coffeepanel;
	private CSDetailPanel detailpanel;

	private ArrayList<SearchData> list;
	private SelectFromCoffee selectCoffee;

	private Choice select;
	private JTextField search;
	private JButton searchButton;
	private JPanel searchPanel;
	private JLabel name;
	
	private JScrollPane scrollpane;
	private JTable table;

	private Vector<String> tableColumn;
	private DefaultTableModel model;
	private Vector<Object> tablerow;
	
	private ButtonHandler buttonhandler;
	private MouseListener mouselistener;
	
	public CSMainPanel() {
		super();
		list = new ArrayList<SearchData>();
		selectCoffee = new SelectFromCoffee();
		this.setBackground(CSConstants.BACKGROUND_COLOR);
		this.setLayout(mainlayout);
		
		buttonhandler = new ButtonHandler();
		mouselistener = new MouseListener();
		
		
		select = new Choice();
		search = new JTextField(15);
		searchButton = new JButton("search");
		searchPanel = new JPanel();
		name = new JLabel("Item List");
		setTable();

		searchPanel.setLayout(sublayout);

		select.add("brand");
		select.add("coffee");

		this.add("North", name);
		this.add("Center", scrollpane);
		this.add("South", searchPanel);
		searchPanel.add("West", select);
		searchPanel.add("Center", search);
		searchPanel.add("East", searchButton);
		
		select.addItemListener(buttonhandler);
		searchButton.addActionListener(buttonhandler);
		table.addMouseListener(mouselistener);
		
	}

	public JTextField getSearch() {
		return search;
	}

	// 디비에서 가져온 데이터 리스트를 ArrayList에 저장하는 메소드
	public void getList() {
		SearchData data;
		ResultSet result = selectCoffee.searchAll();
		try {
			while (result.next()) {
				data = new SearchData(result);
				list.add(data);
			}
		} catch (SQLException e) {
			System.err.println("Select 오류: " + e.getMessage());
		}
	}
	
	// list에 저장된 정보를 table로 만들어주는 메소드
	public void setTable(){
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
		
		getList();
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
	
	//Collections.sort를 사용하기 위하여 만든 비교함수
	private static class CalAscCompare implements Comparator<SearchData>{

		@Override
		public int compare(SearchData arg0, SearchData arg1) {
			// TODO Auto-generated method stub
			return arg0.getCoffeeCal() < arg1.getCoffeeCal() ? -1 : arg0.getCoffeeCal() > arg1.getCoffeeCal() ? 1:0;
		}
		
	}
	
	// 아래쪽 드롭다운과 검색창의 이벤트를 담당하는 핸들러
	private class ButtonHandler implements ActionListener, ItemListener{
		String item = "brand";
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(item.equals("brand")){
				brandpanel = new CSBrandSearchPanel(getSearch().getText());
				CSMainFrame.getInstance().changePanel(brandpanel);
			}else if(item.equals("coffee")){
				coffeepanel = new CSCoffeeSearchPanel(getSearch().getText());
				CSMainFrame.getInstance().changePanel(coffeepanel);
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getItem().equals("brand")){
				item = "brand";
			}else if(e.getItem().equals("coffee")){
				item = "coffee";
			}
		}
		
	}
	
	// 테이블을 선택했을때 상세페이지를 나타내주는 마우스 리스너
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
