package br.gomes.amigosecreto;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import java.util.Locale;

public class Sms
{
  private static final String CATEGORIA = "Exemplo SMS";

  private SmsMessage[] getMessagesFromIntent(Intent paramIntent)
  {
    SmsMessage[] arrayOfSmsMessage = null;
    Log.d("Exemplo SMS", "Sms.getMessagesFromIntent: " + paramIntent.getAction());
    Object[] arrayOfObject = (Object[])paramIntent.getSerializableExtra("pdus");
    byte[][] arrayOfByte1 = new byte[arrayOfObject.length][];
    int i = 0;
    byte[][] arrayOfByte2;
    if (i >= arrayOfObject.length)
    {
      arrayOfByte2 = new byte[arrayOfByte1.length][];
      if (arrayOfByte2 != null)
        break label91;
    }
    while (true)
    {
      return arrayOfSmsMessage;
      arrayOfByte1[i] = ((byte[])arrayOfObject[i]);
      i++;
      break;
      label91: int j = arrayOfByte2.length;
      if (j == 0)
        continue;
      arrayOfSmsMessage = new SmsMessage[j];
      for (int k = 0; k < j; k++)
      {
        arrayOfByte2[k] = arrayOfByte1[k];
        arrayOfSmsMessage[k] = SmsMessage.createFromPdu(arrayOfByte2[k]);
        String str1 = arrayOfSmsMessage[0].getDisplayOriginatingAddress();
        String str2 = arrayOfSmsMessage[0].getDisplayMessageBody();
        Log.d("Exemplo SMS", "Sms.Mensagem: " + str1 + " -> " + str2);
      }
    }
  }

  public void enviarSms(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      SmsManager localSmsManager = SmsManager.getDefault();
      PendingIntent.getBroadcast(paramContext, 0, new Intent("SMS_SENT"), 0);
      if (paramString1.length() > 5)
        if (Locale.getDefault().getLanguage().equals("pt"))
          localSmsManager.sendMultipartTextMessage("+55" + paramString1, null, localSmsManager.divideMessage(paramString2), null, null);
        else
          localSmsManager.sendMultipartTextMessage(paramString1, null, localSmsManager.divideMessage(paramString2), null, null);
    }
    catch (Exception localException)
    {
      Log.e("Exemplo SMS", "Erro ao enviar o SMS: " + localException.getMessage(), localException);
    }
  }

  public SmsMessage receberMensagem(Intent paramIntent)
  {
    SmsMessage[] arrayOfSmsMessage = getMessagesFromIntent(paramIntent);
    if (arrayOfSmsMessage != null);
    for (SmsMessage localSmsMessage = arrayOfSmsMessage[0]; ; localSmsMessage = null)
      return localSmsMessage;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.Sms
 * JD-Core Version:    0.6.0
 */