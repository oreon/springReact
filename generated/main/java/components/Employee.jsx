

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


export function createSchema(){ 
 
 return {
    title: "Employee",
    type: "object",
    required: [ 
],
    properties: {
    

gender:{ type: "string", title: "Gender",   
'enum' : [
'','0' ,'1'   
],
'enumNames' : [
'Select','MALE' ,'FEMALE'   
]
	
},



dob:{ type: "string", title: "Dob",   "format": "date"	
},



address:{ type: "integer", title: "Address",   

 'enum': LookupService.getLookup('addresses').map(x => x.id   ),
 'enumNames': LookupService.getLookup('addresses').map(x => x.displayName)


	
},



department:{ type: "integer", title: "Department",   

 'enum': LookupService.getLookup('departments').map(x => x.id   ),
 'enumNames': LookupService.getLookup('departments').map(x => x.displayName)


	
},



firstName:{ type: "string", title: "First Name",  	
},



lastName:{ type: "string", title: "Last Name",  	
},



code:{ type: "string", title: "Code",  	
},


    
    }
 };

}

export const employeeUISchema = {
 	

gender: {  'ui:placeholder': "Gender" },



dob: {  'ui:placeholder': "Dob" },



address: {  'ui:placeholder': "Address" },



department: {  'ui:placeholder': "Department" },



firstName: {  'ui:placeholder': "First Name" },



lastName: {  'ui:placeholder': "Last Name" },



code: {  'ui:placeholder': "Code" },


    
 }


	export const employeeHeaders = [
	 
	 
	 {property:"gender",title:"Gender" }
	 ,
	 
	 {property:"dob",title:"Dob" }
	 ,
	 
	 {property:"address_displayName",title:"Address" }
	 ,
	 
	 {property:"department_displayName",title:"Department" }
	 ,
	 
	 {property:"firstName",title:"First Name" }
	 ,
	 
	 {property:"lastName",title:"Last Name" }
	 ,
	 
	 {property:"code",title:"Code" }
	      
	 ]


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class EmployeeList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'employees'
        this.name = 'employees'
        this.editLink = "/entities/employees/edit/"
    }

    renderExtra(record) {
        return null
    }


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
                <SimpleList headers={employeeHeaders} editLink={this.editLink}
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

export class EditEmployee extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'customers'
        this.onSubmit = this.onSubmit.bind(this);
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/employees')
    }

    render() {
        return (
            <div>
                <h3> Edit Employee </h3>
                <Form schema={employeeSchema}
                	uiSchema={employeeUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}
