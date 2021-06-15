import React,{Component} from 'react';
import axios from 'axios';

export class DownloadFile extends Component {

    constructor (props) {
        super(props);
        this.state = {
            userID: "",
            fileID: "",
            trackerID: ""
        }
    }

    changeHandler = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    submitHandler = (e) => {
        e.preventDefault();
        console.log(this.state.login);
        axios.post('http://localhost:8081/client/users/'+this.state.userID+'/files/'+this.state.fileID+'/download/'+this.state.trackerID)
        .then(response => {
            console.log(response)
        })
        .catch(error => {
            console.log(error)
        })
    }

    render(){
        return (
            <div className="manageUser">
                <h2>Pobierz plik</h2>
                <form onSubmit ={this.submitHandler}>
                    <input type="number" placeholder="ID użytkownika" name = "idTracker" value={this.state.userID} onChange={this.changeHandler}></input>
                    <input type="number" placeholder="ID pliku" name = "idTracker" value={this.state.fileID} onChange={this.changeHandler}></input>
                    <input type="number" placeholder="ID tracker'a" name = "idTracker" value={this.state.trackerID} onChange={this.changeHandler}></input>
                    <button type="submit" className="assaignBtn">Pobierz</button>
                </form>
            </div>
        )
    }
}