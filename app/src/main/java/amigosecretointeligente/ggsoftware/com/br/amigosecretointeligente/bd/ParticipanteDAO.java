package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.DB;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.ParticipanteVO;

public class ParticipanteDAO
{
  public static final String COLUNA_FAMILIA = "familia";
  public static final String COLUNA_ID = "id";
  public static final String COLUNA_IDSIMPLEDB = "id_simpledb";
  public static final String COLUNA_MEU_PRESENTE = "meu_presente";
  public static final String COLUNA_NOME = "nome";
  public static final String COLUNA_PARTICIPANTE_SORTEADO = "participante_sorteado";
  public static final String COLUNA_PARTICIPANTE_SORTEADO_SIMPLEDB = "participante_sorteado_simpledb";
  public static final String COLUNA_PRESENTE_SORTEADO = "presente_sorteado";
  public static final String COLUNA_SORTEADO = "sorteado";
  public static final String COLUNA_SORTEIO = "sorteio";
  public static final String COLUNA_SORTEIO_SIMPLEDB = "sorteio_simpledb";
  public static final String COLUNA_TELEFONE = "telefone";
  private static Context ctx;
  private static String table_name = "participante_sorteio";
  private String[] columns;

  public ParticipanteDAO(Context paramContext)
  {
    String[] arrayOfString = new String[12];
    arrayOfString[0] = "id";
    arrayOfString[1] = "id_simpledb";
    arrayOfString[2] = "nome";
    arrayOfString[3] = "sorteio";
    arrayOfString[4] = "familia";
    arrayOfString[5] = "telefone";
    arrayOfString[6] = "sorteado";
    arrayOfString[7] = "participante_sorteado";
    arrayOfString[8] = "participante_sorteado_simpledb";
    arrayOfString[9] = "meu_presente";
    arrayOfString[10] = "presente_sorteado";
    arrayOfString[11] = "sorteio_simpledb";
    this.columns = arrayOfString;
    ctx = paramContext;
  }

  public ParticipanteVO buscaPorId(Integer paramInteger)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = table_name;
    String[] arrayOfString1 = this.columns;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = paramInteger.toString();
    Cursor localCursor = localSQLiteDatabase.query(str, arrayOfString1, "id=?", arrayOfString2, null, null, null);
    ParticipanteVO localParticipanteVO = null;
    if (localCursor.moveToFirst())
    {
      localParticipanteVO = new ParticipanteVO();
      localParticipanteVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localParticipanteVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localParticipanteVO.setNome(localCursor.getString(localCursor.getColumnIndex("nome")));
      localParticipanteVO.setSorteio(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sorteio"))));
      localParticipanteVO.setFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("familia"))));
      localParticipanteVO.setTelefone(localCursor.getString(localCursor.getColumnIndex("telefone")));
      localParticipanteVO.setSorteado(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sorteado"))));
      localParticipanteVO.setParticipanteSorteado(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("participante_sorteado"))));
      localParticipanteVO.setParticipanteSorteadoSimpleDB(localCursor.getString(localCursor.getColumnIndex("participante_sorteado_simpledb")));
      localParticipanteVO.setMeuPresente(localCursor.getString(localCursor.getColumnIndex("meu_presente")));
      localParticipanteVO.setPresenteSorteado(localCursor.getString(localCursor.getColumnIndex("presente_sorteado")));
      localParticipanteVO.setSimpleIDSorteio(localCursor.getString(localCursor.getColumnIndex("sorteio_simpledb")));
    }
    localCursor.close();
    localSQLiteDatabase.close();
    return localParticipanteVO;
  }

  public void deletarParticipantesSorteio(int paramInt)
  {
    Iterator localIterator = listarPorSorteio(paramInt).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      delete((ParticipanteVO)localIterator.next());
    }
  }

  public boolean delete(ParticipanteVO paramParticipanteVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    String str = table_name;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramParticipanteVO.getId().toString();
    if (localSQLiteDatabase.delete(str, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();

      i = 0;
    }
  }

  public boolean insert(ParticipanteVO paramParticipanteVO)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id_simpledb", paramParticipanteVO.getIdSimpleDB());
    localContentValues.put("nome", paramParticipanteVO.getNome());
    localContentValues.put("sorteio", paramParticipanteVO.getSorteio());
    localContentValues.put("familia", paramParticipanteVO.getFamilia());
    localContentValues.put("telefone", paramParticipanteVO.getTelefone());
    localContentValues.put("sorteio_simpledb", paramParticipanteVO.getSimpleIDSorteio());
    if (localSQLiteDatabase.insert(table_name, null, localContentValues) > 0L);
    for (int i = 1; ; i = 0)
    {
      localSQLiteDatabase.close();

    }
  }

  public List<ParticipanteVO> listar()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM " + table_name, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        localSQLiteDatabase.close();
        return localArrayList;
      }
      ParticipanteVO localParticipanteVO = new ParticipanteVO();
      localParticipanteVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localParticipanteVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localParticipanteVO.setNome(localCursor.getString(localCursor.getColumnIndex("nome")));
      localParticipanteVO.setSorteio(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sorteio"))));
      localParticipanteVO.setFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("familia"))));
      localParticipanteVO.setTelefone(localCursor.getString(localCursor.getColumnIndex("telefone")));
      localParticipanteVO.setSorteado(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sorteado"))));
      localParticipanteVO.setParticipanteSorteado(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("participante_sorteado"))));
      localParticipanteVO.setParticipanteSorteadoSimpleDB(localCursor.getString(localCursor.getColumnIndex("participante_sorteado_simpledb")));
      localParticipanteVO.setMeuPresente(localCursor.getString(localCursor.getColumnIndex("meu_presente")));
      localParticipanteVO.setPresenteSorteado(localCursor.getString(localCursor.getColumnIndex("presente_sorteado")));
      localParticipanteVO.setSimpleIDSorteio(localCursor.getString(localCursor.getColumnIndex("sorteio_simpledb")));
      localArrayList.add(localParticipanteVO);
    }
  }

  public List<ParticipanteVO> listarPorSorteio(int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM " + table_name + " WHERE sorteio = " + paramInt, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        localSQLiteDatabase.close();
        return localArrayList;
      }
      ParticipanteVO localParticipanteVO = new ParticipanteVO();
      localParticipanteVO.setId(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("id"))));
      localParticipanteVO.setIdSimpleDB(localCursor.getString(localCursor.getColumnIndex("id_simpledb")));
      localParticipanteVO.setNome(localCursor.getString(localCursor.getColumnIndex("nome")));
      localParticipanteVO.setSorteio(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sorteio"))));
      localParticipanteVO.setFamilia(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("familia"))));
      localParticipanteVO.setTelefone(localCursor.getString(localCursor.getColumnIndex("telefone")));
      localParticipanteVO.setSorteado(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("sorteado"))));
      localParticipanteVO.setParticipanteSorteado(Integer.valueOf(localCursor.getInt(localCursor.getColumnIndex("participante_sorteado"))));
      localParticipanteVO.setParticipanteSorteadoSimpleDB(localCursor.getString(localCursor.getColumnIndex("participante_sorteado_simpledb")));
      localParticipanteVO.setMeuPresente(localCursor.getString(localCursor.getColumnIndex("meu_presente")));
      localParticipanteVO.setPresenteSorteado(localCursor.getString(localCursor.getColumnIndex("presente_sorteado")));
      localParticipanteVO.setSimpleIDSorteio(localCursor.getString(localCursor.getColumnIndex("sorteio_simpledb")));
      if (localParticipanteVO.getMeuPresente() == null)
        localParticipanteVO.setMeuPresente("");
      if (localParticipanteVO.getPresenteSorteado() == null)
        localParticipanteVO.setPresenteSorteado("");
      localArrayList.add(localParticipanteVO);
    }
  }

  public int proximoId()
  {
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT max(id) FROM " + table_name, null);
    localCursor.moveToFirst();
    int i = localCursor.getInt(0);
    localCursor.close();
    localSQLiteDatabase.close();
    return i;
  }

  public boolean update(ParticipanteVO paramParticipanteVO)
  {
    int i = 1;
    SQLiteDatabase localSQLiteDatabase = new DB(ctx).getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("nome", paramParticipanteVO.getNome());
    localContentValues.put("sorteio", paramParticipanteVO.getSorteio());
    localContentValues.put("familia", paramParticipanteVO.getFamilia());
    localContentValues.put("sorteado", paramParticipanteVO.getSorteado());
    localContentValues.put("telefone", paramParticipanteVO.getTelefone());
    localContentValues.put("participante_sorteado", paramParticipanteVO.getParticipanteSorteado());
    localContentValues.put("participante_sorteado_simpledb", paramParticipanteVO.getParticipanteSorteadoSimpleDB());
    localContentValues.put("meu_presente", paramParticipanteVO.getMeuPresente());
    localContentValues.put("presente_sorteado", paramParticipanteVO.getPresenteSorteado());
    localContentValues.put("sorteio_simpledb", paramParticipanteVO.getSimpleIDSorteio());
    String str = table_name;
    String[] arrayOfString = new String[i];
    arrayOfString[0] = paramParticipanteVO.getId().toString();
    if (localSQLiteDatabase.update(str, localContentValues, "id=?", arrayOfString) > 0);
    while (true)
    {
      localSQLiteDatabase.close();

      i = 0;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.ParticipanteDAO
 * JD-Core Version:    0.6.0
 */