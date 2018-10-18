package br.gomes.amigosecreto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class Util
{
  static String maska;

  public static String formataTelefoneBR(String paramString)
  {
    String str;
    if (paramString.length() > 10)
    {
      str = "(" + paramString.substring(0, 2) + ")" + paramString.substring(2, 7) + "-" + paramString.substring(7);
      Log.i("NUMERO", "Formatado: " + str);
      paramString = str;
    }
    while (true)
    {
      return paramString;
      if (paramString.length() == 10)
      {
        str = "(" + paramString.substring(0, 2) + ")" + paramString.substring(2, 6) + "-" + paramString.substring(6);
        break;
      }
      Log.i("NUMERO", "Não Formatou");
    }
  }

  public static String formataTelefoneUS(String paramString)
  {
    if (paramString.length() >= 10);
    for (String str = "(" + paramString.substring(0, 3) + ")" + paramString.substring(3, 6) + "-" + paramString.substring(6); ; str = paramString)
      return str;
  }

  public static TextWatcher insertBR(EditText paramEditText)
  {
    return new Util.1(paramEditText);
  }

  public static TextWatcher insertUS(EditText paramEditText)
  {
    return new Util.2(paramEditText);
  }

  public static String unmask(String paramString)
  {
    return paramString.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[)]", "");
  }

  public static boolean verificaConexao(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isAvailable()) && (localConnectivityManager.getActiveNetworkInfo().isConnected()));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.Util
 * JD-Core Version:    0.6.0
 */