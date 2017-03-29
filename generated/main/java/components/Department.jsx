

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
    title: "Department",
    type: "object",
    required: [  'name' 
],
    properties: {
    

employees:{ type: "integer", title: "Employees",   

 'enum': LookupService.getLookup('employees').map(x => x.id   ),
 'enumNames': LookupService.getLookup('employees').map(x => x.displayName)


	
},



name:{ type: "string", title: "Name",  	
},


    
    }
 };

}

export const departmentUISchema = {
 	

employees: {  'ui:placeholder': "Employees" },



name: {  'ui:placeholder': "Name" },


    
 }


	export const departmentHeaders = [
	 
	 
	 {property:"name",title:"Name" }
	      
	 ]


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class DepartmentList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'departments'
        this.name = 'departments'
        this.editLink = "/entities/departments/edit/"
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
                <SimpleList headers={departmentHeaders} editLink={this.editLink}
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

export class EditDepartment extends BaseEditComponent {

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
        this.props.history.push('/entities/departments')
    }

    render() {
        return (
            <div>
                <h3> Edit Department </h3>
                <Form schema={departmentSchema}
                	uiSchema={departmentUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}
