package com.asabirov.translator.translate.domain.history

import com.asabirov.translator.core.domain.util.CommonFlow

interface HistoryDataSource {

    fun getHistory(): CommonFlow<List<HistoryItem>>

    suspend fun insertHistoryItem(item: HistoryItem)
}