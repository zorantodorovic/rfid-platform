package hr.fer.security;

import hr.fer.model.User;
import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public MainUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null) {
            System.out.println("No user found with username: " + username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        System.out.println("User " + username + " passed authentication.");

        return new CurrentUser(user);
    }

}

