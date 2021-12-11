package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.RegisterDTO;
import uz.pdp.hrmanagement.repository.UserRepository;
import uz.pdp.hrmanagement.security.JwtProvider;
import uz.pdp.hrmanagement.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

//    @PostMapping("/register")
//    public HttpEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
//        ApiResponse response = authService.register(registerDTO);
//        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
//    }

    @PostMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email, @RequestParam String code, @RequestBody String password) {
        ApiResponse response = authService.verify(email, code, password);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody RegisterDTO registerDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    registerDTO.getEmail(),
                    registerDTO.getPassword()));
            String token = jwtProvider.generateToken(registerDTO.getEmail());

            ApiResponse apiResponse = new ApiResponse("Token", true, token);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Login yoki parol xatolik!");
        }
    }
}
