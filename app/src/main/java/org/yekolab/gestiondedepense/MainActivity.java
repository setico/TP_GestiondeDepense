package org.yekolab.gestiondedepense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.yekolab.gestiondedepense.data.DbHelper;
import org.yekolab.gestiondedepense.data.DepenseDao;
import org.yekolab.gestiondedepense.models.Categorie;

public class MainActivity extends AppCompatActivity {
    DepenseDao depenseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        depenseDao = new DepenseDao(this);
        depenseDao.saveCategorie(new Categorie(new Long(0),"Categorie 1"));
        Log.i("TAG",depenseDao.getCategorie(new Long(1)).toString());
    }
}
