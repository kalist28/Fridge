package com.example.fridge.main.choice_dish_activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.ImageView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridge.R;
import com.example.fridge.database.Key;
import com.example.fridge.entity.Dish;

import com.example.fridge.entity.Ingredient;
import com.example.fridge.main.choice_dish_activity.comparators.SortByEnergy;
import com.example.fridge.main.choice_dish_activity.comparators.SortBySelectedProducts;
import com.example.fridge.main.choice_dish_activity.comparators.SortByTime;
import com.example.fridge.main.product_selection_activity.ProductSelector;
import com.example.fridge.main.recipe_activity.RecipeActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DishRVAdapter
        extends RecyclerView.Adapter<DishRVAdapter.ViewHolder> {

    private List<Dish> dishes;
    private Context context;

    private static final String CHANNEL_ID = "CHANNEL_ID";
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    public DishRVAdapter(Context context,
                         List<Dish> dishes) {
        this.dishes     = dishes;
        this.context    = context;

        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannelIfNeeded(notificationManager);
         builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_apple)
                        .setContentTitle("Title change")
                        .setContentText("Notification text change");


    }



    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void sortList(int id) {
        switch (id) {
            case 1:
                Collections.sort(dishes, new SortByEnergy());
                break;
            case 2:
                Collections.sort(dishes, new SortBySelectedProducts());
            default:
                Collections.sort(dishes, new SortByTime());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(context).inflate(R.layout.card_dish, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.initialize(position);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameView;
        TextView energyView;
        TextView cookingTimeView;
        TextView countProductsView;
        ImageView imageView;

        ViewHolder(@NonNull View view) {
            super(view);
            cardView            = view.findViewById(R.id.card_view);
            nameView            = view.findViewById(R.id.dish_name);
            imageView           = view.findViewById(R.id.dish_image);
            energyView          = view.findViewById(R.id.energy_value);
            cookingTimeView     = view.findViewById(R.id.cooking_time);
            countProductsView   = view.findViewById(R.id.count_products);
        }

        void initialize(final int id) {
            final Dish dish = dishes.get(id);

            nameView.setText(dishes.get(id).getName());
            energyView.setText(dish.getEnergyValue() + " Ккал");
            cookingTimeView.setText(dish.getCookingTime() + " минут");
            StringBuilder builder = new StringBuilder();
            builder.append(ProductSelector.getCountChoiceProducts())
                    .append('/')
                    .append(dish.getProductCount());
            countProductsView.setText(builder.toString());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RecipeActivity.class);
                    intent.putExtra(Key.ID, dish.getId());
                    context.startActivity(intent);

                    //Timer timer = new Timer();
                    //timer.schedule(new MyTimerTask(), 10000);


                   // notificationManager.notify(101, DishRVAdapter.this.builder.build());

                }
            });

            Picasso.get().load(dish.getImageUri()).into(imageView);
        }
    }

    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            notificationManager.notify(1, builder.build());
        }
    }
}
