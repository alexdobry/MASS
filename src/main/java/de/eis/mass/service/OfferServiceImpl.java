package de.eis.mass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Category;
import de.eis.mass.domain.Criteria;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.Offer;
import de.eis.mass.domain.SubCategory;

@Service("offerService")
public class OfferServiceImpl implements OfferService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private List<String> tvCriterias = Arrays.asList("zoll", "hz", "typ", "hd");
	private List<String> laptopCriterias = Arrays.asList("zoll", "ram", "cpu",
			"hdd");
	private List<String> tableCriterias = Arrays.asList("material", "hoehe",
			"laenge", "breite");
	private List<String> bedCriterias = Arrays.asList("material", "typ",
			"laenge", "breite");
	private List<String> tshirtCriterias = Arrays.asList("groesse", "material",
			"farbe");
	private List<String> pantCriterias = Arrays.asList("groesse", "material",
			"farbe");

	public List<Criteria> getCriteriasFor(SubCategory subCategory) {
		return getCriterias(subCategory);
	}

	public int save(Offer offer) {
		String sql = "INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, Price, Start_Date, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int rowCount = jdbcTemplate.update(sql, getNextOfferId(),
				offer.getSubCategoryId(), offer.getBrandId(),
				offer.getDealerId(), offer.getName(), offer.getPrice(),
				offer.getStart_date(), offer.getEnd_date(),
				offer.getCriteria1(), offer.getCriteria2(),
				offer.getCriteria3(), offer.getCriteria4());
		return rowCount;
	}

	public List<Offer> getAllOffersFor(Category category) {
		String sql = "SELECT o.* FROM OFFER o JOIN SUB_CATEGORY s ON o.sub_c_id = s.sub_c_id JOIN CATEGORY c ON s.category_id = c.category_id WHERE c.name = ?";
		List<Offer> offers = jdbcTemplate.query(sql, new OfferRowMapper(),
				category.getName());
		return offers;
	}

	public List<Offer> getOffersWithAllProperties(List<Offer> offers) {
		String sqlForBrand = "SELECT b.name FROM BRAND b where b.brand_id = ?";
		String sqlForCategory = "SELECT c.name FROM CATEGORY c JOIN SUB_CATEGORY s ON s.category_id = c.category_id AND s.sub_c_id = ?";
		String sqlForSubCategory = "SELECT s.name FROM SUB_CATEGORY s where s.sub_c_id = ?";
		String sqlForDealer = "SELECT d.name FROM DEALER d where d.dealer_id = ?";
		String sqlForStreet = "SELECT d.street FROM DEALER d where d.dealer_id = ?";
		String sqlForZip = "SELECT d.zip FROM DEALER d where d.dealer_id = ?";
		String sqlForCity = "SELECT d.city FROM DEALER d where d.dealer_id = ?";
		for (Offer offer : offers) {
			offer.setCategoryName(jdbcTemplate.query(sqlForCategory,
					new StringRowMapper(), offer.getSubCategoryId()).get(0));
			offer.setSubCategoryName(jdbcTemplate.query(sqlForSubCategory,
					new StringRowMapper(), offer.getSubCategoryId()).get(0));
			offer.setBrandName(jdbcTemplate.query(sqlForBrand,
					new StringRowMapper(), offer.getBrandId()).get(0));
			offer.setDealerName(jdbcTemplate.query(sqlForDealer,
					new StringRowMapper(), offer.getDealerId()).get(0));

			offer.setStreet(jdbcTemplate.query(sqlForStreet,
					new StringRowMapper(), offer.getDealerId()).get(0));
			offer.setZip(jdbcTemplate.query(sqlForZip, new StringRowMapper(),
					offer.getDealerId()).get(0));
			offer.setCity(jdbcTemplate.query(sqlForCity, new StringRowMapper(),
					offer.getDealerId()).get(0));
		}
		return offers;
	}

	public List<Offer> getAllOffersFor(Dealer dealer) {
		String sql = "SELECT o.* FROM OFFER o JOIN DEALER d ON o.dealer_id = d.dealer_id  WHERE d.name = ?";
		List<Offer> offers = jdbcTemplate.query(sql, new OfferRowMapper(),
				dealer.getName());
		return offers;
	}

	public List<Offer> getAllOffersFor(SubCategory subCategory) {
		String sql = "SELECT o.* FROM OFFER o JOIN SUB_CATEGORY s ON s.sub_c_id = o.sub_c_id WHERE s.name = ?";
		List<Offer> offers = jdbcTemplate.query(sql, new OfferRowMapper(),
				subCategory.getName());
		return offers;
	}

	public List<Offer> getAllOffersFor(Brand brand) {
		String sql = "SELECT o.* FROM OFFER o JOIN Brand b ON o.brand_id = b.brand_id  WHERE b.name = ?";
		List<Offer> offers = jdbcTemplate.query(sql, new OfferRowMapper(),
				brand.getName());
		return offers;
	}

	private Long getNextOfferId() {
		String sql = "call next value for Offer_SEQ";
		return jdbcTemplate.queryForLong(sql);
	}

	private class OfferRowMapper implements RowMapper<Offer> {

		public Offer mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return new Offer(resultSet.getLong("Offer_ID"),
					resultSet.getString("Name"), resultSet.getDouble("Price"),
					resultSet.getDouble("Old_Price"),
					resultSet.getLong("SUB_C_ID"),
					resultSet.getLong("Dealer_ID"),
					resultSet.getLong("Brand_ID"),
					resultSet.getString("End_Date"),
					resultSet.getString("Criteria1"),
					resultSet.getString("Criteria2"),
					resultSet.getString("Criteria3"),
					resultSet.getString("Criteria4"));
		}
	}

	private class StringRowMapper implements RowMapper<String> {

		public String mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return resultSet.getString(1);
		}
	}

	private List<Criteria> getCriterias(SubCategory subCategory) {
		List<String> criterias = new ArrayList<String>();
		List<Criteria> filledCriterias = new ArrayList<Criteria>();

		if (subCategory.getName().equals("fernseher")) {
			criterias = tvCriterias;
		}

		if (subCategory.getName().equals("notebook")) {
			criterias = laptopCriterias;
		}
		if (subCategory.getName().equals("tisch")) {
			criterias = tableCriterias;
		}

		if (subCategory.getName().equals("bett")) {
			criterias = bedCriterias;
		}
		if (subCategory.getName().equals("t-shirt")) {
			criterias = tshirtCriterias;
		}

		if (subCategory.getName().equals("hose")) {
			criterias = pantCriterias;
		}

		return fillCriterias(criterias, filledCriterias);
	}

	private List<Criteria> fillCriterias(List<String> criterias,
			List<Criteria> filledCriterias) {
		for (String criteria : criterias) {
			filledCriterias.add(new Criteria(criteria));
		}

		return filledCriterias;
	}

}
