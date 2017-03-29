import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, NavLink, Switch, Redirect } from 'react-router-dom';
import { StudentList, EditStudent } from './Student.jsx'
import { CustomerList, EditCustomer } from './Customer.jsx'
import { CaseDefinitionList, EditCaseDefinition } from './CaseDefinition.jsx';

import { TaskList, TaskView } from './taskList.jsx';


const EntitiyLinks = () => (
    <div>
        <nav>
            <NavLink to="/entities/students" activeClassName="active">Studentsyyy </NavLink>
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
                <Route path="/entities/students/edit/:id(\d+)?" component={EditStudent} />
                <Route path="/entities/students" component={StudentList} />

                <Route path="/entities/customers/edit/:id(\d+)?" component={EditCustomer} />
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


