import java.io.Serializable;
import java.util.Objects;

public class ComponentSupplierRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Component component;
	private Supplier supplier;
	private int quantitySuppliedToDate;

	public ComponentSupplierRelation(Component component, Supplier supplier) {
		this.component = component;
		this.supplier = supplier;
	}

	public int getQuantitySuppliedToDate() {
		return quantitySuppliedToDate;
	}

	public void addQuantity(int quantity) {
		this.quantitySuppliedToDate += quantity;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Component getComponent() {
		return component;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof ComponentSupplierRelation)) {
			return false;
		}

		ComponentSupplierRelation relation = (ComponentSupplierRelation) o;

		return (this.component.equals(relation.component)) && (this.supplier.equals(relation.supplier));
	}

	@Override
	public int hashCode() {
		return Objects.hash(component, supplier);
	}

	@Override
	public String toString() {
		return "Component: " + component.getName() + "\nSupplier: " + supplier.getName()
				+ "\nTotal quantity received from " + supplier.getName() + ": " + quantitySuppliedToDate;
	}

}
