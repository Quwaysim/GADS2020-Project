package com.quwaysim.gads2020project.viewmodels;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.quwaysim.gads2020project.model.Learner;
import com.quwaysim.gads2020project.services.DataService;
import com.quwaysim.gads2020project.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PageViewModel extends ViewModel {
    List<Learner> learnersByHoursList = new ArrayList<>();
    List<Learner> learnersBySkillIqList = new ArrayList<>();

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<List<Learner>> mText = Transformations.map(mIndex, new Function<Integer, List<Learner>>() {
        @Override
        public List<Learner> apply(Integer input) {
            if (input == 1) {
                return learnersByHoursList;
            } else {
                return learnersBySkillIqList;
            }
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<List<Learner>> getStringArray() {
        return mText;
    }

    public List<Learner> makeNetworkRequest() {

        DataService task = ServiceBuilder.buildService(DataService.class);
        Call<List<Learner>> learnersByHoursCall = task.getLearnersByHours();
        Call<List<Learner>> learnersBySkillIqCall = task.getLearnersBySkillIq();

        learnersByHoursCall.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {

                if (response.body() != null) {
                    learnersByHoursList.addAll(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
//                Toast.makeText(c, "not successful!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        learnersBySkillIqCall.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {

                if (response.body() != null) {
                    learnersBySkillIqList.addAll(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
//                Toast.makeText(c, "not successful!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return learnersByHoursList;
    }

}