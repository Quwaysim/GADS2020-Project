package com.quwaysim.gads2020project.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quwaysim.gads2020project.R;
import com.quwaysim.gads2020project.adapters.DataAdapter;
import com.quwaysim.gads2020project.viewmodels.PageViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

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
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
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

        pageViewModel.getStringArray().observe(getViewLifecycleOwner(), response -> {

            recyclerView.setAdapter(new DataAdapter(getContext(), response));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                    RecyclerView.VERTICAL, false));

        });
        return root;
    }
}