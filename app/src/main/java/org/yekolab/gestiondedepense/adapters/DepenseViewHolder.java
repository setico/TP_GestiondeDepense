package org.yekolab.gestiondedepense.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.yekolab.gestiondedepense.R;

/**
 * Created by setico on 3/18/17.
 */

public class DepenseViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView label;
    public TextView categorie;
    public TextView date;
    public TextView montant;

    public DepenseViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        label = (TextView) itemView.findViewById(R.id.label);
        categorie = (TextView) itemView.findViewById(R.id.categorie);
        date = (TextView) itemView.findViewById(R.id.date);
        montant = (TextView) itemView.findViewById(R.id.montant);
    }
}
