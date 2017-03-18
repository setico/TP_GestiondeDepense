package org.yekolab.gestiondedepense.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import org.yekolab.gestiondedepense.MainActivity;
import org.yekolab.gestiondedepense.R;
import org.yekolab.gestiondedepense.data.DepenseDao;
import org.yekolab.gestiondedepense.models.Categorie;

import java.util.ArrayList;

/**
 * Created by setico on 3/14/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategorieViewHolder> {
    ArrayList<Categorie> categories;

    public CategoriesAdapter(ArrayList<Categorie> categories) {
        this.categories = categories;
    }

    @Override
    public CategorieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_items,parent,false);
        return new CategorieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategorieViewHolder holder, int position) {
        final Categorie categorie = categories.get(position);
        holder.label.setText(categorie.getLabel());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //click
            }
        });

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                return true;
            }
        });
        holder.select.setChecked(false);

        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    MainActivity.categories.add(categorie);
                else
                    MainActivity.categories.remove(categorie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
