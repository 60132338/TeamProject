package frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.CSConstants;

// 모든 패널들을 화면에 띄워주는 프레임 클래스
public class CSMainFrame extends JFrame {
	private static CSMainFrame uniqueMainFrame = new CSMainFrame(CSConstants.TITLE_MAIN);
	
	private CSStartPanel startPanel;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	
	private CSMainFrame(String title){
		super(title);
		
		startPanel = new CSStartPanel();
		
		this.getContentPane().add(startPanel);
	}
	
	public static CSMainFrame getInstance(){
		return uniqueMainFrame;
	}
	
	// 화면을 바꿔주는 메소드
	public void changePanel(JPanel panel){
		this.getContentPane().removeAll();
		this.getContentPane().add(panel);
		revalidate();
		repaint();
	}
	
	// 화면을 띄워주는 메소드
	public void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(CSConstants.WIDTH_MAIN,CSConstants.HEIGHT_MAIN);
		this.setLocation(screenSize.width/2-CSConstants.WIDTH_MAIN/2, screenSize.height/2-CSConstants.HEIGHT_MAIN/2);
		this.setVisible(true);
	}
}
