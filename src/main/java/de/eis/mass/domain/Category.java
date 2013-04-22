package de.eis.mass.domain;

/**
 * Beschreibt eine Kategorie
 * 
 */
public class Category {

	private Long id;
	private String name;

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(String name) {
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
		if (obj instanceof Category) {
			Category category = (Category) obj;
			return this.getId().equals(category.getId());
		} else {
			return false;
		}
	}
}
