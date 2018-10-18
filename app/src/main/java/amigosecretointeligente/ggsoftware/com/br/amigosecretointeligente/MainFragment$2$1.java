package br.gomes.amigosecreto;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.model.GraphUser;

class MainFragment$2$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    String str = Util.unmask(this.val$edtNumero.getText().toString());
    if (str.isEmpty())
      Toast.makeText(MainFragment.2.access$0(this.this$1).getActivity(), MainFragment.2.access$0(this.this$1).getString(2131230842), 0).show();
    while (true)
    {
      return;
      MainActivity.setNumber(str);
      MainFragment.CadastrarNovoUsuario localCadastrarNovoUsuario = new MainFragment.CadastrarNovoUsuario(MainFragment.2.access$0(this.this$1), null);
      String[] arrayOfString = new String[2];
      arrayOfString[0] = str;
      arrayOfString[1] = this.val$user.getId();
      localCadastrarNovoUsuario.execute(arrayOfString);
      MainFragment.2.access$0(this.this$1).startActivity(new Intent(MainFragment.2.access$0(this.this$1).getActivity(), MainActivity.class));
      Toast.makeText(MainFragment.2.access$0(this.this$1).getActivity(), MainFragment.2.access$0(this.this$1).getString(2131230844), 0).show();
      MainFragment.2.access$0(this.this$1).getActivity().finish();
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.MainFragment.2.1
 * JD-Core Version:    0.6.0
 */