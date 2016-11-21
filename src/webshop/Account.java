package webshop;

public class Account {
	
	public static final int PASS_LIMIT = 3;
	
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
			   "Unsuccessful tries: " + tries;
	}
	
	public boolean isUserMatch(String user) {
		if (user.equals(this.getUsername())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int login(String pass) {
		if (pass.equals(this.getPassword())) {
			this.loggedIn = true;
			this.tries = 0; // unsuccessful "in a row"!
			System.out.println("\nLogin successful!\n");
			return 0;
		} else {
			this.tries = this.tries+1;
			int triesLeft = PASS_LIMIT-this.tries;
			System.out.println("Incorrect password! Tries left: " + (triesLeft) + "\n");
			return triesLeft;
		}
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