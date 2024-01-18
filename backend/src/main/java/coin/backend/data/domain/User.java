package coin.backend.data.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

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
	private UUID userUuid;

	private String userPassword;

	@Column(unique = true)
	private String userName;

	private BigDecimal cashBalance;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CoinWallet> coinWallets;

	public CoinWallet getCoinWallet(Long coinId) {
		for (CoinWallet w : this.coinWallets)
		{
			if (w.getCoinInfo().getId() == coinId) {
				return w;
			}
		}
		throw new IllegalStateException("coinId not found");
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Trade> trades;

	/*
	* singUp 함수에서만 쓰이는 생성자.
	* */
	public User(String userName, String userPassword) {
		// 랜덤 UUID
		this.userUuid = UUID.randomUUID();
		// 시드머니 100만원
		this.cashBalance = BigDecimal.valueOf(1000000);
		// 유저 이름
		this.userName = userName;
		// 유저 비밀번호
		this.userPassword = userPassword;
	}
}
