const express = require('express');
const router = express.Router();

function getCookieValue(req, cookieName) {
    const cookieValue = req.cookies[cookieName];
    return cookieValue;
}

router.route('/')
.post(async (req,res) => {
    const coinCount = req.body.name + '_count';
    const coinSize = req.body.name + '_size';
    const prevCoinCount = parseFloat(getCookieValue(req, coinCount)) || 0;
    const prevCoinSize = parseInt(getCookieValue(req, coinSize)) || 0;
    res.cookie('money', parseInt(req.cookies.money)-parseInt(req.body.amount), {httpOnly:false});
    res.cookie(coinCount, parseFloat(req.body.ath_price) + prevCoinCount, {httpOnly: false});
    res.cookie(coinSize, parseInt(req.body.amount) + prevCoinSize, {httpOnly: false})
    res.status(200).send('ok');
    //res.cookie('money', nowMoney-req.body)
    //1. 머니에서 현재 받은 가격을 뺌
    //2. 코인이름 - 보유량으로 쿠키 새로 생성
    //3. 코인이름 - 시간으로 쿠키 새로 생성
})

module.exports = router;