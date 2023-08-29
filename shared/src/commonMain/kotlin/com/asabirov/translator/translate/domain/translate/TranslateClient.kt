package com.asabirov.translator.translate.domain.translate

import com.asabirov.translator.core.domain.language.Language

interface TranslateClient {

    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}