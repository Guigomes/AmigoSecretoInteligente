package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.DB;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.FamiliaVO;

public class FamiliaDAO
{
  public static final String COLUNA_ID = "id";
  public static final String COLUNA_ID_SIMPLEDB = "id_simpledb";
  public static final String COLUNA_NOME = "nome";
  private static String TABLE_NAME = "familia";
  private static Context ctx;
  private String[] columns;

  public FamiliaDAO(Context paramContext)
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "id";
    arrayOfString[1] = "id_simpledb";
    arrayOfString[2] = "nome";
    this.columns = arrayOfString;
    ctx = paramContext;
  }

  public int buscaIdPorNome(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    int i = -1;
    String str = TABLE_NAME;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramString;
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "nome=?", arrayOfString2, null, null, null);
    if (localCursor.moveToFirst())
    {
      i = localCursor.getInt(localCursor.getColumnIndex("id"));
      localCursor.close();
      localSQLiteDatabase.close();
    }
    return i;
  }

  public FamiliaVO buscaPorId(Integer paramInteger)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramInteger.toString();
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "id=?", arrayOfString2, null, null, null);
    FamiliaVO localFamiliaVO = null;
    if (localCursor.moveToFirst())
    {
      localFamiliaVO = new FamiliaVO();
      localFamiliaVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localFamiliaVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localFamiliaVO.setNome(localCursor.getString(localCursor.getColumnIndex("nome")));
    }
    localCursor.close();
    localSQLiteDatabase.close();
    return localFamiliaVO;
  }

  public boolean delete(FamiliaVO paramFamiliaVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramFamiliaVO.getId().toString();
    if (localSQLiteDatabase.delete(str, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();

      i = 0;
      break;
    }
    return true;
  }

  public boolean insert(FamiliaVO paramFamiliaVO)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id_simpledb", paramFamiliaVO.getIdSimpleDB());
    localContentValues.put("nome", paramFamiliaVO.getNome());
    if (localSQLiteDatabase.insert(TABLE_NAME, null, localContentValues) > 0L);
    for (int i = 1; ; i = 0)
    {
      localSQLiteDatabase.close();
      break;
    }
    return true;
  }

  public List<FamiliaVO> listar()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localSQLiteDatabase.close();
        localCursor.close();
        return localArrayList;
      }
      FamiliaVO localFamiliaVO = new FamiliaVO();
      localFamiliaVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localFamiliaVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localFamiliaVO.setNome(localCursor.getString(localCursor.getColumnIndex("nome")));
      localArrayList.add(localFamiliaVO);
    }
  }

  public int proximoId()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT max(id) FROM " + TABLE_NAME, null);
    localCursor.moveToFirst();
    int i = 1 + localCursor.getInt(0);
    localSQLiteDatabase.close();
    localCursor.close();
    return i;
  }

  public boolean update(FamiliaVO paramFamiliaVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id_simpledb", paramFamiliaVO.getIdSimpleDB());
    localContentValues.put("nome", paramFamiliaVO.getNome());
    String str = TABLE_NAME;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramFamiliaVO.getId().toString();
    if (localSQLiteDatabase.update(str, localContentValues, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();

      i = 0;
      break;
    }
    return true;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.FamiliaDAO
 * JD-Core Version:    0.6.0
 */