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
            this.url = this.url + "/myTasks"
        }
        
        this.doPost = this.doPost.bind( this );
        this.claimTask = this.claimTask.bind( this )
        
        this.releaseTask = this.releaseTask.bind( this )
        this.startTask = this.startTask.bind( this )

        // this.name = 'students'
        this.state = {
            records: [],
        };
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

    claimTask() {
        this.doPost( URL + 'claim?id=' + this.props.task.id, this.props.task.id )
    }

    releaseTask() {
        this.doPost( URL + 'release?id=' + this.props.task.id, this.props.task.id )
    }

    startTask() {
        this.doPost( URL + 'start?id=' + this.props.task.id, this.props.task.id )
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

    // Create new student
    render() {

        return (
            <div>
                <TaskTable records={this.state.records} mine={this.props.mine}
                claimTask = {this.claimTask}
                releaseTask = {this.releaseTask}
                startTask = {this.startTask}
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
        claimTask = {this.props.claimTask}
        releaseTask = {this.props.releaseTask}
        startTask = {this.props.startTask}
        
        />
        );

        return (

            <div>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Subject</th>
                            <th>Process Id</th>
                            <th>Task status Id</th>

                            <th></th>
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
       // this.deleteRecord = this.deleteRecord.bind( this );
       // this.editRecord = this.editRecord.bind( this );

        this.toLink = "/entities/task/" + this.props.task.id
    }


    render() {
        console.log( this.props.student )
        return (
            <tr>
                <td>{this.props.task.name}</td>
                <td>{this.props.task.subject}</td>
                <td>{this.props.task.processId}</td>
                <td>{this.props.task.id}</td>
                <td>{this.props.task.statusId}</td>
                <td>
                    {!this.props.mine &&
                        <button className="btn btn-danger btn-xs" onClick={this.props.claimTask}>Claim</button>
                    }
                    {this.props.mine &&
                        <p>
                        <button className="btn btn-danger btn-xs" onClick={this.props.releaseTask}>Release</button>
                            {this.props.statusId === "Reserved" &&
                            < button className="btn btn-primary btn-xs" onClick={this.props.rstartTask}>Start</button>
                            }
                        </p>
                    }
                </td>
                <td>
                    <Link className="btn btn-default btn-xs" to={this.toLink}>View</Link>

                </td>
            </tr>
        );
    }
}

const log = (type) => console.log.bind(console, type);

const taskSchema = {
    title: "Todo",
    type: "object",
    required: ["firstname", "lastname"],
    properties: {
        firstname: {type: "string", title: "First Name", default: "xxx"},
        lastname: {type: "string", title: "Last", default: "yyy"},
        email: {type: "string", title: "Email"}
    }
};

export class TaskView extends React.Component {

    constructor( props ) {
        super( props );
        // this.deleteRecord = this.deleteRecord.bind( this );
        // this.editRecord = this.editRecord.bind( this );
        //
        // this.doPost = this.doPost.bind( this );
        // this.claimTask = this.claimTask.bind( this )
        //
        // this.releaseTask = this.releaseTask.bind( this )

        this.url = URL + "show?id=" + this.props.match.params.id

        this.state = {
            task: {},
        };
    }

    editRecord(record) {
        let myurl = URL  + 'complete?id=' + this.props.match.params.id
        console.log(myurl)
        fetch(myurl,
            {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(record)
            })
            .then(
                //res => this.loadRecordsFromServer()

            )
            .catch(err => console.error(err))
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/customers')
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
        return (
            <div>
                <h3> {this.state.task.name}</h3>
                <Form schema={taskSchema}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }


}


//export class EditCustomer extends BaseEditComponent {
//
//    componentDidMount() {
//        if (this.props.match.params.id)
//            this.fetchSingleRecord(this.props.match.params.id);
//    }
//
//    constructor(props) {
//        super(props);
//        this.state = {entity: {}};
//        this.entityName = 'customers'
//        this.onSubmit = this.onSubmit.bind(this);
//        //this.handleChange = this.handleChange.bind(this);
//    }
//
//    onSubmit(formData) {
//        this.editRecord(formData)
//        this.props.history.push('/entities/customers')
//    }
//
//    render() {
//        return (
//            <div>
//                <h3> Edit Customer </h3>
//                <Form schema={customerSchema}
//                    uiSchema={customerUISchema}
//                      formData={this.state.entity }
//                    //onChange={log("changed")}
//                      onSubmit={({formData}) => this.onSubmit(formData) }
//                      onError={log("errors")}/>
//            </div>
//        )
//    }
//}
