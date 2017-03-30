import React from 'react'

export const URL = "http://localhost:9033/rest/";

export  class BaseComponent extends React.Component {

    componentDidMount() {
        this.loadRecordsFromServer();
    }

    constructor(props) {
        super(props);
        this.deleteRecord = this.deleteRecord.bind(this);
        this.createRecord = this.createRecord.bind(this);
        this.url = URL + this.getEntityName() + "/"

        this.state = {
            records: [],
        };
    }

    createRecord(record) {
        fetch(this.url ,
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
            .catch(err => console.error(err))
    }

    // Load students from database
    loadRecordsFromServer() {
        fetch(this.url,
            {credentials: 'same-origin'})
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    records: responseData,
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

export class BaseEditComponent extends React.Component{
    
   
    fetchSingleRecord(id) {
        let url = URL  + this.entityName + "/" + id
        
        console.log("Fetching " + url)
        fetch(url ,
            {credentials: 'same-origin'})
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    entity: responseData
                }); 
            })
            
            //.error(e => console.log(e))
    }

    editRecord(record) {
        let myurl = URL  + this.entityName + "/" + (record.id ? record.id : '');
        console.log(myurl)
        
        let restMethod = record.id ? 'PUT' : 'POST';
        fetch(myurl,
            {
                method: restMethod,
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(record)
            })
            .then(
                //res => this.loadRecordsFromServer()

            )
            .catch(err => console.error(err))
    }
}

