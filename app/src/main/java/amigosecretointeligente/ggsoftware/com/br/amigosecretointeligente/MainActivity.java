package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import br.com.curso.bd.SorteioDAO;
import br.gomes.amigosecreto.simpledb.SimpleDB;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends FragmentActivity
{
  static SharedPreferences sharedPref;
  private final String ANÚNCIO_ID = "ca-app-pub-3737758644488199/9168574065";
  private AdView adView;
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

  public static String getNumber()
  {
    return sharedPref.getString("number", null);
  }

  public static String getPresente(String paramString)
  {
    return sharedPref.getString("presente" + paramString, null);
  }

  public static void setNumber(String paramString)
  {
    SharedPreferences.Editor localEditor = sharedPref.edit();
    localEditor.putString("number", paramString);
    localEditor.commit();
  }

  public static void setPresente(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = sharedPref.edit();
    localEditor.putString("presente" + paramString2, paramString1);
    localEditor.commit();
  }

  public void cancelar(View paramView)
  {
    this.alertDialog.dismiss();
  }

  public void dateDialog()
  {
    Calendar localCalendar = Calendar.getInstance();
    this.mYear = localCalendar.get(1);
    this.mMonth = localCalendar.get(2);
    this.mDay = localCalendar.get(5);
    this.hora = Calendar.getInstance().get(11);
    this.minuto = (1 + Calendar.getInstance().get(12));
    TimePickerDialog localTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
    {
      public void onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2)
      {
        MainActivity.this.calendar.set(11, paramInt1);
        MainActivity.this.calendar.set(12, paramInt2);
        if (MainActivity.this.calendar.before(Calendar.getInstance()))
          Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getString(2131230853), 0).show();
        while (true)
        {
          return;
          MainActivity.this.dataDia = MainActivity.this.sdfDia.format(MainActivity.this.calendar.getTime());
          MainActivity.this.dataHora = MainActivity.this.sdfHora.format(MainActivity.this.calendar.getTime());
          MainActivity.this.data.setText(MainActivity.this.dataDia + " " + MainActivity.this.getString(2131230862) + " " + MainActivity.this.dataHora);
        }
      }
    }
    , this.hora, this.minuto, true);
    localTimePickerDialog.setTitle(getString(2131230806));
    DatePickerDialog localDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(localTimePickerDialog)
    {
      public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
      {
        MainActivity.this.calendar.set(1, paramInt1);
        MainActivity.this.calendar.set(2, paramInt2);
        MainActivity.this.calendar.set(5, paramInt3);
        this.val$tpd.show();
      }
    }
    , this.mYear, this.mMonth, this.mDay);
    localDatePickerDialog.setTitle(getString(2131230807));
    localDatePickerDialog.show();
  }

  public void novoSorteio(View paramView)
  {
    SorteioDAO localSorteioDAO = new SorteioDAO(getApplicationContext());
    this.tituloSorteio = this.userInput.getText().toString();
    String str = this.edtValor.getText().toString();
    if ((this.tituloSorteio == null) || (this.tituloSorteio.isEmpty()))
      Toast.makeText(getApplicationContext(), getString(2131230854), 0).show();
    while (true)
    {
      return;
      if ((this.data.getText().toString() == null) || (this.data.getText().toString().isEmpty()))
      {
        Toast.makeText(getApplicationContext(), getString(2131230855), 0).show();
        continue;
      }
      if ((str == null) || (str.isEmpty()))
      {
        Toast.makeText(getApplicationContext(), getString(2131230856), 0).show();
        continue;
      }
      if (localSorteioDAO.buscaIdPorTitulo(this.tituloSorteio) != -1)
      {
        Toast.makeText(getApplicationContext(), getString(2131230859), 0).show();
        continue;
      }
      Intent localIntent = new Intent(this, ActivityNovoSorteio.class);
      localIntent.putExtra("tituloSorteio", this.tituloSorteio);
      localIntent.putExtra("dataSorteio", this.dataDia + " " + this.dataHora);
      localIntent.putExtra("valorPresente", Double.valueOf(str));
      this.alertDialog.dismiss();
      startActivity(localIntent);
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903068);
    this.adView = new AdView(this);
    this.adView.setAdUnitId("ca-app-pub-3737758644488199/9168574065");
    this.adView.setAdSize(AdSize.BANNER);
    ((LinearLayout)findViewById(2131034208)).addView(this.adView, 0);
    AdRequest localAdRequest = new AdRequest.Builder().build();
    this.adView.loadAd(localAdRequest);
    sharedPref = getPreferences(0);
    Button localButton1 = (Button)findViewById(2131034216);
    Button localButton2 = (Button)findViewById(2131034217);
    Button localButton3 = (Button)findViewById(2131034218);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        View localView1 = LayoutInflater.from(MainActivity.this.context).inflate(2130903094, null);
        MainActivity.this.alertDialogBuilder = new AlertDialog.Builder(MainActivity.this.context);
        View localView2 = ((LayoutInflater)MainActivity.this.getSystemService("layout_inflater")).inflate(2130903086, null);
        MainActivity.this.alertDialogBuilder.setView(localView1);
        MainActivity.this.alertDialogBuilder.setCustomTitle(localView2);
        MainActivity.this.userInput = ((EditText)localView1.findViewById(2131034278));
        MainActivity.this.edtValor = ((EditText)localView1.findViewById(2131034280));
        MainActivity.this.data = ((EditText)localView1.findViewById(2131034279));
        MainActivity.this.data.setOnClickListener(new MainActivity.1.1(this));
        MainActivity.this.data.setOnFocusChangeListener(new MainActivity.1.2(this));
        MainActivity.this.alertDialogBuilder.setCancelable(false);
        MainActivity.this.alertDialog = MainActivity.this.alertDialogBuilder.create();
        MainActivity.this.alertDialog.show();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(MainActivity.this, ActivityListarSorteioCriados.class);
        MainActivity.this.startActivity(localIntent);
      }
    });
    localButton3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(MainActivity.this, ActivityListarSorteioParticipados.class);
        MainActivity.this.startActivity(localIntent);
      }
    });
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492866, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }

  public void onDestroy()
  {
    this.adView.destroy();
    super.onDestroy();
  }

  public void onPause()
  {
    this.adView.pause();
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.adView.resume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }

  private class CadastrarDomainsSimpleDB extends AsyncTask<Void, Void, Void>
  {
    private CadastrarDomainsSimpleDB()
    {
    }

    protected Void doInBackground(Void[] paramArrayOfVoid)
    {
      MainActivity.this.simpleDB.cadastrarDomains();
      return null;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.MainActivity
 * JD-Core Version:    0.6.0
 */