package coin.backend;

import coin.backend.data.repository.Coin;
import coin.backend.service.CoinpaprikaService;
import coin.backend.service.JustTestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class CoinpaprikaServiceTest {

    CoinpaprikaService coinpaprikaService;
    JustTestService justTestService;

    @BeforeEach
    public void beforeEach(){
        Coin coin = new Coin();
        RestTemplate restTemplate = new RestTemplate();
        coinpaprikaService = new CoinpaprikaService(restTemplate,coin);
        justTestService = new JustTestService(coin);
    }

    @Test
    void getCoin_shouldReturnCoinData(){
        coinpaprikaService.getCoin();
        justTestService.getCoin();
    }
}
