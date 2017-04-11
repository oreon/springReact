

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
    title: "Order Item",
    type: "object",
    required: [ 
],
    properties: {
    
    
    }
 };

}

export const orderItemUISchema = {
 	

qty: { 'ui:widget': "updown" , 'ui:placeholder': "Qty" },



product: {  'ui:placeholder': "Product" },


  
customerOrder: {
      "ui:widget": "hidden"
    },


    
 }


	export const orderItemHeaders = [
	 
	 
	 {property:"qty",title:"Qty" }
	 ,
	 
	 {property:"product_displayName",title:"Product" }
	 ,
	 
	 {property:"customerOrder_displayName",title:"Customer Order" }
	      
	 ]




let orderItemSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class OrderItemList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'orderItems'
        this.name = 'orderItems'
        this.baseLink = "/entities/orderItems/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'orderItems' }

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
                <SimpleList headers={orderItemHeaders} editLink={this.editLink}
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

export class EditOrderItem extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'orderItems'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/orderItems')
    }

    render() {
        return (
            <div>
                <h3> Edit OrderItem </h3>
                <Form schema={orderItemSchema}
                	uiSchema={orderItemUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewOrderItem extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'orderItems';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {orderItemHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='OrderItem' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



