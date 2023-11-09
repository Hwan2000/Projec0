package coin.backend.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CoinInfo {
	@Id @GeneratedValue
	@Column(name = "coin_id")
	private Long id;

	private String name;

	private BigDecimal coin_price;

}
