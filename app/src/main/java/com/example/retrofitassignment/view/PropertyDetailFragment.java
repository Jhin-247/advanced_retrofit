package com.example.retrofitassignment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.FragmentPropertyDetailBinding;
import com.example.retrofitassignment.viewmodel.MainViewModel;

public class PropertyDetailFragment extends Fragment {

    private FragmentPropertyDetailBinding mBinding;
    private MainViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_property_detail, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mBinding.setViewModel(mViewModel);

        return mBinding.getRoot();
    }
}
