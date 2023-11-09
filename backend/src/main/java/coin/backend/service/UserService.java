package coin.backend.service;

import coin.backend.data.domain.User;
import coin.backend.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<User> getUsers() {
		return userRepository.findAll();
	}

}
