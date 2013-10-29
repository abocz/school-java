import java.util.ArrayList;
public class AccountManager 
{
	private Account account;
	private String name;
	private double intRate;
	private ArrayList acct;
	private int id = -1;
	
	public AccountManager()
	{
		acct = new ArrayList();
	}
	 
	public int openAccount(String accountOwner,double rate)
	{
		id++;
		Account opAcct = new Account(id, accountOwner, rate);
		account = opAcct;
		name = accountOwner;
		intRate = rate;
		acct.add(account);
		return id; 
		
	}
	
	public Account getAccount(int id) throws NoSuchAccountException
	{
		if(id < 0){
			throw new IllegalArgumentException();
		}
		if(id > acct.size()-1 || acct.get(id) == null){
			throw new NoSuchAccountException("Account does not exist.  Enter a different account ID.");
		}
		
		account = (Account)(acct.get(id));		    
		return account;
	}
	
	public void closeAccount(int id) throws NoSuchAccountException
	{
		if(id < 0)
		{
			throw new IllegalArgumentException();
		}
		if(id > acct.size() || acct.get(id) == null){
			throw new NoSuchAccountException("Account does not exist");
		}
		
		acct.set(id, null);
		
		
	}
}
