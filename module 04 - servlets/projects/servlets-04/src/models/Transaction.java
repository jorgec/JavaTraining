package models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Transaction {
	private int id;
	private int accountId;
	private String transactionType;
	private BigDecimal amount;
	private Date transactionDate;
	
	public Transaction(int accountId, String transactionType, BigDecimal amount) {
		this.setAccountId(accountId);
		this.setTransactionType(transactionType);
		this.setAmount(amount);
	}
	
	public Transaction(ResultSet result) {
		try {
			this.setId(result.getInt("id"));
			this.setAccountId(result.getInt("accountId"));
			this.setTransactionType(result.getString("transactionType"));
			this.setAmount(result.getBigDecimal("amount"));
			this.setTransactionDate(result.getDate("transactionDate"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	

}
