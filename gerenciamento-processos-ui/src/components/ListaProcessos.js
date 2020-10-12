import React from 'react'
import PropTypes from 'prop-types'
import {DataTable} from 'primereact/datatable'
import {Column} from 'primereact/column'

function ListaProcessos(
    {
        processos,
        selectedProcesso,
        handleOnSelectionChange,
        handleProcessoSelected,
        btnNovoProcesso
    }) {

    return (
        <DataTable
            value={processos}
            paginator={true}
            rows={6}
            responsive={true}
            header={processos.length > 0 ? "Gerenciamento de Processos" : "Não há veículos cadastrados"}
            footer={btnNovoProcesso}
            selectionMode="single"
            selection={selectedProcesso}
            onSelectionChange={handleOnSelectionChange}
            onRowSelect={handleProcessoSelected}>
            <Column field="numero" header="Número"/>
            <Column field="dataEntrada" header="Data de Entrada" />
            <Column field="dataBaixa" header="Data de Baixa"/>
            <Column field="parecer" header="Parecer"/>
        </DataTable>
    );
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