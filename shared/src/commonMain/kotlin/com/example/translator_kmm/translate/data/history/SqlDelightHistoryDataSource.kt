package com.example.translator_kmm.translate.data.history

import com.example.translator_kmm.core.database.TranslateDatabase
import com.example.translator_kmm.core.domain.util.CommonFlow
import com.example.translator_kmm.core.domain.util.toCommonFlow
import com.example.translator_kmm.translate.domain.history.HistoryDataSource
import com.example.translator_kmm.translate.domain.history.HistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {

    private val queries = db.translateQueries

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries.getHistory().asFlow()
            .mapToList()
            .map { history ->
                history.map {
                    it.toHistoryItem()
                }
            }.toCommonFlow()

    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        with(item) {
            queries.insertHistoryEntity(
                id = id,
                fromLanguageCode = fromLanguageCode,
                fromText = fromText,
                toLanguageCode = toLanguageCode,
                toText = toText,
                timestamp = Clock.System.now().toEpochMilliseconds()
            )
        }
    }
}