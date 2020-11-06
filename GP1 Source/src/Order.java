import java.io.Serializable;

/**
 * This class represents a single order.
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private ComponentSupplierRelation relation;
	private int quantity;
	private String id;

	/**
	 * Constant {@code String} value that is placed in the beginning of every
	 * {@code id} to indicate that this {@code String} represents an
	 * {@code Order}.
	 */
	private static final String ORDER_MARKER = "O";

	/**
	 * Represents a single component
	 * 
	 * @param relation
	 *            a ComponentSupplierRelation, containing the component being
	 *            ordered and the supplier supplying the order
	 * @param quantity
	 *            the quantity being ordered
	 */
	public Order(ComponentSupplierRelation relation, int quantity) {
		this.relation = relation;
		this.quantity = quantity;
		this.id = ORDER_MARKER + IdGenerator.instance().getOrderId();
	}

	/**
	 * Returns the identification of the order.
	 * 
	 * @return {@code id}.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the order quantity.
	 * 
	 * @return {@code quantity}.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns a {@code ComponentSupplierRelation}, a relation containing the
	 * ordered component and the providing supplier.
	 * 
	 * @return {@code relation}.
	 */
	public ComponentSupplierRelation getRelation() {
		return relation;
	}

	/**
	 * A string representation of the order
	 */
	@Override
	public String toString() {
		return "Order ID: " + id + "\nComponent ID: "
				+ relation.getComponent().getId() + "\nSupplier ID: "
				+ relation.getSupplier().getId() + "\nQuantity ordered: "
				+ quantity;
	}
}
