package com.solarsmart.frontoffice.security;

import com.solarsmart.frontoffice.models.dto.response.AdminResponse;
import com.solarsmart.frontoffice.models.dto.response.AuthenticationResponse;
import com.solarsmart.frontoffice.models.entities.Admin;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.services.api.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final TokenStoreService tokenStoreService;


    @Override
    public AuthenticationResponse<?> signIn(Admin admin) {

        Admin user = (Admin) adminService.findByEmail(admin.getEmail()).orElseThrow(() ->
                new DomainException("utilisateur associé à cette email n'existe pas"));

        if (!passwordEncoder.matches(admin.getPassword(), user.getPassword()))
            throw new DomainException("email ou mot de passe incorrect");

        var jwt = tokenService.generateToken(user);

        this.tokenStoreService.saveToken(jwt);

        AuthenticationResponse<?> response = AuthenticationResponse.builder()
                .user(new AdminResponse(user)).token(jwt).build();

        return response;
    }

    @Override
    public AuthenticationResponse<?> signUp(Admin admin) {
//        System.out.println("password = "+ passwordEncoder.encode(admin.getPassword()));

        admin.setPassword(this.passwordEncoder.encode(admin.getPassword()));

        boolean isExist = this.adminService.existingByEmail(admin.getEmail());

        if (isExist) throw new DomainException("cette email est deja utilisé");

        var user = adminService.create(admin);

        var jwt = tokenService.generateToken(user);

        this.tokenStoreService.saveToken(jwt);
//        var jwt = jwtService.generateToken(customer.convert());

        return AuthenticationResponse.builder()
                .user(new AdminResponse(user))
                .token(jwt).build();
    }

    @Override
    public void logout(String token) {
        this.tokenStoreService.deleteToken(token);
    }
}
