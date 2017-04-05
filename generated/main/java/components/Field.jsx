

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
    title: "Field",
    type: "object",
    required: [  'name' 
],
    properties: {
    

name:{ type: "string", title: "Name",  	
},



type:{ type: "string", title: "Type",   
'enum' : [
'','0' ,'1' ,'2' ,'3'   
],
'enumNames' : [
'Select','string' ,'number' ,'date' ,'boolean'   
]
	
},


  
taskDefinition: {
      "type": "number",
    },



required:{ type: "boolean", title: "Required",  	
},


    
    }
 };

}

export const fieldUISchema = {
 	

name: {  'ui:placeholder': "Name" },



type: {  'ui:placeholder': "Type" },


  
taskDefinition: {
      "ui:widget": "hidden"
    },



required: {  'ui:placeholder': "Required" },


    
 }


	export const fieldHeaders = [
	 
	 
	 {property:"name",title:"Name" }
	 ,
	 
	 {property:"type",title:"Type" }
	 ,
	 
	 {property:"taskDefinition_displayName",title:"Task Definition" }
	 ,
	 
	 {property:"required",title:"Required" }
	      
	 ]


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class FieldList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'fields'
        this.name = 'fields'
        this.editLink = "/entities/fields/edit/"
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
                <SimpleList headers={fieldHeaders} editLink={this.editLink}
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

export class EditField extends BaseEditComponent {

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
        this.props.history.push('/entities/fields')
    }

    render() {
        return (
            <div>
                <h3> Edit Field </h3>
                <Form schema={fieldSchema}
                	uiSchema={fieldUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}
