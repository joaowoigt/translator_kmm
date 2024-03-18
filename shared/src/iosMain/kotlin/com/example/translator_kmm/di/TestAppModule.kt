package com.example.translator_kmm.di

import com.example.translator_kmm.testing.FakeHistoryDataSource
import com.example.translator_kmm.testing.FakeTranslateClient
import com.example.translator_kmm.testing.FakeVoiceToTextParser
import com.example.translator_kmm.translate.domain.history.HistoryDataSource
import com.example.translator_kmm.translate.domain.translate.Translate
import com.example.translator_kmm.translate.domain.translate.TranslateClient
import com.example.translator_kmm.voice_to_text.domain.VoiceToTextParser

class TestAppModule: AppModule {
    override val historyDataSource: HistoryDataSource = FakeHistoryDataSource()

    override val client: TranslateClient = FakeTranslateClient()

    override val translateUseCase: Translate  = Translate(client, historyDataSource)

    override val voiceParser: VoiceToTextParser = FakeVoiceToTextParser()
}