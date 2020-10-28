import java.util.Random;

public class TestBed {
	private Company company;

	/**
	 * Stores the Company object and invokes test method to test program
	 * functionality.
	 * 
	 * @param library the Library object
	 */
	public TestBed(Company company) {
		this.company = company;
		test();
	}

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

	public Component[] testAddComponent() {
		String[] componentNames = generateNames(40);
		Component[] components = new Component[40];
		for (int index = 0; index < componentNames.length; index++) {
			components[index] = company.addComponent(componentNames[index]);
			assert components[index].getName().equals(componentNames[index]);
		}
		return components;
	}

	public Supplier[] testAddSupplier() {
		String[] supplierNames = generateNames(10);
		Supplier[] suppliers = new Supplier[10];
		for (int index = 0; index < supplierNames.length; index++) {
			suppliers[index] = company.addSupplier(supplierNames[index]);
			assert suppliers[index].getName().equals(supplierNames[index]);
		}
		return suppliers;
	}

	public void testFindComponent(Component[] components) {
		for (Component component : components) {
			assert component.equals(company.findComponent(component.getId()));
		}
	}

	public void testFindSupplier(Supplier[] suppliers) {
		for (Supplier supplier : suppliers) {
			assert supplier.equals(company.findSupplier(supplier.getId()));
		}
	}

	public void testComponentSupplierRelations(Component[] components, Supplier[] suppliers) {
		for (Component component : components) {
			for (Supplier supplier : suppliers) {
				company.addComponentSupplierRelation(component, supplier);
				assert component.getSupplier(supplier).getSupplier().equals(supplier);
			}
		}
	}

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

	public void testFulfillOrder(Order[] orders) {
		for (Order order : orders) {
			ComponentSupplierRelation relation = company.fulfillOrder(order.getId());
			assert order.getRelation().equals(relation);
		}
	}

	public void testAssignComponent(Component[] components) {
		for (Component component : components) {
			company.assignComponent(component, 50);
			assert component.getStock() == 950;
		}
	}

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
