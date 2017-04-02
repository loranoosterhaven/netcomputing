import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import Header from './Header';
import NodeInfoSection from './NodeInfoSection';
import Node from '../Node';
import { Nodes } from '../api/nodes';
import { createContainer } from 'meteor/react-meteor-data';


class App extends Component {
    renderNodeInfo() {
        return this.props.nodes.map((node) => (
            <div className="container">
                <NodeInfoSection node={node}/>
            </div>
        ));
    }

    render() {
        return (
            <div id="my-app">
                <Header name="Dashboard"/>

                <div className="container" id="app-content">
                    <h2>{this.props.count} Connected Nodes</h2>
                    <hr/>
                    {this.renderNodeInfo()}
                </div>
            </div>
        );
    }
}

export default createContainer(() => {
    return {
        nodes: Nodes.find({}).fetch(),
        count: Nodes.find({}).count()
    };
}, App);
