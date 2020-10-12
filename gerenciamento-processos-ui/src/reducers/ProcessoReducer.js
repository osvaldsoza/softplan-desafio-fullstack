const INITIAL_STATE = {
    processos: [],
    isMergeProcesso: false
}

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case 'LOAD_PROCESSOS':
            return {...state, processos: action.processos}
        default:
            return state
    }
}