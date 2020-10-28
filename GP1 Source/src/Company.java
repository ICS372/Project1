import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComponentList components;
	private SupplierList suppliers;
	private PendingOrders pendingOrders;
	private static Company company;

	/**
	 * Private for the singleton pattern Creates the components and suppliers
	 * collection objects
	 */
	private Company() {
		components = ComponentList.instance();
		suppliers = SupplierList.instance();
		pendingOrders = PendingOrders.instance();
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

	public Component addComponent(String name) {
		Component component = new Component(name);
		if (components.insert(component)) {
			return component;
		}
		return null;
	}

	public Supplier addSupplier(String name) {
		Supplier supplier = new Supplier(name);
		if (suppliers.insert(supplier)) {
			return supplier;
		}
		return null;
	}

	public Component findComponent(String componentID) {
		return components.search(componentID);
	}

	public Supplier findSupplier(String supplierID) {
		return suppliers.search(supplierID);
	}

	public boolean addComponentSupplierRelation(Component component, Supplier supplier) {
		ComponentSupplierRelation relation = new ComponentSupplierRelation(component, supplier);
		if (component.addSupplierRelation(relation) && supplier.addComponentRelation(relation)) {
			return true;
		}
		return false;
	}

	public boolean assignComponent(Component component, int quantity) {
		return component.assign(quantity);
	}

	public Order placeOrder(Component component, Supplier supplier, int quantity) {
		ComponentSupplierRelation relation = component.getSupplier(supplier);
		if (relation == null) {
			return null;
		}

		Order order = new Order(relation, quantity);
		pendingOrders.insert(order);
		return order;
	}

	public ComponentSupplierRelation fulfillOrder(String orderID) {
		Order order = pendingOrders.search(orderID);
		if (order == null) {
			return null;
		}
		ComponentSupplierRelation relation = order.getRelation();
		relation.addQuantity(order.getQuantity());
		relation.getComponent().addToStock(order.getQuantity());
		pendingOrders.remove(order);
		return relation;
	}

	public Iterator<ComponentSupplierRelation> getComponentSuppliers(Component component) {
		return component.getAllSuppliers();
	}

	public Iterator<ComponentSupplierRelation> getSuppliedComponents(Supplier supplier) {
		return supplier.getAllComponents();
	}

	public PendingOrders displayPendingOrders() {
		return pendingOrders;
	}

	public ComponentList displayAllComponents() {
		return components;
	}

	public SupplierList displayAllSuppliers() {
		return suppliers;
	}

	/**
	 * Retrieves a de-serialized version of the company from disk
	 * 
	 * @return a Company object
	 */
	public static Company retrieve() {
		try {
			FileInputStream file = new FileInputStream("CompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			company = (Company) input.readObject();
			IdGenerator.retrieve(input);
			return company;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Used to serialize the Company
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("CompanyData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(company);
			output.writeObject(IdGenerator.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
}
