package com.example.countrieslist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrieslist.R;
import com.example.countrieslist.model.CountryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>  {

    private List<CountryModel> countriesList;

    public CountryListAdapter(List<CountryModel> countriesList) {
        this.countriesList = countriesList;
    }

    public void updateList(List<CountryModel> newCountriesList){
        countriesList.clear();
        countriesList.addAll(newCountriesList);
        notifyDataSetChanged();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.image)
            ImageView countryImage;

            @BindView(R.id.name)
            TextView countryName;

            @BindView(R.id.capital)
            TextView countryCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        void bind(CountryModel country)
        {
            countryName.setText(country.getCountryName());
            countryCapital.setText(country.getCapital());
            Util.loadImage(countryImage, country.getFlag(), Util.getProgressDrawable(countryImage.getContext()));
        }
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
            holder.bind(countriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }
}
