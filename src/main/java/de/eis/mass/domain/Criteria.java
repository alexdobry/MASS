package de.eis.mass.domain;

/**
 * Beschreibt ein Kriterium eines Angebotes
 * 
 */
public class Criteria {

	private String name;

	public Criteria(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
