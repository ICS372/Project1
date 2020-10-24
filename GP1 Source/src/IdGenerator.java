
public class IdGenerator {
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

}
