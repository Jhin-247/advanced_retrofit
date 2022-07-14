package com.example.retrofitassignment.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.adapter.RealEstateAdapter;
import com.example.retrofitassignment.databinding.ActivityMainBinding;
import com.example.retrofitassignment.network.MarsProperty;
import com.example.retrofitassignment.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements RealEstateAdapter.PropertyClick {

    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;

    private PropertyDetailFragment mDetailFragment;
    private PropertyListFragment mListPropertyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initVariables();

    }

    private void initVariables() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mDetailFragment = new PropertyDetailFragment();
        mListPropertyFragment = new PropertyListFragment();
        mViewModel.searchData("");

        initView();
    }

    private void initView() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fragment_container, mListPropertyFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_rent:
                mViewModel.searchData("rent");
                break;
            case R.id.btn_buy:
                mViewModel.searchData("buy");
                break;
            case R.id.btn_all:
                mViewModel.searchData("");
                break;
        }
        mListPropertyFragment.showDialog();
        if (getSupportFragmentManager().getFragments().size() > 2) {
            getSupportFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPropertyClick(MarsProperty marsProperty) {
        mViewModel.setCurrentProperty(marsProperty);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(mBinding.fragmentContainer.getId(), mDetailFragment).addToBackStack(null).commit();
    }
}