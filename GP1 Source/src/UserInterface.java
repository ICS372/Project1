import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UserInterface {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Company company;

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	public void assignComponents() {
		Component result;
		do {
			String componentID = getToken("Enter component ID: ");
			int quantity = Integer.parseInt(getToken("Enter quantity: "));
			result = company.assignComponent(componentID, quantity);
			if (result != null) {
				System.out.println(result);
			} else {
				System.out.println("Assignment unsuccessful");
			}

		} while (yesOrNo("Assign more components?"));
	}

	public void placeOrder() {
		Order result;
		do {
			String componentID = getToken("Enter component ID: ");
			String supplierID = getToken("Enter supplier ID: ");
			int quantity = Integer.parseInt(getToken("Enter quantity: "));
			result = company.placeOrder(componentID, supplierID, quantity);
			if (result != null) {
				System.out.println(result);
			} else {
				System.out.println("Order could not be placed");
			}
		} while (yesOrNo("Place more orders?"));
	}

}
