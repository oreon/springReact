

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
    title: "Pay Pal",
    type: "object",
    required: [ 
],
    properties: {
    

accountAddress:{ type: "string", title: "Account Address",  	
},



paypalAccountNumber:{ type: "string", title: "Paypal Account Number",  	
},


    
    }
 };

}

export const payPalUISchema = {
 	

accountAddress: {  'ui:placeholder': "Account Address" },



paypalAccountNumber: {  'ui:placeholder': "Paypal Account Number" },


    
 }


	export const payPalHeaders = [
	 
	 
	 {property:"accountAddress",title:"Account Address" }
	 ,
	 
	 {property:"paypalAccountNumber",title:"Paypal Account Number" }
	      
	 ]




let payPalSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class PayPalList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'payPals'
        this.name = 'payPals'
        this.baseLink = "/entities/payPals/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'payPals' }

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
                <SimpleList headers={payPalHeaders} editLink={this.editLink}
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

export class EditPayPal extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'payPals'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/payPals')
    }

    render() {
        return (
            <div>
                <h3> Edit PayPal </h3>
                <Form schema={payPalSchema}
                	uiSchema={payPalUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewPayPal extends BaseEditComponent {

  renderExtra(record) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'payPals';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {payPalHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='PayPal' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



