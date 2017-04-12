import * as React from 'react';
import * as ReactDOM from 'react-dom';

//import { Router, Route, hashHistory, Link  } from 'react-router'
import AppBar from 'material-ui/AppBar';
import IconButton from 'material-ui/IconButton';
import NavigationClose from 'material-ui/svg-icons/navigation/close';
import FlatButton from 'material-ui/FlatButton';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';


 export const Layout = React.createClass( {
    propTypes: {
        children: React.PropTypes.element.isRequired
    },

    render: function() {
        return (
            <MuiThemeProvider>
                <div>
                    <AppBar
                        iconElementLeft={<IconButton><NavigationClose /></IconButton>}
                        iconElementRight={<FlatButton
                            containerElement={<Link to="/admin" />}
                            linkButton={true}
                            label={'Admin'} />}
                        />
                    {this.props.children}
                </div>
            </MuiThemeProvider>
        );
    }
});
