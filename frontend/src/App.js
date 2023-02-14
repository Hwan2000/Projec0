import axios from 'axios';
import { useEffect, useState } from 'react';

function App() {

  const [coinArray, setCoinArray] = useState();

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
      <td>{row.id}</td>
      <td>{row.max_supply}</td>
    </tr>
  )

  return (
    <div>
      <h1>hello world</h1>
      <table>
        <thead>
          <tr>
            <th>Ranking</th>
            <th>Name</th>
            <th>Max_supply</th>
          </tr>
        </thead>
        <tbody>
          {rows}
        </tbody>
      </table>
    </div>
  );
}

export default App;
