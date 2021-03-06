package webshop;

public class Payment {
	
	public static final int LAST_DIGITS_CURRENT_YEAR = 16;
	public static final int CARDNR_LENGTH = 12;
	
	public static boolean checkIfDigits(String digits) {
		int length = digits.length();
		if (CARDNR_LENGTH == length) {
			char c;
			for (int i = 0; i < length; i++) {
				c = digits.charAt(i);
				if (Character.isDigit(c)) {
					continue;
				} else {
					System.out.println("The " + (i+1) + ". character is not a number");
					return false;
				} 
			}
			System.out.println("Number is valid");
			return true;
		} else {
			System.out.println("Incorrect lenght");
			return false;
		}
	}
	
	public static boolean checkCardExpiry(String expiry) {
		try {
			int month = Integer.parseInt(expiry.substring(0, 2));
			if (1 <= month && month <= 12) {
				if (expiry.charAt(2) == '/') {
					if (expiry.length() == 5) {
						try {
							int year = Integer.parseInt(expiry.substring(3, 5));
							if (LAST_DIGITS_CURRENT_YEAR <= year) {
								System.out.println("Valid expiry date");
								return true;
							} else {
								System.out.println("Card expiried");
								return false;
							}
						} catch(NumberFormatException e) {
							System.out.println("Year is not a number");
							return false;
						}
					} else {
						System.out.println("Length of input is incorrect");
						return false;
					}
				} else {
					System.out.println("The third character is not a forward slash");
					return false;
				}
			} else {
				System.out.println("Invalid number for month");
				return false;
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Month is not a number");
			return false;
		}
	}
	
	
	public static boolean checkName(String name) {
		char c;
		for (int i = 0; i < name.length(); i++) {
			c = name.charAt(i);
			if (Character.isLetter(c) || c == '.' || c == ' ') {
				continue;
			} else {
				System.out.println("The " + (i+1) + ". character is not a letter");
				return false;
			} 
		}
		System.out.println("Name is valid");
		return true;
	}

}
