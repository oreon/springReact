

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
    title: "Payment Method",
    type: "object",
    required: [ 
],
    properties: {
    
    
    }
 };

}

export const paymentMethodUISchema = {
 	

accountAddress: {  'ui:placeholder': "Account Address" },


    
 }


	export const paymentMethodHeaders = [
	 
	 
	 {property:"accountAddress",title:"Account Address" }
	      
	 ]




let paymentMethodSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class PaymentMethodList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'paymentMethods'
        this.name = 'paymentMethods'
        this.baseLink = "/entities/paymentMethods/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'paymentMethods' }

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
                <SimpleList headers={paymentMethodHeaders} editLink={this.editLink}
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

export class EditPaymentMethod extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'paymentMethods'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/paymentMethods')
    }

    render() {
        return (
            <div>
                <h3> Edit PaymentMethod </h3>
                <Form schema={paymentMethodSchema}
                	uiSchema={paymentMethodUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewPaymentMethod extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'paymentMethods';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {paymentMethodHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='PaymentMethod' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



