import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';

import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';

import {BrowserRouter as Router, Route, NavLink, Switch, Redirect} from 'react-router-dom';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {Home} from './components/Routes.jsx';




const About = (props) => <h1> About Nehchal is
    chatto {props.match.params.number || 'NONE'} {props.match.params.name}</h1>
const Contact = (props) => <h1> Contact </h1>


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
            <MuiThemeProvider>

            <Router>
                <div>
                    <Links/>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route path="/about/:number(\d+)?/:name?" component={About}/>
                        <Route path="/contact" component={Contact}/>

                        <Redirect from="/admin" to="/contact"/>
                        <Route render={ () => <h1> Page not found </h1>}/>

                    </Switch>
                </div>
            </Router>

            </MuiThemeProvider>

        );
    }
}


ReactDOM.render(<App />, document.getElementById('root'));