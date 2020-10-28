import java.io.Serializable;
import java.util.LinkedList;

public class ComponentList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Component> componentList = new LinkedList<Component>();
	private static ComponentList components;

	/*
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

	public boolean insert(Component component) {
		return componentList.add(component);
	}

	public Component search(String componentID) {
		for (Component component : componentList) {
			if (component.getId().equals(componentID)) {
				return component;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String output = "";
		for (Component component : componentList) {
			output += "Component name: " + component.getName() + " | Component ID: " + component.getId()
					+ " | Quantity on hand: " + component.getStock() + "\n";
		}
		return output;
	}
}
