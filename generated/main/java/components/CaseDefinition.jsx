

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
    title: "Case Definition",
    type: "object",
    required: [ 
],
    properties: {
    
    
    }
 };

}

export const caseDefinitionUISchema = {
 	

name: {  'ui:placeholder': "Name" },



closable: {  'ui:placeholder': "Closable" },


    
taskDefinitions: {
 	items:{
         

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



regularEx: {  'ui:placeholder': "Regular Ex" },

 
         
     }
 },

     }
 },

 }


	export const caseDefinitionHeaders = [
	 
	 
	 {property:"name",title:"Name" }
	 ,
	 
	 {property:"closable",title:"Closable" }
	      
	 ]



	import { TaskDefinitionList} from './TaskDefinition.jsx';


let caseDefinitionSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class CaseDefinitionList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'caseDefinitions'
        this.name = 'caseDefinitions'
        this.baseLink = "/entities/caseDefinitions/"
        this.editLink = this.baseLink + "edit/"
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
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewCaseDefinition extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'caseDefinitions';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {caseDefinitionHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='CaseDefinition' /> 
       
       <Tabs>
       	  <Tab label="TaskDefinition" >
          <TaskDefinitionList records={record.taskDefinitions} 
          nested={true}  
          container={'caseDefinition_displayName'}
          containerId={record.id}
           prev={this.props.location?this.props.location.pathName:null }
          
           />
           </Tab>
		  
         </Tabs>
      </div>
    )	

  }
}



