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

import com.example.kidtest.AlphabetActivity;
import com.example.kidtest.R;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.myOwnHolder> {
    private final Context context;
    private final String[] data = {"Alphabets", "Numbers", "Animals", "Birds", "Flowers", "Months", "Colors", "Vegetable", "Planet", "Transport", "Body Parts"};
    private final int[] data2 = {R.drawable.alphabet_main, R.drawable.numbers, R.drawable.lion, R.drawable.sparrow, R.drawable.lotus, R.drawable.january, R.drawable.red, R.drawable.cauliflower, R.drawable.earth, R.drawable.airplane, R.drawable.hand};

    public LearnAdapter(Context ct) {
        context = ct;
    }

    @NonNull
    @Override
    public myOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.learn_recycle, parent, false);
        return new myOwnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myOwnHolder holder, int position) {
        holder.learn_item.setText(data[position]);
        holder.imageView.setImageResource(data2[position]);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(context, R.anim.up, R.anim.up);
                context.startActivity(new Intent(context, AlphabetActivity.class).putExtra("position", position).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), options.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {

        return data.length;
    }

    public class myOwnHolder extends RecyclerView.ViewHolder {
        TextView learn_item;
        RelativeLayout relativeLayout;
        ImageView imageView;

        public myOwnHolder(@NonNull View itemView) {
            super(itemView);
            learn_item = itemView.findViewById(R.id.learn_item);
            imageView = itemView.findViewById(R.id.imageView1);
            relativeLayout = itemView.findViewById(R.id.relativeAdapterLayout);
        }
    }
}
