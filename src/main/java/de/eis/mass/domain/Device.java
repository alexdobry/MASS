package de.eis.mass.domain;

/**
 * Beschreibt ein Android Gerät
 * 
 */
public class Device {

	private String id;

	public Device(String id) {
		this.setId(id);
	}

	public Device() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Device Id: " + id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Device) {
			Device device = (Device) obj;
			return this.getId().equals(device.getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
