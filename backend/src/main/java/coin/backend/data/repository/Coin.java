package coin.backend.data.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//coin 객체 저장하는 함수
@Data
@Repository
public class Coin {

    //Coin 정보 캐싱하는 클래스
    @Data
    public static class CoinInfo{
        String name;
        String price;
    }

    private Map<String, CoinInfo> coinMap;

    public Coin() {
        coinMap = new HashMap<String, CoinInfo>();

        coinMap.put("BTC", new CoinInfo());
        coinMap.put("ETH", new CoinInfo());
        coinMap.put("BNB", new CoinInfo());
    }

    public List<String> getCoinIdList(){
        List<String> coinIdList = new ArrayList<>(coinMap.keySet());
        return coinIdList;
    }

    public Map<String, CoinInfo> getCoinMap(){
        return coinMap;
    }
}
