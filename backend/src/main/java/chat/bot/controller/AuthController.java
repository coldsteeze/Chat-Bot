package chat.bot.controller;

import chat.bot.dto.AuthenticationDto;
import chat.bot.dto.JwtResponseDto;
import chat.bot.dto.UserDto;
import chat.bot.service.AuthService;
import chat.bot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDto> register(@RequestBody @Valid UserDto userDto) {
        String token = userService.register(userDto);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        String token = authService.authenticate(authenticationDto);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
