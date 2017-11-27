package wbb.billing02.views;

public class PackageOne extends AbstractPackage {
	private Double baseBill = 200.0;
	private Double rate = 15.0;
	private Double freeHours = 10.0;
	
	public PackageOne() {
		super.setBaseBill(baseBill);
		super.setRate(rate);
		super.setFreeHours(freeHours);
	}

}
