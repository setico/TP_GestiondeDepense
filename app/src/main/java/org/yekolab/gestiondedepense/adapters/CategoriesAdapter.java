package org.yekolab.gestiondedepense.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View view_dialog = LayoutInflater.from(view.getContext()).inflate(R.layout.delete_categorie_dialog,null,false);
                builder.setView(view_dialog);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, view.getContext().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DepenseDao depenseDao = new DepenseDao(view.getContext());
                        depenseDao.deleteCategorie(categorie);
                        categories.remove(categorie);
                        notifyDataSetChanged();
                        alertDialog.dismiss();

                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, view.getContext().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
