package com.averoes.footballapp.mvp.presenter

import com.averoes.footballapp.mvp.view.TableView
import com.averoes.footballapp.networking.InitRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TablePresenter(private val view:TableView) {

    fun getTableTeam(idLeague: String) {

        view.showLoading()

        GlobalScope.launch(Dispatchers.IO) {

            try {

                val api = InitRetrofit().getInstance()
                val response = api.getTableTeam(idLeague)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        view.hideLoading()
                        view.showMatchList(response.body()?.table)

                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
}