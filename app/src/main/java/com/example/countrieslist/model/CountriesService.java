package com.example.countrieslist.model;

import com.example.countrieslist.di.DaggerAPIComponants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountriesService {

    private static CountriesService instance;

    @Inject
    public CountryAPI api;

    private CountriesService(){
        DaggerAPIComponants.create().inject(this);
    }

    public static CountriesService getInstance(){
        if(instance == null)
        {
            instance = new CountriesService();
        }
        return instance;
    }

   public Single<List<CountryModel>> getCountries(){
        return api.getCountries();
   }
}
