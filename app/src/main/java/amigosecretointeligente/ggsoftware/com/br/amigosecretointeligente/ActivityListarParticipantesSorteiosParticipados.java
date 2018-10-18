package br.gomes.amigosecreto;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.gomes.amigosecreto.listView.CustomAdapter;
import br.gomes.amigosecreto.listView.ListModel;
import br.gomes.amigosecreto.simpledb.SimpleDB;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectResult;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ActivityListarParticipantesSorteiosParticipados extends ActionBarActivity
{
  private final String ANÚNCIO_ID = "ca-app-pub-3737758644488199/9271148869";
  ArrayList<ListModel> CustomListViewValuesArr = new ArrayList();
  private AdView adView;
  CustomAdapter adapter;
  AlertDialog alertDialogMeuPresente;
  AlertDialog alertDialogPresenteAmigo;
  List<ReplaceableAttribute> attrs;
  List<ReplaceableAttribute> attrs2;
  String data;
  String familia;
  String id;
  String id_simpledb;
  ListView list;
  String meu_presente;
  String nome;
  String participante_sorteado;
  String participante_sorteado_simpledb;
  String presente_sorteado;
  ProgressDialog progressDialog;
  SelectResult resultados;
  SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
  SimpleDateFormat sdfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR"));
  SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm", new Locale("pt-BR"));
  String simpleIdSorteio;
  String sorteado;
  String sorteio;
  String telefone;

  public void meuPresente(View paramView)
  {
    View localView = LayoutInflater.from(this).inflate(2130903087, null);
    EditText localEditText = (EditText)localView.findViewById(2131034251);
    if ((this.meu_presente != null) && (!this.meu_presente.isEmpty()))
      localEditText.setText(this.meu_presente);
    while (true)
    {
      localEditText.setText(MainActivity.getPresente(this.id_simpledb));
      ((Button)localView.findViewById(2131034252)).setOnClickListener(new View.OnClickListener(localEditText)
      {
        public void onClick(View paramView)
        {
          String str = this.val$edtMeuPresente.getText().toString();
          if ((str == null) || (str.isEmpty()))
            Toast.makeText(ActivityListarParticipantesSorteiosParticipados.this.getApplicationContext(), ActivityListarParticipantesSorteiosParticipados.this.getString(2131230838), 0).show();
          while (true)
          {
            return;
            ReplaceableAttribute localReplaceableAttribute1 = new ReplaceableAttribute("id", ActivityListarParticipantesSorteiosParticipados.this.id, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute2 = new ReplaceableAttribute("nome", ActivityListarParticipantesSorteiosParticipados.this.nome, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute3 = new ReplaceableAttribute("sorteio", ActivityListarParticipantesSorteiosParticipados.this.sorteio, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute4 = new ReplaceableAttribute("familia", ActivityListarParticipantesSorteiosParticipados.this.familia, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute5 = new ReplaceableAttribute("telefone", ActivityListarParticipantesSorteiosParticipados.this.telefone, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute6 = new ReplaceableAttribute("sorteado", ActivityListarParticipantesSorteiosParticipados.this.sorteado, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute7 = new ReplaceableAttribute("participante_sorteado", ActivityListarParticipantesSorteiosParticipados.this.participante_sorteado, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute8 = new ReplaceableAttribute("participante_sorteado_simpledb", ActivityListarParticipantesSorteiosParticipados.this.participante_sorteado_simpledb, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute9 = new ReplaceableAttribute("meu_presente", str, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute10 = new ReplaceableAttribute("presente_sorteado", ActivityListarParticipantesSorteiosParticipados.this.presente_sorteado, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute11 = new ReplaceableAttribute("data", ActivityListarParticipantesSorteiosParticipados.this.data, Boolean.TRUE);
            ReplaceableAttribute localReplaceableAttribute12 = new ReplaceableAttribute("sorteio_simpledb", ActivityListarParticipantesSorteiosParticipados.this.simpleIdSorteio, Boolean.TRUE);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute1);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute2);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute3);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute4);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute5);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute6);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute7);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute8);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute9);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute10);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute11);
            ActivityListarParticipantesSorteiosParticipados.this.attrs.add(localReplaceableAttribute12);
            ActivityListarParticipantesSorteiosParticipados.SalvarParticipantesSimpleDB localSalvarParticipantesSimpleDB = new ActivityListarParticipantesSorteiosParticipados.SalvarParticipantesSimpleDB(ActivityListarParticipantesSorteiosParticipados.this, null);
            String[] arrayOfString = new String[1];
            arrayOfString[0] = str;
            localSalvarParticipantesSimpleDB.execute(arrayOfString);
            MainActivity.setPresente(str, ActivityListarParticipantesSorteiosParticipados.this.id_simpledb);
            ActivityListarParticipantesSorteiosParticipados.this.alertDialogMeuPresente.dismiss();
            Toast.makeText(ActivityListarParticipantesSorteiosParticipados.this.getApplicationContext(), ActivityListarParticipantesSorteiosParticipados.this.getString(2131230839), 0).show();
          }
        }
      });
      ((ImageView)localView.findViewById(2131034250)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          ActivityListarParticipantesSorteiosParticipados.this.alertDialogMeuPresente.cancel();
        }
      });
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setView(localView);
      localBuilder.setCancelable(true);
      this.alertDialogMeuPresente = localBuilder.create();
      this.alertDialogMeuPresente.show();
      return;
      if ((MainActivity.getPresente(this.id_simpledb) == null) || (MainActivity.getPresente(this.id_simpledb).isEmpty()))
        continue;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903065);
    Intent localIntent = getIntent();
    this.id = localIntent.getExtras().getString("id");
    this.nome = localIntent.getExtras().getString("nome");
    this.sorteio = localIntent.getExtras().getString("sorteio");
    this.id_simpledb = localIntent.getExtras().getString("name");
    this.familia = localIntent.getExtras().getString("familia");
    this.telefone = localIntent.getExtras().getString("telefone");
    this.sorteado = localIntent.getExtras().getString("sorteado");
    this.participante_sorteado = localIntent.getExtras().getString("participante_sorteado");
    this.participante_sorteado_simpledb = localIntent.getExtras().getString("participante_sorteado_simpledb");
    this.meu_presente = localIntent.getExtras().getString("meu_presente");
    this.presente_sorteado = localIntent.getExtras().getString("presente_sorteado");
    this.data = localIntent.getExtras().getString("data");
    this.simpleIdSorteio = localIntent.getExtras().getString("sorteio_simpledb");
    if (Util.verificaConexao(getApplicationContext()))
    {
      this.attrs = new ArrayList();
      if (!this.participante_sorteado.equals("0"))
      {
        ((TextView)findViewById(2131034209)).setText(this.nome + ", " + getString(2131230865) + " " + this.participante_sorteado + " " + getString(2131230864));
        getSupportActionBar().setTitle(this.sorteio);
        if (!this.data.contains("às"))
          break label597;
        getSupportActionBar().setSubtitle(getString(2131230823) + " " + this.data);
        getResources();
        this.list = ((ListView)findViewById(2131034211));
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setIcon(2130837675);
        this.progressDialog.setMessage(getString(2131230860) + "...");
        this.progressDialog.show();
        ListarParticipanteSimpleDB localListarParticipanteSimpleDB = new ListarParticipanteSimpleDB(null);
        String[] arrayOfString = new String[1];
        arrayOfString[0] = this.simpleIdSorteio;
        localListarParticipanteSimpleDB.execute(arrayOfString);
      }
    }
    while (true)
    {
      while (true)
      {
        this.adView = new AdView(this);
        this.adView.setAdUnitId("ca-app-pub-3737758644488199/9271148869");
        this.adView.setAdSize(AdSize.BANNER);
        ((LinearLayout)findViewById(2131034202)).addView(this.adView, 0);
        AdRequest localAdRequest = new AdRequest.Builder().build();
        this.adView.loadAd(localAdRequest);
        return;
        ((LinearLayout)findViewById(2131034210)).setVisibility(8);
        ((TextView)findViewById(2131034209)).setText(getString(2131230832) + ".");
        break;
        label597: Calendar localCalendar = Calendar.getInstance();
        try
        {
          localCalendar.setTime(this.sdfDiaHora.parse(this.data));
          getSupportActionBar().setSubtitle(getString(2131230823) + " " + this.sdfDia.format(localCalendar.getTime()) + " " + getString(2131230862) + " " + this.sdfHora.format(localCalendar.getTime()));
        }
        catch (ParseException localParseException)
        {
          while (true)
            localParseException.printStackTrace();
        }
      }
      Toast.makeText(getApplicationContext(), getString(2131230836) + ".", 0).show();
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492865, paramMenu);
    return true;
  }

  public void onDestroy()
  {
    this.adView.destroy();
    super.onDestroy();
  }

  public void onItemClick(int paramInt, View paramView)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return super.onOptionsItemSelected(paramMenuItem);
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

  public void presenteAmigo(View paramView)
  {
    View localView = LayoutInflater.from(this).inflate(2130903088, null);
    TextView localTextView = (TextView)localView.findViewById(2131034254);
    if ((this.presente_sorteado != null) && (!this.presente_sorteado.isEmpty()))
      localTextView.setText(getString(2131230840) + " " + this.presente_sorteado);
    ((ImageView)localView.findViewById(2131034250)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActivityListarParticipantesSorteiosParticipados.this.alertDialogPresenteAmigo.cancel();
      }
    });
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setView(localView);
    localBuilder.setCancelable(true);
    this.alertDialogPresenteAmigo = localBuilder.create();
    this.alertDialogPresenteAmigo.show();
  }

  private class ListarParticipanteSimpleDB extends AsyncTask<String, Void, SelectResult>
  {
    private ListarParticipanteSimpleDB()
    {
    }

    protected SelectResult doInBackground(String[] paramArrayOfString)
    {
      return new SimpleDB().listarParticipantes(paramArrayOfString[0]);
    }

    protected void onPostExecute(SelectResult paramSelectResult)
    {
      super.onPostExecute(paramSelectResult);
      ActivityListarParticipantesSorteiosParticipados.this.resultados = paramSelectResult;
      Iterator localIterator1 = paramSelectResult.getItems().iterator();
      if (!localIterator1.hasNext())
      {
        Resources localResources = ActivityListarParticipantesSorteiosParticipados.this.getResources();
        ActivityListarParticipantesSorteiosParticipados.this.list = ((ListView)ActivityListarParticipantesSorteiosParticipados.this.findViewById(2131034211));
        ActivityListarParticipantesSorteiosParticipados.this.adapter = new CustomAdapter(ActivityListarParticipantesSorteiosParticipados.this, ActivityListarParticipantesSorteiosParticipados.this.CustomListViewValuesArr, localResources);
        ActivityListarParticipantesSorteiosParticipados.this.list.setAdapter(ActivityListarParticipantesSorteiosParticipados.this.adapter);
        ActivityListarParticipantesSorteiosParticipados.this.progressDialog.dismiss();
        return;
      }
      Item localItem = (Item)localIterator1.next();
      ListModel localListModel = new ListModel();
      Iterator localIterator2 = localItem.getAttributes().iterator();
      while (true)
      {
        if (!localIterator2.hasNext())
        {
          ActivityListarParticipantesSorteiosParticipados.this.CustomListViewValuesArr.add(localListModel);
          break;
        }
        Attribute localAttribute = (Attribute)localIterator2.next();
        if (localAttribute.getName().equals("nome"))
        {
          localListModel.setCompanyName(ActivityListarParticipantesSorteiosParticipados.this.getString(2131230821) + "  " + localAttribute.getValue());
          continue;
        }
        if (!localAttribute.getName().equals("telefone"))
          continue;
        if (Locale.getDefault().getLanguage().equals("pt"))
        {
          localListModel.setTelefone(ActivityListarParticipantesSorteiosParticipados.this.getString(2131230822) + "  " + Util.formataTelefoneBR(localAttribute.getValue()));
          continue;
        }
        localListModel.setTelefone(ActivityListarParticipantesSorteiosParticipados.this.getString(2131230822) + "  " + Util.formataTelefoneUS(localAttribute.getValue()));
      }
    }
  }

  private class SalvarParticipantesSimpleDB extends AsyncTask<String, Void, Void>
  {
    private SalvarParticipantesSimpleDB()
    {
    }

    protected Void doInBackground(String[] paramArrayOfString)
    {
      new SimpleDB().salvarParticipante(ActivityListarParticipantesSorteiosParticipados.this.attrs, ActivityListarParticipantesSorteiosParticipados.this.id_simpledb, paramArrayOfString[0]);
      return null;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.ActivityListarParticipantesSorteiosParticipados
 * JD-Core Version:    0.6.0
 */