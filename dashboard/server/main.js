import { Meteor } from 'meteor/meteor';
import '../imports/api/nodes.js';
let net = require('net');


Meteor.startup(() => {
    let server = net.createServer(function(socket) {
        socket.write('Echo server\r\n');

        console.log('Got a message mate');
        socket.pipe(socket);
    });

    server.listen(1337, '127.0.0.1');
});