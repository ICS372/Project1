import java.io.Serializable;
import java.util.LinkedList;

/**
 * The collection class for outstanding orders. An outstanding order is an
 * {@code Order} that has not been fulfilled. If an order is fulfilled, then it
 * is removed the {@code PendingOrders} list. See
 * {@link Company#fulfillOrder(String)} and {@link PendingOrders#remove(Order)}
 * for more information.
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class PendingOrders implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Order> pendingOrderList = new LinkedList<Order>();
	private static PendingOrders pendingOrders;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private PendingOrders() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static PendingOrders instance() {
		if (pendingOrders == null) {
			return (pendingOrders = new PendingOrders());
		} else {
			return pendingOrders;
		}
	}

	/**
	 * Inserts a given order to the list of orders
	 * 
	 * @param order
	 *            order to be inserted
	 * @return {@literal true}, if insertion was successful. Otherwise,
	 *         {@literal false}.
	 */
	public boolean insert(Order order) {
		return pendingOrderList.add(order);
	}

	/**
	 * Removes a given order from the list of orders
	 * 
	 * @param order
	 *            order to be removed
	 * @return true, if the collection contained the given order
	 */
	public boolean remove(Order order) {
		return pendingOrderList.remove(order);
	}

	/**
	 * Searches the collection for an order with the given ID.
	 * 
	 * @param orderID
	 *            the ID being searched for
	 * @return an {@code Order} object whose {@code id} matches with
	 *         {@code orderID}, if one is found. Otherwise, {@literal null}.
	 */
	public Order search(String orderID) {
		for (Order order : pendingOrderList) {
			if (order.getId().equals(orderID)) {
				return order;
			}
		}
		return null;
	}

	/**
	 * Returns a string representation of all outstanding orders.
	 */
	@Override
	public String toString() {
		String output = "Outstanding orders: \n";
		for (Order order : pendingOrderList) {
			ComponentSupplierRelation relation = order.getRelation();
			output += "Order number: " + order.getId() + " | Component: "
					+ relation.getComponent().getName() + " | Supplier: "
					+ relation.getSupplier().getName() + " | Quantity ordered: "
					+ order.getQuantity() + "\n";
		}
		return output;
	}
}
