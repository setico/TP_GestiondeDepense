package org.yekolab.gestiondedepense.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;

import org.yekolab.gestiondedepense.DepenseAdapter;
import org.yekolab.gestiondedepense.MainActivity;
import org.yekolab.gestiondedepense.R;
import org.yekolab.gestiondedepense.data.DepenseDao;
import org.yekolab.gestiondedepense.models.Categorie;
import org.yekolab.gestiondedepense.models.Depense;

import java.util.ArrayList;
import java.util.Date;

public class DepenseFragment extends Fragment {
    private RecyclerView depenseRecyclerView;
    private DepenseAdapter depenseAdapter;
    private ArrayList<Depense> depenses = new ArrayList<Depense>();
    private DepenseDao depenseDao;
    private ArrayList<Categorie> categories = new ArrayList<Categorie>();

    public DepenseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_depense,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view_dialog = LayoutInflater.from(getActivity()).inflate(R.layout.add_depense_dialog,null,false);
                final AppCompatSpinner spinner = (AppCompatSpinner) view_dialog.findViewById(R.id.categorie);
                final EditText label = (EditText) view_dialog.findViewById(R.id.label);
                final EditText montant = (EditText) view_dialog.findViewById(R.id.montant);
                builder.setView(view_dialog);
                categories = depenseDao.getCategories();
                final ArrayAdapter<Categorie> categorieArrayAdapter = new ArrayAdapter<Categorie>(getActivity(),android.R.layout.simple_dropdown_item_1line,categories);
                spinner.setAdapter(categorieArrayAdapter);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String montantStr = montant.getText().toString().trim();
                        String labelStr = label.getText().toString().trim();
                        Categorie categorie = categorieArrayAdapter.getItem(spinner.getSelectedItemPosition());

                        if(montantStr.isEmpty()||labelStr.isEmpty())
                            Snackbar.make(getView(),getString(R.string.fill_all),Snackbar.LENGTH_SHORT).show();
                        else {
                            Long id = depenseDao.saveDepense(new Depense(null,labelStr,Integer.parseInt(montantStr),new Date().getTime(),categorie));
                            if(id!=-1){
                                Depense depense = depenseDao.getDepense(id);
                                depenses.add(depense);
                                depenseAdapter.notifyDataSetChanged();
                                alertDialog.dismiss();
                            }
                        }


                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_depense, container, false);

        depenseDao = new DepenseDao(getActivity());
        depenses = depenseDao.getDepenses();

        depenseRecyclerView = (RecyclerView) rootView.findViewById(R.id.depense_recycler_view);
        depenseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        depenseAdapter = new DepenseAdapter(depenses);
        depenseRecyclerView.setAdapter(depenseAdapter);
        return rootView;
    }

}
