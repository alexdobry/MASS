package de.eis.mass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

import de.eis.mass.domain.Device;
import de.eis.mass.domain.Offer;
import de.eis.mass.service.BrandService;
import de.eis.mass.service.CategoryService;
import de.eis.mass.service.DealerService;
import de.eis.mass.service.MessageBrokerService;
import de.eis.mass.service.OfferService;

@Controller
public class MessageController {

	private DealerService dealerService;
	private CategoryService categoryService;
	private MessageBrokerService messageBrokerService;
	private OfferService offerService;
	private BrandService brandService;

	private static final String API_KEY = "AIzaSyANw-HIc9v-Ad_U0lJRbDowx8kTb8xg2Os";
	private Logger log = Logger.getLogger(MessageController.class);

	public MessageController(DealerService dealerService,
			CategoryService categoryService,
			MessageBrokerService messageBrokerService,
			OfferService offerService, BrandService brandService) {
		this.dealerService = dealerService;
		this.categoryService = categoryService;
		this.messageBrokerService = messageBrokerService;
		this.offerService = offerService;
		this.brandService = brandService;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createOffer(String productname, String price,
			String oldprice, String category, String subCategory,
			String dealer, String brand, String criteria1, String criteria2,
			String criteria3, String criteria4, String date, ModelMap map) {
		List<String> criterias = new ArrayList<String>();
		criterias.add(criteria1);
		criterias.add(criteria2);
		criterias.add(criteria3);
		criterias.add(criteria4);

		Offer offer = prepareAndSaveOffer(productname, oldprice, price,
				subCategory, dealer, brand, date, criterias);

		map.addAttribute("productname", productname);
		map.addAttribute("price", price);
		sendOfferToGcm(offer);

		return "thankYou";
	}

	private Offer prepareAndSaveOffer(String productname, String oldprice,
			String price, String subCategory, String dealer, String brand,
			String date, List<String> criterias) {
		Long subCategoryId = categoryService.getSubCategoryByName(subCategory)
				.getId();
		Long dealerId = dealerService.getDealerByName(dealer).getId();
		Long brandId = brandService.getBrandByName(brand).getId();

		Offer offer = new Offer(productname, Double.parseDouble(oldprice),
				Double.parseDouble(price), subCategoryId, dealerId, brandId,
				date, criterias.get(0), criterias.get(1), criterias.get(2),
				criterias.get(3));
		offerService.save(offer);

		// liste an geboten wird erwartet...
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(offer);
		// offer objekt mit allen eigenschaften aufbereiten
		List<Offer> offersWithAllProperties = offerService
				.getOffersWithAllProperties(offers);

		return offersWithAllProperties.get(0);
	}

	private void sendOfferToGcm(Offer offer) {
		Set<Device> subscriber = messageBrokerService.getSubscriberFor(offer);

		String json = new Gson().toJson(offer);
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder().collapseKey("1").timeToLive(3)
				.delayWhileIdle(true).addData("json", json).build();
		for (Device device : subscriber) {
			try {
				Result result = sender.send(message, device.getId(), 5);
				log.debug(result == null ? "result is null" : message.getData()
						.toString() + " messageId: " + result.getMessageId());
				handleExceptions(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void handleExceptions(Result result) {
		if (result.getMessageId() != null) {
			String canonicalRegId = result.getCanonicalRegistrationId();
			if (canonicalRegId != null) {
				log.debug("same device has more than on registration ID: update database");
			}
		} else {
			String error = result.getErrorCodeName();
			if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				log.debug("application has been removed from device - unregister database");
			}
		}
	}
}