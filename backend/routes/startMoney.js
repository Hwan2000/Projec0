const express = require('express');
const router = express.Router();

router.route('/setMoney/:money')
  .get(async (req, res) => {

    const cookies = Object.keys(req.cookies);
    for (let i = 0; i < cookies.length; i++) {
        res.clearCookie(cookies[i]);
    }

    
    const money = req.params.money;
    res.cookie('money', money, { httpOnly: false });
    res.status(200).send('set-money');
  });

  router.route('/')
  .get(async (req,res) => {
    if(req.cookies.money === 0){
      res.status(200).send(0);
    }
    res.status(200).send(req.cookies.money);
  })

module.exports = router;   