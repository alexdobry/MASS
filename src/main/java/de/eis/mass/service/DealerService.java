package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Dealer" innerhalb der
 * Datenbank Operationen durchführen zu können.
 * 
 */
public interface DealerService {

	/**
	 * Sucht nach einem bestimmten Händler, welcher über den Namen bestimmt
	 * wird.
	 * 
	 * @param name
	 *            der zu suchende Händler
	 * @return der gefundene Händler
	 */
	public Dealer getDealerByName(String name);

	/**
	 * Gibt alle Händler die sich in der Datenbank befinden zurück.
	 * 
	 * @return alle Händler
	 */
	public List<Dealer> getAllDealer();

	/**
	 * Gibt alle Händler einer bestimmten Kategorie zurück.
	 * 
	 * @param category
	 *            dessen Händler gefunden werden soll
	 * @return alle Händler die der Kategorie entsprechen
	 */
	public List<Dealer> getAllDealerFor(Category category);

	/**
	 * Sucht nach einem bestimmten Händler, welche über die Id bestimmt wird.
	 * 
	 * @param id
	 *            des zu suchenden Händler
	 * @return der gefundene Händler
	 */
	public Dealer getDealerById(Long id);
}
