import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * This class generates unique IDs for components, suppliers and orders.
 * 
 * @author Shuja Uddin
 *
 */
public class IdGenerator implements Serializable {
	private static final long serialVersionUID = 1L;
	private int componentIdCounter;
	private int supplierIdCounter;
	private int orderIdCounter;
	private static IdGenerator idGenerator;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private IdGenerator() {
		componentIdCounter = 1;
		supplierIdCounter = 1;
		orderIdCounter = 1;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static IdGenerator instance() {
		if (idGenerator == null) {
			return (idGenerator = new IdGenerator());
		} else {
			return idGenerator;
		}
	}

	/**
	 * Returns a component ID
	 * 
	 * @return ID for the component
	 */
	public int getComponentId() {
		return componentIdCounter++;
	}

	/**
	 * Returns a supplier ID
	 * 
	 * @return ID for the supplier
	 */
	public int getSupplierId() {
		return supplierIdCounter++;
	}

	/**
	 * Returns an order ID
	 * 
	 * @return ID for the order
	 */
	public int getOrderId() {
		return orderIdCounter++;
	}

	/**
	 * Retrieves the IdGenerator object
	 * 
	 * @param input an ObjectInputStream object for de-serialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			idGenerator = (IdGenerator) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

}
