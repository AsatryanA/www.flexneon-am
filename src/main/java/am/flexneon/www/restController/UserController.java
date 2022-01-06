package am.flexneon.www.restController;

import am.flexneon.www.model.User;
import am.flexneon.www.model.dto.LoginDto;
import am.flexneon.www.model.dto.MessageDto;
import am.flexneon.www.model.dto.UserDto;
import am.flexneon.www.model.dto.UserResponse;
import am.flexneon.www.repository.RoleRepository;
import am.flexneon.www.repository.UserRepository;
import am.flexneon.www.security.jwt.JwtUtils;
import am.flexneon.www.service.UserDetailsImpl;
import am.flexneon.www.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
/*
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {


    }

        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDto("Error: Username is exist"));
        }

*//*        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }*//*

        User user = new User(userDto.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(userDto.getPassword()));

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository
                                .findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRespository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));*/
}

