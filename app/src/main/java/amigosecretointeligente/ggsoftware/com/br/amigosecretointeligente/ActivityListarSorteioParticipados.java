package br.gomes.amigosecreto;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import br.gomes.amigosecreto.listView.CustomAdapter;
import br.gomes.amigosecreto.listView.ListModel;
import br.gomes.amigosecreto.simpledb.SimpleDB;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.Item;
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

public class ActivityListarSorteioParticipados extends ActionBarActivity
{
  private final String ANÚNCIO_ID = "ca-app-pub-3737758644488199/6317682465";
  ArrayList<ListModel> CustomListViewValuesArr = new ArrayList();
  private AdView adView;
  ArrayAdapter<String> adapter;
  CustomAdapter adapterCustom;
  AlertDialog alertDialog2;
  ListView list;
  List<String> listaSorteios;
  String meuNumero = "";
  int numeroAtivacao = 0;
  ProgressDialog progressDialog;
  SelectResult resultados;
  SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
  SimpleDateFormat sdfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR"));
  SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm", new Locale("pt-BR"));

  public void ativarAgora(View paramView)
  {
    startActivity(new Intent(this, TesteActivity.class));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getSupportActionBar().setSubtitle(getString(2131230885));
    setContentView(2130903093);
    if ((MainActivity.getNumber() != null) && (!MainActivity.getNumber().isEmpty()) && (!MainActivity.getNumber().equals("NULL")));
    while (true)
    {
      try
      {
        this.list = ((ListView)findViewById(2131034211));
        this.list.setVisibility(0);
        if (!Util.verificaConexao(getApplicationContext()))
          continue;
        new ListarParticipanteSimpleDB(null).execute(new Void[0]);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setIcon(2130837675);
        this.progressDialog.setMessage(getString(2131230861) + "...");
        this.progressDialog.show();
        this.adView = new AdView(this);
        this.adView.setAdUnitId("ca-app-pub-3737758644488199/6317682465");
        this.adView.setAdSize(AdSize.BANNER);
        ((LinearLayout)findViewById(2131034202)).addView(this.adView, 0);
        AdRequest localAdRequest = new AdRequest.Builder().build();
        this.adView.loadAd(localAdRequest);
        return;
        Toast.makeText(getApplicationContext(), getString(2131230837) + ".", 0).show();
        continue;
      }
      catch (Exception localException)
      {
        Toast.makeText(getApplicationContext(), localException.getMessage(), 1).show();
        continue;
      }
      ((LinearLayout)findViewById(2131034273)).setVisibility(0);
      ((Button)findViewById(2131034275)).setVisibility(0);
    }
  }

  public void onDestroy()
  {
    this.adView.destroy();
    super.onDestroy();
  }

  public void onItemClick(int paramInt, View paramView)
  {
    Item localItem = (Item)this.resultados.getItems().get(paramInt);
    Intent localIntent = new Intent(this, ActivityListarParticipantesSorteiosParticipados.class);
    Iterator localIterator = localItem.getAttributes().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localIntent.putExtra("name", localItem.getName());
        startActivity(localIntent);
        return;
      }
      Attribute localAttribute = (Attribute)localIterator.next();
      localIntent.putExtra(localAttribute.getName(), localAttribute.getValue());
    }
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

  private class BuscarNomePorIdSimpleBD extends AsyncTask<String, Void, String>
  {
    private BuscarNomePorIdSimpleBD()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      return new SimpleDB().buscarNomePorId(paramArrayOfString[0]);
    }
  }

  private class ListarParticipanteSimpleDB extends AsyncTask<Void, Void, SelectResult>
  {
    private ListarParticipanteSimpleDB()
    {
    }

    protected SelectResult doInBackground(Void[] paramArrayOfVoid)
    {
      return new SimpleDB().listar();
    }

    protected void onPostExecute(SelectResult paramSelectResult)
    {
      super.onPostExecute(paramSelectResult);
      ActivityListarSorteioParticipados.this.resultados = paramSelectResult;
      Iterator localIterator1 = paramSelectResult.getItems().iterator();
      ListModel localListModel;
      String str;
      Iterator localIterator2;
      while (true)
      {
        if (!localIterator1.hasNext())
        {
          Resources localResources = ActivityListarSorteioParticipados.this.getResources();
          ActivityListarSorteioParticipados.this.list = ((ListView)ActivityListarSorteioParticipados.this.findViewById(2131034211));
          ActivityListarSorteioParticipados.this.adapterCustom = new CustomAdapter(ActivityListarSorteioParticipados.this, ActivityListarSorteioParticipados.this.CustomListViewValuesArr, localResources);
          ActivityListarSorteioParticipados.this.list.setAdapter(ActivityListarSorteioParticipados.this.adapterCustom);
          ActivityListarSorteioParticipados.this.progressDialog.dismiss();
          return;
        }
        Item localItem = (Item)localIterator1.next();
        localListModel = new ListModel();
        str = "";
        localIterator2 = localItem.getAttributes().iterator();
        if (localIterator2.hasNext())
          break;
        ActivityListarSorteioParticipados.this.CustomListViewValuesArr.add(localListModel);
      }
      Attribute localAttribute = (Attribute)localIterator2.next();
      if (localAttribute.getName().equals("sorteio"))
        localListModel.setCompanyName(ActivityListarSorteioParticipados.this.getString(2131230824) + " " + localAttribute.getValue());
      while (true)
      {
        localListModel.setIdImagem(Integer.valueOf(2130837687));
        break;
        if (localAttribute.getName().equals("data"))
        {
          if (localAttribute.getValue().contains("às"))
          {
            localListModel.setTelefone(ActivityListarSorteioParticipados.this.getString(2131230823) + " " + localAttribute.getValue());
            continue;
          }
          Calendar localCalendar = Calendar.getInstance();
          try
          {
            localCalendar.setTime(ActivityListarSorteioParticipados.this.sdfDiaHora.parse(localAttribute.getValue()));
            localListModel.setTelefone(ActivityListarSorteioParticipados.this.getString(2131230823) + " " + ActivityListarSorteioParticipados.this.sdfDia.format(localCalendar.getTime()) + " " + ActivityListarSorteioParticipados.this.getString(2131230862) + " " + ActivityListarSorteioParticipados.this.sdfHora.format(localCalendar.getTime()));
          }
          catch (ParseException localParseException)
          {
            while (true)
              localParseException.printStackTrace();
          }
        }
        if ((!localAttribute.getName().equals("participante_sorteado")) || (localAttribute.getValue() == null) || (localAttribute.getValue().isEmpty()) || (localAttribute.getValue().equals("0")))
          continue;
        str = str + " " + localAttribute.getValue();
      }
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.ActivityListarSorteioParticipados
 * JD-Core Version:    0.6.0
 */