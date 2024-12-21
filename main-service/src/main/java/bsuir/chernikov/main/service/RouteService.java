package bsuir.chernikov.main.service;

import bsuir.chernikov.main.dto.OrderDto;
import bsuir.chernikov.main.entities.Address;
import bsuir.chernikov.main.utils.Converter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final AddressService addressService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String API_KEY = "5b3ce3597851110001cf624802b790db3ddb4eb295dd39c94c2c8a5a";

    private static final double BASE_PRICE_PER_KM = 0.5;
    private static final double EXTRA_COST_PER_KG = 0.05;
    private static final double AVERAGE_SPEED_KMH = 65.0;
    private static final double FUEL_CONSUMPTION_PER_100KM = 8.0;
    private static final double CO2_EMISSION_PER_LITER = 2.31;

    public void calculateFullOrderData(OrderDto order) {
        Address startAddress = addressService.getAddressById(Long.valueOf(order.getStartPointId()));
        Address endAddress = addressService.getAddressById(Long.valueOf(order.getEndPointId()));
        double distance = getDistance(startAddress.getCoordinates(), endAddress.getCoordinates());

        order.setDistance(Converter.roundToOneDecimalPlace(distance));
        double timeInMinutes = (distance / AVERAGE_SPEED_KMH) * 60.0;
        order.setCalculatedTime(Converter.roundToOneDecimalPlace(timeInMinutes));
        double fuelUsed = (distance / 100.0) * FUEL_CONSUMPTION_PER_100KM;
        order.setCo2Emission(Converter.roundToOneDecimalPlace(fuelUsed * CO2_EMISSION_PER_LITER));
    }

    private Double getDistance(String start, String end) {
        try {
            String url = String.format("https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + API_KEY
                            + "&start=" + reverseCoordinates(start) + "&end=" + reverseCoordinates(end));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("Error: Status Code: {} Response: {}", response.statusCode(), response.body());
            }

            String responseBody = response.body();
            double distanceInMeters = parseDistance(responseBody);
            return distanceInMeters / 1000.0;
        } catch (Exception e) {
            logger.error("Error occurred while calculating distance", e);
            throw new RuntimeException("Error occurred while calculating distance", e);
        }
    }

    private double parseDistance(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            JsonNode distanceNode = rootNode.path("features")
                    .path(0).path("properties")
                    .path("summary").path("distance");

            return distanceNode.asDouble();
        } catch (Exception e) {
            logger.error("Error parsing response", e);
            throw new RuntimeException("Error parsing OpenRouteService response", e);
        }
    }

    private String reverseCoordinates(String coordinates) {
        if (coordinates == null || !coordinates.contains(",")) {
            throw new IllegalArgumentException("Invalid coordinates format. Expected format: 'latitude,longitude'.");
        }

        String[] parts = coordinates.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid coordinates format. Expected exactly two values separated by a comma.");
        }

        return parts[1].trim() + "," + parts[0].trim();
    }
}