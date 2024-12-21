package bsuir.chernikov.main.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@RequiredArgsConstructor
public enum CARGO_TYPE {
    DOCUMENTS("Documents"),
    ELECTRONICS("Electronics"),
    FRAGILE("Fragile"),
    LIQUID("Liquid"),
    PERISHABLE("Perishable"),
    HAZARDOUS("Hazardous"),
    OVERSIZED("Oversized"),
    FURNITURE("Furniture"),
    CLOTHING("Clothing"),
    FOOD("Food"),
    ANIMALS("Animals"),
    PHARMACEUTICALS("Pharmaceuticals"),
    CONSTRUCTION_MATERIALS("Consumer Materials"),
    AUTOMOTIVE_PARTS("Automotive parts"),
    AGRICULTURAL("Agricultural"),
    RAW_MATERIALS("Raw materials"),
    TOYS("Toys"),
    BOOKS("Books"),
    JEWELRY("Jewelry"),
    ARTWORKS("Artworks"),
    SPORTS_EQUIPMENT("Sports Equipment"),
    MUSICAL_INSTRUMENTS("Musical Instruments"),
    CHEMICALS("Chemicals"),
    METALS("Metals"),
    PLASTICS("Plastics"),
    OTHER("Other");

    private final String name;

    public static CARGO_TYPE fromString(String name) {
        for (CARGO_TYPE type : CARGO_TYPE.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cargo type is not correct " + name);
    }
}
