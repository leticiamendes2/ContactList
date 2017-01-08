package com.orionhealth.contactlist.models;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.RealmObject;

@Parcel(value = Parcel.Serialization.BEAN, analyze = Contact.class)
public class Contact extends RealmObject implements Parcelable {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public Contact(){}

    public Contact(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(android.os.Parcel in) {
            return new Contact(in);
        }
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    private Contact(android.os.Parcel in) {
        setId(in.readInt());
        setName(in.readString());
        setEmail(in.readString());
        setPhone(in.readString());
        setUsername(in.readString());
        setWebsite(in.readString());
        setAddress((Address) in.readParcelable(Address.class.getClassLoader()));
        setCompany((Company) in.readParcelable(Company.class.getClassLoader()));
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
        parcel.writeString(getEmail());
        parcel.writeString(getPhone());
        parcel.writeString(getUsername());
        parcel.writeString(getWebsite());
        parcel.writeParcelable(getAddress(), i);
        parcel.writeParcelable(getCompany(), i);
    }
}
