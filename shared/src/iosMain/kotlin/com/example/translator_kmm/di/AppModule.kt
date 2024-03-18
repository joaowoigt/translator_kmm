package com.example.translator_kmm.di

import com.example.translator_kmm.core.database.TranslateDatabase
import com.example.translator_kmm.translate.data.history.SqlDelightHistoryDataSource
import com.example.translator_kmm.translate.data.local.DatabaseDriverFactory
import com.example.translator_kmm.translate.data.remote.HttpClientFactory
import com.example.translator_kmm.translate.data.translate.KtorTranslateClient
import com.example.translator_kmm.translate.domain.history.HistoryDataSource
import com.example.translator_kmm.translate.domain.translate.Translate
import com.example.translator_kmm.translate.domain.translate.TranslateClient
import com.example.translator_kmm.voice_to_text.domain.VoiceToTextParser

interface AppModule {
    val historyDataSource: HistoryDataSource
    val client: TranslateClient
    val translateUseCase: Translate
    val voiceParser: VoiceToTextParser
}

class AppModuleImpl(
    val parser: VoiceToTextParser
): AppModule {

    override val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    override val client: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    override val translateUseCase: Translate by lazy {
        Translate(
            client, historyDataSource
        )
    }

    override val voiceParser: VoiceToTextParser = parser
}