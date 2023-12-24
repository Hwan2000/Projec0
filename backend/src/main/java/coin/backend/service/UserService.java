package coin.backend.service;

import coin.backend.data.domain.User;
import coin.backend.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/*
	* 회원가입 비즈니스 로직
	* */
	@Transactional
	public void insertUser(String userName, String userPassword){

		try{
			// 현재 회원이 있는지 검사
			Optional<User> byUserName = userRepository.findByUserName(userName);

			// 없으면 삽입
			if(byUserName.isEmpty()){
				User user = new User(userName, userPassword);
				userRepository.save(user);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
