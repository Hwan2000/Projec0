package coin.backend.data.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TradingRecord {
	@Id @GeneratedValue
	@Column(name = "trading_record_id")
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
}
