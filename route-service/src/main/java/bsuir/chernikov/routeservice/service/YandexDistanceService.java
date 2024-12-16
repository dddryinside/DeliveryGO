package bsuir.chernikov.routeservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class YandexDistanceService {

    private static final String API_KEY = "ваш_api_ключ_Яндекс_карты";
    private static final String API_URL = "https://router.api.yandex.ru/v2/route";

    private final RestTemplate restTemplate;

    public YandexDistanceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDistance(String address1, String address2) {
        String coordinates1 = getCoordinates(address1);
        String coordinates2 = getCoordinates(address2);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("apikey", API_KEY)
                .queryParam("points", coordinates1 + ";" + coordinates2)
                .queryParam("format", "json");

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
        return response;
    }

    private String getCoordinates(String address) {
        // Геокодируем адрес с использованием Яндекс Геокодера API
        // Пример API для геокодирования: https://geocode-maps.yandex.ru/1.x/?geocode=Москва
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("https://geocode-maps.yandex.ru/1.x/")
                .queryParam("apikey", API_KEY)
                .queryParam("geocode", address)
                .queryParam("format", "json");

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        // Пример извлечения координат из JSON (псевдокод):
        // "coordinates" - это формат, который вы получите, и его нужно разобрать.
        // В реальном коде используйте JSON парсер (например, Jackson или Gson)

        // Псевдокод для извлечения координат из ответа
        // String coordinates = jsonResponse.getCoordinates();
        // Для простоты возвратим фиктивные координаты
        return "37.6176,55.7558"; // Москва
    }
}
