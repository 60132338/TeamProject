package frames;

//프로그램을 켜는 런처 클래스
public class CSLauncher {
	public static void main(String[] args) {
		CSMainFrame frame = CSMainFrame.getInstance();
		frame.init();
	}
}


/*
 * 각 패널들의 LAYOUT과 버튼 디자인은 강신희, 장현지가 코딩을 하였고 
 * 그외 나머지 부분인 데이터베이스와 연결되는 코드, 메뉴를 패널에 띄우는  table 코드, 각 버튼과 마우스 리스너를 
 * 허승범, 김동현이 코딩을 하였습니다. 
 * 
 * */
