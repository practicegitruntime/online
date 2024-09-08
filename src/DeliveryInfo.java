import java.math.BigDecimal;
import java.util.List;

public class DeliveryInfo {
    private String country;
    private String city;
    private List<DeliveryType> deliveryTypes;
    private BigDecimal price;

    public DeliveryInfo(String country, String city, List<DeliveryType> deliveryTypes, BigDecimal price) {
        this.country = country;
        this.city = city;
        this.deliveryTypes = deliveryTypes;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<DeliveryType> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(List<DeliveryType> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}