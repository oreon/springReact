const baseUrl = '/api/task/'

export default class DataService {
	
	async static post  (url, body)  {	
	
	return fetch(baseUrl + url ,
            {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body)
            })
            .then(
                res => this.loadRecordsFromServer()
            )
            .catch(err => console.error(err))
	}
	
	
//   async static postanother  (url)  {
//    url = baseUrl + url + "Writable";
//    return axios
//      .get(url)
//      .then(response => {
//        //console.log(response);
//        let data = response.data
//
//        return response;
//      });
//  }
}