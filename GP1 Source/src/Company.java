
public class Company {
	private ComponentList components;
	private SupplierList suppliers;
	private static Company company;

	/**
	 * Private for the singleton pattern Creates the components and suppliers
	 * collection objects
	 */
	private Company() {
		components = ComponentList.instance();
		suppliers = SupplierList.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Company instance() {
		if (company == null) {
			IdGenerator.instance(); // instantiate all singletons
			return (company = new Company());
		} else {
			return company;
		}
	}

	public Component assignComponent(String componentID, int quantity) {
		Component component = components.search(componentID);
		boolean assigned = false;
		if (component != null && component.assign(quantity)) {
			assigned = true;
		}

		if (assigned) {
			return component;
		} else {
			return null;
		}
	}

	public Order placeOrder(String componentID, String supplierID, int quantity) {
		if (quantity < 1) {
			return null;
		}
		Component component = components.search(componentID);
		if (component == null) {
			return null;
		}

		Supplier supplier = component.findSupplier(supplierID);
		if (supplier == null) {
			return null;
		}

		return new Order(component, supplier, quantity);
	}

}
