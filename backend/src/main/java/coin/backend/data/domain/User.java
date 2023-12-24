package coin.backend.data.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	private List<TradingRecord> tradingRecords;

	/*
	* singUp 함수에서만 쓰이는 생성자.
	* */
	public User(String user_name, String user_password) {
		// 랜덤 UUID
		this.user_uuid = UUID.randomUUID();
		// 시드머니 100만원
		this.cash_balance = BigDecimal.valueOf(1000000);
		// 유저 이름
		this.user_name = user_name;
		// 유저 비밀번호
		this.user_password = user_password;
	}
}
