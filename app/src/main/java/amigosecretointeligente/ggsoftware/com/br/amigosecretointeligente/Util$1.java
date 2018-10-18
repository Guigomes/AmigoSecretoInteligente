package br.gomes.amigosecreto;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class Util$1
  implements TextWatcher
{
  boolean isUpdating;
  String old = "";

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    String str1 = Util.unmask(paramCharSequence.toString());
    Object localObject = "";
    if (this.isUpdating)
    {
      this.old = str1;
      this.isUpdating = false;
      return;
    }
    int j = 0;
    label55: char[] arrayOfChar;
    if (str1.length() > 10)
    {
      Util.maska = "(##)#####-##########";
      arrayOfChar = Util.maska.toCharArray();
      int k = arrayOfChar.length;
      if (i < k)
        break label112;
    }
    while (true)
    {
      this.isUpdating = true;
      this.val$ediTxt.setText((CharSequence)localObject);
      this.val$ediTxt.setSelection(((String)localObject).length());
      break;
      Util.maska = "(##)####-###########";
      break label55;
      label112: char c = arrayOfChar[i];
      if ((c != '#') && (str1.length() > this.old.length()))
        localObject = localObject + c;
      while (true)
      {
        i++;
        break;
        try
        {
          String str2 = localObject + str1.charAt(j);
          localObject = str2;
          j++;
        }
        catch (Exception localException)
        {
        }
      }
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.Util.1
 * JD-Core Version:    0.6.0
 */