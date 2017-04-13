

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
    title: "Customer Order",
    type: "object",
    required: [ 
],
    properties: {
    

notes:{ type: "string", title: "Notes",  	
},



customer:{ type: "integer", title: "Customer",   

 'enum': LookupService.getLookup('customers').map(x => x.id   ),
 'enumNames': LookupService.getLookup('customers').map(x => x.displayName)


	
},



shipDate:{ type: "string", title: "Ship Date",   "format": "date"	
},



paymentMethod:{ type: "integer", title: "Payment Method",   

 'enum': LookupService.getLookup('paymentMethods').map(x => x.id   ),
 'enumNames': LookupService.getLookup('paymentMethods').map(x => x.displayName)


	
},


    
orderItems: {
            title: "Order Items",
            type: "array",
            required: [
],
            items: {
                "type": "object",
                "properties": {
                 

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
            }
        },

    }
 };

}

export const customerOrderUISchema = {
 	

notes: { 'ui:widget': "textarea" , 'ui:placeholder': "Notes" },



customer: {  'ui:placeholder': "Customer" },



shipDate: {  'ui:placeholder': "Ship Date" },



paymentMethod: {  'ui:placeholder': "Payment Method" },


    
orderItems: {
 	items:{
         

qty: { 'ui:widget': "updown" , 'ui:placeholder': "Qty" },



product: {  'ui:placeholder': "Product" },


  
customerOrder: {
      "ui:widget": "hidden"
    },

 
         
     }
 },

 }


	export const customerOrderHeaders = [
	 
	 
	 {property:"notes",title:"Notes" }
	 ,
	 
	 {property:"customer_displayName",title:"Customer" }
	 ,
	 
	 {property:"shipDate",title:"Ship Date" }
	 ,
	 
	 {property:"paymentMethod_displayName",title:"Payment Method" }
	      
	 ]



	import { OrderItemList} from './OrderItem.jsx';


let customerOrderSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class CustomerOrderList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'customerOrders'
        this.name = 'customerOrders'
        this.baseLink = "/entities/customerOrders/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'customerOrders' }

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
                <SimpleList headers={customerOrderHeaders} editLink={this.editLink}
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

export class EditCustomerOrder extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'customerOrders'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/customerOrders')
    }

    render() {
        return (
            <div>
                <h3> Edit CustomerOrder </h3>
                <Form schema={customerOrderSchema}
                	uiSchema={customerOrderUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewCustomerOrder extends BaseEditComponent {

  renderExtra(record) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'customerOrders';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
     if(!record) return null;
    return (
     <div>
       <SimpleView  headers= {customerOrderHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='CustomerOrder' /> 
       
       <Tabs>
       	  <Tab label="OrderItem" >
          <OrderItemList records={record.orderItems} 
          nested={true}  
          container={'customerOrder_displayName'}
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



