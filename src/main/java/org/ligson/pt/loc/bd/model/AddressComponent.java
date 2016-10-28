package org.ligson.pt.loc.bd.model;

public class AddressComponent {
	private String country;
	private String province;
	private String admin_area_code;
	private String city;
	private String street;
	private String district;
	private String street_number;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAdmin_area_code() {
		return admin_area_code;
	}

	public void setAdmin_area_code(String admin_area_code) {
		this.admin_area_code = admin_area_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

}
