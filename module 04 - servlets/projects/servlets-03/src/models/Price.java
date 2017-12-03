package models;

import java.util.HashMap;

public class Price {
	private static final HashMap<String, Double> priceList;
	
	static {
		priceList = new HashMap<String, Double>();
		priceList.put("addon1", 250.0);
		priceList.put("addon2", 150.0);
		priceList.put("addon3", 2250.0);
		priceList.put("addon4", 750.0);
		priceList.put("addon5", 225.0);
	}
	
	public static HashMap<String, Double> getPriceList(){
		return Price.priceList;
	}

}
