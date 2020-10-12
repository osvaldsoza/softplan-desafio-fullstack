import React from 'react';
import {createStore, applyMiddleware} from 'redux'
import {Provider} from 'react-redux'
import thunk from 'redux-thunk'
import Login from "./components/Login";
import reducers from './reducers/index'
import Processo from './components/Processo'
const store = createStore(reducers, applyMiddleware(thunk))

function App() {
    return (
        <Provider store={store}>
            <Processo/>
        </Provider>
    );
}

export default App;
