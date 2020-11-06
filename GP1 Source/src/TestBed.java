import java.util.Random;

/**
 * This class generates randomized data and uses it to test the system
 * functionality via asserts.
 * 
 * @author Shuja Uddin
 * @version 6 November
 *
 */
public class TestBed {
	private Company company;

	/**
	 * Constant value that represents the minimum number of {@code Component}s
	 * that needs to be tested in order to ensure that the system has been
	 * programmed correctly.
	 */
	private static final int MINIMUM_TEST_COMPONENT_COUNT = 20;

	/**
	 * Constant value that represents the number of {@code Component}s that will
	 * be tested. The implementer must set this value to at least
	 * {@link TestBed#MINIMUM_TEST_COMPONENT_COUNT}. Otherwise, then the program
	 * will crash.
	 */
	private static final int TEST_COMPONENT_COUNT = 40;

	/**
	 * Constant value that represents the minimum number of {@code Supplier}s
	 * that needs to be tested in order to ensure that the system has been
	 * programmed correctly.
	 */
	private static final int MINIMUM_TEST_SUPPLIER_COUNT = 5;

	/**
	 * Constant value that represents the number of {@code Supplier}s that will
	 * be tested. The implementer must set this value to at least
	 * {@link TestBed#MINIMUM_TEST_SUPPLIER_COUNT}. Otherwise, the program will
	 * crash.
	 */
	private static final int TEST_SUPPLIER_COUNT = 10;

	/**
	 * Stores the Company object and invokes test method to test program
	 * functionality.
	 * 
	 * @param company
	 *            the Company object
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
		assert TEST_COMPONENT_COUNT >= MINIMUM_TEST_COMPONENT_COUNT;
		String[] componentNames = generateNames(TEST_COMPONENT_COUNT);
		Component[] components = new Component[componentNames.length];
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
		assert TEST_SUPPLIER_COUNT >= MINIMUM_TEST_SUPPLIER_COUNT;
		String[] supplierNames = generateNames(TEST_SUPPLIER_COUNT);
		Supplier[] suppliers = new Supplier[supplierNames.length];
		for (int index = 0; index < supplierNames.length; index++) {
			suppliers[index] = company.addSupplier(supplierNames[index]);
			assert suppliers[index].getName().equals(supplierNames[index]);
		}
		return suppliers;
	}

	/**
	 * Tests if the added components can be found
	 * 
	 * @param components
	 *            an array of the components that have been added
	 */
	public void testFindComponent(Component[] components) {
		for (Component component : components) {
			assert component.equals(company.findComponent(component.getId()));
		}
	}

	/**
	 * Tests if the added suppliers can be found
	 * 
	 * @param suppliers
	 *            an array of the suppliers that have been added
	 */
	public void testFindSupplier(Supplier[] suppliers) {
		for (Supplier supplier : suppliers) {
			assert supplier.equals(company.findSupplier(supplier.getId()));
		}
	}

	/**
	 * Tests the creation of component-supplier relationships by pairing every
	 * given component with every given supplier
	 * 
	 * @param components
	 *            an array of components
	 * @param suppliers
	 *            an array of suppliers
	 */
	public void testComponentSupplierRelations(Component[] components,
			Supplier[] suppliers) {
		for (Component component : components) {
			for (Supplier supplier : suppliers) {
				company.addComponentSupplierRelation(component, supplier);
				assert component.getSupplier(supplier).getSupplier()
						.equals(supplier);
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
	public Order[] testPlaceOrder(Component[] components,
			Supplier[] suppliers) {
		Order[] orders = new Order[400];
		int ordersIndex = 0;
		for (Component component : components) {
			for (Supplier supplier : suppliers) {
				orders[ordersIndex] = company.placeOrder(component, supplier,
						100);
				ComponentSupplierRelation relation = orders[ordersIndex]
						.getRelation();
				assert (relation.getComponent().equals(component))
						&& (relation.getSupplier().equals(supplier));
				ordersIndex++;
			}
		}
		return orders;
	}

	/**
	 * Tests order fulfillment by checking that every given order returns an
	 * appropriate response.
	 * 
	 * @param orders
	 *            an array of outstanding orders
	 */
	public void testFulfillOrder(Order[] orders) {
		for (Order order : orders) {
			ComponentSupplierRelation relation = company
					.fulfillOrder(order.getId());
			assert order.getRelation().equals(relation);
		}
	}

	/**
	 * Tests assignment of components to production for every given component
	 * 
	 * @param components
	 *            an array of components
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

		for (int namesIndex = 0; namesIndex < names.length; namesIndex++) {
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
