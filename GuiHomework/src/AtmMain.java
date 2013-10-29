 
public class AtmMain {
	public static void main(String[] args) {
		AccountManager mainAcctMan = new AccountManager();
		new BankerGui(mainAcctMan);
		new AtmGui(mainAcctMan);
	}

}
