import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a single relationship between a component and a
 * supplier
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class ComponentSupplierRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Component component;
	private Supplier supplier;

	/**
	 * Represents the total quantity of this component this supplier has
	 * supplied to date.
	 */
	private int quantitySuppliedToDate;

	/**
	 * Generates a constructor for {@code ComponentSupplierRelation}. In it, a
	 * {@code Component} and {@code Supplier} are stored.
	 * 
	 * @param component
	 *            the {@code Component} being supplied by the {@code supplier}.
	 * @param supplier
	 *            the {@code Supplier} supplying the {@code component}.
	 */
	public ComponentSupplierRelation(Component component, Supplier supplier) {
		this.component = component;
		this.supplier = supplier;
	}

	/**
	 * Returns the total quantity supplied by the {@code supplier} so far
	 * 
	 * @return {@code quantitySuppliedToDate}.
	 */
	public int getQuantitySuppliedToDate() {
		return quantitySuppliedToDate;
	}

	/**
	 * Adds a given quantity to the {@code quantitySuppliedToDate}, the quantity
	 * supplied by the supplier so far.
	 * 
	 * @param quantity
	 *            the quantity to be added.
	 */
	public void addQuantity(int quantity) {
		this.quantitySuppliedToDate += quantity;
	}

	/**
	 * Returns the {@code Supplier} that is supplying the {@code Component} in
	 * the relation.
	 * 
	 * @return {@code supplier}.
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Returns the {@code Component} that is being supplied by the
	 * {@code Supplier} in the relation.
	 * 
	 * @return {@code component}.
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * Checks whether a relation is equal to the one supplied.
	 * 
	 * @param object
	 *            the relation that will be compared.
	 * @return {@literal}, if they have the same component and supplier.
	 *         Otherwise, {@literal false}.
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

		return (this.component.equals(relation.component))
				&& (this.supplier.equals(relation.supplier));
	}

	/**
	 * Generates a hash code for the relation based on the hash of the
	 * {@code component}. and {@code supplier}.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(component, supplier);
	}

	/**
	 * Returns a string representation of the relation.
	 */
	@Override
	public String toString() {
		return "Component: " + component.getName() + "\nSupplier: "
				+ supplier.getName() + "\nTotal quantity received from "
				+ supplier.getName() + ": " + quantitySuppliedToDate;
	}

}
