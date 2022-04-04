package com.alapureram.jwt.filter;

import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.model.TokenDetails;
import com.alapureram.jwt.service.ConsumerService;
import com.alapureram.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Lazy
    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        TokenDetails tokenDetails = tokenService.find(token);
        if (tokenDetails == null) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        Consumer consumer = consumerService.find(tokenDetails.getId());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                consumer, null, consumer == null ? new ArrayList<>() : consumer.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
