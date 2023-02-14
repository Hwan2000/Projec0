const express = require('express');
const axios = require('axios');
const cookieParser = require('cookie-parser');
const cors = require('cors');

const app = express();

app.use(express.json());
app.use(express.urlencoded({extended:true}));
app.use(cookieParser());

app.use(
    cors({
        origin: ['http://localhost:3000'],
        credentials:true,
    })
),


app.get('/',async (req,res) => {
    await axios.get('https://api.coinpaprika.com/v1/tickers?quotes=KRW')
    .then(({data}) => {
        res.send(data.slice(0,10));
    })
    .catch(error => {
        console.error(error);
    })
})

app.get('/get-money', async (req,res) => {
    res.cookie('money', 1000000, {maxAge:90000, credentials:true});
    res.status(200).send('get-money');
})

app.listen(5000, () => {
    console.log('server is running on 5000')
})