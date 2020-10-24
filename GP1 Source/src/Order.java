
public class Order {
	private Component component;
	private Supplier supplier;
	private int quantity;
	private String orderID;

	public Order(Component component, Supplier supplier, int quantity) {
		this.component = component;
		this.supplier = supplier;
		this.quantity = quantity;
	}

}
