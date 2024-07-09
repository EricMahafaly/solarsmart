package com.solarsmart.frontoffice.security.aop;

import com.solarsmart.frontoffice.models.exception.UnauthorizedException;
import com.solarsmart.frontoffice.security.TokenService;
import com.solarsmart.frontoffice.security.TokenStoreService;
import com.solarsmart.frontoffice.security.annotation.Authorize;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationAspect {

    private final HttpServletRequest request;
    private final TokenService tokenService;
    private final TokenStoreService tokenStoreService;

    @Pointcut("@within(authorize)")
    public void allMethodsPointcut(Authorize authorize){}

    @Pointcut("@annotation(authorize)")
    public void methodPointcut(Authorize authorize){}

    @Before(value = "methodPointcut(authorize)", argNames = "authorize")
    public void checkAuthorization(Authorize authorize){
        checkAuth(authorize);
    }

    @Before(value = "allMethodsPointcut(authorize)", argNames = "authorize")
    public void checkClass(Authorize authorize){
        checkAuth(authorize);
    }

    private void checkAuth(Authorize authorize) {
        String header = request.getHeader("Authorization");

        if (header == null) throw new UnauthorizedException("");

        String token = header.split(" ")[1].trim();

        boolean isAuthorize = authorizeRoles(token, authorize.roles());
        boolean tokenIsExist = this.tokenIsExist(token);

        if (!tokenIsExist || !isAuthorize){
            throw new UnauthorizedException("");
        }
    }

    private boolean authorizeRoles(String token, String[] authorizeRoles){

        String[] tokenRoles = this.tokenService.getRoles(token);

        if (authorizeRoles.length == 0) return true;

        String tokenRole = Arrays.toString(tokenRoles).toLowerCase();
        for (String authorizeRole : authorizeRoles) {
            if (tokenRole.toLowerCase().contains(authorizeRole)) return true;
        }
        return false;
    }

    private boolean tokenIsExist(String token){
        return this.tokenStoreService.isExist(token);
    }
}
