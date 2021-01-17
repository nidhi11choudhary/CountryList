package com.example.countrieslist.di;

import com.example.countrieslist.model.CountriesService;
import com.example.countrieslist.viewmodel.CountryListViewModel;

import dagger.Component;

@Component(modules = APIModule.class)
public interface APIComponants {

    void inject(CountriesService service);

    void inject(CountryListViewModel service);
}
