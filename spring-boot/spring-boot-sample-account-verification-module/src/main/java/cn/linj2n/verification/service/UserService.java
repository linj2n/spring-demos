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
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public User createUserInformation(String login, String password, String username, String email) {

        /* Users who have already ACTIVATED are not allowed to register */
        if (checkIfExitUserActivatedByLoginOrEmail(login,email)) {
            throw new UserAlreadyExistException("User has already existed. [login=" + login + ",email=" + email + "]");
        }

        User newUser = userRepository.findOneByLoginOrEmail(login, email).filter(user -> !user.isActivated()).orElse(new User());
        logger.info("new User info : {}", newUser);

        /*1. preparing for security*/
        Set<Authority> authorities = new HashSet<>();
        Optional<Authority> authority = authorityRepository.findOneByName("ROLE_ADMIN");
        String encrytedPassword = passwordEncoder.encode(password);

        /*2. set newUser properties*/
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
        logger.info("new user info : {}",newUser);

        /* 4. save new user*/
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<User> activateRegistration(String key) {
        logger.info("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key).map(user -> {
            user.setActivationKey(null);
            user.setActivated(true);
            userRepository.save(user);
            logger.info("Activated user: {}", user);
            return user;
        });
    }

    public void changePassword(String password) {
        // TODO: changePassword
    }

    public Optional<User> requestPasswordReset(String email) {
        // TODO: request password reset service
        return userRepository.findOneByEmail(email)
//                .filter(User::isActivated)
                .map(user -> {
                    logger.info("Request password reset [email= {}].",email);
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(ZonedDateTime.now());
                    userRepository.save(user);
                    return user;
                });
    }

    public Optional<User> resetPassword(String newPassword, String email) {
        // TODO: reset password with new password and email.
        return Optional.empty();
    }

    public Boolean checkIfExitUserActivatedByLoginOrEmail(String login,String email) {
        User user = userRepository.findOneByLoginOrEmail(login.toLowerCase(), email.toLowerCase()).orElse(null);
        return user != null && user.isActivated();
    }
    public Optional<User> getUserByPasswordResetKey(String key) {
        return userRepository.findOneByActivationKey(key).filter(User::isActivated);
    }
}
