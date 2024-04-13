package org.example.entities;

public class Set extends _BaseEntity{
    private String name;
    private int quantity;
    private String patch;
    private String releaseDate;

    public Set() {

    }

    public Set(int id, String name, int quantity, String patch, String releaseDate) {
        super(id);
        this.name = name;
        this.quantity = quantity;
        this.patch = patch;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id = " + getId() +
                ", name = " + name +
                ", quantity = " + quantity +
                ", patch = " + patch +
                ", releaseDate = " + releaseDate +
                "} ";
    }
}
