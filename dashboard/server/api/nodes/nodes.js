const express = require('express');
import { Nodes} from '../../../imports/api/nodes.js';
Fiber = Npm.require('fibers');
import { Meteor } from 'meteor/meteor';
const
    helpers = require('./../helpers'),
    router = express.Router(),
    moment = require('moment');

// Get all nodes
router.get('/', function(req, res) {

    let allNodes = [];

    Fiber(function() {
        Nodes.find({}).forEach(node => {
            allNodes.push(node);
        });

        res.send({status: 'success', result: allNodes});
    }).run();
});


// Get a single node
router.get('/:ip/', Meteor.bindEnvironment(function(req, res) {
    Fiber(function() {
        let node = Nodes.findOne({ip: req.params.ip});

        if(!node) {
            helpers.sendErrorResponse(res, 'Node with given IP could not be found. Did you register it?');
        }

        helpers.sendSuccessResponse(res, node);
    }).run();
}));


// Register a node
router.post('/', Meteor.bindEnvironment(function (req, res) {

    node = Nodes.findOne({ip: req.body.ip});

    // Check if node already exists
    if(node) {
        res.send({status: 'error', message: 'Node with given IP already registered.'});
        return;
    }

    let node = req.body;

    // Sset some defaults if request does not provide them
    node.shutdown = false;
    node.cpu = [];
    node.ram = [];
    node.disk = [];
    node.bandwidth = [];
    node.temperature = [];
    node.registered_at = moment().format();


    // Insert node
    Nodes.insert(node);


    // Send reply
    res.send({status: 'success', message: 'New node successfully registered.'});
}));


// Unregister a node
router.delete('/:ip/', Meteor.bindEnvironment(function(req, res) {
    console.log(Nodes.remove({ip: req.params.ip}));

    res.send({status: 'success', message: 'Node unregistered.'});
}));


// Updating node information
router.post('/:ip/deviceinfo/', Meteor.bindEnvironment(function(req, res) {
    console.log("Received update.");

    let body = req.body;

    if(body.deviceInfo.systemInfo.cpu) {
        Nodes.update({ip: req.params.ip}, {$push: { cpu: body.deviceInfo.systemInfo.cpu }});
    }

    if(body.deviceInfo.systemInfo.freeRAM) {
        Nodes.update({ip: req.params.ip}, {$push: { ram: body.deviceInfo.systemInfo.freeRAM }});
    }

    if(body.deviceInfo.systemInfo.disk) {
        Nodes.update({ip: req.params.ip}, {$push: { disk: body.deviceInfo.systemInfo.disk }});
    }

    if(body.deviceInfo.systemInfo.bandwidth) {
        Nodes.update({ip: req.params.ip}, {$push: { bandwidth: body.deviceInfo.systemInfo.bandwidth }});
    }

    if(body.deviceInfo.systemInfo.temperature) {
        Nodes.update({ip: req.params.ip}, {$push: { temperature: body.deviceInfo.systemInfo.temperature }});
    }

    res.send({status: "success", message: "Device updated."});
}));


router.post('/:ip/shutdown/', Meteor.bindEnvironment(function(req, res) {
    let node = Nodes.findOne({ip: req.params.ip});

    console.log(node);

    // Check if node already exists
    if(!node) {
        res.send({status: 'error', message: 'Node with given IP does not exist.'});
        return;
    }

    res.send({
        shutdown: node.shutdown
    });
}));



module.exports =  router;