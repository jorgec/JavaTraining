package rr.tests;


import java.sql.ResultSet;
import java.sql.SQLException;

import rr.models.Account;
import rr.models.AccountDataAccessObject;

public class Test1 {

	public static void main(String[] args) {
		
		Account a = new Account("admin", "asdf1234", "admin", "admin", "admin");
		
		AccountDataAccessObject accounts = new AccountDataAccessObject();
		System.out.println(accounts.create(a));
		
		System.out.println(accounts.authenticate("admin", "asdf1234").getUsername());
		ResultSet accountsRS = accounts.get();
		
		
		try {
			while(accountsRS.next()) {
				System.out.println(accountsRS.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
