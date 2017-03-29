

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
    title: "Order Item",
    type: "object",
    required: [ 
],
    properties: {
    

qty:{ type: "integer", title: "Qty",  	
},



product:{ type: "integer", title: "Product",   

 'enum': LookupService.getLookup('products').map(x => x.id   ),
 'enumNames': LookupService.getLookup('products').map(x => x.displayName)


	
},


  
customerOrder: {
      "type": "number",
    },


    
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


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class OrderItemList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'orderItems'
        this.name = 'orderItems'
        this.editLink = "/entities/orderItems/edit/"
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
                <SimpleList headers={orderItemHeaders} editLink={this.editLink}
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

export class EditOrderItem extends BaseEditComponent {

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
