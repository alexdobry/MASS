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
 * Datenbank Operationen durchf�hren zu k�nnen. Gleichzeitig werden Operationen
 * zur Verf�gung gestellt, um die jeweiligen Kriterien eines Angebotes zu
 * finden.
 * 
 */
public interface OfferService {

	/**
	 * Gibt alle Kriterien zur�ck, die der �bergebenen Unterkategorie
	 * entsprechen
	 * 
	 * @param subCategory
	 *            welche die Kriterien definiert
	 * @return alle Kriterien der �bergebenen Unterkategorie
	 */
	public List<Criteria> getCriteriasFor(SubCategory subCategory);

	/**
	 * Speichert das �bergebene Angebot
	 * 
	 * @param offer
	 *            welches persistiert werden soll
	 * @return 1, bei erfolg. 0, bei keinem erfolg
	 */
	public int save(Offer offer);

	/**
	 * Gibt alle Angebote zur�ck, die der �bergebenen Kategorie entsprechen
	 * 
	 * @param category
	 *            nach der die Angebote gesucht werden sollen
	 * @return alle Angebote, die der Kategorie entsprechen
	 */
	public List<Offer> getAllOffersFor(Category category);

	/**
	 * Gibt alle Angebote zur�ck, die dem �bergebenen H�ndler entsprechen
	 * 
	 * @param dealer
	 *            nach dem die Angebote gesucht werden sollen
	 * @return alle Angebote, die dem H�ndler entsprechen
	 */
	public List<Offer> getAllOffersFor(Dealer dealer);

	/**
	 * Gibt alle Angebote zur�ck, die der �bergebenen Unterkategorie entsprechen
	 * 
	 * @param subCategory
	 *            nach der die Angebote gesucht werden sollen
	 * @return alle Angebote, die der Unterkategorie entsprechen
	 */
	public List<Offer> getAllOffersFor(SubCategory subCategory);

	/**
	 * Gibt alle Angebote zur�ck, die der �bergebenen Marke entsprechen
	 * 
	 * @param brand
	 *            nach dem die Angebote gesucht werden sollen
	 * @return alle Angebote, die der Marke entsprechen
	 */
	public List<Offer> getAllOffersFor(Brand brand);

	/**
	 * Gibt alle Angebote mit den aufgel�sten Ids in dessen entsprechenden Namen
	 * zur�ck
	 * 
	 * @param offers
	 *            dessen Ids in Namen aufgel�st werden sollen
	 * @return alle Angebote mit allen Eigenschaften
	 */
	public List<Offer> getOffersWithAllProperties(List<Offer> offers);
}
