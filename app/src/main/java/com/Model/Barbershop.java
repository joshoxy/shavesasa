package com.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Barbershop implements Parcelable {
    private String name, address, barbershopID;

    public Barbershop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarbershopID() {
        return barbershopID;
    }

    public void setBarbershopID(String barbershopID) {
        this.barbershopID = barbershopID;
    }

    protected Barbershop(Parcel in) {
        name = in.readString();
        address = in.readString();
        barbershopID = in.readString();
    }

    public static final Creator<Barbershop> CREATOR = new Creator<Barbershop>() {
        @Override
        public Barbershop createFromParcel(Parcel in) {
            return new Barbershop(in);
        }

        @Override
        public Barbershop[] newArray(int size) {
            return new Barbershop[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(barbershopID);
    }
}
