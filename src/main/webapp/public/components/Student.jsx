import React from 'react';
import SkyLight from 'react-skylight';
import Alert from 'react-s-alert';
var fetch = require('node-fetch');
import {Link, NavLink, Switch, Redirect} from 'react-router-dom';
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';



import Form from "react-jsonschema-form";
import {BaseComponent, BaseEditComponent} from '../commons/BaseComponent.jsx'
import Griddle, {plugins} from 'griddle-react';
import {SimpleList} from '../commons/SimpleList.jsx'

const log = (type) => console.log.bind(console, type);

const studentSchema = {title: "Todo",type: "object",
    properties: {
        firstname: {type: "string", title: "First Name", default: "A new task"},
        lastname: {type: "string", title: "Last", default: "A new task"},
        email: {type: "string", title: "Email"}
    }
};

export const studentHeaders = [

    {property: "firstname", title: "first Name"},
    {property: "lastname", title: "Last Name"},
    {property: "email", title: "email"},
    {property: "", title: "Edit"}

]


export class StudentList extends BaseComponent {

    constructor(props) {
        super(props);
        this.url = this.URL + 'students';
        this.name = 'students'
        this.editLink = "/entities/students/edit/"
    }

    renderExtra(record) {
        return null
    }
    
    getEntityName() { return  'students' }



    handleRowSelection(selectedRows) {
        this.props.push(<Link className="btn btn-default btn-xs" to={this.toLink}>Edit</Link>)
        console.log('selectedRows: ' + selectedRows);
    }


    render() {
        let records = this.props.nested ? this.props.records : this.state.records

        if (!records)
            return (<p>Loading...</p>)

        return (
            <div>
                <SimpleList headers={studentHeaders} editLink={this.editLink}
                            renderExtra={this.renderExtra}
                            records={ records } nested={this.props.nested}
                            container={this.props.container} uneditable={this.props.uneditable}
                            containerId={this.props.containerId}
                            prev={this.props.prev}
                />
            </div>
        );
    }
}

export class EditStudent extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.url = URL + 'students';
        this.onSubmit = this.onSubmit.bind(this);
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/students')
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
                          //onChange={log("changed")}
                          onSubmit={({formData}) => this.onSubmit(formData) }
                          onError={log("errors")}/>

                </SkyLight>
            </div>
        )
    }


}




let headers = ["firstName", "lastName", "email"]

class StudentTable extends React.Component {
    constructor(props) {
        super(props);
        this.toLink = "/entities/students/edit/"
    }

    render() {
        let records = this.props.records.map(student =>
            <Student key={student._links.self.href} student={student} deleteRecord={this.props.deleteRecord}/>
        );

        let titles = headers.map(x => `<th> ${x}  </th>`)

        return (

            <div>
                <Link className="btn btn-primary" to={this.toLink}>New Student</Link>
                <table className="table table-striped">
                    <thead>
                    <tr>headers.map( (x,i) =>
                        <th key={x}>{i} </th>
                        )

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

        let rowVals = headers.map(x => `<td> ${this.props.student[x]} </td>`)

        //console.log(this.props.student)
        return (
            <tr>
                {rowVals}
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


