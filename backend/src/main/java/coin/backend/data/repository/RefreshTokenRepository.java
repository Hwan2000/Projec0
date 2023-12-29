package coin.backend.data.repository;

import coin.backend.data.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>{

    Optional<RefreshToken> findByValue(String value);
}
