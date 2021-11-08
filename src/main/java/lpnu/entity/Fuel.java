package lpnu.entity;

import lpnu.entity.enumeration.FuelState;

import java.util.Objects;

public class Fuel {
    private Long id;
    //todo fix name
    private String name;
    private Double priceSell;
    private Double priceBuy;
    private FuelState fuelState;


    public Fuel() {
    }

    public Fuel(final Long id, final String name, final Double priceSell, final Double priceBuy, final FuelState fuelState) {
        this.id = id;
        this.name = name;
        this.priceSell = priceSell;
        this.priceBuy = priceBuy;
        this.fuelState = fuelState;
    }

    public Double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(final Double priceSell) {
        this.priceSell = priceSell;
    }

    public Double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(final Double priceBuy) {
        this.priceBuy = priceBuy;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public FuelState getFuelState() {
        return fuelState;
    }

    public void setFuelState(final FuelState fuelState) {
        this.fuelState = fuelState;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Fuel fuel = (Fuel) o;
        return Objects.equals(id, fuel.id) &&
                Objects.equals(name, fuel.name) &&
                Objects.equals(priceSell, fuel.priceSell) &&
                Objects.equals(priceBuy, fuel.priceBuy) &&
                fuelState == fuel.fuelState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priceSell, priceBuy, fuelState);
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceSell='" + priceSell + '\'' +
                ", priceBuy='" + priceBuy + '\'' +
                ", fuelState=" + fuelState +
                '}';
    }
}
