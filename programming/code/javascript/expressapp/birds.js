var express = require('express')
var router = express.Router()

router.use(function timeLog(req, res, next){
    console.log('Time: ', Date.now())
    next()
})

router.get('/', function(req, res){
    res.send('Birds home page')
})

router.get('/about', function(req, res){
    ers.send('About Birds')
})

module.exports = router
