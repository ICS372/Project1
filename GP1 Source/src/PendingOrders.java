import java.io.Serializable;
import java.util.LinkedList;

public class PendingOrders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Order> pendingOrderList = new LinkedList<Order>();
	private static PendingOrders pendingOrders;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private PendingOrders() {
	}

	public static PendingOrders instance() {
		if (pendingOrders == null) {
			return (pendingOrders = new PendingOrders());
		} else {
			return pendingOrders;
		}
	}

	public boolean insert(Order order) {
		return pendingOrderList.add(order);
	}

	public boolean remove(Order order) {
		return pendingOrderList.remove(order);
	}

	public Order search(String orderID) {
		for (Order order : pendingOrderList) {
			if (order.getId().equals(orderID)) {
				return order;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String output = "Outstanding orders: \n";
		for (Order order : pendingOrderList) {
			ComponentSupplierRelation relation = order.getRelation();
			output += "Order number: " + order.getId() + " | Component: " + relation.getComponent().getName()
					+ " | Supplier: " + relation.getSupplier().getName() + " | Quantity ordered: " + order.getQuantity()
					+ "\n";
		}
		return output;
	}
}
