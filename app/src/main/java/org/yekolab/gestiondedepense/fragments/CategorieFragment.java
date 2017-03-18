package org.yekolab.gestiondedepense.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.yekolab.gestiondedepense.MainActivity;
import org.yekolab.gestiondedepense.R;
import org.yekolab.gestiondedepense.adapters.CategoriesAdapter;
import org.yekolab.gestiondedepense.data.DepenseDao;
import org.yekolab.gestiondedepense.models.Categorie;

import java.util.ArrayList;


public class CategorieFragment extends Fragment {
    private RecyclerView categorieRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private AppCompatButton ajouterButton;
    private EditText labelEditText;
    private DepenseDao depenseDao;
    private ArrayList<Categorie> categories = new ArrayList<>();

    public CategorieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_categorie,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view_dialog = LayoutInflater.from(getActivity()).inflate(R.layout.delete_categorie_dialog,null,false);
                builder.setView(view_dialog);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        depenseDao.deleteCategories(MainActivity.categories);
                        categories.clear();
                        for(Categorie categorie:depenseDao.getCategories())
                        categories.add(categorie);
                        categoriesAdapter.notifyDataSetChanged();
                        MainActivity.categories.clear();
                        alertDialog.dismiss();

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
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categorie, container, false);

        depenseDao = new DepenseDao(getActivity());
        categories = depenseDao.getCategories();
        categoriesAdapter = new CategoriesAdapter(categories);
        categorieRecyclerView = (RecyclerView) rootView.findViewById(R.id.categorie_recycler_view);
        ajouterButton = (AppCompatButton) rootView.findViewById(R.id._ajouter);
        labelEditText = (EditText) rootView.findViewById(R.id.label);
        categorieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        categorieRecyclerView.setAdapter(categoriesAdapter);

        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String labelStr = labelEditText.getText().toString().trim();
                if(labelStr.isEmpty()){
                    Snackbar.make(getView(),getString(R.string.empty_label_notif),Snackbar.LENGTH_SHORT).show();
                }else {
                    Categorie categorie = new Categorie(new Long(0),labelStr);
                    Long id = depenseDao.saveCategorie(categorie);
                    categorie = depenseDao.getCategorie(id);
                    categories.add(categorie);
                    categoriesAdapter.notifyDataSetChanged();
                    labelEditText.setText("");
                }
            }
        });

    return rootView;
    }





}
