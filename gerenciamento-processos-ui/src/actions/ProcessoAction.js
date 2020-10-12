import axios from 'axios';

export const loadProcessos = () => {
    return dispatch => {
        axios.get(`http://localhost:8080/processo`)
            .then((res) => {
                dispatch({
                    type: 'LOAD_PROCESSOS',
                    processos: res.data
                })
            })
    }
}

export const mergeProcesso = (processo) => {
    return dispatch => {
        axios.post('http://localhost:8080/processo', processo)
            .then((res) => {
                dispatch(loadProcessos())
            })
    }
}

export const deleteProcesso = (codigo) =>{
    console.log(codigo)
    return dispatch =>{
        axios.delete(`http://localhost:8080/processo/${codigo}`)
            .then((res) => {
                dispatch(loadProcessos())
            }).catch(error => console.log(error))
    }
}

