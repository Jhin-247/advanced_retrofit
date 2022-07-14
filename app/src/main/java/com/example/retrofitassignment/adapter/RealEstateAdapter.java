package com.example.retrofitassignment.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ItemRealEstateBinding;
import com.example.retrofitassignment.network.MarsProperty;

import java.util.List;

public class RealEstateAdapter extends RecyclerView.Adapter<RealEstateAdapter.RealEstateHolder> {
    private LayoutInflater mInflater;
    private List<MarsProperty> mProperties;
    private PropertyClick mClick;

    public void setClick(PropertyClick click){
        this.mClick = click;
    }

    public interface PropertyClick{
        void onPropertyClick(MarsProperty marsProperty);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setProperties(List<MarsProperty> mProperties) {
        this.mProperties = mProperties;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RealEstateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRealEstateBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.item_real_estate, parent, false);
        return new RealEstateHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RealEstateHolder holder, int position) {
        MarsProperty marsProperty = mProperties.get(position);
        holder.bind(marsProperty);
        holder.itemView.setOnClickListener(v -> mClick.onPropertyClick(marsProperty));
    }

    @Override
    public int getItemCount() {
        return mProperties == null ? 0 : mProperties.size();
    }

    public static class RealEstateHolder extends RecyclerView.ViewHolder {
        ItemRealEstateBinding mBinding;
        public RealEstateHolder(@NonNull ItemRealEstateBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(MarsProperty marsProperty) {
            mBinding.setProperty(marsProperty);
            mBinding.executePendingBindings();
        }
    }
}
