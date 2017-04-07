

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
    title: "App User",
    type: "object",
    required: [ 
],
    properties: {
    

userName:{ type: "string", title: "User Name",  	
},



password:{ type: "string", title: "Password",  	
},



enabled:{ type: "boolean", title: "Enabled",  	
},



appRoles:{ type: "array", title: "App Roles",   

    "items":{
 'enum': LookupService.getLookup('appRoles').map(x => x.id .toString()  ),
 'enumNames': LookupService.getLookup('appRoles').map(x => x.displayName)
    },
    "uniqueItems": true

	
},


    
    }
 };

}

export const appUserUISchema = {
 	

userName: {  'ui:placeholder': "User Name" },



password: {  'ui:placeholder': "Password" },



enabled: {  'ui:placeholder': "Enabled" },



appRoles: {  'ui:placeholder': "App Roles" },


    
 }


	export const appUserHeaders = [
	 
	 
	 {property:"userName",title:"User Name" }
	 ,
	 
	 {property:"password",title:"Password" }
	 ,
	 
	 {property:"enabled",title:"Enabled" }
	      
	 ]




let appUserSchema = createSchema()
const log = (type) => console.log.bind(console, type);


export class AppUserList extends BaseComponent {

    constructor(props) {
        super(props);
        this.entityName = 'appUsers'
        this.name = 'appUsers'
        this.baseLink = "/entities/appUsers/"
        this.editLink = this.baseLink + "edit/"
    }
    
     getEntityName() { return  'appUsers' }

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
                <SimpleList headers={appUserHeaders} editLink={this.editLink}
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

export class EditAppUser extends BaseEditComponent {

    componentDidMount() {
        if (this.props.match.params.id)
            this.fetchSingleRecord(this.props.match.params.id);
    }

    constructor(props) {
        super(props);
        this.state = {entity: {}};
        this.entityName = 'appUsers'
        this.onSubmit = this.onSubmit.bind(this);
        
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit(formData) {
        this.editRecord(formData)
        this.props.history.push('/entities/appUsers')
    }

    render() {
        return (
            <div>
                <h3> Edit AppUser </h3>
                <Form schema={appUserSchema}
                	uiSchema={appUserUISchema}
                      formData={this.state.entity }
                    //onChange={log("changed")}
                      onSubmit={({formData}) => this.onSubmit(formData) }
                      onError={log("errors")}/>
            </div>
        )
    }
}


export class ViewAppUser extends BaseEditComponent {

  renderExtra(record: any) { <p> IN render </p> }
  
  constructor(props) {
    super(props);
    this.state = { record: {}, error: {}, message: {} };
    this.entityName = 'appUsers';
    //this.onSubmit = this.onSubmit.bind(this)
  }
  
  render() {
  
    let record = this.state.entity
    return (
     <div>
       <SimpleView  headers= {appUserHeaders} renderExtra={this.renderExtra}
       record={record}   entityName='AppUser' /> 
       
       <Tabs>
        
         </Tabs>
      </div>
    )	

  }
}



