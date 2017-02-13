package by.beregeiko.tuningshop.entity;

import java.util.List;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Product.
 * Created by Think on 09.12.2016.
 */
public class Product {
    private int id;
    private String name;
    private String producer;
    private String productCode;
    private String producerProductCode;
    private Set<Catalog> catalogs;
    private Set<Car> cars;
    private List<String> images;

    public Product() {
    }

    public Product(int id, String name, String producer, String productCode, String producerProductCode, Set<Catalog> catalogs, Set<Car> cars, List<String> images) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.productCode = productCode;
        this.producerProductCode = producerProductCode;
        this.catalogs = catalogs;
        this.cars = cars;
        this.images = images;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProducerProductCode() {
        return producerProductCode;
    }

    public void setProducerProductCode(String producerProductCode) {
        this.producerProductCode = producerProductCode;
    }

    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (producer != null ? !producer.equals(product.producer) : product.producer != null) return false;
        if (productCode != null ? !productCode.equals(product.productCode) : product.productCode != null) return false;
        if (producerProductCode != null ? !producerProductCode.equals(product.producerProductCode) : product.producerProductCode != null)
            return false;
        if (catalogs != null ? !catalogs.equals(product.catalogs) : product.catalogs != null) return false;
        if (cars != null ? !cars.equals(product.cars) : product.cars != null) return false;
        return images != null ? images.equals(product.images) : product.images == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (producerProductCode != null ? producerProductCode.hashCode() : 0);
        result = 31 * result + (catalogs != null ? catalogs.hashCode() : 0);
        result = 31 * result + (cars != null ? cars.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", catalogs=" + catalogs +
                ", cars=" + cars +
                '}';
    }
}
