import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * This class generates unique IDs for {@code Component}s, {@code Supplier}s and
 * {@code Order}s.
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class IdGenerator implements Serializable {
	private static final long serialVersionUID = 1L;
	private int componentIdCounter;
	private int supplierIdCounter;
	private int orderIdCounter;
	private static IdGenerator idGenerator;

	/**
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
	 * Generates an identification number for a {@code Component}. Increments
	 * the {@code componentIdCounter} by {@literal 1}, to ensure that each
	 * component ID is unique. That new value is finally returned.
	 * 
	 * @return {@code componentIdCounter} as the ID for the {@code Component}.
	 */
	public int getComponentId() {
		return componentIdCounter++;
	}

	/**
	 * Generate an identification number for a {@code Supplier}. Increments the
	 * {@code suppliertIdCounter} by {@literal 1}, to ensure that each supplier
	 * ID is unique. That new value is finally returned.
	 * 
	 * @return {@code supplierIdCounter} as the ID for the {@code Supplier}.
	 */
	public int getSupplierId() {
		return supplierIdCounter++;
	}

	/**
	 * Generate an identification number for a {@code Order}. Increments the
	 * {@code orderIdCounter} by {@literal 1}, to ensure that each order ID is
	 * unique. That new value is finally returned.
	 * 
	 * @return {@code orderIdCounter} as the ID for the {@code Order}.
	 */
	public int getOrderId() {
		return orderIdCounter++;
	}

	/**
	 * Retrieves the IdGenerator object
	 * 
	 * @param input
	 *            an ObjectInputStream object for de-serialization
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
