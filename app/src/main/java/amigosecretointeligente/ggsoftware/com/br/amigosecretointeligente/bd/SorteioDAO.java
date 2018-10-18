package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.DB;

public class SorteioDAO
{
  public static final String COLUNA_COM_FAMILIA = "com_familia";
  public static final String COLUNA_DATA = "data_sorteio";
  public static final String COLUNA_ID = "id";
  public static final String COLUNA_ID_SIMPLEDB = "id_simpledb";
  public static final String COLUNA_TITULO = "titulo";
  public static final String COLUNA_VALOR_PRESENTE = "valor_presente";
  private static String TABLE_NAME = "sorteio_amigo_secreto";
  private static Context ctx;
  private String[] columns;

  public SorteioDAO(Context paramContext)
  {
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "id";
    arrayOfString[1] = "id_simpledb";
    arrayOfString[2] = "data_sorteio";
    arrayOfString[3] = "titulo";
    arrayOfString[4] = "com_familia";
    arrayOfString[5] = "valor_presente";
    this.columns = arrayOfString;
    ctx = paramContext;
  }

  public int buscaIdPorTitulo(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramString;
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "titulo=?", arrayOfString2, null, null, null);
    int i = -1;
    if (localCursor.moveToFirst())
      i = localCursor.getInt(localCursor.getColumnIndex("id"));
    localCursor.close();
    localSQLiteDatabase.close();
    return i;
  }

  public SorteioVO buscaPorId(Integer paramInteger)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramInteger.toString();
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "id=?", arrayOfString2, null, null, null);
    SorteioVO localSorteioVO = null;
    if (localCursor.moveToFirst())
    {
      localSorteioVO = new SorteioVO();
      localSorteioVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localSorteioVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localSorteioVO.setDataSorteio(localCursor.getString(localCursor.getColumnIndex("data_sorteio")));
      localSorteioVO.setTitulo(localCursor.getString(localCursor.getColumnIndex("titulo")));
      localSorteioVO.setComFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("com_familia"))));
      localSorteioVO.setValorPresente(Double.valueOf(localCursor.getDouble(localCursor.getColumnIndex("valor_presente"))));
    }
    localCursor.close();
    localSQLiteDatabase.close();
    return localSorteioVO;
  }

  public boolean delete(SorteioVO paramSorteioVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramSorteioVO.getId().toString();
    if (localSQLiteDatabase.delete(str, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();

      i = 0;
    }
  }

  public boolean insert(SorteioVO paramSorteioVO)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("data_sorteio", paramSorteioVO.getDataSorteio());
    localContentValues.put("id_simpledb", paramSorteioVO.getIdSimpleDB());
    localContentValues.put("titulo", paramSorteioVO.getTitulo());
    localContentValues.put("com_familia", paramSorteioVO.getComFamilia());
    localContentValues.put("valor_presente", paramSorteioVO.getValorPresente());
    if (localSQLiteDatabase.insert(TABLE_NAME, null, localContentValues) > 0L);
    for (int i = 1; ; i = 0)
    {
      localSQLiteDatabase.close();

    }
  }

  public List<SorteioVO> listar()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        localSQLiteDatabase.close();
        return localArrayList;
      }
      SorteioVO localSorteioVO = new SorteioVO();
      localSorteioVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localSorteioVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localSorteioVO.setDataSorteio(localCursor.getString(localCursor.getColumnIndex("data_sorteio")));
      localSorteioVO.setTitulo(localCursor.getString(localCursor.getColumnIndex("titulo")));
      localSorteioVO.setComFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("com_familia"))));
      localSorteioVO.setValorPresente(Double.valueOf(localCursor.getDouble(localCursor.getColumnIndex("valor_presente"))));
      localArrayList.add(localSorteioVO);
    }
  }

  public int proximoId()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT max(id) FROM " + TABLE_NAME, null);
    localCursor.moveToFirst();
    int i = localCursor.getInt(0);
    localCursor.close();
    localSQLiteDatabase.close();
    return i;
  }

  public boolean update(SorteioVO paramSorteioVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id_simpledb", paramSorteioVO.getIdSimpleDB());
    localContentValues.put("data_sorteio", paramSorteioVO.getDataSorteio());
    localContentValues.put("titulo", paramSorteioVO.getTitulo());
    localContentValues.put("com_familia", paramSorteioVO.getComFamilia());
    localContentValues.put("valor_presente", paramSorteioVO.getValorPresente());
    String str = TABLE_NAME;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramSorteioVO.getId().toString();
    if (localSQLiteDatabase.update(str, localContentValues, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();

      i = 0;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.SorteioDAO
 * JD-Core Version:    0.6.0
 */