import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class Supplier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SUPPLIER_MARKER = "S";
	private String id;
	private String name;
	private HashSet<ComponentSupplierRelation> componentRelations = new HashSet<ComponentSupplierRelation>();

	public Supplier(String name) {
		this.name = name;
		this.id = SUPPLIER_MARKER + IdGenerator.instance().getSupplierId();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public HashSet<ComponentSupplierRelation> getComponentRelations() {
		return this.componentRelations;
	}

	public boolean addComponentRelation(ComponentSupplierRelation relation) {
		return componentRelations.add(relation);
	}

	public Iterator<ComponentSupplierRelation> getAllComponents() {
		return componentRelations.iterator();
	}

	@Override
	public String toString() {
		return "Supplier name: " + name + "\nSupplier ID: " + id;
	}
}
