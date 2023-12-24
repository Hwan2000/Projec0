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
	public void insertUser(String user_name, String user_password){

		try{
			// 현재 회원이 있는지 검사
			Optional<User> byUser_name = userRepository.findByUser_name(user_name);

			// 없으면 삽입
			if(byUser_name.isEmpty()){
				User user = new User(user_name, user_password);
				userRepository.save(user);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
