import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComponentSupplierRelation relation;
	private int quantity;
	private String id;
	private static final String ORDER_MARKER = "O";

	public Order(ComponentSupplierRelation relation, int quantity) {
		this.relation = relation;
		this.quantity = quantity;
		this.id = ORDER_MARKER + IdGenerator.instance().getOrderId();
	}

	public String getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public ComponentSupplierRelation getRelation() {
		return relation;
	}

	@Override
	public String toString() {
		return "Order ID: " + id + "\nComponent ID: " + relation.getComponent().getId() + "\nSupplier ID: "
				+ relation.getSupplier().getId() + "\nQuantity ordered: " + quantity;
	}
}
