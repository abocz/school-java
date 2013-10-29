import java.util.*;

public class Account implements Chronological, Comparable  {
	private String owner;
	private int id;
	private double balance;
	private double annualInterest;
	private Date dateCreated;
	private double monthlyInterest;
	
	public Account(int idvalue, String ownerName, double rate)
	{
		id = idvalue;
		owner = ownerName;
		annualInterest = rate;
		balance = 0;
		dateCreated = new Date();
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int newId)
	{
		id = newId;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public void setOwner(String newName)
	{
		owner = newName;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public double getAnnualInterestRate()
	{
		return annualInterest;
	}
	
	public void setAnnualInterestRate(double newAnnualInterestRate)
	{
		if(newAnnualInterestRate < 0)
		{
			throw new IllegalArgumentException();
		}
		
		annualInterest = newAnnualInterestRate;
	}
	
	public Date getDateCreated()
	{
		return dateCreated;
	}
	
	public double getMonthlyInterestRate()
	{
		
		monthlyInterest = annualInterest/12;
		return monthlyInterest;
	}
	
	public void withdraw(double amount) throws InsufficientFundsException
	{
		if(amount < 0)
		{
			throw new IllegalArgumentException();
		}
		if(amount > balance){
			throw new InsufficientFundsException("Cannot complete transaction. Insufficient funds");
		}
		
		balance = balance - amount;
	}
	
	public void deposit(double amount) 
	{
		if(amount < 0)
		{
			throw new IllegalArgumentException();
		}
		
		
		balance = balance + amount;
	}
	
	public boolean before(Chronological other){
		if(this.getDateCreated().before(other.getDate())){
			return true;
		}
		else
			return false;
	}
	
	public boolean after(Chronological other){
		if(this.getDateCreated().after(other.getDate())){
			return true;
		}
		else
			return false;
	}
	
	public Date getDate(){
		return dateCreated;
	}

	public int compareTo(Object other) {
		if(this.getBalance() == ((Account)other).getBalance()){
			return 0;
		}
		if(this.getBalance() > ((Account)other).getBalance()){
			return 1;
		}
		else
			return -1;
	}

}
