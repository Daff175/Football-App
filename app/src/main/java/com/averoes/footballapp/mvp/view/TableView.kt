package com.averoes.footballapp.mvp.view

import com.averoes.footballapp.mvp.model.table.TableItem

interface TableView {

    fun showLoading()
    fun hideLoading()
    fun showMessage(pesan:String)
    fun errorMessage(error:String)
    fun showMatchList(match:List<TableItem>?)
}