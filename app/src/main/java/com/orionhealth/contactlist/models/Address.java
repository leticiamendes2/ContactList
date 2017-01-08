package com.orionhealth.contactlist.models;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.RealmObject;

@Parcel(value = Parcel.Serialization.BEAN, analyze = Address.class)
public class Address extends RealmObject implements Parcelable {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private long lat;
    private long lng;

    public Address(){}

    public Address(String street, String suite, String city, String zipcode, long lat, long lng) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.lat = lat;
        this.lng = lng;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(android.os.Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    private Address(android.os.Parcel in) {
        setStreet(in.readString());
        setCity(in.readString());
        setSuite(in.readString());
        setZipcode(in.readString());
        setLat(in.readLong());
        setLng(in.readLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(getStreet());
        parcel.writeString(getCity());
        parcel.writeString(getSuite());
        parcel.writeString(getZipcode());
        parcel.writeLong(getLat());
        parcel.writeLong(getLng());
    }
}
