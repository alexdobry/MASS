package de.eis.mass.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import de.eis.mass.domain.Device;
import de.eis.mass.domain.Offer;

@Service("messageBrokerService")
public class MessageBrokerServiceImpl implements MessageBrokerService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DeviceSubscribedTopicService deviceSubscribedTopicService;
	@Autowired
	private OfferService offerService;

	private Logger log = Logger.getLogger(MessageBrokerServiceImpl.class);
	private static final String API_KEY = "AIzaSyANw-HIc9v-Ad_U0lJRbDowx8kTb8xg2Os";

	public Set<Device> getSubscriberFor(Offer offer) {
		List<Device> subscriber = new ArrayList<Device>();

		String sqlForCategoryId = "SELECT c.category_id FROM CATEGORY c JOIN SUB_CATEGORY s ON s.category_id = c.category_id AND s.sub_c_id = ?";
		long categoryId = jdbcTemplate.queryForLong(sqlForCategoryId,
				offer.getSubCategoryId());

		String sqlCategory = "SELECT * FROM DEVICE d JOIN SUBSCRIPTION s ON d.reg_id = s.reg_id JOIN SUB_CATEGORY sc ON s.sub_c_id = sc.sub_c_id WHERE sc.category_id = ? OR sc.category_id = 0";
		List<Device> categorieSubscriber = jdbcTemplate.query(sqlCategory,
				new DeviceRowMapper(), categoryId);

		String sqlSubCategory = "SELECT * FROM DEVICE d JOIN SUBSCRIPTION s ON d.reg_id = s.reg_id WHERE s.sub_c_id = ? OR s.sub_c_id = 0";
		List<Device> subCategorieSubscriber = jdbcTemplate
				.query(sqlSubCategory, new DeviceRowMapper(),
						offer.getSubCategoryId());

		String sqlDealer = "SELECT * FROM DEVICE d JOIN SUBSCRIPTION s ON d.reg_id = s.reg_id WHERE s.dealer_id = ? OR s.dealer_id = 0";
		List<Device> dealerSubscriber = jdbcTemplate.query(sqlDealer,
				new DeviceRowMapper(), offer.getDealerId());

		String sqlBrand = "SELECT * FROM DEVICE d JOIN SUBSCRIPTION s ON d.reg_id = s.reg_id WHERE s.brand_id = ? OR s.brand_id = 0";
		List<Device> brandSubscriber = jdbcTemplate.query(sqlBrand,
				new DeviceRowMapper(), offer.getBrandId());

		subscriber = intersection(categorieSubscriber, subCategorieSubscriber);
		subscriber = intersection(subscriber, dealerSubscriber);
		subscriber = intersection(subscriber, brandSubscriber);

		return new HashSet<Device>(subscriber);
	}

	public void sendMessageToDevice(Device device, List<String> json) {
		Sender sender = new Sender(API_KEY);
		for (int i = 0; i < json.size(); i++) {
			Message message = new Message.Builder().collapseKey("1")
					.timeToLive(3).delayWhileIdle(true)
					.addData("initial", json.get(i))
					.addData("current", Integer.toString(i + 1))
					.addData("last", Integer.toString(json.size())).build();
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

	private class DeviceRowMapper implements RowMapper<Device> {

		public Device mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return new Device(resultSet.getString("Reg_ID"));
		}

	}

	@SuppressWarnings("hiding")
	private <Device> List<Device> intersection(List<Device> list1,
			List<Device> list2) {
		List<Device> list = new ArrayList<Device>();

		for (Device t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

}
