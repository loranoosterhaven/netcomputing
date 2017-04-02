import { Meteor } from 'meteor/meteor';
import { Nodes} from '../imports/api/nodes.js';
let net = require('net');

let port = 1337;



Meteor.startup(() => {
    let server = net.createServer(Meteor.bindEnvironment(function(socket) {
        console.log('CONNECTED (' + socket.localPort + '): ' + socket.remoteAddress + ':' + socket.remotePort);

        socket.on('data', Meteor.bindEnvironment(function (data) {
            Nodes.insert({ip: '192.168.2.' + (Math.random() % 255)});

            // close connection
            socket.end();
        }));

        socket.on('error', function (error) {
            console.log('******* ERROR ' + error + ' *******');

            // close connection
            socket.end();
        });
    }));

    server.listen(port, function() {
        console.log('Listening on ' + port);
    });
});