package wbb.atm01.views;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountController {
	
	private String dbFile = "D:\\java\\oxygenws\\data_sources\\ATM\\accounts.csv";
	private CSV csv;
	private ArrayList<String[]> rawTable;
	private HashMap<Integer, Account> accounts;

	public AccountController() {
		csv = new CSV(this.dbFile);
		buildDB();
	}
	
	public void buildDB() {
		ArrayList<String[]> csvArray = csv.getCSV();
		rawTable = csvArray;
		HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
		
		for(String[] account: csvArray) {
			int id = Integer.valueOf(account[0].trim());
			int pin = Integer.valueOf(account[1].trim());
			Double balance = Double.valueOf(account[2].trim());
			
			Account a = new Account(id, pin, balance);
			
			accounts.put(pin, a);
		}
		
		this.accounts = accounts;
	}
	
	public void updateAccount(int pin, Double balance) {
		Account account = accounts.get(pin);
		rawTable.get( account.getId() - 1 )[2] = balance.toString();
	}
	
	public Account getAccount(Integer pin) {
		Account account = accounts.get(pin);
		return account;
	}
	
	public ArrayList<String[]> getAccountsAsCSV() {
		return rawTable;
	}
	
	public Object[][] getAccountsAsTable() {
		ArrayList<String[]> accountsArray = rawTable;;
		int numRows = accountsArray.size();
		int numCols = accountsArray.get(0).length;
		
		Object[][] accountsTable = new Object[numRows][numCols];
		
		for( int row = 0; row < numRows; row++ ) {
			for( int col = 0; col < numCols; col++ ) {
				accountsTable[row][col] = accountsArray.get(row)[col];
			}
		}
		
		return accountsTable;
	}

}
