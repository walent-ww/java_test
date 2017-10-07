package ru.stqa.pft.mantis.model;

/**
 * Created by user on 07.10.17.
 */
public class Issue {
    private int id;
    private String sum;
    private String status;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSum() {
        return sum;
    }

    public Issue withSum(String sum) {
        this.sum = sum;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }
}
