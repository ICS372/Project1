import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class represents a single component.
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class Component implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constant {@code String} value that is placed in the beginning of every
	 * {@code id} to indicate that this {@code String} represents a
	 * {@code Component}.
	 */
	private static final String COMPONENT_MARKER = "C";
	private String name;
	private String id;
	private int stock;
	private HashSet<ComponentSupplierRelation> supplierRelations = new HashSet<ComponentSupplierRelation>();

	/**
	 * Represents a single {@code Component}.
	 * 
	 * @param name
	 *            the name of the {@code Component}.
	 */
	public Component(String name) {
		this.name = name;
		id = COMPONENT_MARKER + IdGenerator.instance().getComponentId();
	}

	/**
	 * Returns the name of the {@code Component}.
	 * 
	 * @return {@code name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the identification of the {@code Component}.
	 * 
	 * @return {@code id}.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the current stock quantity of the {@code Component}.
	 * 
	 * @return {@code stock}.
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Adds the given quantity to the component stock
	 * 
	 * @param quantity
	 *            the quantity to be added.
	 */
	public void addToStock(int quantity) {
		this.stock += quantity;
	}

	/**
	 * Returns a set of relationships representing the suppliers that supply
	 * this component.
	 * 
	 * @return a set of {@code ComponentSupplierRelation} objects
	 */
	public HashSet<ComponentSupplierRelation> getSupplierRelations() {
		return this.supplierRelations;
	}

	/**
	 * Adds a relationship between the component and a supplier.
	 * 
	 * @param relation
	 *            the relation to be added
	 * @return {@literal true}, if the relation was successfully added.
	 *         Otherwise, {@literal false}.
	 */
	public boolean addSupplierRelation(ComponentSupplierRelation relation) {
		return supplierRelations.add(relation);
	}

	/**
	 * Removes the given quantity from {@code stock}.
	 * 
	 * @param quantity
	 *            quantity to be removed
	 * @return {@literal true}, if there is enough {@code stock}. Otherwise,
	 *         {@literal false}.
	 */
	public boolean assign(int quantity) {
		if (stock >= quantity) {
			stock -= quantity;
			return true;
		}
		return false;
	}

	/**
	 * Finds and returns the relation between this component and the given
	 * supplier, if one exists.
	 * 
	 * @param supplier
	 *            the supplier whose relation is being searched for
	 * @return a ComponentSupplierRelation object of this component and the
	 *         given supplier.
	 */
	public ComponentSupplierRelation getSupplier(Supplier supplier) {
		for (ComponentSupplierRelation relation : supplierRelations) {
			if (relation.getSupplier().equals(supplier)) {
				return relation;
			}
		}
		return null;
	}

	/**
	 * Returns an iterator over all the supplier relations of this component
	 * 
	 * @return an Iterator of all ComponentSupplierRelation objects related to
	 *         this component.
	 */
	public Iterator<ComponentSupplierRelation> getAllSuppliers() {
		return supplierRelations.iterator();
	}

	/**
	 * Returns a {@code String} representation of the {@code Component}.
	 */
	@Override
	public String toString() {
		/*
		 * return "Component name: " + name + " | Component ID: " + id +
		 * " | Quantity on hand: " + stock;
		 */
		String output = "Component name: " + name + " | Component ID: " + id
				+ " | Quantity on hand: " + stock + "\nSuppliers:\n";
		Iterator<ComponentSupplierRelation> componentSuppliers = getAllSuppliers();
		while (componentSuppliers.hasNext()) {
			Supplier supplier = componentSuppliers.next().getSupplier();
			output += (supplier.getName() + ", " + supplier.getId() + "\n");
		}
		output += "\n";
		return output;
	}
}
