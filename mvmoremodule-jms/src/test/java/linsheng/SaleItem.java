package linsheng;

import java.util.Date;

public class SaleItem {
	private int month;
	private Date date;
	private String transationId;
	private double saleNumbers;

	public SaleItem(){

	}

	public SaleItem(int month, Date date, double saleNumbers) {
		this.month = month;
		this.date = date;
		this.saleNumbers = saleNumbers;
	}

	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTransationId() {
		return transationId;
	}
	public void setTransationId(String transationId) {
		this.transationId = transationId;
	}
	public double getSaleNumbers() {
		return saleNumbers;
	}
	public void setSaleNumbers(double saleNumbers) {
		this.saleNumbers = saleNumbers;
	}	
}
