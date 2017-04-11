

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
    title: "Task Instance",
    type: "object",
    required: [ 
],
    properties: {
    

taskId:{ type: "integer", title: "Task Id",  	
},



name:{ type: "string", title: "Name",  	
},



//taskDefinition:{ type: "integer", title: "Task Definition",   
//
// 'enum': LookupService.getLookup('taskDefinitions').map(x => x.id   ),
// 'enumNames': LookupService.getLookup('taskDefinitions').map(x => x.displayName)
//
//	
//},


  
caseInstance: {
      "type": "number",
    },



taskData:{ type: "string", title: "Task Data",  	
},



status:{ type: "string", title: "Status",  	
},


    
    }
 };

}

export const taskInstanceUISchema = {
 	

taskId: { 'ui:widget': "updown" , 'ui:placeholder': "Task Id" },



name: {  'ui:placeholder': "Name" },



taskDefinition: {  'ui:placeholder': "Task Definition" },


  
caseInstance: {
      "ui:widget": "hidden"
    },



taskData: {  'ui:placeholder': "Task Data" },



status: {  'ui:placeholder': "Status" },


    
 }


	export const taskInstanceHeaders = [
	 
	 
	 {property:"taskId",title:"Task Id" }
	 ,
	 
	 {property:"name",title:"Name" }
	 ,
	 
	 {property:"taskDefinition_displayName",title:"Task Definition" }
	 ,
	 
	 {property:"caseInstance_displayName",title:"Case Instance" }
	 ,
	 
	 {property:"taskData",title:"Task Data" }
	 ,
	 
	 {property:"status",title:"Status" }
	      
	 ]




let taskInstanceSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class TaskInstanceList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'taskInstances'
        this.name = 'taskInstances'
        this.baseLink = "/entities/taskInstances/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'taskInstances' }

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
                <SimpleList headers={taskInstanceHeaders} editLink={this.editLink}
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

export class EditTaskInstance extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'taskInstances'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/taskInstances')
    }

    render() {
        return (
            <div>
                <h3> Edit TaskInstance </h3>
                <Form schema={taskInstanceSchema}
                	uiSchema={taskInstanceUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewTaskInstance extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'taskInstances';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
    return (
     <div>
       <SimpleView  headers= {taskInstanceHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='TaskInstance' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



