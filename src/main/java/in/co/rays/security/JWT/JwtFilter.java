package in.co.rays.security.JWT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.co.rays.dto.UserDTO;
import in.co.rays.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created By Zhu Lin on 1/1/2019.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getToken(httpServletRequest);
        System.out.println(httpServletRequest.getParameter("email")+""+httpServletRequest.getParameter("password"));
        System.out.println("Inside doFilter()"+jwt);
        if (jwt != null && jwtProvider.validate(jwt)) {
            try {
                String userAccount = jwtProvider.getUserAccount(jwt);
                UserDTO user = userService.findOne(userAccount);
                System.out.println(user.toString());
                // pwd not necessary
                // if jwt ok, then authenticate
                SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRoleName());
                ArrayList<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
                list.add(sga);
                UsernamePasswordAuthenticationToken auth
                        = new UsernamePasswordAuthenticationToken(user.getEmail(), null, list);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
            	e.printStackTrace();
                logger.error("Set Authentication from JWT failed");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getToken(HttpServletRequest request) {
    	System.out.println("Gete token "+request.getParameter("email")+""+request.getParameter("password"));
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
