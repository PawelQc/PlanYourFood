package pl.qceyco.model;

import pl.qceyco.dao.AdminDao;

import java.sql.Timestamp;

public class Plan {

    private int id;
    private String name;
    private String description;
    private Timestamp created;
    private Admin admin;

    public Plan() {
    }

    public Plan(String name, String description, Admin admin) {
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    public Plan(int id, String name, String description, Timestamp created, Admin admin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.admin = admin;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdminbyId(int adminId) {
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.getAdminById(adminId);
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", admin=" + admin +
                '}';
    }
}
