package br.gomes.amigosecreto;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.gomes.amigosecreto.simpledb.SimpleDB;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TesteActivity extends FragmentActivity
{
  private static final String TAG = "MainFragment";
  static SharedPreferences sharedPref;
  AlertDialog alertDialog;
  AlertDialog alertDialog2 = null;
  AlertDialog.Builder alertDialogBuilder;
  Button btnTrocar;
  Calendar calendar = Calendar.getInstance();
  Context context = this;
  EditText data;
  String dataDia;
  String dataHora;
  EditText edtAtivacao;
  EditText edtNumero;
  EditText edtValor;
  int hora;
  int mDay;
  int mMonth;
  int mYear;
  private MainFragment mainFragment;
  String meuNumero = null;
  int minuto;
  int numeroAtivacao = 0;
  SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
  SimpleDateFormat sdfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR"));
  SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm", new Locale("pt-BR"));
  SimpleDB simpleDB = new SimpleDB();
  TextWatcher textWatcher;
  String tituloSorteio;
  EditText userInput;

  public void envioSMS(View paramView)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    View localView1 = localLayoutInflater.inflate(2130903098, null);
    View localView2 = localLayoutInflater.inflate(2130903086, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    ((TextView)localView2.findViewById(2131034248)).setText(getString(2131230888));
    this.edtNumero = ((EditText)localView1.findViewById(2131034281));
    if (Locale.getDefault().getLanguage().equals("pt"))
      this.edtNumero.addTextChangedListener(Util.insertBR(this.edtNumero));
    while (true)
    {
      this.edtAtivacao = ((EditText)localView1.findViewById(2131034285));
      this.edtAtivacao.setHint(getString(2131230877));
      this.btnTrocar = ((Button)localView1.findViewById(2131034287));
      this.btnTrocar.setVisibility(8);
      this.btnTrocar.setOnClickListener(new TesteActivity.1(this, localView1));
      ((Button)localView1.findViewById(2131034286)).setOnClickListener(new TesteActivity.2(this, localView1));
      localBuilder.setCustomTitle(localView2);
      localBuilder.setView(localView1);
      AlertDialog localAlertDialog = localBuilder.create();
      localAlertDialog.setOnCancelListener(new TesteActivity.3(this));
      localAlertDialog.show();
      return;
      this.edtNumero.addTextChangedListener(Util.insertUS(this.edtNumero));
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903086, null);
    ((TextView)localView.findViewById(2131034248)).setText(getString(2131230888));
    setContentView(2130903096);
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131034276);
    if (paramBundle == null)
    {
      this.mainFragment = new MainFragment();
      getSupportFragmentManager().beginTransaction().add(2131034282, this.mainFragment).commit();
    }
    while (true)
    {
      localLinearLayout.addView(localView, 0);
      return;
      this.mainFragment = ((MainFragment)getSupportFragmentManager().findFragmentById(2131034276));
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onPause()
  {
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.TesteActivity
 * JD-Core Version:    0.6.0
 */