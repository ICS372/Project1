import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * This class implements the user interface for the Company project.
 * 
 * @author Shuja Uddin
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static Company company;
	private static final int EXIT = 0;
	private static final int ADD_COMPONENT = 1;
	private static final int ADD_SUPPLIER = 2;
	private static final int ADD_COMPONENT_SUPPLIER = 3;
	private static final int ASSIGN_COMPONENTS = 4;
	private static final int PLACE_ORDER = 5;
	private static final int FULFILL_ORDER = 6;
	private static final int DISPLAY_COMPONENT = 7;
	private static final int DISPLAY_SUPPLIER = 8;
	private static final int DISPLAY_PENDING_ORDERS = 9;
	private static final int DISPLAY_ALL_COMPONENTS = 10;
	private static final int DISPLAY_ALL_SUPPLIERS = 11;
	private static final int SAVE = 12;
	private static final int HELP = 13;

	/**
	 * Uses the singleton pattern. Looks for any saved data and retrieves it. If no
	 * data is found, it gets a singleton Company object that can be used for
	 * testing.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			company = Company.instance();
			if (yesOrNo("Do you want to generate a test bed and invoke the functionality using asserts?")) {
				new TestBed(company);
			}
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

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
				String line = input.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.print("Please input a number. ");
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " Enter Y/y for yes, anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = getNumber("Enter command, " + HELP + " for help: ");
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Method for adding a component. Uses the appropriate Company method.
	 */
	public void addComponent() {
		Component component;
		do {
			String name = getToken("Enter component name: ");
			component = company.addComponent(name);
			if (component != null) {
				System.out.println(component);
			} else {
				System.out.println("Component " + name + " could not be added.");
			}
		} while (yesOrNo("Add more components?"));
	}

	/**
	 * Method for adding a supplier. Uses the appropriate Company method.
	 */
	public void addSupplier() {
		Supplier supplier;
		do {
			String name = getToken("Enter supplier name: ");
			supplier = company.addSupplier(name);
			if (supplier != null) {
				System.out.println(supplier);
			} else {
				System.out.println("Supplier " + name + " could not be added.");
			}
		} while (yesOrNo("Add more suppliers?"));
	}

	/**
	 * Method for adding a relationship between a component and a supplier. Uses the
	 * functionality of appropriate Company methods.
	 */
	public void addComponentSupplierRelation() {
		String componentID = getToken("Enter component ID: ");
		Component component = company.findComponent(componentID);
		if (component == null) {
			System.out.println("Entered component does not exist.");
			return;
		}

		String supplierID = getToken("Enter supplier ID: ");
		Supplier supplier = company.findSupplier(supplierID);
		if (supplier == null) {
			System.out.println("Entered supplier does not exist.");
			return;
		}

		boolean relationCreated = company.addComponentSupplierRelation(component, supplier);
		if (relationCreated) {
			System.out.println("Component-Supplier Relationship has been successfully created.");
			return;
		}
		System.out.println("Supplier already associated with component.");
	}

	/**
	 * Method for assigning a component to production. Uses the functionality of
	 * appropriate Company methods.
	 */
	public void assignComponents() {
		String componentID = getToken("Enter component ID: ");
		Component component = company.findComponent(componentID);
		if (component == null) {
			System.out.println("Entered component does not exist.");
			return;
		}

		int quantity = getNumber("Enter quantity to assign: ");

		while (quantity < 1) {
			quantity = getNumber("Quantity must be more than 0, enter quantity: ");
		}

		boolean assigned = company.assignComponent(component, quantity);
		if (assigned) {
			System.out.println("Assignment successful. Updated quantity:\n" + component);
			return;
		}
		System.out.println("Assignment unsuccessful. Assignment quantity exceeds quantity on hand.");
	}

	/**
	 * Method for placing an order for a component. Uses the functionality of
	 * appropriate Company methods.
	 */
	public void placeOrder() {
		String componentID = getToken("Enter component ID: ");
		Component component = company.findComponent(componentID);
		if (component == null) {
			System.out.println("Entered component does not exist.");
			return;
		}

		String supplierID = getToken("Enter supplier ID: ");
		Supplier supplier = company.findSupplier(supplierID);
		if (supplier == null) {
			System.out.println("Entered supplier does not exist.");
			return;
		}

		int quantity = getNumber("Enter order quantity: ");

		while (quantity < 1) {
			quantity = getNumber("Quantity must be more than 0, enter quantity: ");
		}

		Order order = company.placeOrder(component, supplier, quantity);
		if (order != null) {
			System.out.println("Order created: \n" + order);
			return;
		}
		System.out.println("Supplier does not supply this component.");
	}

	/**
	 * Method for fulfilling an order. Uses the appropriate Company method.
	 */
	public void fulfillOrder() {
		String orderID = getToken("Enter outstanding order's ID: ");
		ComponentSupplierRelation relation = company.fulfillOrder(orderID);
		if (relation == null) {
			System.out.println("Order not found.");
			return;
		}
		System.out.println(relation);
	}

	/**
	 * Method for displaying the details of a given component. Uses the appropriate
	 * Company methods to find the component and its suppliers.
	 */
	public void displayComponent() {
		String componentID = getToken("Enter component ID: ");
		Component component = company.findComponent(componentID);
		if (component == null) {
			System.out.println("Entered component does not exist.");
			return;
		}

		System.out.println(component + "\nSuppliers:");
		Iterator<ComponentSupplierRelation> componentSuppliers = company.getComponentSuppliers(component);
		while (componentSuppliers.hasNext()) {
			Supplier supplier = componentSuppliers.next().getSupplier();
			System.out.println(supplier.getName() + ", " + supplier.getId());
		}
	}

	/**
	 * Method for displaying the details of a given supplier. Uses the appropriate
	 * Company methods to find the supplier and the components it supplies.
	 */
	public void displaySupplier() {
		String supplierID = getToken("Enter supplier ID: ");
		Supplier supplier = company.findSupplier(supplierID);
		if (supplier == null) {
			System.out.println("Entered supplier does not exist.");
			return;
		}

		System.out.println(supplier + "\nComponents supplied:");
		Iterator<ComponentSupplierRelation> suppliedComponents = company.getSuppliedComponents(supplier);
		while (suppliedComponents.hasNext()) {
			Component component = suppliedComponents.next().getComponent();
			System.out.println(component.getName() + ", " + component.getId());
		}
	}

	/**
	 * Method for displaying all outstanding orders. Uses the appropriate Company
	 * method to get the list of outstanding orders.
	 */
	public void displayPendingOrders() {
		System.out.println(company.displayPendingOrders());
	}

	/**
	 * Method for displaying all components. Uses the appropriate Company method to
	 * get the list of all components.
	 */
	public void displayAllComponents() {
		System.out.println(company.displayAllComponents());
	}

	/**
	 * Method for displaying all suppliers. Uses the appropriate Company method to
	 * get the list of all suppliers.
	 */
	public void displayAllSuppliers() {
		System.out.println(company.displayAllSuppliers());
	}

	/**
	 * Calls the appropriate Company method to retrieve a saved Company object.
	 * 
	 */
	private void retrieve() {
		try {
			if (company == null) {
				company = Company.retrieve();
				if (company != null) {
					System.out.println("The company has been successfully retrieved from the file CompanyData.\n");
				} else {
					System.out.println("File doesn't exist; creating new company.");
					company = Company.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Calls the appropriate Company method for saving the Company object.
	 * 
	 */
	private void save() {
		if (Company.save()) {
			System.out.println("The company has been successfully saved in the file CompanyData.\n");
		} else {
			System.out.println("File could not be saved due to an unknown error.\n");
		}
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 13 as explained below:");
		System.out.println(EXIT + " to exit");
		System.out.println(ADD_COMPONENT + " to add a component.");
		System.out.println(ADD_SUPPLIER + " to add a supplier.");
		System.out.println(ADD_COMPONENT_SUPPLIER + " to add a supplier to a component.");
		System.out.println(ASSIGN_COMPONENTS + " to assign a quantity of components to production.");
		System.out.println(PLACE_ORDER + " to order components.");
		System.out.println(FULFILL_ORDER + " to mark outstanding orders as fulfilled.");
		System.out.println(DISPLAY_COMPONENT + " to display a component's details.");
		System.out.println(DISPLAY_SUPPLIER + " to display a supplier's details.");
		System.out.println(DISPLAY_PENDING_ORDERS + " to display all outstanding orders.");
		System.out.println(DISPLAY_ALL_COMPONENTS + " to display all components.");
		System.out.println(DISPLAY_ALL_SUPPLIERS + " to display all suppliers.");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Organizes the whole process. Calls the appropriate method for the different
	 * functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_COMPONENT:
				addComponent();
				break;
			case ADD_SUPPLIER:
				addSupplier();
				break;
			case ADD_COMPONENT_SUPPLIER:
				addComponentSupplierRelation();
				break;
			case ASSIGN_COMPONENTS:
				assignComponents();
				break;
			case PLACE_ORDER:
				placeOrder();
				break;
			case FULFILL_ORDER:
				fulfillOrder();
				break;
			case DISPLAY_COMPONENT:
				displayComponent();
				break;
			case DISPLAY_SUPPLIER:
				displaySupplier();
				break;
			case DISPLAY_PENDING_ORDERS:
				displayPendingOrders();
				break;
			case DISPLAY_ALL_COMPONENTS:
				displayAllComponents();
				break;
			case DISPLAY_ALL_SUPPLIERS:
				displayAllSuppliers();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}
