

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
    title: "Task Definition",
    type: "object",
    required: [  'name' 
],
    properties: {
    

name:{ type: "string", title: "Name",  	
},


  
caseDefinition: {
      "type": "number",
    },


    
fields: {
            title: "Fields",
            type: "array",
            required: [ 'name' 
],
            items: {
                "type": "object",
                "properties": {
                 

name:{ type: "string", title: "Name",  	
},



type:{ type: "string", title: "Type",  	
},


  
taskDefinition: {
      "type": "number",
    },

 
                 
                }
            }
        },

    }
 };

}

export const taskDefinitionUISchema = {
 	

name: {  'ui:placeholder': "Name" },


  
caseDefinition: {
      "ui:widget": "hidden"
    },


    
fields: {
 	items:{
         

name: {  'ui:placeholder': "Name" },



type: {  'ui:placeholder': "Type" },


  
taskDefinition: {
      "ui:widget": "hidden"
    },

 
         
     }
 },

 }


	export const taskDefinitionHeaders = [
	 
	 
	 {property:"name",title:"Name" }
	 ,
	 
	 {property:"caseDefinition_displayName",title:"Case Definition" }
	      
	 ]


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class TaskDefinitionList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'taskDefinitions'
        this.name = 'taskDefinitions'
        this.editLink = "/entities/taskDefinitions/edit/"
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
                <SimpleList headers={taskDefinitionHeaders} editLink={this.editLink}
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

export class EditTaskDefinition extends BaseEditComponent {

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
        this.props.history.push('/entities/taskDefinitions')
    }

    render() {
        return (
            <div>
                <h3> Edit TaskDefinition </h3>
                <Form schema={taskDefinitionSchema}
                	uiSchema={taskDefinitionUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}
