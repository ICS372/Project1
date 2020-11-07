import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class represents a single supplier.
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUPPLIER_MARKER = "S";
	private String id;
	private String name;
	private HashSet<ComponentSupplierRelation> componentRelations = new HashSet<ComponentSupplierRelation>();

	/**
	 * Represents a single Supplier
	 * 
	 * @param name
	 *            name of the supplier
	 */
	public Supplier(String name) {
		this.name = name;
		this.id = SUPPLIER_MARKER + IdGenerator.instance().getSupplierId();
	}

	/**
	 * Returns the ID of the supplier.
	 * 
	 * @return ID of the supplier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the name of the supplier.
	 * 
	 * @return name of the supplier
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a set of relationships representing the components supplied by
	 * this supplier.
	 * 
	 * @return a set of ComponentSupplierRelation objects
	 */
	public HashSet<ComponentSupplierRelation> getComponentRelations() {
		return this.componentRelations;
	}

	/**
	 * Adds a relationship between the supplier and a component.
	 * 
	 * @param relation
	 *            the relation to be added
	 * @return true, iff the relation was added
	 */
	public boolean addComponentRelation(ComponentSupplierRelation relation) {
		return componentRelations.add(relation);
	}

	/**
	 * Returns an iterator over all the component relations of this supplier
	 * 
	 * @return an Iterator of all ComponentSupplierRelation objects related to
	 *         this supplier
	 */
	public Iterator<ComponentSupplierRelation> getAllComponents() {
		return componentRelations.iterator();
	}

	/**
	 * Returns a string representation of the supplier.
	 */
	@Override
	public String toString() {
		// return "Supplier name: " + name + " | Supplier ID: " + id;
		String output = "Supplier name: " + name + " | Supplier ID: " + id
				+ "\nComponents supplied:\n";
		Iterator<ComponentSupplierRelation> suppliedComponents = getAllComponents();
		while (suppliedComponents.hasNext()) {
			Component component = suppliedComponents.next().getComponent();
			output += (component.getName() + ", " + component.getId() + "\n");
		}
		output += "\n";
		return output;
	}
}
