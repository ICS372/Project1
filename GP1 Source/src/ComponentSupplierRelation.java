import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a single relationship between a component and a
 * supplier
 * 
 * @author Shuja Uddin
 *
 */
public class ComponentSupplierRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Component component;
	private Supplier supplier;

	/**
	 * Represents the total quantity of this component this supplier has supplied to
	 * date
	 */
	private int quantitySuppliedToDate;

	/**
	 * The component and supplier in the relation are stored.
	 * 
	 * @param component the component being supplied
	 * @param supplier  the supplier supplying the component
	 */
	public ComponentSupplierRelation(Component component, Supplier supplier) {
		this.component = component;
		this.supplier = supplier;
	}

	/**
	 * Returns the total quantity supplied by the supplier so far
	 * 
	 * @return
	 */
	public int getQuantitySuppliedToDate() {
		return quantitySuppliedToDate;
	}

	/**
	 * Adds to the quantity supplied by the supplier so far
	 * 
	 * @param quantity the quantity to be added
	 */
	public void addQuantity(int quantity) {
		this.quantitySuppliedToDate += quantity;
	}

	/**
	 * Returns the supplier in this relation
	 * 
	 * @return the Supplier object
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Returns the component in this relation
	 * 
	 * @return the Component object
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * Checks whether a relation is equal to the one supplied.
	 * 
	 * @param object the relation that will be compared
	 * @return true, iff they have the same component and supplier.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof ComponentSupplierRelation)) {
			return false;
		}

		ComponentSupplierRelation relation = (ComponentSupplierRelation) object;

		return (this.component.equals(relation.component)) && (this.supplier.equals(relation.supplier));
	}

	/**
	 * Generates a hash code for the relation based on the hash of the component and
	 * supplier
	 */
	@Override
	public int hashCode() {
		return Objects.hash(component, supplier);
	}

	/**
	 * A string representation of the relation
	 */
	@Override
	public String toString() {
		return "Component: " + component.getName() + "\nSupplier: " + supplier.getName()
				+ "\nTotal quantity received from " + supplier.getName() + ": " + quantitySuppliedToDate;
	}

}
