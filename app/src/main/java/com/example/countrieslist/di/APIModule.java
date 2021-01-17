package com.example.countrieslist.di;

import com.example.countrieslist.model.CountriesService;
import com.example.countrieslist.model.CountryAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {
    private static final String BASE_URL = "https://raw.githubusercontent.com";

    @Provides
    public CountryAPI provideCountriesAPI(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountryAPI.class);
    }

    @Provides
    public CountriesService provideCountryServices(){
        return CountriesService.getInstance();
    }
}
