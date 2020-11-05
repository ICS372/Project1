import java.io.Serializable;
import java.util.LinkedList;

/**
 * The collection class for Supplier objects.
 * 
 * @author Shuja Uddin
 *
 */
public class SupplierList implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Supplier> supplierList = new LinkedList<Supplier>();
	private static SupplierList suppliers;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private SupplierList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static SupplierList instance() {
		if (suppliers == null) {
			return (suppliers = new SupplierList());
		} else {
			return suppliers;
		}
	}

	/**
	 * Inserts a given supplier to the list of suppliers
	 * 
	 * @param supplier supplier to be inserted
	 * @return true, if insertion was successful
	 */
	public boolean insert(Supplier supplier) {
		return supplierList.add(supplier);
	}

	/**
	 * Searches the collection for a supplier with the given ID.
	 * 
	 * @param supplierID the ID being searched for
	 * @return a Supplier object with the matching ID, if one is found.
	 */
	public Supplier search(String supplierID) {
		for (Supplier supplier : supplierList) {
			if (supplier.getId().equals(supplierID)) {
				return supplier;
			}
		}
		return null;
	}

	/**
	 * A string representation of all suppliers
	 */
	@Override
	public String toString() {
		String output = "";
		for (Supplier supplier : supplierList) {
			output += "Supplier name: " + supplier.getName() + " | Supplier ID: " + supplier.getId() + "\n";
		}
		return output;
	}
}
