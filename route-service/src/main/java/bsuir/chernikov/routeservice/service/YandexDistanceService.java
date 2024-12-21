package bsuir.chernikov.routeservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class YandexDistanceService {

    private static final String API_KEY = "b98133fa-d4a2-4aab-b24c-bfeb242448ee";
    private static final String API_URL = "https://router.api.yandex.ru/v2/route";
    private static final String GEOCODE_API_URL = "https://geocode-maps.yandex.ru/1.x/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public YandexDistanceService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Double getDistance(String address1, String address2) {
        try {
            String coordinates1 = getCoordinates(address1);
            String coordinates2 = getCoordinates(address2);

            System.out.println(coordinates1);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL)
                    .queryParam("apikey", API_KEY)
                    .queryParam("points", coordinates1 + ";" + coordinates2)
                    .queryParam("format", "json");

            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
            JsonNode jsonResponse = objectMapper.readTree(response);

            if (jsonResponse.has("routes") && jsonResponse.get("routes").isArray()) {
                JsonNode route = jsonResponse.get("routes").get(0);
                if (route.has("distance")) {
                    long distance = route.get("distance").asLong();
                    return (double) distance;
                }
            } else {
                throw new RuntimeException("No routes found");
            }

        } catch (Exception e) {
            throw new RuntimeException("No routes found");
        }
        return null;
    }

    private String getCoordinates(String address) {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(GEOCODE_API_URL)
                    .queryParam("apikey", API_KEY)
                    .queryParam("geocode", address)
                    .queryParam("format", "json");

            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
            JsonNode jsonResponse = objectMapper.readTree(response);

            if (jsonResponse.has("response") && jsonResponse.get("response").has("GeoObjectCollection")) {
                JsonNode geoObjectCollection = jsonResponse.get("response").get("GeoObjectCollection");
                if (geoObjectCollection.has("featureMember") && geoObjectCollection.get("featureMember").isArray()) {
                    JsonNode firstFeature = geoObjectCollection.get("featureMember").get(0);
                    if (firstFeature.has("GeoObject")) {
                        JsonNode geoObject = firstFeature.get("GeoObject");
                        if (geoObject.has("Point")) {
                            JsonNode point = geoObject.get("Point");
                            if (point.has("pos")) {
                                return point.get("pos").asText();
                            }
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
