import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DeliveryManager {
    private final List<DeliveryInfo> deliveryInfos = new ArrayList<>();

    public DeliveryManager() {
        deliveryInfos.add(new DeliveryInfo("США", "Вашингтон", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR), new BigDecimal("324")));
        deliveryInfos.add(new DeliveryInfo("США", "Чикаго", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR, DeliveryType.PUCK_UP_POINT), new BigDecimal("430")));
        deliveryInfos.add(new DeliveryInfo("США", "Хьюстон", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR), new BigDecimal("280")));
        deliveryInfos.add(new DeliveryInfo("США", "Филадельфия", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.PUCK_UP_POINT), new BigDecimal("330")));
        deliveryInfos.add(new DeliveryInfo("США", "Сан-Диего", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.PUCK_UP_POINT), new BigDecimal("334")));
        deliveryInfos.add(new DeliveryInfo("США", "Даллас", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR, DeliveryType.PUCK_UP_POINT), new BigDecimal("399")));
        deliveryInfos.add(new DeliveryInfo("США", "Индианаполис", List.of(DeliveryType.DOOR_TO_DOOR), new BigDecimal("412")));
        deliveryInfos.add(new DeliveryInfo("Китай", "Шанхай", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR), new BigDecimal("280")));
        deliveryInfos.add(new DeliveryInfo("Китай", "Пекин", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR, DeliveryType.PUCK_UP_POINT), new BigDecimal("250")));
        deliveryInfos.add(new DeliveryInfo("Китай", "Чэнду", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.DOOR_TO_DOOR), new BigDecimal("243")));
        deliveryInfos.add(new DeliveryInfo("Китай", "Сиань", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.PUCK_UP_POINT), new BigDecimal("308")));
        deliveryInfos.add(new DeliveryInfo("Китай", "Дунгуань", List.of(DeliveryType.DELIVERY_CENTER, DeliveryType.PUCK_UP_POINT), new BigDecimal("284")));
    }

    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        for (DeliveryInfo info: deliveryInfos) {
            if (!result.contains(info.getCountry())) {
                result.add(info.getCountry());
            }
        }
        return result;
    }

    public List<String> getCities(String country) {
        List<String> result = new ArrayList<>();
        for (DeliveryInfo info: deliveryInfos) {
            if (info.getCountry().equals(country) && !result.contains(info.getCity())) {
                result.add(info.getCity());
            }
        }
        return result;
    }

    public List<DeliveryType> getAvailableDeliveryTypes(String country, String city) {
        for (DeliveryInfo info: deliveryInfos) {
            if (info.getCountry().equals(country) && info.getCity().equals(city)) {
                return info.getDeliveryTypes();
            }
        }
        throw new RuntimeException("Выбраны неизвестные страна и город");
    }

    public BigDecimal getPrice(String country, String city, DeliveryType type, int height, int length, int width, int weight) {
        for (DeliveryInfo info: deliveryInfos) {
            if (info.getCountry().equals(country) && info.getCity().equals(city)) {
                BigDecimal price = info.getPrice();
                price = switch (type) {
                    case DOOR_TO_DOOR -> price.multiply(new BigDecimal("1.1"));
                    case DELIVERY_CENTER -> price;
                    case PUCK_UP_POINT -> price.add(new BigDecimal("90"));
                };

                BigDecimal sizePrice = new BigDecimal(height * length * width / 1000000).multiply(new BigDecimal(100));
                BigDecimal weightPrice = new BigDecimal(weight / 10).multiply(new BigDecimal(100));
                return price.add(sizePrice).add(weightPrice);
            }
        }
        throw new RuntimeException("Выбраны неизвестные страна и город");
    }
}