import java.io.Serializable;
import java.util.LinkedList;

public class SupplierList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Supplier> supplierList = new LinkedList<Supplier>();
	private static SupplierList suppliers;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private SupplierList() {
	}

	public static SupplierList instance() {
		if (suppliers == null) {
			return (suppliers = new SupplierList());
		} else {
			return suppliers;
		}
	}

	public boolean insert(Supplier supplier) {
		return supplierList.add(supplier);
	}

	public Supplier search(String supplierID) {
		for (Supplier supplier : supplierList) {
			if (supplier.getId().equals(supplierID)) {
				return supplier;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String output = "";
		for (Supplier supplier : supplierList) {
			output += "Supplier name: " + supplier.getName() + " | Supplier ID: " + supplier.getId() + "\n";
		}
		return output;
	}
}
