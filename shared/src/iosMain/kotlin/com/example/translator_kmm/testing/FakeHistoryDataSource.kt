package com.example.translator_kmm.testing

import com.example.translator_kmm.core.domain.util.CommonFlow
import com.example.translator_kmm.core.domain.util.toCommonFlow
import com.example.translator_kmm.translate.domain.history.HistoryDataSource
import com.example.translator_kmm.translate.domain.history.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHistoryDataSource: HistoryDataSource {

    private val _data = MutableStateFlow<List<HistoryItem>>(emptyList())

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return _data.toCommonFlow()

    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        _data.value += item
    }
}
