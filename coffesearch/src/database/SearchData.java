package database;

import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.CSConstants;

public class SearchData {
	/**
	 * 데이터 베이스에서 가져온 데이터row를 저장하는 클래스
	 * */
	private String coffeeBrand;
	private String coffeeMenu;
	private int coffeeCal;
	private String coffeeSize;
	private int coffeePrice;
	private String coffeeImage;

	public SearchData(ResultSet result) {
		try {
			this.coffeeBrand = result.getString(CSConstants.BRAND);
			this.coffeeMenu = result.getString(CSConstants.MENU);
			this.coffeeCal = result.getInt(CSConstants.CAL);
			this.coffeeSize = result.getString(CSConstants.SIZE);
			this.coffeePrice = result.getInt(CSConstants.PRICE);
			this.coffeeImage = result.getString(CSConstants.IMAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCoffeeBrand() {
		return coffeeBrand;
	}

	public String getCoffeeMenu() {
		return coffeeMenu;
	}

	public int getCoffeeCal() {
		return coffeeCal;
	}

	public String getCoffeeSize() {
		return coffeeSize;
	}

	public int getCoffeePrice() {
		return coffeePrice;
	}

	public String getCoffeeImage() {
		return coffeeImage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return coffeeBrand +" "+coffeeMenu +" "+ coffeeCal +" "+ coffeePrice;
	}
	
}
