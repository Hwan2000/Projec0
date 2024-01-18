package coin.backend.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class TradeServiceTest {

	@Autowired TradeService tradeService;
	@Autowired UserService userService;

	@Test
	public void 코인구매하기() {
		userService.insertUser("userA", "passA");
		
	}

}
