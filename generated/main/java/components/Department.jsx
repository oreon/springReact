

import React from 'react';
import SkyLight from 'react-skylight';
import Alert from 'react-s-alert';
var fetch = require('node-fetch');
import {Link, NavLink, Switch, Redirect} from 'react-router-dom';
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';


import Form from "react-jsonschema-form";
import {BaseComponent, BaseEditComponent} from '../commons/BaseComponent.jsx'
import Griddle, {plugins} from 'griddle-react';

import { Layout } from '../commons/Layout.jsx'
import { SimpleView } from '../commons/SimpleView.jsx'
import {Tabs, Tab} from 'material-ui/Tabs';
import {SimpleList} from '../commons/SimpleList.jsx'


export function createSchema(){ 
 
 return {
    title: "Department",
    type: "object",
    required: [ 
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



	import { EmployeeList} from './Employee.jsx';


let departmentSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class DepartmentList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'departments'
        this.name = 'departments'
        this.baseLink = "/entities/departments/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'departments' }

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
                            baseLink = {this.baseLink}
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
        this.entityName = 'departments'
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


export class ViewDepartment extends BaseEditComponent {

  renderExtra(record) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'departments';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {departmentHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='Department' /> 
       
       <Tabs>
       	  <Tab label="Employee" >
          <EmployeeList records={record.employees} 
          nested={true}  
          container={'department_displayName'}
          containerId={record.id}
           prev={this.props.location?this.props.location.pathName:null }
          
           />
           </Tab>
		  
         </Tabs>
      </div>
    )	

  }
}



