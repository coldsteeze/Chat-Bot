package chat.bot.service;

import chat.bot.dto.UserDto;
import chat.bot.entity.User;
import chat.bot.exception.UserAlreadyExistsException;
import chat.bot.repository.UserRepository;
import chat.bot.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public String register(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
    }
}
