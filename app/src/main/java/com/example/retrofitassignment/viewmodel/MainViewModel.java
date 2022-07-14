package com.example.retrofitassignment.viewmodel;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitassignment.network.MarsProperty;
import com.example.retrofitassignment.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<MarsProperty>> mProperties = new MutableLiveData<>();

    private MarsProperty mChosenProperty;
    private Call<List<MarsProperty>> mDataCall;

    public String getCurrentPropertyImage() {
        return mChosenProperty.getImgSrcUrl();
    }

    public String getCurrentPropertyPrice() {
        return "Price: " + mChosenProperty.getPrice();
    }

    public String getType() {
        return mChosenProperty.getType();
    }

    public String getId() {
        return mChosenProperty.getId();
    }

    public void setCurrentProperty(MarsProperty property) {
        this.mChosenProperty = property;
    }

    public void searchData(String filter) {
        if (TextUtils.isEmpty(filter)) {
            mDataCall = RetrofitClient.getInstance().getService().getProperties();
        } else {
            mDataCall = RetrofitClient.getInstance().getService().getPropertiesByType(filter);
        }
        mDataCall.enqueue(new Callback<List<MarsProperty>>() {
            @Override
            public void onResponse(@NonNull Call<List<MarsProperty>> call, @NonNull Response<List<MarsProperty>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mProperties.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MarsProperty>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void cancelRequest() {
        if (mDataCall != null) {
            mDataCall.cancel();
            mDataCall = null;
        }
    }

    public MutableLiveData<List<MarsProperty>> getProperties() {
        return mProperties;
    }

}
