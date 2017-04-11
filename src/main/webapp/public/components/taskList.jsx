import React from 'react';
import SkyLight from 'react-skylight';
import Alert from 'react-s-alert';
var fetch = require( 'node-fetch' );
import Form from "react-jsonschema-form";
import { Link, NavLink, Switch, Redirect } from 'react-router-dom';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn } from 'material-ui/Table';
//import {URL} from '../commons/BaseComponent'
const URL = "http://localhost:9033/api/task/";

export class TaskList extends React.Component {

    constructor( props ) {
        super( props );
        this.url = URL;
        if ( props.mine ) {
            this.url = this.url + "myTasks"
        }

        //this.doPost = this.doPost.bind( this );
        // this.name = 'students'
        this.state = {
            records: [],
        };
    }


    componentDidMount() {
        console.log( "called component" )
        this.loadRecordsFromServer();
    }

    loadRecordsFromServer() {
        fetch( this.url,
            { credentials: 'same-origin' })
            .then(( response ) => { return response.json() })
            .then(( responseData ) => {
                this.setState( {
                    records: responseData,
                });
            });
    }

    refresh() {
        this.loadRecordsFromServer()
    }

    // Create new student
    render() {

        return (
            <div>
                <TaskTable records={this.state.records} mine={this.props.mine}
                    refresh={this.refresh}
                    />

            </div>
        );
    }
}


class TaskTable extends React.Component {
    constructor( props ) {
        super( props );
        this.toLink = "/entities/students/edit/"
    }



    render() {

        if ( !this.props.records ) return;

        let records = this.props.records.map( task =>
            <Task key={task.id} task={task} mine={this.props.mine}
                refresh={this.props.loadRecordsFromServer}
                />
        );

        return (

            <div>
                <button className="btn btn-danger btn-xs" onClick={this.props.refresh}>Press</button>

                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Subject</th>
                            <th>Process Id</th>
                            <th>Task status Id</th>
                            <th> Status </th>
                            <th> Expiration </th>
                            <th> </th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>{records}</tbody>
                </table>
            </div> );
    }
}

class Task extends React.Component {
    constructor( props ) {
        super( props );
        //        this.doPost = this.doPost.bind( this );
        this.claimTask = this.claimTask.bind( this )
        this.releaseTask = this.releaseTask.bind( this )
        this.startTask = this.startTask.bind( this )
        this.close = this.close.bind(this)
        this.id = this.props.task.id;
        this.procId = this.props.task.processInstanceId;

        // this.deleteRecord = this.deleteRecord.bind( this );
        // this.editRecord = this.editRecord.bind( this );

        this.toLink = "/entities/task/" + this.props.task.id
    }

    claimTask( id ) {
        this.doPost( URL + 'claim?id=' + this.id, this.id )
        this.refresh()
    }

    refresh() {
        window.location.reload();
    }

    releaseTask( id ) {
        this.doPost( URL + 'release?id=' + this.id, this.id )
        this.refresh()
    }

    startTask( id ) {
        this.doPost( URL + 'start?id=' + this.id, this.id )
        this.refresh()
    }
    
    close( id ) {
        this.doPost( URL + 'close?id=' + this.procId, this.procId )
       this.refresh()
    }

    doPost( url, body ) {
        fetch( url,
            {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                //               body: body
            })
            .then(
            res => console.log( res )
            //res => this.loadRecordsFromServer()
            )
            .catch( err => console.error( err ) )
    }



    render() {
        //console.log( this.props.student )
        let id = this.props.task.id;
        return (
            <tr>
                <td>{this.props.task.name}</td>
                <td>{this.props.task.subject}</td>
                <td>{this.props.task.processId}</td>
                <td>{this.props.task.id}-{this.props.task.processInstanceId}</td>
                <td>{this.props.task.statusId}</td>
                <td>{this.props.task.expirationTime}</td>
                
                
                <td>
                    
                    {!this.props.mine &&
                        <button className="btn btn-danger btn-xs" onClick={this.claimTask}>Claim</button>
                       
                    }
                    {this.props.mine &&
                        <p>
                            <button className="btn btn-danger btn-xs" onClick={this.releaseTask}>Release</button>
                            {this.props.task.statusId === "Reserved" &&
                                <button className="btn btn-primary btn-xs" onClick={this.startTask}>Start</button>
                            }
                        </p>
                    }
                </td>
                <td>
                    <div>
                    
                    {this.props.task.statusId === "InProgress" &&
                    <Link className="btn btn-default btn-xs" to={this.toLink}>View</Link>
                    }
                    <button className="btn btn-danger btn-xs" onClick={this.close}>Close</button>
                    </div>

                </td>
            </tr>
        );
    }
}

const log = ( type ) => console.log.bind( console, type );

//const taskSchema = {
//    title: "Todo",
//    type: "object",
//    required: ["firstname", "lastname"],
//    properties: {
//        firstname: { type: "string", title: "First Name", default: "xxx" },
//        lastname: { type: "string", title: "Last", default: "yyy" },
//        email: { type: "string", title: "Email" }
//    }
//};

const taskSchema = 
{title: 'lender_assesment',type: 'object',properties: {'recommended_risk-rating': {  title: 'recommended_risk-rating',type: 'number', required:true}, 
    'risk-rating': {  title: 'risk-rating',type: 'number', required:true}, 
    'color': {  title: 'color',type: 'string', required:true}}}

//
//
//const taskSchema = {
//    title: 'Todo', type: 'object', properties: {
//        color: { title: 'color', type: 'string', required: true }, riskrating: { title: 'riskrating', type: 'string', required: true }
//    }
//}


export class TaskView extends React.Component {

    constructor( props ) {
        super( props );
        // this.deleteRecord = this.deleteRecord.bind( this );
        // this.editRecord = this.editRecord.bind( this );
        //


        this.url = URL + "show?id=" + this.props.match.params.id

        this.state = {
            task: {},
        };
    }



    editRecord( record ) {
        let myurl = URL + 'complete?id=' + this.props.match.params.id
        console.log( myurl )
        fetch( myurl,
            {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify( record )
            })
            .then(
            //res => this.loadRecordsFromServer()

            )
            .catch( err => console.error( err ) )
    }

    onSubmit( formData ) {
        this.editRecord( formData )
        this.props.history.push( '/' )
        window.location.reload();
    }

    componentDidMount() {
        this.loadTask();
    }

    loadTask() {
        fetch( this.url,
            { credentials: 'same-origin' })
            .then(( response ) => { return response.json() })
            .then(( responseData ) => {
                this.setState( {
                    task: responseData,
                });
            });
    }



    render() {
        
      if ( !this.state.task.name )
            return null;
      
          console.log(this.state.task.schema)
        
        let schema = JSON.parse(this.state.task.schema)

        return (
            <div>
                <h3> {this.state.task.name} !!!</h3>
                <Form schema= {schema}
                    onSubmit={( {formData}) => this.onSubmit( formData )}
                    onError={log( "errors" )} />
            </div>
        )
    }


}


