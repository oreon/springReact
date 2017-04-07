import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, NavLink, Switch, Redirect } from 'react-router-dom';
import { StudentList, EditStudent } from './Student.jsx'
import { CustomerList, EditCustomer, ViewCustomer } from './Customer.jsx'
import { CaseDefinitionList, EditCaseDefinition } from './CaseDefinition.jsx';
import {CaseInstanceList,EditCaseInstance, ViewCaseInstance} from './CaseInstance.jsx';

import { TaskList, TaskView } from './taskList.jsx';


const EntitiyLinks = () => (
    <div>
        <nav>
            <NavLink to="/entities/caseInstances" activeClassName="active">CaseIsntances </NavLink>
            <NavLink to="/entities/customers" activeClassName="active">Customers </NavLink>
            <NavLink to="/entities/tasks" activeClassName="active">Tasks </NavLink>
            <NavLink to="/entities/CaseDefinitions" activeClassName="active">CaseDefinitions </NavLink>
        </nav>
    </div>
)


export const Home = ( props ) => (
    <Router>
        <div>
            <EntitiyLinks />
            <Switch>
                <Route path="/entities/CaseInstances/edit/:id(\d+)?" component={EditCaseInstance}/>
                <Route path="/entities/CaseInstances/view/:id(\d+)?" component={ViewCaseInstance}/>
                <Route path="/entities/CaseInstances" component={CaseInstanceList}/>

                <Route path="/entities/customers/edit/:id(\d+)?" component={EditCustomer} />
                <Route path="/entities/customers/view/:id(\d+)?" component={ViewCustomer}/>
                <Route path="/entities/customers" component={CustomerList} />

                <Route path="/entities/CaseDefinitions/edit/:id(\d+)?" component={EditCaseDefinition} />
                <Route path="/entities/CaseDefinitions" component={CaseDefinitionList} />

                <Route path="/entities/task/:id(\d+)" component={TaskView} />
                <Route exact path="/entities/task" component={TaskList} />
            </Switch>

            <h4> Group Tasks </h4>
            <TaskList />

            <h4> My Tasks </h4>
            <TaskList mine={true} />
        </div>
    </Router>
)


