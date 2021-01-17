package com.example.countrieslist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countrieslist.R;
import com.example.countrieslist.model.CountryModel;
import com.example.countrieslist.viewmodel.CountryListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countriesList)
    RecyclerView countriesList;

    @BindView(R.id.errorMessage)
    TextView errorMessage;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeLayout;

    private CountryListViewModel viewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(CountryListViewModel.class);
        viewModel.refresh();
        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(adapter);
        swipeLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            swipeLayout.setRefreshing(false);
        });
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.countries.observe(this, countryModels -> {
            if(countryModels!=null){
                countriesList.setVisibility(View.VISIBLE);
                adapter.updateList(countryModels);
            }
        });
        viewModel.countryLoadError.observe(this, isError -> {
            if(isError!= null){
                errorMessage.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if(isLoading!=null){
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading)
                {
                    errorMessage.setVisibility(View.GONE);
                    countriesList.setVisibility(View.GONE);
                }
            }

        });
    }
}