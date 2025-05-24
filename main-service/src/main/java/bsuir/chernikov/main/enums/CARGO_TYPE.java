package bsuir.chernikov.main.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@RequiredArgsConstructor
public enum CARGO_TYPE {
    DOCUMENTS("Документы"),
    ELECTRONICS("Электроника"),
    FRAGILE("Хрупкое"),
    LIQUID("Жидкости"),
    PERISHABLE("Скоропортящееся"),
    HAZARDOUS("Опасные грузы"),
    OVERSIZED("Негабаритное"),
    FURNITURE("Мебель"),
    CLOTHING("Одежда"),
    FOOD("Продукты питания"),
    ANIMALS("Животные"),
    PHARMACEUTICALS("Фармацевтика"),
    CONSTRUCTION_MATERIALS("Строительные материалы"),
    AUTOMOTIVE_PARTS("Автозапчасти"),
    AGRICULTURAL("Сельскохозяйственная продукция"),
    RAW_MATERIALS("Сырьё"),
    TOYS("Игрушки"),
    BOOKS("Книги"),
    JEWELRY("Украшения"),
    ARTWORKS("Произведения искусства"),
    SPORTS_EQUIPMENT("Спортивный инвентарь"),
    MUSICAL_INSTRUMENTS("Музыкальные инструменты"),
    CHEMICALS("Химикаты"),
    METALS("Металлы"),
    PLASTICS("Пластмассы"),
    OTHER("Другое");

    private final String name;

    public static CARGO_TYPE fromString(String name) {
        for (CARGO_TYPE type : CARGO_TYPE.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cargo type is not correct " + name);
    }
}
