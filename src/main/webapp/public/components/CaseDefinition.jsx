

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
    title: "Case Definition",
    type: "object",
    required: [  'name' 
],
    properties: {
    

name:{ type: "string", title: "Name",  	
},


    
taskDefinitions: {
            title: "Task Definitions",
            type: "array",
            items: {
                "type": "object",
                "properties": {
                 

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
            }
        },

    }
 };

}

export const caseDefinitionUISchema = {
 	

name: {  'ui:placeholder': "Name" },


    
taskDefinitions: {
 	items:{
         

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
 },

 }


	export const caseDefinitionHeaders = [
	 
	 
	 {property:"name",title:"Name" }
	      
	 ]


let caseDefinitionSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class CaseDefinitionList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'caseDefinitions'
        this.name = 'caseDefinitions'
        this.editLink = "/entities/caseDefinitions/edit/"
    }
    
    getEntityName() { return  'caseDefinitions' }

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
                <SimpleList headers={caseDefinitionHeaders} editLink={this.editLink}
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

export class EditCaseDefinition extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'caseDefinitions'
        this.onSubmit = this.onSubmit.bind(this);
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/caseDefinitions')
    }

    render() {
        return (
            <div>
                <h3> Edit CaseDefinition </h3>
                <Form schema={caseDefinitionSchema}
                	uiSchema={caseDefinitionUISchema}
                      formData={this.state.entity }
                    onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}
