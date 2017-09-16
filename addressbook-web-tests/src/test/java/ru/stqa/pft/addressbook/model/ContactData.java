package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id  = 0;
    @Expose
    private String fname;
    private String mname;
    @Expose
    private String lname;
    private String phone1;
    private String email1;
    private String email2;
    private String email3;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String allEmails;
    @Expose
    private String address1;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public String getAddress1() {
        return address1;
    }

    public ContactData withAddress1(String address1) {
        this.address1 = address1;
        return  this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFname(String fname) {
        this.fname = fname;
        return this;
    }

    public ContactData withMname(String mname) {
        this.mname = mname;
        return this;
    }

    public ContactData withLname(String lname) {
        this.lname = lname;
        return this;
    }

    public ContactData withPhone1(String phone1) {
        this.phone1 = phone1;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public int getId() { return id; }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }


    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (fname != null ? !fname.equals(that.fname) : that.fname != null) return false;
        return lname != null ? lname.equals(that.lname) : that.lname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        return result;
    }


}
