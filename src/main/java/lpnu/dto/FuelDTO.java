package lpnu.dto;

import lpnu.entity.enumeration.FuelState;

public class FuelDTO {
    private Long id;
    private String name;
    private Double priceSell;
    private Double priceBuy;

    public FuelDTO() {
    }

    public FuelDTO(final Long id, final String name, final Double priceSell, final Double priceBuy) {
        this.id = id;
        this.name = name;
        this.priceSell = priceSell;
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
}
