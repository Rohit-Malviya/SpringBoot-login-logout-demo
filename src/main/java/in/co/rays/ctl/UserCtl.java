package in.co.rays.ctl;

/*import me.zhulin.shopapi.entity.User;
import me.zhulin.shopapi.security.JWT.JwtProvider;
import me.zhulin.shopapi.vo.request.LoginForm;
import me.zhulin.shopapi.vo.response.JwtResponse;
*/import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import in.co.rays.dto.UserDTO;
import in.co.rays.form.LoginForm;
import in.co.rays.security.JWT.JwtProvider;
import in.co.rays.security.JWT.JwtResponse;
import in.co.rays.service.UserService;

/*import me.zhulin.shopapi.service.UserService;*/

import java.security.Principal;

/**
 * Created By Zhu Lin on 1/1/2019.
 */
@CrossOrigin
@RestController
public class UserCtl {

    @Autowired
    UserService userService;


    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) {
        // throws Exception if authentication failed
    	System.out.println("inside login------"+loginForm.getUsername()+" "+loginForm.getPassword() );
    	
    	try {
        	Authentication auth =new UsernamePasswordAuthenticationToken(loginForm.getUsername(),loginForm.getPassword());
        	System.out.println("isAuthenticated-------"+auth.isAuthenticated());
        	Authentication authentication = authenticationManager.authenticate(auth);
        	System.out.println("11111111");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("222222222");
            String jwt = jwtProvider.generate(authentication);
            System.out.println("after-------");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDTO user = userService.findOne(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getName(), user.getRoleName()));
        } catch (AuthenticationException e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        System.out.println("user-----"+user);
    	try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /*@PutMapping("/profile")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user, Principal principal) {

        try {
            if (!principal.getName().equals(user.getEmail())) throw new IllegalArgumentException();
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }*/

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable("email") String email, Principal principal) {
        if (principal.getName().equals(email)) {
            return ResponseEntity.ok(userService.findOne(email));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
