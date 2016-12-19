package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import constants.CSConstants;
import database.SearchData;
import database.SelectFromCoffee;


// 메인페이지, 커피검색페이지, 브랜드검색페이지에서 테이블의 row를 선택했을때 나오는 상세페이지
public class CSDetailPanel extends JPanel{
	private BorderLayout main = new BorderLayout();
	//private BorderLayout down = new BorderLayout();
	private GridLayout grid = new GridLayout(1, 2);
	
	private SelectFromCoffee selectCoffee;
	private SearchData data;
	
	private JPanel imgPanel; 
	private JPanel detailPanel; 
	private JPanel Panel;
	
	private JButton mainBtn; 
	
	private ImageIcon temp;
	
	private JLabel name;
	
	private JLabel brand;
	private JLabel menu;
	private JLabel cal;
	private JLabel size;
	private JLabel price;
	
	private CSMainPanel mainpanel;
	private ButtonHandler buttonhandler;
	
	public CSDetailPanel(String searchbrand, String searchmenu) {
		super();
		
		this.setBackground(CSConstants.BACKGROUND_COLOR);
		this.setLayout(main);
		buttonhandler = new ButtonHandler();
		selectCoffee = new SelectFromCoffee();
		
		imgPanel = new JPanel();
		detailPanel = new JPanel();
		Panel = new JPanel();
		
		mainBtn = new JButton("Main");
		name = new JLabel("Detail Page");
		
		getData(searchbrand, searchmenu);
		
		this.add("North",name);
		this.add("Center",Panel);
		Panel.setLayout(grid);
		Panel.add(imgPanel);
		Panel.add(detailPanel);
		
		detailPanel.setLayout(new GridLayout(5, 1));
		brand = new JLabel("브랜드: "+data.getCoffeeBrand());
		menu = new JLabel("메뉴: "+data.getCoffeeMenu());
		cal = new JLabel("칼로리: "+data.getCoffeeCal());
		size = new JLabel("사이즈: "+data.getCoffeeSize());
		price = new JLabel("가격: "+data.getCoffeePrice());
		detailPanel.add(brand);
		detailPanel.add(menu);
		detailPanel.add(cal);
		detailPanel.add(size);
		detailPanel.add(price);
		
		
		this.add("South",mainBtn);
		
		mainBtn.addActionListener(buttonhandler);
		
		temp = new ImageIcon(data.getCoffeeImage()+".jpg");
		JLabel templabel = new JLabel(temp);
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add("Center",templabel);
		
		
	}
	
	// 브랜드이름과 메뉴이름을 통해 한개의 데이터를 가져오는 메소드
	public void getData(String brand, String menu){
		ResultSet result = selectCoffee.searchToDetail(brand,menu);
		try {
			while (result.next()) {
				data = new SearchData(result);
			}
		} catch (SQLException e) {
			System.err.println("Select 오류: " + e.getMessage());
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
}
