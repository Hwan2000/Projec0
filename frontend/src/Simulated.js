import axios from "axios";
import { useState } from "react";

const Simulated = () =>{
    const [money,setMoney] = useState(0);

    const handleSubmit = async (event) => {
        event.preventDefault();
        if(money === ""){
            await axios.get(`http://localhost:5000/money/setMoney/1000000`, {
                withCredentials: true,
              })
              .then(({ data }) => {
                console.log(data);
              })
              .catch((error) => {
                console.error(error);
              });
              setMoney(0);
            return;
        }
        await axios.get(`http://localhost:5000/money/setMoney/${money}`, {
          withCredentials: true,
        })
        .then(({ data }) => {
          console.log(data);
        })
        .catch((error) => {
          console.error(error);
        });
        setMoney(0);
      };

    return(
        <>
            <h3>초기 자본 설정</h3>
            <form onSubmit={handleSubmit}>
                <input type="number" value={money} onChange={(event) => {setMoney(event.target.value)}}/>
                <button type="submit">설정</button>
                <p>설정 시, 계좌가 초기화됩니다.</p>
            </form>
        </>
    )
}

export default Simulated;