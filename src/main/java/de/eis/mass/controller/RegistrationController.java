package de.eis.mass.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.Device;
import de.eis.mass.domain.Offer;
import de.eis.mass.service.BrandService;
import de.eis.mass.service.CategoryService;
import de.eis.mass.service.DealerService;
import de.eis.mass.service.DeviceService;
import de.eis.mass.service.DeviceSubscribedTopicService;
import de.eis.mass.service.MessageBrokerService;
import de.eis.mass.service.OfferService;

/**
 * Nimmt 2 Arten von Registrierungen entgegen. Zum einen die Registrierung eines
 * Devices mit seiner von GCM erhaltenen RegistrationId. Zum anderen das
 * Abonnement zwischen Device und einer Liste von Topics.
 * 
 * 
 */
@Controller
public class RegistrationController {

	private DeviceService deviceService;
	private DealerService dealerService;
	private CategoryService categoryService;
	private DeviceSubscribedTopicService deviceSubscribedTopicService;
	private BrandService brandService;
	private OfferService offerService;
	private MessageBrokerService messageBrokerService;
	private Logger log = Logger.getLogger(RegistrationController.class);

	public RegistrationController(DeviceService deviceService,
			DeviceSubscribedTopicService deviceSubscribedTopicService,
			DealerService dealerService, CategoryService categoryService,
			BrandService brandService, OfferService offerService,
			MessageBrokerService messageBrokerService) {
		this.deviceService = deviceService;
		this.deviceSubscribedTopicService = deviceSubscribedTopicService;
		this.dealerService = dealerService;
		this.categoryService = categoryService;
		this.brandService = brandService;
		this.offerService = offerService;
		this.messageBrokerService = messageBrokerService;
	}

	private static final String API_KEY = "AIzaSyANw-HIc9v-Ad_U0lJRbDowx8kTb8xg2Os";

	/**
	 * Nimmt alle REST POSTs auf die Ressource /device entgegen. Anschließend
	 * wird das Device mit einer übergebenen Id persistiert.
	 * 
	 * @param id
	 *            des Gerätes
	 * @return String, falls die Registrierung erfolgreich war.
	 * @throws NullPointerException
	 *             wenn die Id null ist oder das Gerät bereits im System
	 *             gespeichert wurde
	 */
	@RequestMapping(value = "/device", method = RequestMethod.POST)
	@ResponseBody
	public String registerDevice(@RequestBody String id) {
		if (!deviceService.getAllDevices().contains(new Device(id))) {
			String regId = id.substring(3);
			log.debug("registerDevice called with id: " + regId);
			Device device = new Device();
			device.setId(regId);
			deviceService.save(device);
			return "device registred!";
		} else {
			throw new NullPointerException(
					"id was null or device is already registred");
		}

	}

	/**
	 * Nimmt alle REST POSTs auf die Ressource /device/id/topicSubscription
	 * entgegen. Das Abonnement zwischen Device und Topics wird persistiert und
	 * anschließend werden alle Angebote, die dem Abonnement entsprechen, an das
	 * Device über GCM geschickt.
	 * 
	 * @param json
	 *            objekt einer Liste von Topics
	 * @param id
	 *            des Abonnementen
	 * @return String response, sofern alles erfolgreich war
	 */
	@RequestMapping(value = "/device/{id}/topicSubscription", method = RequestMethod.POST)
	@ResponseBody
	public String subscribeTopic(@RequestBody String json,
			@PathVariable String id) {
		log.debug("registerTopics JSON String: " + json);
		log.debug("post request from device: " + id);
		Type type = new TypeToken<List<Category>>() {
		}.getType();
		List<Category> topics = new Gson().fromJson(json, type);
		// category objekte aus der datenbank, mit id
		List<Category> categories = new ArrayList<Category>();
		for (Category category : topics) {
			categories
					.add(categoryService.getCategoryByName(category.getName()));
		}

		log.debug("json to category objects: " + categories);

		// abonnement
		Device device = deviceService.getDeviceByid(id);

		for (Category category : categories) {
			// erstmal keine lebensmittel
			if (!category.getName().equals("Lebensmittel")) {
				deviceSubscribedTopicService.save(device, category);
			}
		}

		prepareSubscriptionFor(device);
		return "obtained: " + topics;
	}

	/**
	 * Bereitet alle Angebote, die das übergebene Device abonniert hat vor.
	 * Anschließend werden alle Angebote in JSON Objekte gemarshalled und über
	 * den Message Broker übertragen.
	 * 
	 * @param device
	 *            der Abonnement
	 */
	private void prepareSubscriptionFor(Device device) {
		List<Category> categories = deviceSubscribedTopicService
				.getCategorySubscriptionsFor(device);
		List<String> json = new ArrayList<String>();
		List<List<Offer>> allOffers = new ArrayList<List<Offer>>();
		// je eine liste für eine abonnierte kategorie
		for (Category category : categories) {
			allOffers.add(new ArrayList<Offer>(offerService
					.getOffersWithAllProperties(offerService
							.getAllOffersFor(category))));
		}

		// je ein json pro liste
		for (int i = 0; i < allOffers.size(); i++) {
			json.add(new Gson().toJson(allOffers.get(i)));
		}

		messageBrokerService.sendMessageToDevice(device, json);
	}
}