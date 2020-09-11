package com.quwaysim.gads2020project.services;

import com.quwaysim.gads2020project.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("hours")
    Call<List<Learner>> getLearnersByHours();

    @GET("skilliq")
    Call<List<Learner>> getLearnersBySkillIq();
}
