package org.ligson.pt.loc.bd.model;

import java.util.List;

public class IpReturnContent {
	private String formatted_address;
	private String business;
	private int radius;
	private String location_description;
	private String locid;
	private Location location;
	private AddressComponent address_component;
	private List<Pois> pois;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getLocation_description() {
		return location_description;
	}

	public void setLocation_description(String location_description) {
		this.location_description = location_description;
	}

	public String getLocid() {
		return locid;
	}

	public void setLocid(String locid) {
		this.locid = locid;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public AddressComponent getAddress_component() {
		return address_component;
	}

	public void setAddress_component(AddressComponent address_component) {
		this.address_component = address_component;
	}

	public List<Pois> getPois() {
		return pois;
	}

	public void setPois(List<Pois> pois) {
		this.pois = pois;
	}

}
