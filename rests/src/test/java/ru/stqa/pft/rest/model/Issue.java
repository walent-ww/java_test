package ru.stqa.pft.rest.model;

/**
 * Created by user on 08.10.17.
 */
public class Issue {
    private int id;
    private String state_name;
    private String description;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return state_name;
    }

    public Issue withStatus(String status) {
        this.state_name = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", state_name='" + state_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
