package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 01.10.17.
 */
@Table(name = "mantis_user_table")
public class UserData {
    @Id
    @Column(name = "id")
    private int id;
    @Transient
    private String name;
    @Transient
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
