import React from 'react';
import SkyLight from 'react-skylight';
import Alert from 'react-s-alert';
var fetch = require('node-fetch');
import { Link, NavLink, Switch, Redirect} from 'react-router-dom';
//import { browserHistory } from 'react-router';

const URL = "http://localhost:9033/api/";

import Form from "react-jsonschema-form";
import BaseComponent from '../commons/BaseComponent.jsx'

const studentSchema = {
    title: "Todo",
    type: "object",
    required: ["firstname", "lastname"],
    properties: {
        firstname: {type: "string", title: "First Name", default: "A new task"},
        lastname: {type: "string", title: "Last", default: "A new task"},
        email: {type: "string", title: "Email"}

        //done: {type: "boolean", title: "Done?", default: false}
    }
};

const log = (type) => console.log.bind(console, type);

// render((
//
// ), document.getElementById("app"));


export  class Students extends BaseComponent {

    constructor(props) {
        super(props);
        this.url = URL + 'students';
        this.name = 'students'
    }

    componentDidMount() {
        this.loadRecordsFromServer();
    }

    // Create new student
    render() {

        return (
            <div>
                <StudentTable deleteRecord={this.deleteRecord} records={this.state.records}/>
                <StudentForm createRecord={this.createRecord}/>
                <Alert stack={true} timeout={2000}/>
            </div>
        );
    }
}

class StudentTable extends React.Component {
    constructor(props) {
        super(props);
        this.toLink = "/entities/students/edit/"
    }

    render() {
        let records = this.props.records.map(student =>
            <Student key={student._links.self.href} student={student} deleteRecord={this.props.deleteRecord}/>
        );

        return (

            <div>
                <Link className="btn btn-default btn-xs" to={this.toLink}>Edit</Link>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>{records}</tbody>
                </table>
            </div>);
    }
}

class Student extends React.Component {
    constructor(props) {
        super(props);
        this.deleteRecord = this.deleteRecord.bind(this);
        this.editRecord = this.editRecord.bind(this);
        this.toLink = "/entities/students/edit/" + props.student.id
    }

    deleteRecord() {
        this.props.deleteRecord(this.props.student);
    }

    editRecord() {
        this.props.editRecord(this.props.student);
    }

    render() {
        console.log(this.props.student)
        return (
            <tr>
                <td>{this.props.student.firstname}</td>
                <td>{this.props.student.lastname}</td>
                <td>{this.props.student.email}</td>
                <td>
                    <Link className="btn btn-default btn-xs" to={this.toLink}>Edit</Link>
                </td>
                <td>
                    <button className="btn btn-danger btn-xs" onClick={this.deleteRecord}>Delete</button>
                </td>
            </tr>
        );
    }
}

export class EditStudent extends React.Component {

    fetchSingleRecord(id) {
        let url = this.url + "/" + id
        console.log("Fetiching " + url)
        fetch(this.url + "/" + id,
            {credentials: 'same-origin'})
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    entity: responseData
                });
            });
    }

    editRecord(record) {
        let url = record.id ? this.url + "/" + record.id : this.url;
        let restMethod = record.id ? 'PUT' : 'POST';
        fetch(url,
            {
                method: restMethod,
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

    componentDidMount() {
        if(this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {} };
        this.url = URL + 'students';
        this.onSubmit = this.onSubmit.bind(this);
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/students')
        //var newRecord = formData
        //this.props.editRecord(formData);
        //this.refs.simpleDialog.hide();
    }

    render() {
        return (
            <div>
                <h3> Edit Student </h3>
                <Form schema={studentSchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }

}


class StudentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {firstname: '', lastname: '', email: ''};
        this.onSubmit = this.onSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }

    // handleSubmit(event) {
    //     event.preventDefault();
    //     console.log("Firstname: " + this.state.firstname);
    //     var newRecord = formData
    //     this.props.createRecord(newRecord);
    //     this.refs.simpleDialog.hide();
    // }

    onSubmit(formData) {
        var newRecord = formData
        this.props.createRecord(newRecord);
        this.refs.simpleDialog.hide();
    }


    render() {
        return (
            <div>
                <div className="col-md-2">
                    <button className="btn btn-primary" onClick={() => this.refs.simpleDialog.show()}>New student
                    </button>
                </div>


                <SkyLight hideOnOverlayClicked ref="simpleDialog">

                    <Form schema={studentSchema}
                          onChange={log("changed")}
                          onSubmit={({formData}) => this.onSubmit(formData) }
                          onError={log("errors")}/>

                </SkyLight>
            </div>
        )
    }

    // render() {
    //     return (
    //         <div>
    //             <SkyLight hideOnOverlayClicked ref="simpleDialog">
    //                 <div className="panel panel-default">
    //                     <div className="panel-heading">Create student</div>
    //                     <div className="panel-body">
    //                         <form className="form">
    //                             <div className="col-md-4">
    //                                 <input type="text" placeholder="Firstname" className="form-control" name="firstname"
    //                                        onChange={this.handleChange}/>
    //                             </div>
    //                             <div className="col-md-4">
    //                                 <input type="text" placeholder="Lastname" className="form-control" name="lastname"
    //                                        onChange={this.handleChange}/>
    //                             </div>
    //                             <div className="col-md-4">
    //                                 <input type="text" placeholder="Email" className="form-control" name="email"
    //                                        onChange={this.handleChange}/>
    //                             </div>
    //                             <div className="col-md-2">
    //                                 <button className="btn btn-primary" onClick={this.handleSubmit}>Save</button>
    //                             </div>
    //                         </form>
    //                     </div>
    //                 </div>
    //             </SkyLight>
    //             <div className="col-md-2">
    //                 <button className="btn btn-primary" onClick={() => this.refs.simpleDialog.show()}>New student
    //                 </button>
    //             </div>
    //         </div>
    //     );
    // }
}
