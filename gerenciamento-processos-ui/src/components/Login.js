import React, {useState} from 'react';
import {connect} from 'react-redux'
import {InputText} from 'primereact/inputtext';
import {Password} from 'primereact/password';
import {Button} from 'primereact/button';
import {Panel} from 'primereact/panel';
import {loadLogin} from "../actions/LoginAction";

function Login() {
    const [login, setLogin] = useState('')
    const [senha, setSenha] = useState('')

    const handleOnChangelogin = (e) => {
        setLogin(e.target.value)
    }
    const handleOnChangeSenha = (e) => {
        setSenha(e.target.value)
    }

    const handleClickLogar = () => {
        let user = {
            login,
            senha
        }
        console.log(user)
        this.props.loadLogin(user)
    }

    return (
        <div className="p-grid">
            <Panel header="Usuário">
                <div className="p-grid p-fluid">
                    <span className="p-float-label" style={{marginTop: '1em'}}>
                        <InputText id="login" value={login} onChange={handleOnChangelogin}/>
                        <label htmlFor="login">Login</label>
                    </span>

                    <span className="p-float-label" style={{marginTop: '2em'}}>
                        <Password feedback={false} id="senha" onChange={handleOnChangeSenha} value={senha}/>
                         <label htmlFor="senha">Senha</label>
                      </span>

                    <Button
                        label="Logar"
                        onClick={handleClickLogar}
                        disabled={!login || !senha}
                    />
                </div>
            </Panel>
        </div>
    );
}

const mapStateToProps = state =>{
    return{
        user:state.LoginReducer.user
    }
}

const mapDispatchToProps = dispatch =>{
    return{
        loadLogin:(user)=> dispatch(loadLogin(user))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Login)