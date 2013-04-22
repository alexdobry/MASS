package de.eis.mass.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Beschreibt ein Angebot
 * 
 */
public class Offer {

	private Long offerId;
	private Long subCategoryId;
	private Long brandId;
	private Long dealerId;
	private String name;
	private double oldPrice;
	private double price;
	private String start_date;
	private String end_date;
	private String criteria1;
	private String criteria2;
	private String criteria3;
	private String criteria4;

	private String categoryName;
	private String subCategoryName;
	private String brandName;
	private String dealerName;
	private String street;
	private String zip;
	private String city;

	public Offer(double oldPrice, double newPrice) {
		this.oldPrice = oldPrice;
		this.price = newPrice;
	}

	public Offer(Long offerId, Long subCategoryId, Long brandId, Long dealerId,
			String name, double price) {
		this.setOfferId(offerId);
		this.setSubCategoryId(subCategoryId);
		this.setBrandId(brandId);
		this.setDealerId(dealerId);
		this.setName(name);
		this.setPrice(price);

	}

	public Offer(String productname, double oldPrice, double price,
			Long subCategoryId, Long dealerId, Long brandId, String date,
			String criteria1, String criteria2, String criteria3,
			String criteria4) {
		this.name = productname;
		this.price = price;
		this.subCategoryId = subCategoryId;
		this.dealerId = dealerId;
		this.brandId = brandId;
		this.setOldPrice(oldPrice);
		this.start_date = getCurrentDate();
		this.end_date = getEndDate(date);
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
		this.criteria3 = criteria3;
		this.criteria4 = criteria4;
	}

	public Offer(Long offerId, String productname, double oldPrice,
			double price, Long subCategoryId, Long dealerId, Long brandId,
			String date, String criteria1, String criteria2, String criteria3,
			String criteria4) {
		this.name = productname;
		this.price = price;
		this.subCategoryId = subCategoryId;
		this.dealerId = dealerId;
		this.brandId = brandId;
		this.setOldPrice(oldPrice);
		this.start_date = getCurrentDate();
		this.end_date = getEndDate(date);
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
		this.criteria3 = criteria3;
		this.criteria4 = criteria4;
	}

	public Offer(String productname, double price, Long subCategoryId,
			Long dealerId, Long brandId, String date) {
		this.name = productname;
		this.price = price;
		this.subCategoryId = subCategoryId;
		this.dealerId = dealerId;
		this.brandId = brandId;
		this.setStart_date(getCurrentDate());
		this.setEnd_date(getEndDate(date));
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name + " " + price;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Offer) {
			Offer offer = (Offer) obj;
			return this.getOfferId().equals(offer.getOfferId());
		} else {
			return false;
		}
	}

	public String getEndDate(String date) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String endDate = new SimpleDateFormat("dd.MM.yyyy").format(d);
		return endDate;
	}

	/**
	 * Gibt das Angebotsende als Date Format zurück
	 * 
	 * @return das Datum
	 */
	public Date getEffectiveEndDate() {
		// auf die instanz variablen beziehen
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date endDate = null;
		try {
			endDate = simpleDateFormat.parse(this.getEnd_date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}

	public String getCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date now = new Date();
		String currentDate = simpleDateFormat.format(now);
		return currentDate;
	}

	/**
	 * Setzt den Angebotsanfang in ein Datum Format
	 * 
	 * @return das Datum
	 */
	public Date getEffectiveStartDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = null;
		try {
			date = simpleDateFormat.parse(this.getStart_date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Berechnet den prozentualen Unterschied zwischen altem und neuen Preis und
	 * gibt diesen zurück
	 * 
	 * @return den prozentualen Unterschied
	 */
	public int getDiscount() {
		int percent = ((int) (100 * price / oldPrice));
		return 100 - percent;
	}

	public String getCriteria1() {
		return criteria1;
	}

	public void setCriteria1(String criteria1) {
		this.criteria1 = criteria1;
	}

	public String getCriteria2() {
		return criteria2;
	}

	public void setCriteria2(String criteria2) {
		this.criteria2 = criteria2;
	}

	public String getCriteria3() {
		return criteria3;
	}

	public void setCriteria3(String criteria3) {
		this.criteria3 = criteria3;
	}

	public String getCriteria4() {
		return criteria4;
	}

	public void setCriteria4(String criteria4) {
		this.criteria4 = criteria4;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
}
