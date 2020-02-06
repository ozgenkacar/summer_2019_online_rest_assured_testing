package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

public class Spartan {
//      {
//           "id": 112,
//           "name": "Vasya",
//           "gender": "Male",
//           "phone": 7654321876
//        }
@SerializedName("id")
    private int spartanID;
    private String name;
    private String gender;
    private Long phone;

    public Spartan() {
    }

    public Spartan(String name, String gender, Long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public Spartan(int spartanID, String name, String gender, Long phone) {
        this.spartanID = spartanID;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public Spartan withPhone(long phone){
        this.phone=phone;
        return this;
    }
    public Spartan withName(String name) {
        this.name = name;
        return this;
    }
    public Spartan withGender(String gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "spartanID=" + spartanID +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public int getspartanID() {
        return spartanID;
    }

    public void setspartanID(int id) {
        this.spartanID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
