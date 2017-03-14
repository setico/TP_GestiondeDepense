package org.yekolab.gestiondedepense.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    public void onCreate(Bundle savedInstanceState) {
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
