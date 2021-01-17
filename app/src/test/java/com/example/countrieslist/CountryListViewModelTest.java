package com.example.countrieslist;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.countrieslist.model.CountriesService;
import com.example.countrieslist.model.CountryModel;
import com.example.countrieslist.viewmodel.CountryListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class CountryListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule  =  new InstantTaskExecutorRule();

    @Mock
    CountriesService countriesService;

    @InjectMocks
    CountryListViewModel viewModel = new CountryListViewModel();

        public Single<List<CountryModel>> singleTest;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountrySuccess(){
        CountryModel country = new CountryModel("India", "Delhi", "Flag");
        ArrayList<CountryModel> list = new ArrayList<>();
        list.add(country);

        singleTest = Single.just(list);
        Mockito.when(countriesService.getCountries()).thenReturn(singleTest);

        viewModel.refresh();

        Assert.assertEquals(1, viewModel.countries.getValue().size());
        Assert.assertEquals(false, viewModel.countryLoadError.getValue());
        Assert.assertEquals(false, viewModel.loading.getValue());

    }

    @Test
    public void getCountryFailure(){

        singleTest = Single.error(new Throwable());
        Mockito.when(countriesService.getCountries()).thenReturn(singleTest);

        viewModel.refresh();

        Assert.assertEquals(true, viewModel.countryLoadError.getValue());
        Assert.assertEquals(false, viewModel.loading.getValue());
    }

    @Before
    public void setUpRxSchedulers(){
        Scheduler immidiate = new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> { runnable.run(); },true);
            } };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler-> immidiate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler-> immidiate);
    }
}
