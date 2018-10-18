package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper
{
  private static String dbName = "amigo_secreto.db";
  private static String sql1 = "CREATE TABLE IF NOT EXISTS [participante_sorteio] ([id] INTEGER PRIMARY KEY AUTOINCREMENT,[id_simpledb] TEXT, [nome] VARCHAR(30),[sorteio] INTEGER,[familia] INTEGER, [telefone] VARCHAR(13),[sorteado] INTEGER,[participante_sorteado] INTEGER, [participante_sorteado_simpledb] TEXT, [meu_presente] TEXT, [presente_sorteado] TEXT, [sorteio_simpledb] TEXT);";
  private static String sql2 = "CREATE TABLE IF NOT EXISTS [sorteio_amigo_secreto] ([id] INTEGER PRIMARY KEY AUTOINCREMENT, [id_simpledb] TEXT, [data_sorteio] VARCHAR(23),[titulo] VARCHAR(30),[com_familia] INTEGER, [valor_presente] DOUBLE);";
  private static String sql3 = "CREATE TABLE IF NOT EXISTS [familia] ([id] INTEGER PRIMARY KEY AUTOINCREMENT,[id_simpledb] TEXT,  [nome] VARCHAR(30));";
  private static String sql4 = "DROP TABLE familia; DROP TABLE participante_sorteio; DROP TABLE sorteio;";
  private static String sql5 = "CREATE TABLE IF NOT EXISTS [contato] ([id] INTEGER PRIMARY KEY AUTOINCREMENT,[nome_contato] TEXT,  [telefone] TEXT, [idFamilia] TEXT);";
  private static int version = 1;
  StringBuilder strb = new StringBuilder();

  public DB(Context paramContext)
  {
    super(paramContext, dbName, null, version);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL(sql1);
    paramSQLiteDatabase.execSQL(sql2);
    paramSQLiteDatabase.execSQL(sql3);
    paramSQLiteDatabase.execSQL(sql5);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL(sql2);
    paramSQLiteDatabase.execSQL(sql5);
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.DB
 * JD-Core Version:    0.6.0
 */