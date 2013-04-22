package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.Offer;
import de.eis.mass.domain.SubCategory;
import de.eis.mass.domain.Criteria;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Offer" innerhalb der
 * Datenbank Operationen durchführen zu können. Gleichzeitig werden Operationen
 * zur Verfügung gestellt, um die jeweiligen Kriterien eines Angebotes zu
 * finden.
 * 
 */
public interface OfferService {

	/**
	 * Gibt alle Kriterien zurück, die der übergebenen Unterkategorie
	 * entsprechen
	 * 
	 * @param subCategory
	 *            welche die Kriterien definiert
	 * @return alle Kriterien der übergebenen Unterkategorie
	 */
	public List<Criteria> getCriteriasFor(SubCategory subCategory);

	/**
	 * Speichert das übergebene Angebot
	 * 
	 * @param offer
	 *            welches persistiert werden soll
	 * @return 1, bei erfolg. 0, bei keinem erfolg
	 */
	public int save(Offer offer);

	/**
	 * Gibt alle Angebote zurück, die der übergebenen Kategorie entsprechen
	 * 
	 * @param category
	 *            nach der die Angebote gesucht werden sollen
	 * @return alle Angebote, die der Kategorie entsprechen
	 */
	public List<Offer> getAllOffersFor(Category category);

	/**
	 * Gibt alle Angebote zurück, die dem übergebenen Händler entsprechen
	 * 
	 * @param dealer
	 *            nach dem die Angebote gesucht werden sollen
	 * @return alle Angebote, die dem Händler entsprechen
	 */
	public List<Offer> getAllOffersFor(Dealer dealer);

	/**
	 * Gibt alle Angebote zurück, die der übergebenen Unterkategorie entsprechen
	 * 
	 * @param subCategory
	 *            nach der die Angebote gesucht werden sollen
	 * @return alle Angebote, die der Unterkategorie entsprechen
	 */
	public List<Offer> getAllOffersFor(SubCategory subCategory);

	/**
	 * Gibt alle Angebote zurück, die der übergebenen Marke entsprechen
	 * 
	 * @param brand
	 *            nach dem die Angebote gesucht werden sollen
	 * @return alle Angebote, die der Marke entsprechen
	 */
	public List<Offer> getAllOffersFor(Brand brand);

	/**
	 * Gibt alle Angebote mit den aufgelösten Ids in dessen entsprechenden Namen
	 * zurück
	 * 
	 * @param offers
	 *            dessen Ids in Namen aufgelöst werden sollen
	 * @return alle Angebote mit allen Eigenschaften
	 */
	public List<Offer> getOffersWithAllProperties(List<Offer> offers);
}
