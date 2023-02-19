const express = require('express');
const router = express.Router();

router.route('/setMoney/:money')
  .get(async (req, res) => {

    // Object.keys(req.cookies).forEach((cookieName) => {
    //     res.clearCookie(cookieName);
    // });

    const cookies = Object.keys(req.cookies);
    for (let i = 0; i < cookies.length; i++) {
        res.clearCookie(cookies[i]);
    }

    
    const money = req.params.money;
    res.cookie('money', money, { httpOnly: true });
    res.status(200).send('set-money');
  });

module.exports = router;    