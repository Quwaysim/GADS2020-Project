package com.quwaysim.gads2020project.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quwaysim.gads2020project.R;
import com.quwaysim.gads2020project.adapters.DataAdapter;
import com.quwaysim.gads2020project.model.Learner;
import com.quwaysim.gads2020project.services.DataService;
import com.quwaysim.gads2020project.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

//    private PageViewModel pageViewModel;
    int index;
    List<Learner> learnersByHoursList = new ArrayList<>();
    List<Learner> learnersBySkillIqList = new ArrayList<>();

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pageViewModel = new PageViewModel();
        index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
//        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);

        /*DataService task = ServiceBuilder.buildService(DataService.class);
        Call<List<Learner>> call = task.getLearnersByHours();
        call.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                recyclerView.setAdapter(new DataAdapter(getContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                Toast.makeText(getContext(), "not successful!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

//        pageViewModel.makeNetworkRequest(getContext());
//        List<Learner> response = pageViewModel.getStringArray();
//        Log.d("TAG", "onCreateView: " + response.toString());
        makeNetworkRequest(getContext());
        if (index == 1) {
            String achievementDetails = " learning hours, ";
            recyclerView.setAdapter(new DataAdapter(getContext(), learnersByHoursList, achievementDetails));
        } else {
            String achievementDetails = " skill IQ score, ";
            recyclerView.setAdapter(new DataAdapter(getContext(), learnersBySkillIqList, achievementDetails));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false));


        return root;
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