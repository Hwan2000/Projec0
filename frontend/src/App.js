import axios from 'axios';
import Simulated from './Simulated';
import { useEffect, useState } from 'react';

function App() {

  const [coinArray, setCoinArray] = useState([]);

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

  const rows = coinArray.map((row, index) =>
    <tr key={index}>
      <td>{index+1}</td>
      <td>{row.name}</td>
      <td>{row.quotes.KRW.ath_price}</td>
      <td>{row.last_updated}</td>
      <td>{row.quotes.KRW.market_cap}</td>
    </tr>
  )

  return (
    <div>
      <h1>hello world</h1>
      <table border="1">
        <thead>
          <tr>
            <th>Ranking</th>
            <th>Name</th>
            <th>Price</th>
            <th>Updated</th>
            <th>Calculating supply</th>
          </tr>
        </thead>
        <tbody>
          {rows}
        </tbody>
      </table>
      <Simulated/>
    </div>
  );
}

export default App;
