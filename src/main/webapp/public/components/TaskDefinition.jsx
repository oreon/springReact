

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
    title: "Task Definition",
    type: "object",
    required: [ 
],
    properties: {
    

name:{ type: "string", title: "Name",  	
},


  
caseDefinition: {
      "type": "number",
    },



formSchema:{ type: "string", title: "Form Schema",  	
},


    
fields: {
            title: "Fields",
            type: "array",
            required: [
],
            items: {
                "type": "object",
                "properties": {
                 

name:{ type: "string", title: "Name",  	
},



type:{ type: "string", title: "Type",   
'enum' : [
'','0' ,'1' ,'2' ,'3' ,'4'   
],
'enumNames' : [
'Select','string' ,'number' ,'date' ,'bool' ,'textBlob'   
]
	
},


  
taskDefinition: {
      "type": "number",
    },



required:{ type: "boolean", title: "Required",  	
},



min:{ type: "integer", title: "Min",  	
},



max:{ type: "integer", title: "Max",  	
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



formSchema: {  'ui:placeholder': "Form Schema" },


    
fields: {
 	items:{
         

name: {  'ui:placeholder': "Name" },



type: {  'ui:placeholder': "Type" },


  
taskDefinition: {
      "ui:widget": "hidden"
    },



required: {  'ui:placeholder': "Required" },



min: { 'ui:widget': "updown" , 'ui:placeholder': "Min" },



max: { 'ui:widget': "updown" , 'ui:placeholder': "Max" },

 
         
     }
 },

 }


	export const taskDefinitionHeaders = [
	 
	 
	 {property:"name",title:"Name" }
	 ,
	 
	 {property:"caseDefinition_displayName",title:"Case Definition" }
	 ,
	 
	 {property:"formSchema",title:"Form Schema" }
	      
	 ]



	import { FieldList} from './Field.jsx';


let taskDefinitionSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class TaskDefinitionList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'taskDefinitions'
        this.name = 'taskDefinitions'
        this.baseLink = "/entities/taskDefinitions/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'taskDefinitions' }

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

export class EditTaskDefinition extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'taskDefinitions'
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


export class ViewTaskDefinition extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'taskDefinitions';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
    return (
     <div>
       <SimpleView  headers= {taskDefinitionHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='TaskDefinition' /> 
       
       <Tabs>
       	  <Tab label="Field" >
          <FieldList records={record.fields} 
          nested={true}  
          container={'taskDefinition_displayName'}
          containerId={record.id}
           prev={this.props.location?this.props.location.pathName:null }
           uneditable={true} 
           />
           </Tab>
		  
         </Tabs>
      </div>
    )	

  }
}



