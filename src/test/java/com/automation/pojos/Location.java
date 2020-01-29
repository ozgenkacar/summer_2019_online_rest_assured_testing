package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

/*
Create POJO for Location:
public class Location{
}
Write 2 tests:
	#1 get single POJO of Location class
	#2 get collection of POJO’s.
	Same thing like we did with Job class
 */
public class Location {
    @SerializedName("location_id")
    private int locationId;

    @SerializedName("street_address")
    private String streetAddress;

    @SerializedName("postal_code")
    private String postalCode;

    private String city;

    @SerializedName("state_province")
    private String stateProvince;

    @SerializedName("country_id")
    private String countryId;

    public Location(){

    }

    public Location(int locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getCountryId() {
        return countryId;
    }



}
