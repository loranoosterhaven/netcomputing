import React, { Component, PropTypes } from 'react';
import { Nodes } from '../api/nodes';
import { createContainer } from 'meteor/react-meteor-data';

const
    LineChart = require("react-chartjs").Line,
    moment = require('moment');

let options = {
    pointDotRadius: 0,
    scaleShowVerticalLines: false,
};


let Graph = React.createClass({
    getData: function() {
        console.log(this.props.ip);

        let labels = [];
        for(let i = 0; i < this.props.data.length; ++i) {
            labels.push('');
        }

        return {
            labels: labels,
            datasets: [
                {
                    label: "Prime and Fibonacci",
                    fillColor: "rgba(0,0,0,0)",
                    strokeColor: "rgba(0,0,0,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(100,100,100,1)",
                    data: this.props.data,
                    bezierCurve: false
                }
            ]
        }
    },

    render: function() {
        return <LineChart data={this.getData()} options={options} width="900" height="250"/>;
    }
});

export default class NodeInfoSection extends Component
{
    constructor(props) {
        super(props);
        this.state = {displayedSection: "cpu"};
    }

    killNode() {
        let ip = this.props.node.ip;

        if(confirm('Are you sure you want to shutdown this machine?')) {
            console.log(`Killing node with IP: ${ip}`);

            let node = Nodes.findOne({ip: ip});
            node.shutdown = true;

            Nodes.update({_id: node._id}, node);
        }
    }


    renderGraph()
    {
        return (
            <div className="graph-container">
                <Graph ip={this.props.node.ip} type_of_data={this.state.displayedSection} data={this.props.node[this.state.displayedSection].slice(-25)}/>
            </div>
        );
    }

    handleChangeSection(event)
    {
        console.log('Changing displayed section: ' + event.target.value);

        this.setState({displayedSection: event.target.value});


    }

    renderSelectionBox()
    {
        return (
            <select onChange={this.handleChangeSection.bind(this)}>
                <option selected={this.state.displayedSection === 'cpu'} value="cpu">CPU</option>
                <option selected={this.state.displayedSection === 'ram'} value="ram">RAM</option>
                <option selected={this.state.displayedSection === 'disk'} value="disk">Disk I/O</option>
                <option selected={this.state.displayedSection === 'bandwidth'} value="bandwidth">Bandwidth</option>
                <option selected={this.state.displayedSection === 'temperature'} value="temp">Temperature</option>
            </select>
        );
    }

    render() {
        console.log(this.props.node);

        let time_diff = moment(this.props.node.registered_at).fromNow();

        return (
            <div className="node-info-section">
                <h3>{this.props.node.ip}
                    <a onClick={this.killNode.bind(this)} className="btn btn-danger pull-right" href="#">Shutdown</a>
                </h3>
                <small>connected <span id="time_diff">{time_diff}</span></small>
                <br/>
                { this.renderSelectionBox() }
                <hr/>
                {this.renderGraph()}
            </div>
        )
    }
}

NodeInfoSection.propTypes = {
    node: PropTypes.object.isRequired,
};