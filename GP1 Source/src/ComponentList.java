import java.util.LinkedList;

public class ComponentList {
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

	public Component search(String componentID) {
		for (Component component : componentList) {
			if (component.getComponentID().equals(componentID)) {
				return component;
			}
		}
		return null;
	}

}
