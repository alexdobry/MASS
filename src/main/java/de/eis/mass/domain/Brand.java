package de.eis.mass.domain;

/**
 * Beschreibt eine Marke
 *
 */
public class Brand {

	private Long id;
	private String name;

	public Brand(String name) {
		this.name = name;
	}

	public Brand(long id, String name) {
		this.setId(id);
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
		if (obj instanceof Brand) {
			Brand brand = (Brand) obj;
			return this.getId().equals(brand.getId());
		} else {
			return false;
		}
	}
}
