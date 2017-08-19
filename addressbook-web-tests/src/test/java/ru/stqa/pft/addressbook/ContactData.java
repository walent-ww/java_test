package ru.stqa.pft.addressbook;

public class ContactData {
    private final String fname;
    private final String mname;
    private final String lname;
    private final String phone1;
    private final String email1;

    public ContactData(String fname, String mname, String lname, String phone1, String email1) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.phone1 = phone1;
        this.email1 = email1;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getEmail1() {
        return email1;
    }
}
