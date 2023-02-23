const express = require('express');
const router = express.Router();

router.route('/')
.post(async (req,res) => {
    res.cookie('money', parseInt(req.cookies.money)-parseInt(req.body.amount), {httpOnly:false});
    res.cookie(req.body.name, req.body.ath_price, {httpOnly: false});
    res.cookie(req.body.name+'_order_history',String(req.body.hour) + String(req.body.minute), {httpOnly: true});
    res.status(200).send('ok');
    //res.cookie('money', nowMoney-req.body)
    //1. 머니에서 현재 받은 가격을 뺌
    //2. 코인이믈 - 보유량으로 쿠키 새로 생성
    //3. 코인이름 - 시간으로 쿠키 새로 생성
})

module.exports = router;