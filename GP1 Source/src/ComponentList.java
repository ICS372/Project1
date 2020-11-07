import java.io.Serializable;
import java.util.LinkedList;

/**
 * The collection class for Component objects
 * 
 * @author Shuja Uddin
 * @version 6 November 2020
 *
 */
public class ComponentList implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Component> componentList = new LinkedList<Component>();
	private static ComponentList components;

	/**
	 * Private constructor for singleton pattern
	 * 
	 */
	private ComponentList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ComponentList instance() {
		if (components == null) {
			return (components = new ComponentList());
		} else {
			return components;
		}
	}

	/**
	 * Inserts a given component to the list of components
	 * 
	 * @param component
	 *            component to be inserted
	 * @return {@literal}, if insertion was successful. Otherwise,
	 *         {@literal false}.
	 */
	public boolean insert(Component component) {
		return componentList.add(component);
	}

	/**
	 * Searches the collection for a component with the given ID.
	 * 
	 * @param componentID
	 *            the ID being searched for
	 * @return a {@code Component} object with the matching ID, if one is found.
	 *         Otherwise, {@literal null}.
	 */
	public Component search(String componentID) {
		for (Component component : componentList) {
			if (component.getId().equals(componentID)) {
				return component;
			}
		}
		return null;
	}

	/**
	 * A {@code String} representation of all {@code Component} objects in
	 * {@code ComponentList}.
	 */
	@Override
	public String toString() {
		String output = "";
		for (Component component : componentList) {
			output += (component.toString() + "\n");
		}
		return output;
	}
}
