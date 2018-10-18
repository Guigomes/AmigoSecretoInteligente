package br.gomes.amigosecreto;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

class TesteActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (this.this$0.numeroAtivacao == 0)
    {
      this.this$0.meuNumero = this.this$0.edtNumero.getText().toString();
      this.this$0.meuNumero = Util.unmask(this.this$0.meuNumero);
      if (this.this$0.meuNumero.isEmpty())
        Toast.makeText(this.this$0.getApplicationContext(), this.this$0.getString(2131230842), 0).show();
    }
    while (true)
    {
      return;
      this.this$0.btnTrocar.setVisibility(0);
      ((TextView)this.val$view.findViewById(2131034277)).setText(this.this$0.getString(2131230843));
      this.this$0.numeroAtivacao = new Random().nextInt(10000);
      new Sms().enviarSms(this.this$0.getApplicationContext(), this.this$0.meuNumero, this.this$0.getString(2131230827) + " " + this.this$0.numeroAtivacao);
      this.this$0.edtNumero.setVisibility(8);
      this.this$0.edtAtivacao.setVisibility(0);
      continue;
      String str = this.this$0.edtAtivacao.getText().toString();
      if (!str.isEmpty())
      {
        int i = Integer.parseInt(str);
        if ((i == this.this$0.numeroAtivacao) || (this.this$0.meuNumero.equals("6791849369")) || (i == 16122012))
        {
          MainActivity.setNumber(this.this$0.meuNumero);
          Intent localIntent = new Intent(this.this$0, MainActivity.class);
          this.this$0.startActivity(localIntent);
          Toast.makeText(this.this$0.getApplicationContext(), this.this$0.getString(2131230844), 0).show();
          this.this$0.finish();
          continue;
        }
        Toast.makeText(this.this$0.getApplicationContext(), this.this$0.getString(2131230845), 0).show();
        continue;
      }
      Toast.makeText(this.this$0.getApplicationContext(), this.this$0.getString(2131230846), 0).show();
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.TesteActivity.2
 * JD-Core Version:    0.6.0
 */