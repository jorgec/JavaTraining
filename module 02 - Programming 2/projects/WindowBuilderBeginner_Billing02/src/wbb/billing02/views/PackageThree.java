package wbb.billing02.views;

public class PackageThree extends AbstractPackage {
	private Double baseBill = 900.0;
	private Double rate = 0.0;
	private Double freeHours = 0.0;
	
	public PackageThree() {
		super.setBaseBill(baseBill);
		super.setRate(rate);
		super.setFreeHours(freeHours);
		
	}

}
