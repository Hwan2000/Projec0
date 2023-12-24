package coin.backend.util;

import coin.backend.data.domain.User;
import coin.backend.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUserName(userName);

        if(optionalUser.isPresent()){

            User user = optionalUser.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getUserPassword())
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not Found");
        }
    }
}
