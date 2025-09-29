package org.papelariapachecotorres.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        final String jwt = service.verifyUser(request.getUsername(), request.getPassword());
        if (jwt != null) {
            return ResponseEntity.ok(new AuthResponse(jwt));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest request) {
        service.saveUser(request.getUsername(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
