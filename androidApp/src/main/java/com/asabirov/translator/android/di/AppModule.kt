package com.asabirov.translator.android.di

import android.app.Application
import com.asabirov.translator.database.TranslateDatabase
import com.asabirov.translator.translate.data.history.SqlDelightHistoryDataSource
import com.asabirov.translator.translate.data.local.DatabaseDriverFactory
import com.asabirov.translator.translate.data.remote.HttpClientFactory
import com.asabirov.translator.translate.data.translate.KtorTranslateClient
import com.asabirov.translator.translate.domain.history.HistoryDataSource
import com.asabirov.translator.translate.domain.translate.Translate
import com.asabirov.translator.translate.domain.translate.TranslateClient
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(client, dataSource)
    }
}