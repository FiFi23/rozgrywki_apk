package com.example.rozgrywki.activity.druzyny.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgrywki.R;
import com.example.rozgrywki.model.Druzyny;

import java.util.List;

public class MainAdapterDruzyny extends RecyclerView.Adapter<MainAdapterDruzyny.RecyclerViewAdapter> {

    private Context context;
    private List<Druzyny> druzynies;
    private ItemClickListener itemClickListener;

    public MainAdapterDruzyny(Context context, List<Druzyny> druzyny, ItemClickListener itemClickListener) {
        this.context = context;
        this.druzynies = druzyny;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.karta_druzyny,
                parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Druzyny druzyna = druzynies.get(position);
        holder.tv_nazwa_druzyny.setText(druzyna.getNazwa_Druzyny());
        holder.tv_wygrane.setText(druzyna.getWygrane_Druzyny());
        holder.tv_przegrane.setText(druzyna.getPrzegrane_Druzyny());
    }

    @Override
    public int getItemCount() {
        return druzynies.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nazwa_druzyny, tv_wygrane, tv_przegrane;
        CardView karta_druzyny;
        ItemClickListener itemClickListener;


        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_nazwa_druzyny = itemView.findViewById(R.id.karta_druzyny_nazwa_druzyny);
            tv_wygrane = itemView.findViewById(R.id.karta_druzyny_liczba_wygranych);
            tv_przegrane = itemView.findViewById(R.id.karta_druzyny_liczba_przegranych);
            karta_druzyny = itemView.findViewById(R.id.karta_druzyny);

            this.itemClickListener = itemClickListener;
            karta_druzyny.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
