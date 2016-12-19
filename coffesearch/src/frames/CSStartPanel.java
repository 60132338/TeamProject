package frames;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import constants.CSConstants;

// 시작화면
public class CSStartPanel extends JPanel{
	private BorderLayout layout = new BorderLayout();
	
	private Image image;
	private JLabel empty;
	private JButton start;
	private ButtonHandler btnHandler;
	
	private CSMainPanel mainpanel;
	
	public CSStartPanel(){
		super();
		
		this.setBackground(CSConstants.BACKGROUND_COLOR);
		this.setLayout(layout);
		start = new JButton("시작하기");
		empty = new JLabel();
		image = new ImageIcon("images/main.jpg").getImage();
		btnHandler = new ButtonHandler();
		start.addActionListener(btnHandler);
		this.add("Center",empty);
		this.add("South",start);
	}
	
	public JButton getStart() {
		return start;
	}

	// 배경화면에 이미지를 넣어주는 메소드
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}
	
	// 시작하기 버튼의 이벤트를 담당하는 핸들러
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			mainpanel = new CSMainPanel();
			CSMainFrame.getInstance().changePanel(mainpanel);
			
		}
		
	}
}
