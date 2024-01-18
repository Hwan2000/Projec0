package coin.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coin.backend.data.repository.UserRepository;

@SpringBootTest
@Transactional
public class UserServiceTest {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;

	@Test
	public void 회원가입() throws Exception {
		userService.insertUser("hello", "pass");
		
		assertEquals("hello", userRepository.findByUserName("hello").orElseThrow(Exception::new).getUserName());;
		assertEquals("pass", userRepository.findByUserName("hello").orElseThrow(Exception::new).getUserPassword());;
	}
}
