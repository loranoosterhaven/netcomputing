import React, { Component, PropTypes } from 'react';
import ReactDOM from 'react-dom';
var LineChart = require("react-chartjs").Line;

let options = {
    scales: {
        yAxes: [{
            ticks: {
                beginAtZero:true
            }
        }]
    }
};

let data = {
    labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
    datasets: [{
        label: '# of Votes',
        data: [12, 19, 3, 5, 2, 3],
        borderWidth: 1
    }]
};

let MyComponent = React.createClass({
    getData: function() {
        let data = {
            labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
            datasets: [{
                label: '# of Votes',
                data: this.props.data,
                borderColor: [
                    'rgba(1,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(1, 206, 86, 1)',
                    'rgba(1, 192, 192, 1)',
                    'rgba(1, 102, 255, 1)',
                    'rgba(1, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        };

        return data;
    },

    render: function() {
        return <LineChart data={this.getData()} options={options}/>
    }
});

export default class NodeInfoSection extends Component
{
    render() {
        return (
            <div className="node-info-section">
                <h1>{this.props.node.ip}</h1>
                <hr/>
                <MyComponent data={this.props.node.cpu_usage}/>
            </div>
        )
    }
}

NodeInfoSection.propTypes = {
    node: PropTypes.object.isRequired,
};