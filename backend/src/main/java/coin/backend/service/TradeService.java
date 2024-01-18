package coin.backend.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coin.backend.data.domain.CoinInfo;
import coin.backend.data.domain.Trade;
import coin.backend.data.domain.TradeType;
import coin.backend.data.domain.User;
import coin.backend.data.repository.CoinRepository;
import coin.backend.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {
	
	private final UserRepository userRepository;
	private final CoinRepository coinRepository;

	@Transactional
	public Long newTrade(UUID userId, Long coinId, TradeType type, BigDecimal amount, BigDecimal price) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user found"));
		CoinInfo coinInfo = coinRepository.findById(coinId).orElseThrow(() -> new IllegalArgumentException("No coin info found"));
		
		validateTrade(user, coinInfo, type, amount, price);

		Trade trade = Trade.createTrade(amount, price, type, user, coinInfo);
		// tradeReporitory.save(trade);
		return trade.getId();
	}

	private void validateTrade(User user, CoinInfo coinInfo, TradeType type, BigDecimal amount, BigDecimal price) {
		if (type == TradeType.BUY) {
			if (user.getCashBalance().compareTo(amount.multiply(price)) < 0) {
				throw new IllegalStateException("잔고가 부족합니다.");
			}
		}
		else if (type == TradeType.SELL) {
			if (user.getCoinWallet(coinInfo.getId()).getCoinBalance().compareTo(amount) < 0) {
				throw new IllegalStateException("코인 보유량이 부족합니다.");
			}
		}
	}
}
