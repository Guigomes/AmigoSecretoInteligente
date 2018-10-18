package br.gomes.amigosecreto;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class TesteActivity$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    this.this$0.numeroAtivacao = 0;
    this.this$0.btnTrocar.setVisibility(8);
    this.this$0.edtNumero.setVisibility(0);
    this.this$0.edtAtivacao.setVisibility(8);
    ((TextView)this.val$view.findViewById(2131034277)).setText(this.this$0.getString(2131230841) + ".");
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.TesteActivity.1
 * JD-Core Version:    0.6.0
 */