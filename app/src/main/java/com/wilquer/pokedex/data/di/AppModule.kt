package com.wilquer.pokedex.data.di

import androidx.paging.Pager
import androidx.paging.PagingData
import com.wilquer.pokedex.data.paging.model.Results
import com.wilquer.pokedex.data.repository.PokemonRepository
import com.wilquer.pokedex.data.repository.PokemonRepositoryImpl
import com.wilquer.pokedex.data.service.PokeService
import com.wilquer.pokedex.domain.usecase.PokemonDetailsUseCase
import com.wilquer.pokedex.domain.usecase.PokemonListUseCase
import com.wilquer.pokedex.domain.usecase.PokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    internal fun providePokeService(retrofit: Retrofit): PokeService =
        retrofit.create(PokeService::class.java)

    @Singleton
    @Provides
    internal fun providePokemonRepository(service: PokeService): PokemonRepository =
        PokemonRepositoryImpl(service)

    @Singleton
    @Provides
    internal fun providePokemonListUseCase(repository: PokemonRepository): PokemonUseCase<Pager<Int,Results>, Unit> =
        PokemonListUseCase(repository)

//    @Singleton
//    @Provides
//    internal fun providePokemonDetailsUseCase(repository: PokemonRepository): PokemonUseCase<String, String> =
//        PokemonDetailsUseCase(repository)
}