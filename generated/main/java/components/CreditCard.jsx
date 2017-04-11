

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
    title: "Credit Card",
    type: "object",
    required: [ 
],
    properties: {
    
    
    }
 };

}

export const creditCardUISchema = {
 	

accountAddress: {  'ui:placeholder': "Account Address" },



ccNumber: {  'ui:placeholder': "Cc Number" },



expiry: {  'ui:placeholder': "Expiry" },


    
 }


	export const creditCardHeaders = [
	 
	 
	 {property:"accountAddress",title:"Account Address" }
	 ,
	 
	 {property:"ccNumber",title:"Cc Number" }
	 ,
	 
	 {property:"expiry",title:"Expiry" }
	      
	 ]




let creditCardSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class CreditCardList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'creditCards'
        this.name = 'creditCards'
        this.baseLink = "/entities/creditCards/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'creditCards' }

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
                <SimpleList headers={creditCardHeaders} editLink={this.editLink}
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

export class EditCreditCard extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'creditCards'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/creditCards')
    }

    render() {
        return (
            <div>
                <h3> Edit CreditCard </h3>
                <Form schema={creditCardSchema}
                	uiSchema={creditCardUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewCreditCard extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'creditCards';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {creditCardHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='CreditCard' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



