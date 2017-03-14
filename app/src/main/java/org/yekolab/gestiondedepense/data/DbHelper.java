package org.yekolab.gestiondedepense.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.yekolab.gestiondedepense.contracts.DepenseContract;
import org.yekolab.gestiondedepense.models.Depense;

/**
 * Created by setico on 3/13/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "depense";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //table categorie
        final String SQL_CATEGORIE ="CREATE TABLE "+
                DepenseContract.CategorieEntry.TABLE_NAME+" ("+
                DepenseContract.CategorieEntry._ID+" INTEGER PRIMARY KEY,"+
                DepenseContract.CategorieEntry.LABEL+" TEXT);";

        //table depense
        final String SQL_DEPENSE = "CREATE TABLE "+
                DepenseContract.DepenseEntry.TABLE_NAME+" ("+
                DepenseContract.DepenseEntry._ID+" INTEGER PRIMARY KEY,"+
                DepenseContract.DepenseEntry.LABEL+" TEXT,"+
                DepenseContract.DepenseEntry.MONTANT+" INTEGER,"+
                DepenseContract.DepenseEntry.DATE+" NUMERIC,"+
                DepenseContract.DepenseEntry.CATEGORIE+" INTEGER);";

        sqLiteDatabase.execSQL(SQL_CATEGORIE);
        sqLiteDatabase.execSQL(SQL_DEPENSE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
