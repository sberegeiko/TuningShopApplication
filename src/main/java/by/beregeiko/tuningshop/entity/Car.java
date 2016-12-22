package by.beregeiko.tuningshop.entity;

/**
 * Simple JavaBean object that represents a Car of {@link Product}.
 * Created by Think on 09.12.2016.
 */
public class Car {
    private int id;
    private String brand;
    private String model;
    private String yearFromTo;

    public Car() {
    }

    public Car(int id, String brand, String model, String yearFromTo) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.yearFromTo = yearFromTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearFromTo() {
        return yearFromTo;
    }

    public void setYearFromTo(String yearFromTo) {
        this.yearFromTo = yearFromTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return yearFromTo != null ? yearFromTo.equals(car.yearFromTo) : car.yearFromTo == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (yearFromTo != null ? yearFromTo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearFromTo='" + yearFromTo + '\'' +
                '}';
    }
}
