import React from 'react'
import PropTypes from 'prop-types'
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column'
import moment from 'moment'
class ListaProcessos extends React.Component {
    constructor(props) {
        super(props)
    }


    render() {
        // const listaProcessos = this.props.processos.map(p => {
        //     p.dataEntrada = moment(p.dataEntrada).format('DD/MM/YYYY')
        //     return p
        // })
        return (
            <DataTable
                value={this.props.processos}
                paginator={true}
                rows={6}
                responsive={true}
                header={this.props.processos.length > 0 ? "Gerenciamento de Processos" : "Não há veículos cadastrados"}
                footer={this.props.btnNovoProcesso}
                selectionMode="single"
                selection={this.props.selectedProcesso}
                onSelectionChange={this.props.handleOnSelectionChange}
                onRowSelect={this.props.handleProcessoSelected}>
                <Column field="numero" header="Número" />
                <Column field="dataEntrada" header="Data de Entrada"/>
                {/* <Column field="dataBaixa" header="Data de Baixa" /> */}
                <Column field="parecer" header="Parecer" />
            </DataTable>
        );

    }
}

ListaProcessos.propTypes = {
    processos: PropTypes.array,
    selectedProcesso: PropTypes.string.isRequired,
    handleOnSelectionChange: PropTypes.func.isRequired,
    handleProcessoSelected: PropTypes.func.isRequired,
    btnNovoProcesso: PropTypes.object.isRequired
}
ListaProcessos.defaultProps = {
    processos: []
}
export default ListaProcessos