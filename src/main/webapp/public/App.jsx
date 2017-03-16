import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';
import SkyLight from 'react-skylight';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';
var fetch = require('node-fetch');
import {BrowserRouter as Router, Route, NavLink, Switch, Redirect} from 'react-router-dom';


const Home = (props) => <Students/>
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

        );
    }
}

const URL = "http://localhost:9033/api/";

class BaseComponent extends React.Component {

    constructor(props) {
        super(props);
        this.deleteRecord = this.deleteRecord.bind(this);
        this.createRecord = this.createRecord.bind(this);

        this.state = {
            records: [],
        };
    }

    createRecord(record) {
        fetch(this.url,
            {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(record)
            })
            .then(
                res => this.loadRecordsFromServer()
            )
            .catch(err => cosole.error(err))
    }

    // Load students from database
    loadRecordsFromServer() {
        fetch(this.url,
            {credentials: 'same-origin'})
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    records: responseData._embedded[this.name],
                });
            });
    }

    // Delete student
    deleteRecord(record) {
        fetch(record._links.self.href,
            {
                method: 'DELETE',
                credentials: 'same-origin'
            })
            .then(
                res => this.loadRecordsFromServer()
            )
            .then(() => {
                Alert.success('Record deleted', {
                    position: 'bottom-left',
                    effect: 'slide'
                });
            })
            .catch(err => console.error(err))
    }

}

class Students extends BaseComponent {

    constructor(props) {
        super(props);
        this.url = URL + 'students';
        this.name = 'students'
    }

    componentDidMount() {
        this.loadRecordsFromServer();
    }

    // Create new student
    render() {
        return (
            <div>
                <StudentTable deleteRecord={this.deleteRecord} records={this.state.records}/>
                <StudentForm createRecord={this.createRecord}/>
                <Alert stack={true} timeout={2000}/>
            </div>
        );
    }
}

class StudentTable extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        var records = this.props.records.map(student =>
            <Student key={student._links.self.href} student={student} deleteRecord={this.props.deleteRecord}/>
        );

        return (
            <div>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>{records}</tbody>
                </table>
            </div>);
    }
}

class Student extends React.Component {
    constructor(props) {
        super(props);
        this.deleteRecord = this.deleteRecord.bind(this);
    }

    deleteRecord() {
        this.props.deleteRecord(this.props.student);
    }

    render() {
        return (
            <tr>
                <td>{this.props.student.firstname}</td>
                <td>{this.props.student.lastname}</td>
                <td>{this.props.student.email}</td>
                <td>
                    <button className="btn btn-danger btn-xs" onClick={this.deleteRecord}>Delete</button>
                </td>
            </tr>
        );
    }
}

class StudentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {firstname: '', lastname: '', email: ''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }

    handleSubmit(event) {
        event.preventDefault();
        console.log("Firstname: " + this.state.firstname);
        var newRecord = {firstname: this.state.firstname, lastname: this.state.lastname, email: this.state.email};
        this.props.createRecord(newRecord);
        this.refs.simpleDialog.hide();
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="simpleDialog">
                    <div className="panel panel-default">
                        <div className="panel-heading">Create student</div>
                        <div className="panel-body">
                            <form className="form">
                                <div className="col-md-4">
                                    <input type="text" placeholder="Firstname" className="form-control" name="firstname"
                                           onChange={this.handleChange}/>
                                </div>
                                <div className="col-md-4">
                                    <input type="text" placeholder="Lastname" className="form-control" name="lastname"
                                           onChange={this.handleChange}/>
                                </div>
                                <div className="col-md-4">
                                    <input type="text" placeholder="Email" className="form-control" name="email"
                                           onChange={this.handleChange}/>
                                </div>
                                <div className="col-md-2">
                                    <button className="btn btn-primary" onClick={this.handleSubmit}>Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </SkyLight>
                <div className="col-md-2">
                    <button className="btn btn-primary" onClick={() => this.refs.simpleDialog.show()}>New student
                    </button>
                </div>
            </div>
        );
    }
}

ReactDOM.render(<App />, document.getElementById('root'));