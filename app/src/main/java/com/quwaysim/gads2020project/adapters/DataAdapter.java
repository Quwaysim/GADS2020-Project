package com.quwaysim.gads2020project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quwaysim.gads2020project.R;
import com.quwaysim.gads2020project.model.Learner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context mContext;
    private List<Learner> learners;
    private String achievementDetails;

    public DataAdapter(Context context, List<Learner> learners, String achievementDetails) {
        mContext = context;
        this.learners = learners;
        this.achievementDetails = achievementDetails;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_list_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Picasso.get().load(learners.get(position).getBadgeUrl()).fit().into(holder.logo);
        holder.name.setText((learners.get(position).getName()));
        holder.achievement.setText(learners.get(position).getAchievement() + achievementDetails);
        holder.country.setText(learners.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView name, achievement, country;
        ImageView logo;

        //TODO use Picasso
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_name);
            logo = itemView.findViewById(R.id.imageview_badge);
            achievement = itemView.findViewById(R.id.textview_achievement);
            country = itemView.findViewById(R.id.textview_country);
        }
    }
}
