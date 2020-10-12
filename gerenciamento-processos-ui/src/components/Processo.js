import React, {Component} from 'react';
import {connect} from 'react-redux'
import {Button} from 'primereact/button';
import {InputText} from 'primereact/inputtext';
import {Dialog} from 'primereact/dialog';
import {Message} from 'primereact/message';
import Container from 'react-bootstrap/Container';
import ListaProcessos from './ListaProcessos';
import {loadProcessos, mergeProcesso, deleteProcesso} from "../actions/ProcessoAction";

class processos extends Component {

    constructor(props) {
        super(props);
        this.state = {
            processos: [],
        }
    }

    componentDidMount() {
        this.props.loadProcessos()
    }

    handleClickMergeProcesso = () => {
        this.props.mergeProcesso(this.state.processo)
        this.setState({selectedProcesso: null, processo: '', displayFormProcesso: false})
    }

    handleClickExcluirProcesso = () => {
        let id = this.state.selectedProcesso.id
        this.props.deleteProcesso(id)
        this.setState({
            selectedProcesso: null,
            processo: null,
            displayFormProcesso: false
        })
    }

    handleOnChangeField = (property, value) => {
        let processo = this.state.processo;
        processo[property] = typeof value === 'object' ? value.label : value;
        this.setState({processo})
    }

    handleProcessoSelected = (e) => {
        this.ehNovoProcesso = false;
        this.setState({
            displayFormProcesso: true,
            processo: Object.assign({}, e.data)
        })
    }

    handleClickNovoProcesso = () => {
        this.ehNovoProcesso = true;

        //     id : 1 ADMINISTRADOR
        //     id : 2 USUARIO_TRIADOR
        //     id : 3 USUARIO_FINALIZADOR
        
        this.setState({
            processo: {numero: '', parecer: '', usuario: {id: 1}},
            displayFormProcesso: true
        })
    }

    handleOnSelectionChange = (e) => this.setState({selectedProcesso: e.value, numero: e.value.numero})

    render() {
        let btnNovoProcesso = <div className="p-clearfix" style={{width: '100%'}}>
            <Button
                style={{float: 'left'}}
                label="Novo Processo"
                icon="pi pi-plus"
                onClick={this.handleClickNovoProcesso}
            />
        </div>

        let corButton = this.ehNovoProcesso ? "p-button-success" : "p-button-warning"
        let actionsButtons = <div className="ui-dialog-buttonpane p-clearfix">
            <Button
                label="Excluir"
                icon="pi pi-times"
                disabled={this.ehNovoProcesso}
                onClick={this.handleClickExcluirProcesso}
                className="p-button-danger p-button-raised p-button-rounded"
            />
            <Button
                label={this.ehNovoProcesso ? "Salvar" : "Atualizar"}
                icon="pi pi-check"
                onClick={this.handleClickMergeProcesso}
                disabled={this.ehNovoProcesso && (this.state.processo.numero === '' || this.state.processo.parecer === '' )}
                className={`${corButton} p-button-raised p-button-rounded`}
            />
        </div>

        return (
            <Container>
                <div className="content-section implementation">
        
                    <ListaProcessos
                        processos={this.props.processos}
                        selectedProcesso={this.state.selectedProcesso}
                        handleOnSelectionChange={this.handleOnSelectionChange}
                        handleProcessoSelected={this.handleProcessoSelected}
                        btnNovoProcesso={btnNovoProcesso}
                    />

                    <Dialog
                        visible={this.state.displayFormProcesso}
                        width="300px"
                        header="Processo"
                        modal={true}
                        footer={actionsButtons}
                        onHide={() => this.setState({displayFormProcesso: false})}>
                        {
                            this.state.processo &&

                            <div className="p-grid p-fluid">
                                <div className="d-flex p-col-9" style={{padding: '.5em', marginBottom: '.3em'}}>
                                    <span className="p-float-label">
                                        <InputText id="numero" className="p-error" onChange={(e) => {
                                            this.handleOnChangeField('numero', e.target.value.toUpperCase())
                                        }} value={this.state.processo.numero} name="numero"/>
                                        <label htmlFor="in">Número</label>
                                    </span>
                                    {
                                        this.state.processo.numero === '' &&
                                        <Message severity="error" text="Campo obrigatório" style={{width: '20em'}}/>
                                    }
                                </div>

                                <div className="d-flex p-col-9" style={{padding: '.5em', marginBottom: '.3em'}}>
                                    <span className="p-float-label">
                                        <InputText id="parecer" className="p-error" onChange={(e) => {
                                            this.handleOnChangeField('parecer', e.target.value)
                                        }} value={this.state.processo.parecer} name="parecer"/>
                                        <label htmlFor="in">Parecer</label>
                                    </span>
                                    {
                                        this.state.processo.parecer === '' &&
                                        <Message severity="error" text="Campo obrigatório" style={{width: '20em'}}/>
                                    }
                                </div>
                            </div>
                        }
                    </Dialog>
                </div>
            </Container>
        )
    }
}

const mapStateToProps = state => {
    return {
        processos: state.ProcessoReducer.processos,
        isMergeProcesso: state.ProcessoReducer.isMergeProcesso,

    }
}

const mapDispatchToProps = dispatch => {
    return {
        loadProcessos: () => dispatch(loadProcessos()),
        mergeProcesso: (processo) => dispatch(mergeProcesso(processo)),
        deleteProcesso: (id) => dispatch(deleteProcesso(id)),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(processos)
