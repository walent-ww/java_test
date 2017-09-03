package ru.stqa.pft.addressbook.model;

public class ContactData {

    private int id;
    private final String fname;
    private final String mname;
    private final String lname;
    private final String phone1;
    private final String email1;

    public ContactData(int id, String fname, String mname, String lname, String phone1, String email1) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.phone1 = phone1;
        this.email1 = email1;
    }

    public ContactData(String fname, String mname, String lname, String phone1, String email1) {
        this.id = 0;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.phone1 = phone1;
        this.email1 = email1;
    }

    public int getId() { return id; }

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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }

}
