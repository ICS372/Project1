import java.util.LinkedList;

public class Component {
	private String componentID;
	private int quantityOnHand;
	private LinkedList<Supplier> suppliers = new LinkedList<Supplier>();

	public String getComponentID() {
		return componentID;
	}

	public int getQuantityOnHand() {
		return quantityOnHand;
	}

	public boolean assign(int quantity) {
		if (quantity > quantityOnHand) {
			quantityOnHand -= quantity;
			return true;
		}
		return false;
	}

	public Supplier findSupplier(String supplierID) {
		for (Supplier supplier : suppliers) {
			if (supplier.getSupplierID().equals(supplierID)) {
				return supplier;
			}
			System.out.println("HELLO");
		}
		return null;
	}

}
