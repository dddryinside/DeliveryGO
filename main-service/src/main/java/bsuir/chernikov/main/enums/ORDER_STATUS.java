package bsuir.chernikov.main.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@RequiredArgsConstructor
public enum ORDER_STATUS {
    CREATED("Создан"),
    IN_PROGRESS("В работе"),
    COMPLETED("Выполнен"),
    CANCELED("Отменён");

    private final String name;

    public static ORDER_STATUS fromString(String name) {
        for (ORDER_STATUS status : ORDER_STATUS.values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order status is not correct " + name);
    }
}
