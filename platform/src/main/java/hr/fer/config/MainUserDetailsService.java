package hr.fer.config;

import hr.fer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    private final static List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>() {{
        add(new SimpleGrantedAuthority("USER"));
    }};

    @Autowired
    public MainUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        hr.fer.model.User user = userRepo.findByUsername(username);

        if (user == null) {
            System.out.println("No user found with username: " + username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        System.out.println("User " + username + " passed authentication.");
        return new User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }

}

