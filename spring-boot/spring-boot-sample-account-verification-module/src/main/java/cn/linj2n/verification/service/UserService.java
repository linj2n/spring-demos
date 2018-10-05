package cn.linj2n.verification.service;

import cn.linj2n.verification.domain.Authority;
import cn.linj2n.verification.domain.User;
import cn.linj2n.verification.repository.AuthorityRepository;
import cn.linj2n.verification.repository.UserRepository;
import cn.linj2n.verification.service.utils.RandomUtil;
import cn.linj2n.verification.web.errors.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public User createUserInformation(String login, String password, String username, String email) {

        if (checkIfExitUserActivatedByLoginOrEmail(login,email)) {
            throw new UserAlreadyExistException("User has already existed. [login=" + login + ",email=" + email + "]");
        }

        User newUser = new User();

        // 1. preparing for security
        Set<Authority> authorities = new HashSet<>();
        Optional<Authority> authority = authorityRepository.findOneByName("ROLE_USER");
        String encrytedPassword = passwordEncoder.encode(password);

        // 2. init newUser
        newUser.setLogin(login);
        newUser.setPassword(encrytedPassword);
        newUser.setUsername(username);
        newUser.setEmail(email);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authority.ifPresent(authorities::add);
        newUser.setAuthorities(authorities);

        // 4. save new user
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<User> activateRegistration(String key) {
        // TODO: activateRegistration
        return Optional.empty();
    }

    public void changePassword(String password) {
        // TODO: changePassword
    }

    public Boolean checkIfExitUserActivatedByLoginOrEmail(String login,String email) {
        User user = userRepository.findOneByLoginOrEmail(login.toLowerCase(), email.toLowerCase()).orElse(null);
        return user != null && user.isActivated();
    }

}
