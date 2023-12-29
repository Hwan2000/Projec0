package coin.backend.data.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

/*
* refresh 토큰 엔티티
* 나중에 redis로 업데이트 할 수 있음
* 유저 uuid와 해당 uuid의 토큰 값으로 생성됨
* */
@Entity
@NoArgsConstructor
@Getter
public class RefreshToken {

    @Id
    @Column(name = "refresh_token_owner", columnDefinition = "BINARY(16)")
    private UUID uuid;
    @Column(name = "refresh_token_value")
    private String value;

    public RefreshToken(String value, UUID uuid) {
        this.uuid = uuid;
        this.value = value;
    }

    public void updateValue(String value){
        this.value = value;
    }
}
