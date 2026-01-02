package com.example.findstations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBClass extends SQLiteOpenHelper {

    public static String dbname ="agroapp";

    public static String url = "http://192.168.131.159/agroapp/";

    public static String urlUsers= url + "users.php";
    public  static String urlLogin = url + "login.php";
    public static String urlProfile = url + "profile.php";

    public static String urlBooking = url + "booking.php";
    public static String urlViewbooking = url + "viewbooking.php";
    public static String urlUpdateProfile = url + "updateprofile.php";
    public static String urlAddSeeds = url + "addseeds.php";

    public static String urlAddPesticides = url + "addpesticides.php";

    public static String urlAddFertilizers = url + "addfertilizers.php";

    public static String urlAddFungicides = url + "addfungicides.php";

    public static String urlAddHerbicides = url + "addherbicides.php";

    public static String urlAddFarmmachines = url + "addfarmmachines.php";

    public  static String urlRegistration = url + "register.php";

    public static String urlseeds = url + "seeds.php";

    public static String urlpesticides = url + "pesticides.php";

    public static String urlfertilizers = url + "fertilizers.php";

    public static String urlfungicides = url + "fungicides.php";

    public static String urlherbicides = url + "herbicides.php";

    public static String urlfarmmachines = url + "farmmachines.php";

    public static SQLiteDatabase database;

    public DBClass(Context context) {
        super(context, DBClass.dbname,null,1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void execNonQuery(String query){
        //Execute insert,update,Delete,Create table
        database.execSQL(query);
    }
    public static Cursor getCursorData(String query){
        //Log.d("SQuery", query);
        Cursor res =  database.rawQuery(query, null);
        return res;
    }

    public static String getSingleValue(String query) {
        try {
            Cursor res = getCursorData(query);
            String value = "";
            if (res.moveToNext()) {
                return res.getString(0);
            }
            return value;
        }
        catch (Exception ex)
        {
            return "";
        }
    }
    public static boolean checkIfRecordExist(String query){
        //Log.e("CheckQuery", query);
        Cursor res =  database.rawQuery(query, null );
        if(res.getCount() > 0)
            return true;
        else
            return false;
    }
}

