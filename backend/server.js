const express = require('express');
const axios = require('axios');

const app = express();

app.use(express.json());
app.use(express.urlencoded({extended:true}));

app.get('/',async (req,res) => {
    await axios.get('https://api.coinpaprika.com/v1/tickers?quotes=KRW')
    .then(({data}) => {
        res.send(data.slice(0,10));
    })
    .catch(error => {
        console.error(error);
    })
})

app.listen(5000, () => {
    console.log('server is running on 5000')
})

