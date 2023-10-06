package coin.backend.service;

import coin.backend.data.repository.Coin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JustTestService {

    private final Coin coin;

    public void getCoin(){
        System.out.println(coin.getCoinMap().get("BTC"));
    }
}
