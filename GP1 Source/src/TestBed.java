import java.util.Random;

/**
 * This class generates randomized data and uses it to test the system
 * functionality via asserts.
 * 
 * @author Shuja Uddin
 *
 */
public class TestBed {
	private Company company;

	/**
	 * Stores the Company object and invokes test method to test program
	 * functionality.
	 * 
	 * @param company the Company object
	 */
	public TestBed(Company company) {
		this.company = company;
		test();
	}

	/**
	 * Tests various functionalities by calling on appropriate test methods.
	 */
	public void test() {
		Component[] components = testAddComponent();
		Supplier[] suppliers = testAddSupplier();
		testFindComponent(components);
		testFindSupplier(suppliers);
		testComponentSupplierRelations(components, suppliers);
		Order[] orders = testPlaceOrder(components, suppliers);
		testFulfillOrder(orders);
		testAssignComponent(components);
	}

	/**
	 * Tests component creation.
	 * 
	 * @return an array of all Components added, to be used to test other
	 *         functionalities
	 */
	public Component[] testAddComponent() {
		String[] componentNames = generateNames(40);
		Component[] components = new Component[40];
		for (int index = 0; index < componentNames.length; index++) {
			components[index] = company.addComponent(componentNames[index]);
			assert components[index].getName().equals(componentNames[index]);
		}
		return components;
	}

	/**
	 * Test supplier creation.
	 * 
	 * @return an array of all Suppliers added, to be used to test other
	 *         functionalities
	 */
	public Supplier[] testAddSupplier() {
		String[] supplierNames = generateNames(10);
		Supplier[] suppliers = new Supplier[10];
		for (int index = 0; index < supplierNames.length; index++) {
			suppliers[index] = company.addSupplier(supplierNames[index]);
			assert suppliers[index].getName().equals(supplierNames[index]);
		}
		return suppliers;
	}

	/**
	 * Tests if the added components can be found
	 * 
	 * @param components an array of the components that have been added
	 */
	public void testFindComponent(Component[] components) {
		for (Component component : components) {
			assert component.equals(company.findComponent(component.getId()));
		}
	}

	/**
	 * Tests if the added suppliers can be found
	 * 
	 * @param suppliers an array of the suppliers that have been added
	 */
	public void testFindSupplier(Supplier[] suppliers) {
		for (Supplier supplier : suppliers) {
			assert supplier.equals(company.findSupplier(supplier.getId()));
		}
	}

	/**
	 * Tests the creation of component-supplier relationships by pairing every given
	 * component with every given supplier
	 * 
	 * @param components an array of components
	 * @param suppliers  an array of suppliers
	 */
	public void testComponentSupplierRelations(Component[] components, Supplier[] suppliers) {
		for (Component component : components) {
			for (Supplier supplier : suppliers) {
				company.addComponentSupplierRelation(component, supplier);
				assert component.getSupplier(supplier).getSupplier().equals(supplier);
			}
		}
	}

	/**
	 * Tests order placement by placing an order for every given component with
	 * every given supplier
	 * 
	 * @param components
	 * @param suppliers
	 * @return an array of the orders placed, to be used for testing other
	 *         functionalities
	 */
	public Order[] testPlaceOrder(Component[] components, Supplier[] suppliers) {
		Order[] orders = new Order[400];
		int ordersIndex = 0;
		for (Component component : components) {
			for (Supplier supplier : suppliers) {
				orders[ordersIndex] = company.placeOrder(component, supplier, 100);
				ComponentSupplierRelation relation = orders[ordersIndex].getRelation();
				assert (relation.getComponent().equals(component)) && (relation.getSupplier().equals(supplier));
				ordersIndex++;
			}
		}
		return orders;
	}

	/**
	 * Tests order fulfillment by checking that every given order returns an
	 * appropriate response.
	 * 
	 * @param orders an array of outstanding orders
	 */
	public void testFulfillOrder(Order[] orders) {
		for (Order order : orders) {
			ComponentSupplierRelation relation = company.fulfillOrder(order.getId());
			assert order.getRelation().equals(relation);
		}
	}

	/**
	 * Tests assignment of components to production for every given component
	 * 
	 * @param components an array of components
	 */
	public void testAssignComponent(Component[] components) {
		for (Component component : components) {
			company.assignComponent(component, 50);
			assert component.getStock() == 950;
		}
	}

	/**
	 * Used to generate randomized names for components and suppliers
	 * 
	 * @param numberOfNames
	 * @return an array of randomized names
	 */
	public String[] generateNames(int numberOfNames) {
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String upperCaseLetters = lowerCaseLetters.toUpperCase();
		String alphabets = upperCaseLetters + lowerCaseLetters;

		Random random = new Random();

		int lengthOfName = 6;

		String[] names = new String[numberOfNames];

		for (int namesIndex = 0; namesIndex < numberOfNames; namesIndex++) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int stringIndex = 0; stringIndex < lengthOfName; stringIndex++) {
				int randomNumber = random.nextInt(alphabets.length());
				char randomChar = alphabets.charAt(randomNumber);
				stringBuilder.append(randomChar);
			}
			names[namesIndex] = stringBuilder.toString();
		}

		return names;
	}
}
