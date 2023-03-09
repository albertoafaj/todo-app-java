package model;

import java.util.Date;

public class Projects {

    private int id;
    private String name;
    private String description;
    private Date dateCreated;
    private Date dateLastUpdate;
    private int IdOwnerUser;

    public Projects(int id, String name, String description, Date dateCreated, Date dateLastUpdate, int IdOwnerUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateLastUpdate = dateLastUpdate;
        this.IdOwnerUser = IdOwnerUser;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(Date dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public int getIdOwnerUser() {
        return IdOwnerUser;
    }

    public void setIdOwnerUser(int IdOwnerUser) {
        this.IdOwnerUser = IdOwnerUser;
    }

    @Override
    public String toString() {
        return "Projects{" + "id=" + id + ", name=" + name + ", description=" + description + ", dateCreated="
                + dateCreated + ", dateLastUpdate=" + dateLastUpdate + ", IdOwnerUser=" + IdOwnerUser + '}';
    }

}
