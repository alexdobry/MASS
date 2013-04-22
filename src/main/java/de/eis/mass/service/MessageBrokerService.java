package de.eis.mass.service;

import java.util.List;
import java.util.Set;

import de.eis.mass.domain.Device;
import de.eis.mass.domain.Offer;

/**
 * Beschreibt die Schnittstelle, um aus der Tabelle "Subscription" die
 * Abonnementen zu einem Angebot zu extrahieren. Gleichzeitig werden die
 * jeweiligen Angebote an die zuvor ermittelten Abonnementen �ber GCM versendet.
 * 
 */
public interface MessageBrokerService {

	/**
	 * Gibt alle Abonnementen zur�ck, die �ber das �bergebene Angebot
	 * Benachrichtigt werden sollen. Dabei wird das Abonnement gepr�ft.
	 * 
	 * @param offer
	 *            welches an die Abonnementen verteilt werden soll
	 * @return alle Ger�te, die �ber GCM benachrichtigt werden sollen
	 */
	public Set<Device> getSubscriberFor(Offer offer);

	/**
	 * Sendet alle Angebote die dem Abonnement entsprechen
	 * 
	 * @param device
	 *            der Abonnement
	 * @param json
	 *            basierte Listen, die alle Angebote enthalten
	 */
	public void sendMessageToDevice(Device device, List<String> json);

}
