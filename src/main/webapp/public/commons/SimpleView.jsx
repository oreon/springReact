import React from 'react';
//import SkyLight from 'react-skylight';
//import Alert from 'react-s-alert';
//var fetch = require('node-fetch');
import {Link, NavLink, Switch, Redirect} from 'react-router-dom';
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

import RaisedButton from 'material-ui/FlatButton';
import FontIcon from 'material-ui/FontIcon';
import ActionAndroid from 'material-ui/svg-icons/action/android';

import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';

export class SimpleView extends React.Component{
    render() {

        let current = this.props.record;


        if ( !current ) {
            return <p> No Record Selected </p>
        } else {
            
            
            let vals = this.props.headers.map
            ( x => { return <tr key={x.property}> <td><b> {x.property} </b> </td> <td> {current[x.property]}</td></tr> });

            return (

                <Card>
                    <CardHeader title={current['displayName']}>  </CardHeader>
                    <CardActions>
                        <RaisedButton onClick={() => hashHistory.push( '/admin/' + this.props.entityName + 'Edit/' + current.id )}>Edit</RaisedButton>
                        <RaisedButton onClick={() => this.props.data.gotoList()}>Cancel</RaisedButton>
                    </CardActions>
                    <CardText>
                       <table className="table table-striped">
                        <tbody>
                        {vals}
                        </tbody>
                       </table>
                    </CardText>
                    {this.props.renderExtra( current )}
                </Card>
            )
        }
        //return null;
    }
}