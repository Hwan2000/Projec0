package coin.backend.data.domain;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class User {
	@Id @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID user_uuid;

	private String user_password;

	@Column(unique = true)
	private String user_name;

	private BigDecimal cash_balance;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CoinWallet> coinWallets;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Trade> trades;
}
