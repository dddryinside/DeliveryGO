package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.entities.User;
import bsuir.chernikov.main.security.entities.RegRequest;
import bsuir.chernikov.main.service.UserService;
import bsuir.chernikov.main.utils.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = {"/api/registrate-user"})
    public void registerUser(@RequestBody RegRequest request) {
        userService.saveUser(Converter.convertRegRequest(request));
    }

    @Operation(summary = "Get current user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/get-user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getUserFromContext(), HttpStatus.OK);
    }

    @Operation(summary = "Top up user's balance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance topped up successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid top-up value"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/top-up-balance")
    public void topUpBalance(@RequestParam Integer value) {
        userService.topUpBalance(userService.getUserFromContext(), value);
    }
}
