package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.LoginRequest;
import com.hirehub.hirehub_backend.dto.LoginResponse;
import com.hirehub.hirehub_backend.dto.RegisterRequest;
import com.hirehub.hirehub_backend.dto.UserResponse;
import com.hirehub.hirehub_backend.entity.User;
import com.hirehub.hirehub_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import com.hirehub.hirehub_backend.security.JwtService;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.hirehub.hirehub_backend.service.storage.FileStorageService;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final FileStorageService fileStorageService;
public UserServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        FileStorageService fileStorageService) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.fileStorageService = fileStorageService;
}


    @Override
public UserResponse registerUser(RegisterRequest request) {

    User user = new User();

    user.setFullName(request.getFullName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setPhone(request.getPhone());
    user.setRole(request.getRole());
    user.setCreatedAt(LocalDateTime.now());

    User savedUser = userRepository.save(user);

    return new UserResponse(
            savedUser.getId(),
            savedUser.getFullName(),
            savedUser.getEmail(),
            savedUser.getPhone(),
            savedUser.getResumeUrl(),
            savedUser.getRole(),
            savedUser.getCreatedAt()
    );
}

    @Override
public List<UserResponse> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(user -> new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getResumeUrl(),
                    user.getRole(),
                    user.getCreatedAt()))
            .toList();
}

    @Override
public UserResponse getUserById(Long id) {

    User user = userRepository.findById(id).orElse(null);

    if (user == null) {
        return null;
    }

    return new UserResponse(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getResumeUrl(),
            user.getRole(),
            user.getCreatedAt()
    );
}
@Override
public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtService.generateToken(user.getEmail());

return new LoginResponse(token);
}
@Override
public String uploadResume(Long userId, MultipartFile file) {

    try {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileName = fileStorageService.uploadResume(file);

        user.setResumeUrl(fileName);

        userRepository.save(user);

        return "Resume uploaded successfully";

    } catch (IOException e) {
        throw new RuntimeException("Could not upload resume");
    }
}

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}