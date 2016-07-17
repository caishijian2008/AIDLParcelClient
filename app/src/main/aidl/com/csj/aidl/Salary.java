package com.csj.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by caishijian on 16-7-14.
 */
public class Salary implements Parcelable {
    private String type;
    private Integer salary;

    public Salary() {
    }

    public Salary(String type, Integer salary) {
        this.salary = salary;
        this.type = type;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static final Creator<Salary> CREATOR = new Creator<Salary>() {
        @Override
        public Salary createFromParcel(Parcel in) {
            return new Salary(in.readString(), in.readInt());
        }

        @Override
        public Salary[] newArray(int size) {
            return new Salary[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeInt(salary);
    }

    @Override
    public String toString() {
        return "工作:" + type + "    薪水: " + salary;
    }
}
