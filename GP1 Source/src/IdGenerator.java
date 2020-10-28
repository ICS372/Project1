import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class IdGenerator implements Serializable {
	/**
	 * 
	 */
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

	public int getComponentId() {
		return componentIdCounter++;
	}

	public int getSupplierId() {
		return supplierIdCounter++;
	}

	public int getOrderId() {
		return orderIdCounter++;
	}

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
