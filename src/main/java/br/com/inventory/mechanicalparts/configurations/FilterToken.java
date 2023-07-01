package br.com.inventory.mechanicalparts.configurations;

import br.com.inventory.mechanicalparts.repositories.UserRepository;
import br.com.inventory.mechanicalparts.services.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token;

        var authorizarion = request.getHeader("Authorization");

        if(authorizarion != null){
            token = authorizarion.replace("Bearer ", "");
            var subject = this.tokenServiceImpl.getSubject(token);

            var usuario = this.userRepository.findByLogin(subject);

            var authentication =  new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
