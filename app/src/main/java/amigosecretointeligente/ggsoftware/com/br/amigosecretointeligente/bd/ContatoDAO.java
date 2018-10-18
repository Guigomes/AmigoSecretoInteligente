package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.DB;

public class ContatoDAO
{
  public static final String COLUNA_ID = "id";
  public static final String COLUNA_ID_FAMILIA = "idFamilia";
  public static final String COLUNA_NOME_CONTATO = "nome_contato";
  public static final String COLUNA_TELEFONE = "telefone";
  private static String TABLE_NAME = "contato";
  private static Context ctx;
  private String[] columns;

  public ContatoDAO(Context paramContext)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "id";
    arrayOfString[1] = "nome_contato";
    arrayOfString[2] = "telefone";
    arrayOfString[3] = "idFamilia";
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
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "nome_contato=?", arrayOfString2, null, null, null);
    if (localCursor.moveToFirst())
    {
      i = localCursor.getInt(localCursor.getColumnIndex("id"));
      localCursor.close();
      localSQLiteDatabase.close();
    }
    return i;
  }

  public int buscaIdPorTelefone(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    int i = -1;
    String str = TABLE_NAME;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramString;
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "telefone=?", arrayOfString2, null, null, null);
    if (localCursor.moveToFirst())
    {
      i = localCursor.getInt(localCursor.getColumnIndex("id"));
      localCursor.close();
      localSQLiteDatabase.close();
    }
    return i;
  }

  public ContatoVO buscaPorId(Integer paramInteger)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramInteger.toString();
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "id=?", arrayOfString2, null, null, null);
    ContatoVO localContatoVO = null;
    if (localCursor.moveToFirst())
    {
      localContatoVO = new ContatoVO();
      localContatoVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localContatoVO.setNomeContato(localCursor.getString(localCursor.getColumnIndex("nome_contato")));
      localContatoVO.setNumeroTelefone(localCursor.getString(localCursor.getColumnIndex("telefone")));
      localContatoVO.setIdFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("idFamilia"))));
    }
    localCursor.close();
    localSQLiteDatabase.close();
    return localContatoVO;
  }

  public boolean delete(ContatoVO paramContatoVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = TABLE_NAME;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramContatoVO.getId().toString();
    if (localSQLiteDatabase.delete(str, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();
      i = 0;
       break;

    }
    return true;
  }

  public boolean insert(ContatoVO paramContatoVO)
  {
    int i = 0;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    Iterator localIterator = listarPorNome(paramContatoVO.getNomeContato()).iterator();
    if (!localIterator.hasNext())
    {
      localContentValues.put("nome_contato", paramContatoVO.getNomeContato());
      localContentValues.put("telefone", paramContatoVO.getNumeroTelefone());
      localContentValues.put("idFamilia", paramContatoVO.getIdFamilia());
      if (localSQLiteDatabase.insert(TABLE_NAME, null, localContentValues) > 0L)
        i = 1;
      localSQLiteDatabase.close();
    }
    while (true)
    {

      ContatoVO localContatoVO = (ContatoVO)localIterator.next();
      if (!paramContatoVO.getNumeroTelefone().equals(localContatoVO.getNumeroTelefone()))
        break;

    }
    return true;
  }

  public List<ContatoVO> listar()
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
      ContatoVO localContatoVO = new ContatoVO();
      localContatoVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localContatoVO.setNomeContato(localCursor.getString(localCursor.getColumnIndex("nome_contato")));
      localContatoVO.setNumeroTelefone(localCursor.getString(localCursor.getColumnIndex("telefone")));
      localContatoVO.setIdFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("idFamilia"))));
      localArrayList.add(localContatoVO);
    }
  }

  public List<ContatoVO> listarPorNome(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + "nome_contato" + " = " + paramString, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localSQLiteDatabase.close();
        localCursor.close();
        return localArrayList;
      }
      ContatoVO localContatoVO = new ContatoVO();
      localContatoVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localContatoVO.setNomeContato(localCursor.getString(localCursor.getColumnIndex("nome_contato")));
      localContatoVO.setNumeroTelefone(localCursor.getString(localCursor.getColumnIndex("telefone")));
      localContatoVO.setIdFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("idFamilia"))));
      localArrayList.add(localContatoVO);
    }
  }

  public int proximoId()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT max(id) FROM " + TABLE_NAME, null);
    localCursor.moveToFirst();
    int i = localCursor.getInt(0);
    localSQLiteDatabase.close();
    localCursor.close();
    return i;
  }

  public boolean update(ContatoVO paramContatoVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("nome_contato", paramContatoVO.getNomeContato());
    localContentValues.put("telefone", paramContatoVO.getNumeroTelefone());
    localContentValues.put("idFamilia", paramContatoVO.getIdFamilia());
    String str = TABLE_NAME;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramContatoVO.getId().toString();
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
 * Qualified Name:     br.com.curso.bd.ContatoDAO
 * JD-Core Version:    0.6.0
 */