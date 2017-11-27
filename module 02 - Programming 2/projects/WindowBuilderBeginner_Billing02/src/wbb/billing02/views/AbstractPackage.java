package wbb.billing02.views;

public abstract class AbstractPackage {
	private Double baseBill, rate, freeHours, payable;
	
	public AbstractPackage() {
		
	}
	
	/**
	 * @return the baseBill
	 */
	public Double getBaseBill() {
		return baseBill;
	}

	/**
	 * @param baseBill the baseBill to set
	 */
	public void setBaseBill(Double baseBill) {
		this.baseBill = baseBill;
	}

	/**
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	/**
	 * @return the freeHours
	 */
	public Double getFreeHours() {
		return freeHours;
	}

	/**
	 * @param freeHours the freeHours to set
	 */
	public void setFreeHours(Double freeHours) {
		this.freeHours = freeHours;
	}

	/**
	 * @return the payable
	 */
	public Double getPayable() {
		return payable;
	}

	/**
	 * @param payable the payable to set
	 */
	public void setPayable(Double payable) {
		this.payable = payable;
	}
	
	public void calculatePayable(Double hours) {
		Double payable = 0.0;
		
		if( hours >= freeHours ) {
			payable = baseBill + ( (hours - freeHours) * rate );
		}else {
			payable = baseBill;
		}
		
		this.payable = payable;
	}



}
