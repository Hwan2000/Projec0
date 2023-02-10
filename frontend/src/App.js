import axios from 'axios';
import { useEffect } from 'react';

function App() {

  useEffect(()=>{
    const getCoin = async () => {
      await axios.get('http://localhost:5000')
      .then(({data}) => {
        console.log(data);
      })
      .catch((error) => {
        console.log(error);
      })
    }
    getCoin();
  },[])

  return (
    <div>
      <h1>hello world</h1>
      <button>new button</button>
    </div>
  );
}

export default App;
