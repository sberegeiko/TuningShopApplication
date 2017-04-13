package by.beregeiko.tuningshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Catalog of {@link Product}.
 * Created by Think on 09.12.2016.
 */
@Entity
@Table(name = "catalogs")
public class Catalog implements Serializable{
    private int id;
    private String name;
    private Set<Product> products;

    public Catalog() {
    }

    public Catalog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "catalogs")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (id != catalog.id) return false;
        return name != null ? name.equals(catalog.name) : catalog.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
