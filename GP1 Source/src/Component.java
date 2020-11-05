import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class represents a single component.
 * 
 * @author Shuja Uddin
 *
 */
public class Component implements Serializable {
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

	/**
	 * Returns the name of the component
	 * 
	 * @return name of the component
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the id of the component
	 * 
	 * @return id of the component
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the current stock quantity of the component
	 * 
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Adds the given quantity to the component stock
	 * 
	 * @param quantity quantity to be added
	 */
	public void addToStock(int quantity) {
		this.stock += quantity;
	}

	/**
	 * Returns a set of relationships representing the suppliers that supply this
	 * component.
	 * 
	 * @return a set of ComponentSupplierRelation objects
	 */
	public HashSet<ComponentSupplierRelation> getSupplierRelations() {
		return this.supplierRelations;
	}

	/**
	 * Adds a relationship between the component and a supplier
	 * 
	 * @param relation the relation to be added
	 * @return true, iff the relation was added
	 */
	public boolean addSupplierRelation(ComponentSupplierRelation relation) {
		return supplierRelations.add(relation);
	}

	/**
	 * Removes the given quantity from stock
	 * 
	 * @param quantity quantity to be removed
	 * @return true, if there is enough stock
	 */
	public boolean assign(int quantity) {
		if (stock >= quantity) {
			stock -= quantity;
			return true;
		}
		return false;
	}

	/**
	 * Finds and returns the relation between this component and the given supplier,
	 * if one exists.
	 * 
	 * @param supplier the supplier whose relation is being searched for
	 * @return a ComponentSupplierRelation object of this component and the given
	 *         supplier
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
	 * @return an Iterator of all ComponentSupplierRelation objects related to this
	 *         component
	 */
	public Iterator<ComponentSupplierRelation> getAllSuppliers() {
		return supplierRelations.iterator();
	}

	/**
	 * Returns a string representation of the component
	 */
	@Override
	public String toString() {
		return "Component name: " + name + "\nComponent ID: " + id + "\nQuantity on hand: " + stock;
	}
}
