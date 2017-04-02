/**
 * Root of the API.
 */

const express = require('express');
const nodesApi = require('./nodes/nodes');

let router = express.Router();

router.get('/', function(req, res) {
    res.send('Welcome to the API');
});

router.use('/nodes', nodesApi);

module.exports = router;