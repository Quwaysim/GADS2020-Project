package com.quwaysim.gads2020project.services;

import com.quwaysim.gads2020project.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("hours")
    Call<List<Learner>> getLearnersByHours();

    @GET("skilliq")
    Call<List<Learner>> getLearnersBySkillIq();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> submitForm(
            @Field("entry.1877115667") String name,
            @Field("entry.2006916086") String lastname,
            @Field("entry.1824927963") String email,
            @Field("entry.284483984") String projectLink
    );
}
