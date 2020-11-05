import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

/**
 * This class acts as a facade for the entire system and facilitates the
 * interactions between the user interface and other parts of the system
 * 
 * @author Shuja Uddin
 *
 */
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private ComponentList components;
	private SupplierList suppliers;
	private PendingOrders pendingOrders;
	private static Company company;

	/**
	 * Uses singleton pattern; creates collection objects for components, suppliers
	 * and orders
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
			IdGenerator.instance();
			return (company = new Company());
		} else {
			return company;
		}
	}

	/**
	 * Adds a new component to the component collection
	 * 
	 * @param name component name
	 * @return the Component object created
	 */
	public Component addComponent(String name) {
		Component component = new Component(name);
		if (components.insert(component)) {
			return component;
		}
		return null;
	}

	/**
	 * Adds a new supplier to the supplier collection
	 * 
	 * @param name supplier name
	 * @return the Supplier object created
	 */
	public Supplier addSupplier(String name) {
		Supplier supplier = new Supplier(name);
		if (suppliers.insert(supplier)) {
			return supplier;
		}
		return null;
	}

	/**
	 * Searches for a given component
	 * 
	 * @param componentID identifier of the component
	 * @return the Component object, if found
	 */
	public Component findComponent(String componentID) {
		return components.search(componentID);
	}

	/**
	 * Searches for a given supplier
	 * 
	 * @param supplierID identifier of the supplier
	 * @return the Supplier object, if found
	 */
	public Supplier findSupplier(String supplierID) {
		return suppliers.search(supplierID);
	}

	/**
	 * Adds a relationship between a component and supplier, indicating that the
	 * supplier provides the component
	 * 
	 * @param component the Component being supplied
	 * @param supplier  the Supplier of the component
	 * @return
	 */
	public boolean addComponentSupplierRelation(Component component, Supplier supplier) {
		ComponentSupplierRelation relation = new ComponentSupplierRelation(component, supplier);
		if (component.addSupplierRelation(relation) && supplier.addComponentRelation(relation)) {
			return true;
		}
		return false;
	}

	/**
	 * Adjusts the in-stock quantity of the given component after a given quantity
	 * has been assigned for production
	 * 
	 * @param component the Component object being assigned
	 * @param quantity  the quantity assigned
	 * @return true iff there is enough stock for the assignment
	 */
	public boolean assignComponent(Component component, int quantity) {
		return component.assign(quantity);
	}

	/**
	 * Places an order for a given component, with the given supplier, for a given
	 * quantity
	 * 
	 * @param component the Component object being ordered
	 * @param supplier  the Supplier who will be supplying the component
	 * @param quantity  the quantity ordered
	 * @return an Order object, if placing the order was successful
	 */
	public Order placeOrder(Component component, Supplier supplier, int quantity) {
		ComponentSupplierRelation relation = component.getSupplier(supplier);
		if (relation == null) {
			return null;
		}

		Order order = new Order(relation, quantity);
		pendingOrders.insert(order);
		return order;
	}

	/**
	 * Fulfills a given order, updating the stock of the component and the quantity
	 * provided by the supplier so far
	 * 
	 * @param orderID identifier of the Order object
	 * @return a ComponentSupplierRelation object if the order was found
	 */
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

	/**
	 * Returns all the supplier relations of a given component
	 * 
	 * @param component the Component object
	 * @return an Iterator of all supplier relations of the component
	 */
	public Iterator<ComponentSupplierRelation> getComponentSuppliers(Component component) {
		return component.getAllSuppliers();
	}

	/**
	 * Returns all component relations of a given supplier
	 * 
	 * @param supplier the Supplier object
	 * @return an Iterator of all component relations of the supplier
	 */
	public Iterator<ComponentSupplierRelation> getSuppliedComponents(Supplier supplier) {
		return supplier.getAllComponents();
	}

	/**
	 * Returns the list of all outstanding orders
	 * 
	 * @return a PendingOrders object
	 */
	public PendingOrders getPendingOrders() {
		return pendingOrders;
	}

	/**
	 * Returns the list of all components
	 * 
	 * @return a ComponentList object
	 */
	public ComponentList getAllComponents() {
		return components;
	}

	/**
	 * Returns the list of all suppliers
	 * 
	 * @return a SupplierList object
	 */
	public SupplierList getAllSuppliers() {
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
