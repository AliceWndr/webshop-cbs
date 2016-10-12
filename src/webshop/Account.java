package webshop;

public class Account {
	
	private String username;
	private String password;
	private boolean loggedIn;
	private int tries;
	
	public Account(String firstName, String lastName, String zip){
		setUsername(firstName, lastName);
		setPassword(lastName, zip);
		this.loggedIn = false;
		this.tries = 0;
	}
	
	@Override
	public String toString() {
		return "Username: " + username + "; " +
			   "Password: " + password + "; " +
			   "Logged in: " + loggedIn + "; " +
			   "Unsucceful tries: " + tries;
	}
	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String first, String last) {
		this.username = first.substring(0, 3) + last.substring(0, 3);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String last, String zip) {
		this.password = last.substring(0, 3) +  zip;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}
	

}
