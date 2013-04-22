package de.eis.mass.domain;

/**
 * Beschreibt einen Händler
 * 
 */
public class Dealer {

	private Long id;
	private String name;

	public Dealer(String name) {
		this.name = name;
	}

	public Dealer(long id, String name) {
		this.setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dealer) {
			Dealer dealer = (Dealer) obj;
			return this.getId().equals(dealer.getId());
		} else {
			return false;
		}
	}
}
