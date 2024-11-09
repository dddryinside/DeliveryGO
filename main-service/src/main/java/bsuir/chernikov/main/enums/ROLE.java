package bsuir.chernikov.main.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@RequiredArgsConstructor
public enum ROLE {
    CLIENT,
    COURIER,
    ADMIN,
    DIRECTOR;

    public static ROLE fromString(String roleName) {
        for (ROLE role : ROLE.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role is not correct " + roleName);
    }
}