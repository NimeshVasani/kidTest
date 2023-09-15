package com.example.kidtest.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidtest.MainActivity;
import com.example.kidtest.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.myOwnHolder> {
    Context context;
    String[] itemName;
    TextToSpeech t1;
    int[] item_image;

    public ItemAdapter(Context ctx, String[] data, TextToSpeech tts, int[] image) {
        context = ctx;
        itemName = data;
        item_image = image;
        t1 = tts;

    }

    @NonNull
    @Override
    public myOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_item, parent, false);

        return new myOwnHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myOwnHolder holder, int position) {
        holder.item_name.setText(itemName[position]);
        if (item_image != null) {
            holder.item_image.setImageResource(item_image[position]);
        }

        holder.relativeLayout.setOnClickListener((View.OnClickListener) v -> {
            View popupView = LayoutInflater.from(context).inflate(R.layout.popup_layout, null);
            popupView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.myanim));
            final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.anim.myanim);
            ImageButton btnDismiss = (ImageButton) popupView.findViewById(R.id.ib_close);
            ImageView imageView = popupView.findViewById(R.id.popup_image);
            imageView.setImageResource(item_image[(position)]);
            new CountDownTimer(2000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    popupWindow.dismiss();
                }
            }.start();
            btnDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            popupWindow.showAsDropDown(popupView, 0, 0);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.t1.speak(itemName[(position)], TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }).start();

        });

    }


    @Override
    public int getItemCount() {
        return itemName.length;
    }

    public class myOwnHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView item_name;
        ImageView item_image;

        public myOwnHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.inner_items);
            item_name = itemView.findViewById(R.id.item_name);
            item_image = itemView.findViewById(R.id.item_image);
        }
    }
}
