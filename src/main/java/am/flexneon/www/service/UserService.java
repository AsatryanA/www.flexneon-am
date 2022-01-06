package am.flexneon.www.service;

import am.flexneon.www.model.User;
import am.flexneon.www.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Transactional
    public User creatNewUser(User user) {
        if (user.getUsername().equals(userRepository.findByUsername(user.getUsername()))) {
            throw new UsernameNotFoundException("Duplicate");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        return save;
    }
}

