package coin.backend.data.domain;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trade {
	@Id @GeneratedValue
	@Column(name = "trade_id")
	private Long id;

	private BigDecimal amount; //거래된 코인 개수

	private LocalDateTime time;
	
	private BigDecimal price; //거래 당시 가격

	@Enumerated(EnumType.STRING)
	private TradeType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_uuid")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private CoinInfo coinInfo;


	public static Trade createTrade(BigDecimal amount, BigDecimal price, TradeType type, User user, CoinInfo coinInfo) {
		Trade trade = new Trade();
		trade.amount = amount;
		trade.price = price;
		trade.type = type;
		trade.time = LocalDateTime.now();

		trade.user = user;
		user.getTrades().add(trade);

		trade.coinInfo = coinInfo;

		return trade;
	}
}
