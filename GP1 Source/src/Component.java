import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class Component implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String COMPONENT_MARKER = "C";
	private String name;
	private String id;
	private int stock;
	private HashSet<ComponentSupplierRelation> supplierRelations = new HashSet<ComponentSupplierRelation>();

	/**
	 * Represents a single component
	 * 
	 * @param name name of the component
	 */
	public Component(String name) {
		this.name = name;
		id = COMPONENT_MARKER + IdGenerator.instance().getComponentId();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getStock() {
		return stock;
	}

	public void addToStock(int quantity) {
		this.stock += quantity;
	}

	public HashSet<ComponentSupplierRelation> getSupplierRelations() {
		return this.supplierRelations;
	}

	public boolean addSupplierRelation(ComponentSupplierRelation relation) {
		return supplierRelations.add(relation);
	}

	public boolean assign(int quantity) {
		if (stock > quantity) {
			stock -= quantity;
			return true;
		}
		return false;
	}

	public ComponentSupplierRelation getSupplier(Supplier supplier) {
		for (ComponentSupplierRelation relation : supplierRelations) {
			if (relation.getSupplier().equals(supplier)) {
				return relation;
			}
		}
		return null;
	}

	public Iterator<ComponentSupplierRelation> getAllSuppliers() {
		return supplierRelations.iterator();
	}

	@Override
	public String toString() {
		return "Component name: " + name + "\nComponent ID: " + id + "\nQuantity on hand: " + stock;
	}
}
