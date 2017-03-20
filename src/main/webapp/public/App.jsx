import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';

import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';

import {BrowserRouter as Router, Route, NavLink, Switch, Redirect} from 'react-router-dom';
import {Students, EditStudent} from './components/Student.jsx';


const Home = (props) => <div> <div>Home:  ${props.match.url}</div> <Entities/> </div>
const About = (props) => <h1> About Nehchal is
    chatto {props.match.params.number || 'NONE'} {props.match.params.name}</h1>
const Contact = (props) => <h1> Contact </h1>

//const MyStudents = (props) => <div>    <Students/> </div>

const Entities = () => (
    <div>
        <nav>
            <NavLink to="/entities/students" activeClassName="active">Students </NavLink>
            <NavLink to="/entities/users" activeClassName="active">Users </NavLink>
        </nav>
    </div>
)


const Links = () => (
    <nav>
        <NavLink to="/" activeClassName="active">Home </NavLink>
        <NavLink to="/about/" activeClassName="active">About </NavLink>
        <NavLink to="/contact" activeClassName="active">Contact </NavLink>

    </nav>
)


class App extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <Links/>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route path="/about/:number(\d+)?/:name?" component={About}/>
                        <Route path="/contact" component={Contact}/>

                        <Route exact path="/entities/students" component={Students}/>
                        <Route  path="/entities/students/edit/:id(\d+)?" component={EditStudent}/>

                        <Redirect from="/admin" to="/contact"/>
                        <Route render={ () => <h1> Page not found </h1>}/>

                    </Switch>
                </div>
            </Router>

        );
    }
}


ReactDOM.render(<App />, document.getElementById('root'));