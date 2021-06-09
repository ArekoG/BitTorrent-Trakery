import React,{Component} from 'react';
import axios from 'axios';

export class ManageUser extends Component {

    constructor (props) {
        super(props);
        this.state = {
            login: "",
            button: 1
        }
    }

    changeHandler = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    submitHandler = (e) => {
        e.preventDefault();
        console.log(this.state.login);
        if(this.state.button === 1){
            axios.post('http://localhost:8081/client/users/'+this.state.login+'/enable')
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
        }
        if(this.state.button === 2){
            axios.post('http://localhost:8081/client/users/'+this.state.login+'/disable')
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
        }
    }

    render(){
        return (
            <div className="manageUser">
                <h2>Włącz / wyłącz użytkownika</h2>
                <form onSubmit ={this.submitHandler}>
                    <input type="text" placeholder="login" name = "login" value={this.state.login} onChange={this.changeHandler}></input>
                    <button onClick={() => (this.state.button = 1)} type="submit" className="enableBtn">Włącz</button>
                    <button onClick={() => (this.state.button = 2)} type="submit" className="disableBtn">Wyłącz</button>
                </form>
            </div>
        )
    }
}