package com.example.rozgrywki.activity.zawodnicy.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgrywki.R;
import com.example.rozgrywki.model.Zawodnicy;

import java.util.List;

public class MainAdapterZawodnicy extends RecyclerView.Adapter<MainAdapterZawodnicy.RecyclerViewAdapter> {

    private Context context;
    private List<Zawodnicy> zawodnicies;
    private ItemClickListener itemClickListener;

    public MainAdapterZawodnicy(Context context, List<Zawodnicy> zawodnicy, ItemClickListener itemClickListener) {
        this.context = context;
        this.zawodnicies = zawodnicy;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.karta_zawodnicy,
                parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Zawodnicy note = zawodnicies.get(position);
        holder.tv_imie.setText(note.getImie_Zawodnicy());
        holder.tv_id_druzyny.setText(note.getNazwa_d_Zawodnicy());
    }

    @Override
    public int getItemCount() {
        return zawodnicies.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_imie, tv_id_druzyny;
        CardView karta_zawodnicy;
        ItemClickListener itemClickListener;


        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_imie = itemView.findViewById(R.id.karta_zawodnicy_nazwa_zawodnika);
            tv_id_druzyny = itemView.findViewById(R.id.karta_zawodnicy_nazwa_druzyny);
            karta_zawodnicy = itemView.findViewById(R.id.karta_zawodnicy);


            this.itemClickListener = itemClickListener;
            karta_zawodnicy.setOnClickListener(this);

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
