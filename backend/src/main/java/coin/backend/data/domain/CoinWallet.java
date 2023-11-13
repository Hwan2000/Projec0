package coin.backend.data.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CoinWallet {
	@Id @GeneratedValue
	@Column(name = "coin_wallet_id")
	private Long id;

	private BigDecimal coin_balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_uuid")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private Long coin_id;
}
