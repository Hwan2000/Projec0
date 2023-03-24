import axios from 'axios';
import Simulated from './Simulated';
import { useEffect, useState } from 'react';
import Buy from './Buy';
import Wallet from './Wallet';

function App() {

  const [coinArray, setCoinArray] = useState([]);
  const [money, setMoney] = useState(0);

  //코인 데이터 가져오기
  useEffect(()=>{
    const getCoin = async () => {
      await axios.get('http://localhost:5000')
      .then(({data}) => {
        console.log(data);
        setCoinArray(data);
      })
      .catch((error) => {
        console.error(error);
      })
    }
    getCoin();
  },[])

  //돈 데이터 가져오기
  useEffect(() => {
    const getMoney = async () => {
      await axios.get('http://localhost:5000/money',{withCredentials:true})
      .then(({data}) => {
        setMoney(data);
      })
      .catch((error)=>{
        console.log(error);
      })
    }
    getMoney();
  })
 

  const rows = coinArray.map((row, index) =>
    <tr key={index}>
      <td>{index+1}</td>
      <td>{row.name}</td>
      <td>{row.quotes.KRW.ath_price}</td>
      <td>{row.last_updated}</td>
      <td>{row.quotes.KRW.market_cap}</td>
      <td><Buy name={row.name} ath_price={row.quotes.KRW.ath_price} money={money}/></td>
    </tr>
  )

  return (
    <div>
      <h1>{money}</h1>
      <table border="1">
        <thead>
          <tr>
            <th>Ranking</th>
            <th>Name</th>
            <th>Price</th>
            <th>Updated</th>
            <th>Calculating supply</th>
            <th>Buy</th>
          </tr>
        </thead>
        <tbody>
          {rows}
        </tbody>
      </table>
      <Simulated/>
      <Wallet money={money} coinArray={coinArray}/>
    </div>
  );
}

export default App;
