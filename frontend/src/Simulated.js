import { useEffect } from "react";
import axios from "axios";

const Simulated = () =>{

    useEffect(() => {
        const getMoney = async () =>{
            await axios.get('http://localhost:5000/get-money',{withCredentials:true})
            .then(({data})=>{
                console.log(data);
            })
            .catch((error) => {
                console.error(error);
            })
        }
        getMoney();
    },[])

    return(
        <>
            <p>hello Simulated</p>
        </>
    )
}

export default Simulated;