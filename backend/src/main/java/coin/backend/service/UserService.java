package coin.backend.service;

import coin.backend.data.domain.RefreshToken;
import coin.backend.data.domain.User;
import coin.backend.data.dto.TokenRes;
import coin.backend.data.repository.RefreshTokenRepository;
import coin.backend.data.repository.UserRepository;
import coin.backend.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional(readOnly = true)
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * 유저 정보 저장해서 회원가입 완료
 	 * @param userName : 유저 이름(String)
	 * @param userPassword 유저 비밀번호(String)
	 */
	@Transactional
	public void insertUser(String userName, String userPassword){

		try{
			// 현재 회원이 있는지 검사
			Optional<User> byUserName = userRepository.findByUserName(userName);

			// 없으면 삽입
			if(byUserName.isEmpty()){
				User user = new User(userName, passwordEncoder.encode(userPassword));
				userRepository.save(user);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Transactional
	public TokenRes loginUser(String userName, String userPassword){
		try {
			Optional<User> byUserName = userRepository.findByUserName(userName);

			byUserName.orElseThrow(() -> new UsernameNotFoundException("유저 없음"));

			User user = byUserName.get();

			if(passwordEncoder.matches(userPassword, user.getUserPassword())){
				String refreshToken = jwtTokenProvider.createRefreshToken();
				String accessToken = jwtTokenProvider.createAccessToken(user.getUserUuid().toString());

				RefreshToken refreshTokenSave = new RefreshToken(refreshToken, user.getUserUuid());

				refreshTokenRepository.save(refreshTokenSave);

				return new TokenRes(accessToken, refreshToken);
			} else {
				throw new AuthenticationException("비밀번호 오류");
			}

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
