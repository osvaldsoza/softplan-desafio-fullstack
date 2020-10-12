import axios from 'axios';

export const loadLogin = (user) => {
    return dispatch => {
        axios.get(`http://localhost:8080/usuario/${user}`)
            .then((res) => {
                dispatch({
                    type: 'LOAD_LOGIN',
                    login: res
                })
            })
    }
}