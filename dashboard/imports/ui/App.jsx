    import React, { Component } from 'react';
import Header from './Header';
import NodeInfoSection from './NodeInfoSection';
import { Nodes } from '../api/nodes';
import { createContainer } from 'meteor/react-meteor-data';


class App extends Component {
    renderNodeInfo() {
        return this.props.nodes.map((node) => (
                <NodeInfoSection node={node}/>
        ));
    }

    renderTitle() {
        if(this.props.count === 0) {
            return <h1>No connected nodes</h1>;
        }
        else if(this.props.count === 1) {
            return <h1>One connected node</h1>;
        }
        else {
            return <h1>{this.props.count} connected nodes</h1>
        }
    }

    updateMyState() {
        this.setState({trigger: Math.random() });

        console.log('Rendering...');
    }

    render() {

        let output = (
            <div id="my-app">
                <Header name="Dashboard"/>

                <div className="container" id="app-content">
                    <div className="row">
                        <div className="col-md-10">

                            <div id="title">
                                {this.renderTitle()}
                                <hr/>
                            </div>
                            {this.renderNodeInfo()}
                        </div>
                    </div>
                </div>
            </div>
        );

        setTimeout(this.updateMyState.bind(this), 100);

        return output;
    }
}


export default createContainer(() => {
    let nodes = Nodes.find({}, {
        sort: {_id: -1},
    });
    let count = nodes.count();

    return {
        nodes,
        count
    };
}, App);


