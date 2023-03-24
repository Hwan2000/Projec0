import { useState } from "react";
import axios from "axios";

const Buy = ({name, ath_price, money}) =>{

    const [amount, setAmount] = useState('');

    //구매
    const handleSubmit = async (event) =>{
        //event.preventDefault();
        if(parseInt(money) < parseInt(amount)){
            alert('돈이 부족합니다');
            return;
        } else if(parseInt(amount) < 0){
            alert('올바른 값을 입력해 주세요');
            return;
        } else {
            await axios.post(`http://localhost:5000/buy`, {
                name: name,
                amount: parseInt(amount),
                ath_price: parseInt(amount)/ath_price,
            },{
                withCredentials:true
            })
            .then(({data}) => {
                console.log(data);
            })
            .catch((error) => {
                console.error(error);
            })
        }
    }

    return(
        <>
            <form onSubmit={handleSubmit}>
                <input type="number" placeholder={name} value={amount} onChange={(event) => {setAmount(event.target.value)}}></input>
                <button type="submit">매수</button>
            </form>
        </>
    )

}

export default Buy;