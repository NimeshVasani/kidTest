package com.example.kidtest.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidtest.QuizActivity;
import com.example.kidtest.R;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.myOwnHolder> {
    private final String[] data1 = {"Alphabet Quiz", "Number Quiz", "Animal Quiz", "Color Quiz", "Bird Quiz", "Vegetable Quiz", "Flower Quiz", "Transport Quiz", "Body-Part Quiz"};
    private final int[] data2 = {R.drawable.alphabet_main, R.drawable.numbers, R.drawable.lion, R.drawable.red, R.drawable.sparrow, R.drawable.cauliflower, R.drawable.lotus, R.drawable.airplane, R.drawable.hand};
    Context context;
    View view;
    private List<Integer> highScoreList;

    public QuizAdapter(Context ct, List<Integer> ll) {
        context = ct;
        highScoreList = ll;
    }

    @NonNull
    @Override
    public myOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.quiz_recycle, parent, false);
        return new myOwnHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myOwnHolder holder, int position) {
        holder.quiz_type.setText(data1[(position)]);
        holder.quiz_recycle_logo.setImageResource(data2[(position)]);
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(context, R.anim.swipe, R.anim.up);
        holder.relativeLayout.setOnClickListener(v -> context.startActivity(new Intent(context, QuizActivity.class).putExtra("position", (position)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), options.toBundle()));
        holder.score.setText(highScoreList.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class myOwnHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView quiz_recycle_logo;
        TextView quiz_type, score;

        public myOwnHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.quiz_relative);
            quiz_recycle_logo = itemView.findViewById(R.id.quiz_recycle_logo);
            quiz_type = itemView.findViewById(R.id.quiz_type);
            score = itemView.findViewById(R.id.score);
        }
    }
}
