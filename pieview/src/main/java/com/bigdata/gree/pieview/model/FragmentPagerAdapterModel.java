package com.bigdata.gree.pieview.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:liuyanguo
 * Date:2017/10/9
 * Time:14:08
 * Description:
 */

public class FragmentPagerAdapterModel implements Parcelable{

    private String name;
    private String sex;
    private int age;

    public FragmentPagerAdapterModel(){
    }

    public FragmentPagerAdapterModel(Parcel in) {
        name = in.readString();
        sex = in.readString();
        age = in.readInt();
    }

    public static final Creator<FragmentPagerAdapterModel> CREATOR = new Creator<FragmentPagerAdapterModel>() {
        @Override
        public FragmentPagerAdapterModel createFromParcel(Parcel in) {
            return new FragmentPagerAdapterModel(in);
        }

        @Override
        public FragmentPagerAdapterModel[] newArray(int size) {
            return new FragmentPagerAdapterModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(sex);
        parcel.writeInt(age);
    }

    @Override
    public String toString() {
        return "FragmentPagerAdapterModel{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
