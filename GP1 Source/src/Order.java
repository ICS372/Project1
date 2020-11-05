import java.io.Serializable;

/**
 * This class represents a single order.
 * 
 * @author Shuja Uddin
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private ComponentSupplierRelation relation;
	private int quantity;
	private String id;
	private static final String ORDER_MARKER = "O";

	/**
	 * Represents a single component
	 * 
	 * @param relation a ComponentSupplierRelation, containing the component being
	 *                 ordered and the supplier supplying the order
	 * @param quantity the quantity being ordered
	 */
	public Order(ComponentSupplierRelation relation, int quantity) {
		this.relation = relation;
		this.quantity = quantity;
		this.id = ORDER_MARKER + IdGenerator.instance().getOrderId();
	}

	/**
	 * Returns the id of the order
	 * 
	 * @return id of the order
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the order quantity
	 * 
	 * @return the order quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns a relation containing the ordered component and the providing
	 * supplier
	 * 
	 * @return a ComponentSupplierRelation
	 */
	public ComponentSupplierRelation getRelation() {
		return relation;
	}

	/**
	 * A string representation of the order
	 */
	@Override
	public String toString() {
		return "Order ID: " + id + "\nComponent ID: " + relation.getComponent().getId() + "\nSupplier ID: "
				+ relation.getSupplier().getId() + "\nQuantity ordered: " + quantity;
	}
}
