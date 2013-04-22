package de.eis.mass.domain;

/**
 * Beschreibt eine Unterkategorie
 * 
 */
public class SubCategory {

	private Long id;
	private String name;

	public SubCategory(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public SubCategory(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubCategory) {
			SubCategory subCategory = (SubCategory) obj;
			return this.getId().equals(subCategory.getId());
		} else {
			return false;
		}
	}
}
