package com.example.rozgrywki.activity.mecze.main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgrywki.R;
import com.example.rozgrywki.model.Mecze;

import java.util.List;

public class MainAdapterMecze extends RecyclerView.Adapter<MainAdapterMecze.RecyclerViewAdapter> {

    private Context context;
    private List<Mecze> meczes;
    private ItemClickListener itemClickListener;

    public MainAdapterMecze(Context context, List<Mecze> mecze, ItemClickListener itemClickListener) {
        this.context = context;
        this.meczes = mecze;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.karta_mecze,
                parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Mecze mecze = meczes.get(position);
        holder.tv_nazwa_gospodarz.setText(mecze.getNazwa_gosp_Mecze());
        holder.tv_nazwa_gosc.setText(mecze.getNazwa_gosc_Mecze());
        holder.tv_wynik_gospodarz.setText(mecze.getWynik_gosp_Mecze());
        holder.tv_wynik_gosc.setText(mecze.getWynik_gosc_Mecze());
        holder.tv_data.setText(mecze.getData_Mecze());

        //Ustawianie kolorow wyników

        if(mecze.getWynik_gosp_Mecze().isEmpty() || mecze.getWynik_gosc_Mecze().isEmpty()){}
        else {
            int wynik_gosp = Integer.parseInt(mecze.getWynik_gosp_Mecze());
            int wynik_gosc = Integer.parseInt(mecze.getWynik_gosc_Mecze());

            //Jeśli remis
            if(wynik_gosp == wynik_gosc){
                holder.tv_wynik_gospodarz.setTextColor(Color.parseColor("#C7FFE1"));
                holder.tv_wynik_gosc.setTextColor(Color.parseColor("#A9E8D9"));

            }
            else { // Jeśli gospodarz wygrał
                if(wynik_gosp > wynik_gosc){
                    holder.tv_wynik_gospodarz.setTextColor(Color.parseColor("#00FF00"));
                    holder.tv_wynik_gosc.setTextColor(Color.parseColor("#FF0000"));
                }else { // Jeśli gośc wygrał
                    holder.tv_wynik_gospodarz.setTextColor(Color.parseColor("#FF0000"));
                    holder.tv_wynik_gosc.setTextColor(Color.parseColor("#00FF00"));
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return meczes.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nazwa_gospodarz, tv_nazwa_gosc, tv_wynik_gospodarz, tv_wynik_gosc, tv_data;
        CardView karta_mecze;
        ItemClickListener itemClickListener;


        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_nazwa_gospodarz = itemView.findViewById(R.id.karta_mecze_nazwa_gospodarz);
            tv_nazwa_gosc = itemView.findViewById(R.id.karta_mecze_nazwa_gosc);
            tv_wynik_gospodarz = itemView.findViewById(R.id.karta_mecze_wynik_gospodarz);
            tv_wynik_gosc = itemView.findViewById(R.id.karta_mecze_wynik_gosc);
            tv_data = itemView.findViewById(R.id.karta_mecze_data);
            karta_mecze = itemView.findViewById(R.id.karta_mecze);


            this.itemClickListener = itemClickListener;
            karta_mecze.setOnClickListener(this);

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
