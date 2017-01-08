package com.orionhealth.contactlist.models;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.RealmObject;

@Parcel(value = Parcel.Serialization.BEAN, analyze = Company.class)
public class Company extends RealmObject implements Parcelable {

    private String name;
    private String catchPhrase;
    private String bs;

    public Company(){}

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {
        public Company createFromParcel(android.os.Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    private Company(android.os.Parcel in) {
        setName(in.readString());
        setCatchPhrase(in.readString());
        setBs(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(getName());
        parcel.writeString(getCatchPhrase());
        parcel.writeString(getBs());
    }
}
