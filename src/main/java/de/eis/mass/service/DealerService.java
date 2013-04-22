package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Dealer" innerhalb der
 * Datenbank Operationen durchf�hren zu k�nnen.
 * 
 */
public interface DealerService {

	/**
	 * Sucht nach einem bestimmten H�ndler, welcher �ber den Namen bestimmt
	 * wird.
	 * 
	 * @param name
	 *            der zu suchende H�ndler
	 * @return der gefundene H�ndler
	 */
	public Dealer getDealerByName(String name);

	/**
	 * Gibt alle H�ndler die sich in der Datenbank befinden zur�ck.
	 * 
	 * @return alle H�ndler
	 */
	public List<Dealer> getAllDealer();

	/**
	 * Gibt alle H�ndler einer bestimmten Kategorie zur�ck.
	 * 
	 * @param category
	 *            dessen H�ndler gefunden werden soll
	 * @return alle H�ndler die der Kategorie entsprechen
	 */
	public List<Dealer> getAllDealerFor(Category category);

	/**
	 * Sucht nach einem bestimmten H�ndler, welche �ber die Id bestimmt wird.
	 * 
	 * @param id
	 *            des zu suchenden H�ndler
	 * @return der gefundene H�ndler
	 */
	public Dealer getDealerById(Long id);
}
