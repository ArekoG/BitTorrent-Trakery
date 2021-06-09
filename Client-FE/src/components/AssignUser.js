import React,{Component} from 'react';
import axios from 'axios';

export class AssignUser extends Component {

    constructor (props) {
        super(props);
        this.state = {
            userLogin: "",
            idTracker: ""
        }
    }

    changeHandler = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    submitHandler = (e) => {
        e.preventDefault();
        console.log(this.state.login);
        axios.post('http://localhost:8081/client/trackers/'+this.state.idTracker+'/users/'+this.state.userLogin+'/assign')
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
                <h2>Przypisz tracker do użytkownika</h2>
                <form onSubmit ={this.submitHandler}>
                    <input type="text" placeholder="login" name = "userLogin" value={this.state.userLogin} onChange={this.changeHandler}></input>
                    <input type="number" placeholder="ID tracker'a" name = "idTracker" value={this.state.idTracker} onChange={this.changeHandler}></input>
                    <button type="submit" className="assaignBtn">Przydziel</button>
                </form>
            </div>
        )
    }
}