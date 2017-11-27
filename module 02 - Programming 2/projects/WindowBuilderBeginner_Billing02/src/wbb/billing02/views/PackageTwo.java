package wbb.billing02.views;

public class PackageTwo extends AbstractPackage {
	private Double baseBill = 500.0;
	private Double rate = 10.0;
	private Double freeHours = 20.0;
	
	public PackageTwo() {
		super.setBaseBill(baseBill);
		super.setRate(rate);
		super.setFreeHours(freeHours);
		
	}

}
