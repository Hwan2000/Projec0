package coin.backend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coin.backend.data.domain.CoinInfo;

public interface CoinRepository extends JpaRepository<CoinInfo, Long> {
	
}
