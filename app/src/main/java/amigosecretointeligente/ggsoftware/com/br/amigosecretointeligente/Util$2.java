package br.gomes.amigosecreto;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class Util$2
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
    char[] arrayOfChar = "(###)###-##########".toCharArray();
    int k = arrayOfChar.length;
    if (i >= k);
    while (true)
    {
      this.isUpdating = true;
      this.val$ediTxt.setText((CharSequence)localObject);
      this.val$ediTxt.setSelection(((String)localObject).length());
      break;
      char c = arrayOfChar[i];
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
 * Qualified Name:     br.gomes.amigosecreto.Util.2
 * JD-Core Version:    0.6.0
 */