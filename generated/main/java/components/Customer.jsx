

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
    title: "Customer",
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

	
},



firstName:{ type: "string", title: "First Name",  	
},



lastName:{ type: "string", title: "Last Name",  	
},



caseInstances:{ type: "integer", title: "Case Instances",   

 'enum': LookupService.getLookup('caseInstances').map(x => x.id   ),
 'enumNames': LookupService.getLookup('caseInstances').map(x => x.displayName)


	
},


    
    }
 };

}

export const customerUISchema = {
 	

gender: {  'ui:placeholder': "Gender" },



dob: {  'ui:placeholder': "Dob" },



address: {  'ui:placeholder': "Address" },



firstName: {  'ui:placeholder': "First Name" },



lastName: {  'ui:placeholder': "Last Name" },



caseInstances: {  'ui:placeholder': "Case Instances" },


    
 }


	export const customerHeaders = [
	 
	 
	 {property:"gender",title:"Gender" }
	 ,
	 
	 {property:"dob",title:"Dob" }
	 ,
	 
	 {property:"address_displayName",title:"Address" }
	 ,
	 
	 {property:"firstName",title:"First Name" }
	 ,
	 
	 {property:"lastName",title:"Last Name" }
	      
	 ]




let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class CustomerList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'customers'
        this.name = 'customers'
        this.baseLink = "/entities/customers/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'customers' }

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
                <SimpleList headers={customerHeaders} editLink={this.editLink}
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

export class EditCustomer extends BaseEditComponent {

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
        this.props.history.push('/entities/customers')
    }

    render() {
        return (
            <div>
                <h3> Edit Customer </h3>
                <Form schema={customerSchema}
                	uiSchema={customerUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewCustomer extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'customers';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
    return (
     <div>
       <SimpleView  headers= {customerHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='Customer' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



