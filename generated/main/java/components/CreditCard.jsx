

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
    title: "Credit Card",
    type: "object",
    required: [ 
],
    properties: {
    

accountAddress:{ type: "string", title: "Account Address",  	
},



ccNumber:{ type: "string", title: "Cc Number",  	
},



expiry:{ type: "string", title: "Expiry",   "format": "date"	
},


    
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


let customerSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class CreditCardList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'creditCards'
        this.name = 'creditCards'
        this.editLink = "/entities/creditCards/edit/"
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
                <SimpleList headers={creditCardHeaders} editLink={this.editLink}
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

export class EditCreditCard extends BaseEditComponent {

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
