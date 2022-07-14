package com.example.retrofitassignment.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.adapter.RealEstateAdapter;
import com.example.retrofitassignment.databinding.FragmentListPropertiesBinding;
import com.example.retrofitassignment.viewmodel.MainViewModel;

public class PropertyListFragment extends Fragment {
    private RealEstateAdapter mAdapter;
    private RealEstateAdapter.PropertyClick click;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        click = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListPropertiesBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_properties, container, false);
        mBinding.rcvRealEstate.setLayoutManager(new GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false));
        mAdapter = new RealEstateAdapter();
        MainViewModel mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mBinding.rcvRealEstate.setAdapter(mAdapter);

        mAdapter.setClick(click);

        mViewModel.getProperties().observe(requireActivity(), marsProperties -> {
            mAdapter.setProperties(marsProperties);
            Log.i("PropertyListFragment", "onCreateView: " + marsProperties.size());
        });


        return mBinding.getRoot();
    }
}
