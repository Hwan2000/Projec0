package coin.backend.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class User {
	@Id
	private String user_uuid;

	private String user_password;

	@Column(unique = true)
	private String user_name;

	private BigDecimal cash_balance;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CoinWallet> coinWallets;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TradingRecord> tradingRecords;
}
