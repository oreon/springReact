import React from 'react';
//import SkyLight from 'react-skylight';
//import Alert from 'react-s-alert';
//var fetch = require('node-fetch');
import {Link, NavLink, Switch, Redirect} from 'react-router-dom';
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

import RaisedButton from 'material-ui/FlatButton';
import FontIcon from 'material-ui/FontIcon';
import ActionAndroid from 'material-ui/svg-icons/action/android';

export class SimpleList extends React.Component {

    //let baseLink = "";

    constructor(props) {
        super(props);
        this.baseLink =  this.props.baseLink
    }

    renderRow(record, headers) {

        let cells = headers.map(x => {
            return <TableRowColumn key={x.property}> {record[x.property]} </TableRowColumn>
        });
        let prnt = ( this.props.parent ) ? "/" + this.props.parent : ""
        let editLink = this.props.editLink + record.id + prnt;
        let viewLink = this.props.baseLink + "view/" + record.id

        return (
            <TableRow key={record.id}>
                {cells}
                {( !this.props.uneditable ) &&
                <TableRowColumn key='edit'>
                    <Link to={{pathname: editLink, query: {prev: this.props.prev}}}>Edit</Link>
                    &nbsp;
                    <Link to={{pathname: viewLink, query: {prev: this.props.prev}}}>View</Link>
                </TableRowColumn>
                }
            </TableRow>
        )
    }

    renderExtra(record) {
        return this.props.renderExtra(record)
    }

    render() {

        let styles = this.getStyles();

        let headers = this.props.headers
            .filter(x => {
                return x.property != this.props.container
            })

        let ths = headers.map(x => {
            return <TableHeaderColumn key={x.property}>{x.title} </TableHeaderColumn>
        });

        let mainrows = this.props.records.map(customer => this.renderRow(customer, headers));

        let extras = this.props.records.map(customer => this.renderExtra(customer));

        let rows = _.zip(mainrows, extras)

        let addLink = this.props.editLink;

        // if (this.props.containerId) {
        //     addLink = this.props.editLink
        // }

        return (

            <div>

                {this.props.renderExtra({})}

                {( !this.props.uneditable ) &&
                <Link className="btn btn-default btn-xs" to={addLink}>Create New</Link>
                }

                <br />

                {( this.props.records.length > 0 ) &&
                <Table>
                    <TableHeader displaySelectAll={false} style={styles.header}>
                        <TableRow key="this.props.headers">
                            {ths}
                        </TableRow>
                    </TableHeader>
                    <TableBody showRowHover={true} displayRowCheckbox={false} stripedRows={this.props.nested}>
                        {rows}
                    </TableBody>
                </Table>
                }
                {( this.props.records.length == 0 ) && <p> No records </p>}

            </div>
        )
    }

    getStyles() {
        return {
            root: {
                // paddingTop: Spacing.desktopKeylineIncrement
            },
            header: {
                fontWeight: 'bolder',
                fontSize: 15,
                background: this.props.nested ? 'lightblue' : '#ffe'
            },
            headerRight: {
                fontWeight: 'normal',
                fontSize: 13,
                // paddingLeft: scrollBarVisible ? 24 - (window.scrollbarWidth / 2) : 24
            },
            row: {
                borderBottom: '0px'
            }
        }
    }
}

