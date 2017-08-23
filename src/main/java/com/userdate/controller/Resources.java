package com.userdate.controller;

/**
 * Created by Grand Circus Student on 8/23/2017.
 */
public class Resources {
        private int id;
        private String organizationName;
        private String zipCode;
        private String website;
        private long phoneNumber;
        private String address;
        private String hours;
        private String description;

        public Resources(int id, String organizationName, String zipCode, String website, long phoneNumber, String address, String hours, String description) {
            this.id = id;
            this.organizationName = organizationName;
            this.zipCode = zipCode;
            this.website = website;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.hours = hours;
            this.description = description;
        }
        public Resources() {
            id = 0;
            organizationName = "";
            zipCode = "";
            website = "";
            phoneNumber = 0;
            address = "";
            hours = "";
            description = "";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return
                    zipCode + ": " + "\n"   + organizationName  + '\n';
        }

    }

