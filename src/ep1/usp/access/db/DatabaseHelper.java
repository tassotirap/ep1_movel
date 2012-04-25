package ep1.usp.access.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

abstract class DatabaseHelper extends SQLiteOpenHelper
{

	private static int VERSAO_DO_BD = 3;
	private static String NOME_DO_BANCO = "ep1.tasso";

	public DatabaseHelper(Context ctx)
	{
		super(ctx, NOME_DO_BANCO, null, VERSAO_DO_BD);
		
	}

	public void onCreate(SQLiteDatabase bd)
	{
		try
		{
			CreateMapConfigs(bd);
			CreateMapConfigsView(bd);
			CreateOverlay(bd);			
			CreateRestaurants(bd);
			CreateRestaurantCommets(bd);
		}
		catch (Exception e)
		{
		}
	}

	private void DropConfigs(SQLiteDatabase bd)
	{
		bd.execSQL("DROP TABLE MAP_CONFIG;");
	}

	private void CreateMapConfigs(SQLiteDatabase bd) throws Exception
	{
		try
		{
			bd.execSQL("CREATE TABLE MAP_CONFIG (usp_center_latitude INTEGER, usp_center_longitude INTEGER);");
			ContentValues contentValues = new ContentValues();
			contentValues.put("usp_center_latitude", -23562283);
			contentValues.put("usp_center_longitude", -46726241);
			bd.insertOrThrow("MAP_CONFIG", null, contentValues);
		}
		catch (Exception e)
		{
			throw new Exception("Erro ao Criar a base de dados MAP_CONFIG");
		}
	}

	private void DropMapConfigsView(SQLiteDatabase bd)
	{
		bd.execSQL("DROP TABLE MAP_CONFIG_VIEW;");
	}

	private void CreateMapConfigsView(SQLiteDatabase bd) throws Exception
	{
		try
		{
			bd.execSQL("CREATE TABLE MAP_CONFIG_VIEW (type INTEGER PRIMARY KEY, name INTEGER, enable INTEGER);");

			ContentValues contentValues = new ContentValues();
			contentValues.put("type", 1);
			contentValues.put("name", "bus_stop");
			contentValues.put("enable", "1");
			bd.insertOrThrow("MAP_CONFIG_VIEW", null, contentValues);

			contentValues.put("type", 2);
			contentValues.put("name", "locations");
			contentValues.put("enable", "1");
			bd.insertOrThrow("MAP_CONFIG_VIEW", null, contentValues);

			contentValues.put("type", 3);
			contentValues.put("name", "restaurant");
			contentValues.put("enable", "0");
			bd.insertOrThrow("MAP_CONFIG_VIEW", null, contentValues);
		}
		catch (Exception e)
		{
			throw new Exception("Erro ao Criar a base de dados MAP_CONFIG_VIEW");
		}
	}

	private void DropOverlay(SQLiteDatabase bd)
	{
		bd.execSQL("DROP TABLE MAP_OVERLAY;");
	}

	private void CreateOverlay(SQLiteDatabase bd)
	{
		bd.execSQL("CREATE TABLE MAP_OVERLAY (id INTEGER PRIMARY KEY, type INTEGER NOT NULL, latitude INTEGER NOT NULL, longitude INTEGER NOT NULL, name TEXT NOT NULL);");
	}
	private void DropRestaurants(SQLiteDatabase bd)
	{
		bd.execSQL("DROP TABLE RESTAURANTS;");
	}
	
	private void CreateRestaurants(SQLiteDatabase bd)
	{
		bd.execSQL("CREATE TABLE RESTAURANTS (id INTEGER PRIMARY KEY, name TEXT NOT NULL, status INTEGER NOT NULL);");
	}
	
	private void DropRestaurantCommets(SQLiteDatabase bd)
	{
		bd.execSQL("DROP TABLE RESTAURANT_COMMENTS;");
	}
	
	private void CreateRestaurantCommets(SQLiteDatabase bd)
	{
		bd.execSQL("CREATE TABLE RESTAURANT_COMMENTS (idRestaurant INTEGER NOT NULL, comment TEXT NOT NULL, date TEXT NOT NULL, status INTEGER NOT NULL);");
	}

	public void onUpgrade(SQLiteDatabase bd, int versaoAnterior, int versaoNova)
	{

		try
		{
			DropConfigs(bd);
			CreateMapConfigs(bd);

			DropMapConfigsView(bd);
			CreateMapConfigsView(bd);

			DropOverlay(bd);
			CreateOverlay(bd);
			
			DropRestaurants(bd);
			CreateRestaurants(bd);
			
			DropRestaurantCommets(bd);
			CreateRestaurantCommets(bd);
		}
		catch (Exception e)
		{
		}
	}
}
