package constants;

import java.awt.Color;
import java.util.Comparator;

import database.SearchData;

public class CSConstants {
	// MainFrame
	public static final int WIDTH_MAIN = 500;
	public static final int HEIGHT_MAIN = 500;
	public static final String TITLE_MAIN = "Coffee Searcher";

	public static final Color BACKGROUND_COLOR = Color.WHITE;

	// JDBC DB관련 정보
	public static final String URL = "jdbc:mysql://180.224.86.30:3306/coffee?useUnicode=true&characterEncoding=utf8";
	public static final String USERID = "team";
	public static final String USERPWD = "teampj";
	public static final String SEARCHALL = "SELECT * FROM coffee";

	// DataBase column
	public static final String BRAND = "brand";
	public static final String MENU = "menu";
	public static final String CAL = "cal";
	public static final String SIZE = "size";
	public static final String PRICE = "price";
	public static final String IMAGE = "image";
	
}
