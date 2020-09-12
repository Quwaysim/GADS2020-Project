package com.quwaysim.gads2020project.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.quwaysim.gads2020project.model.Learner;
import com.quwaysim.gads2020project.services.DataService;
import com.quwaysim.gads2020project.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PageViewModel{
    List<Learner> learnersByHoursList = new ArrayList<>();
    List<Learner> learnersBySkillIqList = new ArrayList<>();

    private int mIndex = 1;


    public void setIndex(int index) {
        mIndex = index;
    }

    public List<Learner> getStringArray() {
        if (mIndex == 1) {
            Log.d("myTag", "apply: " + learnersByHoursList.toString());
            return learnersByHoursList;
        } else {
            Log.d("myTag", "apply: " + learnersBySkillIqList.toString());
            return learnersBySkillIqList;
        }
    }

    public void makeNetworkRequest(Context c) {

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
                Toast.makeText(c, "Please check your internet connection", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(c, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}