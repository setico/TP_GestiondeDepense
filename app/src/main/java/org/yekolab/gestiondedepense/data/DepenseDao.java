package org.yekolab.gestiondedepense.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.yekolab.gestiondedepense.contracts.DepenseContract;
import org.yekolab.gestiondedepense.models.Categorie;
import org.yekolab.gestiondedepense.models.Depense;

import java.util.ArrayList;

/**
 * Created by setico on 3/13/17.
 */

public class DepenseDao extends DbHelper {

    public DepenseDao(Context context) {
        super(context);
    }

    public Long saveCategorie(Categorie categorie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DepenseContract.CategorieEntry.LABEL,categorie.getLabel());
        Long id = db.insert(
                DepenseContract.CategorieEntry.TABLE_NAME,
                null,
                values
        );
        db.close();
        return id;
    }

    public Long saveDepense(Depense depense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DepenseContract.DepenseEntry.LABEL,depense.getLabel());
        values.put(DepenseContract.DepenseEntry.MONTANT, depense.getMontant());
        values.put(DepenseContract.DepenseEntry.DATE,depense.getDate());
        values.put(DepenseContract.DepenseEntry.CATEGORIE,depense.getCategorie().getId());
    Long id = db.insert(
            DepenseContract.DepenseEntry.TABLE_NAME,
            null,
            values
    );
        db.close();
        return id;
    }

    public Categorie getCategorie(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_CATEGORIE = "SELECT "+
                DepenseContract.CategorieEntry._ID+", "+
                DepenseContract.CategorieEntry.LABEL+" FROM "+
                DepenseContract.CategorieEntry.TABLE_NAME+" WHERE "+
                DepenseContract.CategorieEntry._ID+"="+id;
        Cursor cursor = db.rawQuery(SELECT_CATEGORIE,null);
        if(cursor.moveToFirst())
            return new Categorie(cursor.getLong(0), cursor.getString(1));
        return null;
    }

    public Depense getDepense(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_DEPENSE = "SELECT "+
                DepenseContract.DepenseEntry.TABLE_NAME+"."+DepenseContract.DepenseEntry._ID+", "+
                DepenseContract.DepenseEntry.LABEL+", "+
                DepenseContract.DepenseEntry.MONTANT+", "+
                DepenseContract.DepenseEntry.DATE+", "+
                DepenseContract.DepenseEntry.CATEGORIE+", "+
                DepenseContract.CategorieEntry.LABEL+" FROM "+
                DepenseContract.DepenseEntry.TABLE_NAME+", "+
                DepenseContract.CategorieEntry.TABLE_NAME+" WHERE "+
                DepenseContract.DepenseEntry.CATEGORIE+"="+
                DepenseContract.CategorieEntry.TABLE_NAME+"."+DepenseContract.CategorieEntry._ID+" AND "+
                DepenseContract.DepenseEntry.TABLE_NAME+"."+DepenseContract.DepenseEntry._ID+"="+id;

        Cursor cursor = db.rawQuery(SELECT_DEPENSE,null);
        if (cursor.moveToFirst())
            return new Depense(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getLong(3),
                    new Categorie(cursor.getLong(4),cursor.getString(5)));
        return null;
    }

    public ArrayList<Depense> getDepenses(){
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_DEPENSE = "SELECT "+
                DepenseContract.DepenseEntry.TABLE_NAME+"."+DepenseContract.DepenseEntry._ID+", "+
                DepenseContract.DepenseEntry.LABEL+", "+
                DepenseContract.DepenseEntry.MONTANT+", "+
                DepenseContract.DepenseEntry.DATE+", "+
                DepenseContract.DepenseEntry.CATEGORIE+", "+
                DepenseContract.CategorieEntry.LABEL+" FROM "+
                DepenseContract.DepenseEntry.TABLE_NAME+", "+
                DepenseContract.CategorieEntry.TABLE_NAME+" WHERE "+
                DepenseContract.DepenseEntry.CATEGORIE+"="+
                DepenseContract.CategorieEntry.TABLE_NAME+"."+DepenseContract.CategorieEntry._ID;

        ArrayList<Depense> depenses = new ArrayList<Depense>();
        Cursor cursor = db.rawQuery(SELECT_DEPENSE,null);
        if(cursor.moveToFirst())
            do {
                depenses.add(
                        new Depense(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getLong(3),
                                new Categorie(cursor.getLong(4),cursor.getString(5))));
            }while(cursor.moveToNext());

        return depenses;
        }

    public ArrayList<Categorie> getCategories(){
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_CATEGORIE = "SELECT "+
                DepenseContract.CategorieEntry._ID+", "+
                DepenseContract.CategorieEntry.LABEL+" FROM "+
                DepenseContract.CategorieEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery(SELECT_CATEGORIE,null);
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        if(cursor.moveToFirst())
            do{
                categories.add(new Categorie(cursor.getLong(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        return categories;
    }

    public void updateCategorie(Categorie categorie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DepenseContract.CategorieEntry._ID, categorie.getId());
        values.put(DepenseContract.CategorieEntry.LABEL, categorie.getLabel());
        db.update(DepenseContract.CategorieEntry.TABLE_NAME,
                values,
                DepenseContract.CategorieEntry._ID+"=?",
                new String[]{String.valueOf(categorie.getId())});
    }

    public void updateDepense(Depense depense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DepenseContract.DepenseEntry.LABEL,depense.getLabel());
        values.put(DepenseContract.DepenseEntry.MONTANT, depense.getMontant());
        values.put(DepenseContract.DepenseEntry.DATE,depense.getDate());
        values.put(DepenseContract.DepenseEntry.CATEGORIE,depense.getCategorie().getId());
        db.update(DepenseContract.DepenseEntry.TABLE_NAME,
                values,
                DepenseContract.DepenseEntry._ID+"=?",
                new String[]{String.valueOf(depense.getId())});
    }

    public void deleteCategorie(Categorie categorie){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DepenseContract.CategorieEntry.TABLE_NAME,
                DepenseContract.CategorieEntry._ID+"=?",
                new String[]{String.valueOf(categorie.getId())});
    }

    public void deleteDepense(Depense depense){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DepenseContract.DepenseEntry.TABLE_NAME,
                DepenseContract.DepenseEntry._ID+"=?",
                new String[]{String.valueOf(depense.getId())});
    }

    public void deleteCategories(ArrayList<Categorie> categories){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try{
            for(Categorie categorie:categories) {
                db.delete(DepenseContract.CategorieEntry.TABLE_NAME,
                        DepenseContract.CategorieEntry._ID + "=?",
                        new String[]{String.valueOf(categorie.getId())});
            }
            db.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            db.endTransaction();
        }
    }


}
