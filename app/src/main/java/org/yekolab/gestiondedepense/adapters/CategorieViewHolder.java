package org.yekolab.gestiondedepense.adapters;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.yekolab.gestiondedepense.R;

/**
 * Created by setico on 3/14/17.
 */

public class CategorieViewHolder extends RecyclerView.ViewHolder {

    public TextView label;
    public View view;
    public AppCompatCheckBox select;

    public CategorieViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        label = (TextView) itemView.findViewById(R.id.label);
        select = (AppCompatCheckBox) itemView.findViewById(R.id.select);
    }
}
