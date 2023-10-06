package coin.backend.service;

import coin.backend.data.repository.Coin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CoinpaprikaService {

    private final RestTemplate restTemplate;
    private final Coin coin;
    public void getCoin(){
        //CoinPaprika 에서 코인 정보 가져옴
        List<Map> coinRawList = restTemplate.getForObject("https://api.coinpaprika.com/v1/tickers?quotes=KRW", List.class);

        List<String> coinIdList = coin.getCoinIdList();

        for(Map<String,Object> coinRaw: coinRawList){
                if(coinIdList.contains(coinRaw.get("symbol"))) {
                    Map<String, Object> quotes = (Map<String, Object>) coinRaw.get("quotes");
                    Map<String, Object> krw = (Map<String, Object>) quotes.get("KRW");

                    //코인 정보 파싱해서 CoinInfo 에 저장
                    Coin.CoinInfo coinInfo = new Coin.CoinInfo();
                    coinInfo.setName((String) coinRaw.get("name"));
                    coinInfo.setPrice(String.valueOf(krw.get("price")));

                    //저장한 Coin Info 를 적절한 Symbol 과 함께 Map 에 저장
                    coin.getCoinMap().put((String) coinRaw.get("symbol"),coinInfo);
                }
        }

        //System.out.println(coin.coinMap.get("BTC").toString());
    }
}
