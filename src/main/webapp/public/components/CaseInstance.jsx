

import React from 'react';
import SkyLight from 'react-skylight';
import Alert from 'react-s-alert';
var fetch = require( 'node-fetch' );
import { Link, NavLink, Switch, Redirect } from 'react-router-dom';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn } from 'material-ui/Table';


import Form from "react-jsonschema-form";
import { BaseComponent, BaseEditComponent } from '../commons/BaseComponent.jsx'
import Griddle, { plugins } from 'griddle-react';
import { SimpleList } from '../commons/SimpleList.jsx'

import { Layout } from '../commons/Layout.jsx'
import { SimpleView } from '../commons/SimpleView.jsx'
import {Tabs, Tab} from 'material-ui/Tabs';


export function createSchema() {

    return {
        title: "Case Instance",
        type: "object",
        required: [
        ],
        properties: {


            caseDefinition: {
                type: "integer", title: "Case Definition",
//
//                'enum': LookupService.getLookup( 'caseDefinitions' ).map( x => x.id ),
//                'enumNames': LookupService.getLookup( 'caseDefinitions' ).map( x => x.displayName )



            },



            processInstanceId: {
                type: "integer", title: "Process Instance Id",
            },



            name: {
                type: "string", title: "Name",
            },



            status: {
                type: "string", title: "Status",
            },



            taskInstances: {
                title: "Task Instances",
                type: "array",
                required: [
                ],
                items: {
                    "type": "object",
                    "properties": {


                        taskId: {
                            type: "integer", title: "Task Id",
                        },



                        name: {
                            type: "string", title: "Name",
                        },



                        taskDefinition: {
                            type: "integer", title: "Task Definition",

//                            'enum': LookupService.getLookup( 'taskDefinitions' ).map( x => x.id ),
//                            'enumNames': LookupService.getLookup( 'taskDefinitions' ).map( x => x.displayName )



                        },



                        caseInstance: {
                            "type": "number",
                        },



                        taskData: {
                            type: "string", title: "Task Data",
                        },



                        status: {
                            type: "string", title: "Status",
                        },



                    }
                }
            },

        }
    };

}

export const caseInstanceUISchema = {


    caseDefinition: { 'ui:placeholder': "Case Definition" },



    processInstanceId: { 'ui:widget': "updown", 'ui:placeholder': "Process Instance Id" },



    name: { 'ui:placeholder': "Name" },



    status: { 'ui:placeholder': "Status" },



    taskInstances: {
        items: {


            taskId: { 'ui:widget': "updown", 'ui:placeholder': "Task Id" },



            name: { 'ui:placeholder': "Name" },



            taskDefinition: { 'ui:placeholder': "Task Definition" },



            caseInstance: {
                "ui:widget": "hidden"
            },



            taskData: { 'ui:placeholder': "Task Data" },



            status: { 'ui:placeholder': "Status" },



        }
    },

}


export const caseInstanceHeaders = [


    { property: "caseDefinition_displayName", title: "Case Definition" }
    ,

    { property: "processInstanceId", title: "Process Instance Id" }
    ,

    { property: "name", title: "Name" }
    ,

    { property: "status", title: "Status" }

]


let customerSchema = createSchema()
const log = ( type ) => console.log.bind( console, type );


export class CaseInstanceList extends BaseComponent {

    constructor( props ) {
        super( props );
        this.entityName = 'caseInstances'
        this.name = 'caseInstances'
        this.editLink = "/entities/caseInstances/edit/"
        this.baseLink = "/entities/caseInstances/"
    }
    
    getEntityName() { return  'caseInstances' }

    renderExtra( record ) {
        return null
    }


    handleRowSelection( selectedRows ) {
        this.props.push( <Link className="btn btn-default btn-xs" to={this.toLink}>Edit</Link> )
        console.log( 'selectedRows: ' + selectedRows );
    }


    render() {
        let records = this.props.nested ? this.props.records : this.state.records

        if ( !records )
            return ( <p>Loading...</p> )

        return (
            <div>
                <SimpleList headers={caseInstanceHeaders} editLink={this.editLink}
                    renderExtra={this.renderExtra}
                    baseLink = {this.baseLink}
                    records={records} nested={this.props.nested}
                    container={this.props.container} uneditable={this.props.uneditable}
                    containerId={this.props.containerId}
                    prev={this.props.prev}
                    />
            </div>
        );
    }
}

export class EditCaseInstance extends BaseEditComponent {

    componentDidMount() {
        if ( this.props.match.params.id )
            this.fetchSingleRecord( this.props.match.params.id );
    }

    constructor( props ) {
        super( props );
        this.state = { entity: {} };
        this.entityName = 'caseInstances'
        this.onSubmit = this.onSubmit.bind( this );
        //this.handleChange = this.handleChange.bind(this);
    }

    onSubmit( formData ) {
        this.editRecord( formData )
        this.props.history.push( '/entities/caseInstances' )
    }

    render() {
        return (
            <div>
                <h3> Edit CaseInstance </h3>
                <Form schema={createSchema()}
                    uiSchema={caseInstanceUISchema}
                    formData={this.state.entity}
                    //onChange={log("changed")}
                    onSubmit={( {formData}) => this.onSubmit( formData )}
                    onError={log( "errors" )} />
            </div>
        )
    }
}

export class ViewCaseInstance extends BaseEditComponent {

    componentDidMount() {
        if ( this.props.match.params.id )
            this.fetchSingleRecord( this.props.match.params.id );
    }

    constructor( props ) {
        super( props );
        this.state = { entity: {} ,error: {}, message: {} };
        this.entityName = 'caseInstances'
    }
    
    renderExtra(record: any) { <p> IN render </p> }
    
    render() {
      let record = this.state.entity
      return (
         <div>
           <button onClick={() => this.props.data.gotoList()}>Close Case</button>
         <SimpleView  headers= {caseInstanceHeaders} renderExtra={this.renderExtra} record={record}/> 
              <Tabs></Tabs>
         </div>
      )   

    }
}

