package com.asabirov.translator.core.presentation

import com.asabirov.translator.core.domain.language.Language

expect class UiLanguage {

    val language: Language

    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}