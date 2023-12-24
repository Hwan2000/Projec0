package coin.backend.data.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CoinWallet {
	@Id @GeneratedValue
	@Column(name = "coin_wallet_id")
	private Long id;

	private BigDecimal coinBalance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_uuid")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private CoinInfo coinInfo;
}
