package com.example.sahil.bloodbank;



public class artist
{
    String name,phone,pass,gender,blood,state,city;
    public artist(){

    }

    public artist(String name,String phone,String pass,String gender,String blood,String state,String city)
    {
        this.name=name;
        this.phone=phone;
        this.pass=pass;
        this.gender=gender;
        this.blood=blood;
        this.state=state;
        this.city=city;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPass() {
        return pass;
    }

    public String getGender() {
        return gender;
    }

    public String getBlood() {
        return blood;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
