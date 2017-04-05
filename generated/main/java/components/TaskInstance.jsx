

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
    title: "Task Instance",
    type: "object",
    required: [ 
],
    properties: {
    

taskId:{ type: "integer", title: "Task Id",  	
},



name:{ type: "string", title: "Name",  	
},



taskDefinition:{ type: "integer", title: "Task Definition",   

 'enum': LookupService.getLookup('taskDefinitions').map(x => x.id   ),
 'enumNames': LookupService.getLookup('taskDefinitions').map(x => x.displayName)


	
},


  
caseInstance: {
      "type": "number",
    },



taskData:{ type: "string", title: "Task Data",  	
},



string:{ type: "string", title: "String",   

 'enum': LookupService.getLookup('statuses').map(x => x.id   ),
 'enumNames': LookupService.getLookup('statuses').map(x => x.displayName)


	
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



string: {  'ui:placeholder': "String" },


    
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
	 
	 {property:"string",title:"String" }
	      
	 ]


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class TaskInstanceList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'taskInstances'
        this.name = 'taskInstances'
        this.editLink = "/entities/taskInstances/edit/"
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
                <SimpleList headers={taskInstanceHeaders} editLink={this.editLink}
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

export class EditTaskInstance extends BaseEditComponent {

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
