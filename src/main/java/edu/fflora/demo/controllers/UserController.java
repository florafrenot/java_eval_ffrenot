package edu.fflora.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.dao.UserDao;
import edu.fflora.demo.models.Chantier;
import edu.fflora.demo.models.User;
import edu.fflora.demo.security.AppUserDetailsService;
import edu.fflora.demo.security.JwtUtils;
import edu.fflora.demo.views.ChantierView;
import edu.fflora.demo.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        try {

            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getPseudo(),
                    user.getPassword()
            )).getPrincipal();

            return jwtUtils.generateJwt(userDetails);

        } catch (Exception ex) {
            return null;
        }

    }

    @GetMapping("/user/{id}/")
    @Secured("ROLE_ADMINISTRATEUR")
    @JsonView(UserView.class)
    public List<User> list() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    @DeleteMapping("/user/{id}")
    @Secured("ROLE_ADMINISTRATEUR")
    @JsonView(UserView.class)
    public ResponseEntity<User> delete(@PathVariable int id) {
        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isPresent()) {
            userDao.deleteById(id);
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
