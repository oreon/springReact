import React from 'react'

export default class BaseComponent extends React.Component {

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
            .catch(err => console.error(err))
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