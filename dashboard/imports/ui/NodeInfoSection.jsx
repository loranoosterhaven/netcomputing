import React, { Component, PropTypes } from 'react';
import ReactDOM from 'react-dom';


export default class NodeInfoSection extends Component
{
    render() {
        return (
            <div className="panel panel-default">
                <div className="panel-heading">{this.props.node.ip}</div>
                <div className="panel-body">
                    A graph?
                </div>
            </div>
        )
    }
}

NodeInfoSection.propTypes = {
    node: PropTypes.object.isRequired,
};