const INITIAL_STATE = {
    user: {}
}

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case 'LOAD_LOGIN':
            return {state: action.user}
        default:
            return state
    }
}