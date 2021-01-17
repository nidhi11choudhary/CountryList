package com.example.countrieslist.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountryAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>> getCountries();

    /* if you don't have URL handy you can pass url in method as parameter using URL annotation

        @GET
        Single<Object> getMethodName(@URL String URL);

     */

    /* If a base URL contains 2 or more API so we can use differnt method for API
        @GET("2ndAPIURL")
        Single<Object> getMethodNameForSecond();
    */
}
