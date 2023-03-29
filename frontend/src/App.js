import axios from 'axios';
import Simulated from './Simulated';
import { useEffect, useState } from 'react';
import Buy from './Buy';

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
  useEffect(()=>{
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
  });
 

  const rows = coinArray.map((row, index) =>
    <tr key={index}>
      <td>{index+1}</td>
      <td>{row.name}</td>
      <td>{row.quotes.KRW.price}</td>
      <td>{row.last_updated}</td>
      <td>{row.quotes.KRW.percent_change_24h}</td>
      <td><Buy name={row.name} ath_price={row.quotes.KRW.ath_price} money={money}/></td>
    </tr>
  )

  return (
    <>
    { (money === "") ?
    <div>
      <Simulated money={money} setMoney={setMoney}/>
    </div>
    :
    <div>
      <h1>SEED:{money}</h1>
      <table border="1">
        <thead>
          <tr>
            <th>Ranking</th>
            <th>Name</th>
            <th>Price</th>
            <th>Updated</th>
            <th>Change(24H)</th>
            <th>Buy</th>
          </tr>
        </thead>
        <tbody>
          {rows}
        </tbody>
      </table>
    </div>
    }
    </>
  );
}

export default App;
