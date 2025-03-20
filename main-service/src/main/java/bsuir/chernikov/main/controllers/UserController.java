package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.CourierDto;
import bsuir.chernikov.main.entities.User;
import bsuir.chernikov.main.enums.ROLE;
import bsuir.chernikov.main.security.entities.RegRequest;
import bsuir.chernikov.main.service.CourierService;
import bsuir.chernikov.main.service.UserService;
import bsuir.chernikov.main.utils.Converter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CourierService courierService;

    @PostMapping(value = {"/api/registrate-user"})
    public void registerUser(@RequestBody RegRequest request) {
        userService.saveUser(Converter.convertRegRequest(request));
    }

    @GetMapping("/api/get-user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getUserFromContext(), HttpStatus.OK);
    }

    @PostMapping("/api/top-up-balance")
    public void topUpBalance(@RequestParam Integer value) {
        userService.topUpBalance(userService.getUserFromContext(), value);
    }

    @GetMapping("/account")
    public void redirectToPersonalPage(HttpServletResponse response) {
        User user = userService.getUserFromContext();
        try {
            if (user.getRole() == ROLE.CLIENT) {
                response.sendRedirect("/client/client-panel.html");
            } else if (user.getRole() == ROLE.COURIER) {
                response.sendRedirect("/courier/courier-panel.html");
            } else if (user.getRole() == ROLE.ADMIN) {
                response.sendRedirect("/admin/admin-panel.html");
            } else if (user.getRole() == ROLE.DIRECTOR) {
                response.sendRedirect("/director/director-panel.html");
            } else {
                response.sendRedirect("/common/undefined-role.html");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
