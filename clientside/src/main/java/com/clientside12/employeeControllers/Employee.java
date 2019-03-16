package com.clientside12.employeeControllers;

public class Employee {
    private int emp_id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String hire;
    private String job_id;
    private String dept_id;
    private String salary;
    private String com_per;
    private String mng_id;

    public Employee(String emp_id,
                    String fname,
                    String lname,
                    String email,
                    String phone,
                    String hire,
                    String job_id,
                    String dept_id,
                    String salary,
                    String com_per,
                    String mng_id) {
        this.emp_id = Integer.parseInt(emp_id);
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.hire = hire;
        this.job_id = job_id;
        this.dept_id = dept_id;
        this.salary = salary;
        this.com_per = com_per;
        this.mng_id = mng_id;
    }

    public Employee() {

    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = Integer.parseInt(emp_id);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHire() {
        return hire;
    }

    public void setHire(String hire) {
        this.hire = hire;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCom_per() {
        return com_per;
    }

    public void setCom_per(String com_per) {
        this.com_per = com_per;
    }

    public String getMng_id() {
        return mng_id;
    }

    public void setMng_id(String mng_id) {
        this.mng_id = mng_id;
    }
}

