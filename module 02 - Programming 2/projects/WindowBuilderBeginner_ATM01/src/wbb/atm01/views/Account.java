package wbb.atm01.views;

public class Account {
	private int id;
	private int pin;
	private Double balance;

	public Account() {
	}
	
	public Account(int id, int pin, Double balance) {
		this.id = id;
		this.pin = pin;
		this.balance = balance;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}

	/**
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public boolean deposit(Double amount) {
		if( amount > 0 ) {
			this.balance = this.balance + amount;
			return true;
		}
		return false;
	}
	
	public boolean withdraw(Double amount) {
		if( amount > 0 && amount <= this.balance) {
			this.balance = this.balance - amount;
			return true;
		}
		return false;
	}

}
