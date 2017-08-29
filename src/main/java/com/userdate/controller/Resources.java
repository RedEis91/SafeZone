package com.userdate.controller;

/**
 * Created by Grand Circus Student on 8/23/2017.
 */
public class Resources {
    private int ID;
    private String Organization;
    private String Zip;
    private String Website;
    private long Phone;
    private String address;
    private String description;
    private boolean food;
    private boolean clothing;
    private boolean counseling;
    private boolean healthcare;
    private boolean education;
    private boolean job;
    private boolean female;
    private boolean male;
    private float latitude;
    private float longitude;


    //specific constructor for when user wants resources that are in a particular category
    public Resources(String Organization, float latitude, float longitude) {
        this.Organization = Organization;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //returns entire list with this info. below on our resourceview View
    public Resources(int ID, String Organization, String Zip, String website, long Phone, String address, String description) {
        this.ID = ID;
        this.Organization = Organization;
        this.Zip = Zip;
        this.Website = website;
        this.Phone = Phone;
        this.address = address;
        this.description = description;
    }

    public Resources(int ID, String Organization, String Zip, String website, long Phone, String address, String description, boolean food, boolean clothing, boolean counseling, boolean healthcare, boolean education, boolean job, boolean female, boolean male, float latitude, float longitude) {
        this.ID = ID;
        this.Organization = Organization;
        this.Zip = Zip;
        this.Website = website;
        this.Phone = Phone;
        this.address = address;
        this.description = description;
        this.food = food;
        this.clothing = clothing;
        this.counseling = counseling;
        this.healthcare = healthcare;
        this.education = education;
        this.job = job;
        this.female = female;
        this.male = male;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Resources (){
        ID =0;
        Organization ="";
        Zip ="";
        Website ="";
        Phone =0;
        address="";
        description="";
        food =true;
        clothing=true;
        counseling=true;
        healthcare=true;
        education=true;
        job=true;
        female=true;
        male=true;
        latitude=0;
        longitude =0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        this.Organization = organization;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        this.Zip = zip;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        this.Website = website;
    }

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long phone) {
        this.Phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isClothing() {
        return clothing;
    }

    public void setClothing(boolean clothing) {
        this.clothing = clothing;
    }

    public boolean isCounseling() {
        return counseling;
    }

    public void setCounseling(boolean counseling) {
        this.counseling = counseling;
    }

    public boolean isHealthcare() {
        return healthcare;
    }

    public void setHealthcare(boolean healthcare) {
        this.healthcare = healthcare;
    }

    public boolean isEducation() {
        return education;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }

    public boolean isJob() {
        return job;
    }

    public void setJob(boolean job) {
        this.job = job;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "ID=" + ID +
                ", Organization='" + Organization + '\'' +
                ", Zip='" + Zip + '\'' +
                ", Website='" + Website + '\'' +
                ", Phone=" + Phone +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", Meals=" + food +
                ", clothing=" + clothing +
                ", counseling=" + counseling +
                ", healthcare=" + healthcare +
                ", education=" + education +
                ", job=" + job +
                ", female=" + female +
                ", male=" + male +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}


