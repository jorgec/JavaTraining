package cc;

public class Change {
	private int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
	private int bill = 0;
	private int payment = 0;
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	public int[] makeChange() {
		int[] breakdowns = new int[this.denominations.length];
		int amount = this.payment - this.bill;
		for( int i = 0; i < this.denominations.length; i++ ){
			while( amount >= this.denominations[i] && amount > 0 ){
				amount = amount - this.denominations[i];
				breakdowns[i]++;
			}
		}
		
		return breakdowns;
	}
	
	public int getChange() {
		return this.payment - this.bill;
	}
	public int[] getDenominations() {
		return denominations;
	}
	public void setDenominations(int[] denominations) {
		this.denominations = denominations;
	}
	

}
