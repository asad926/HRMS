package com.clientside12.departmentControllers;

    public class Departments {

        private String dept_name;
        private String mng_name;
        private String street;
        private String postal;
        private String city;
        private String state;
        private String country;
        private String region;

        public Departments(String dept_name, String mng_name, String street, String postal, String city, String state, String country, String region) {
            this.dept_name = dept_name;
            this.mng_name = mng_name;
            this.street = street;
            this.postal = postal;
            this.city = city;
            this.state = state;
            this.country = country;
            this.region = region;
        }

        public Departments() {
        }

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getMng_name() {
            return mng_name;
        }

        public void setMng_name(String mng_name) {
            this.mng_name = mng_name;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getPostal() {
            return postal;
        }

        public void setPostal(String postal) {
            this.postal = postal;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }

