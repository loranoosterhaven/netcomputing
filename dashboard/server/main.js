/**
 * Main file for the server.
 *
 *
 */

import { Meteor } from 'meteor/meteor';
const api = require('./api/api');
const bodyParser = require('body-parser');

// Boot API server
Meteor.startup(() => {
    let express = require('express');
    let app = express();

    app.use(bodyParser.json());
    app.use('/api', api);

    app.listen('8080', function() {
        console.log('API listening on port 8080');
    });
});