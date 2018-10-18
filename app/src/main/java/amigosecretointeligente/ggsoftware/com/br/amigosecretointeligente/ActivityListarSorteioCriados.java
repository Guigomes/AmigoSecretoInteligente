package br.gomes.amigosecreto;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.curso.bd.ParticipanteDAO;
import br.com.curso.bd.SorteioDAO;
import br.com.curso.bd.SorteioVO;
import br.gomes.amigosecreto.listView.ListModel;
import br.gomes.amigosecreto.simpledb.SimpleDB;
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

public class ActivityListarSorteioCriados extends ActionBarActivity
{
  private final String ANÚNCIO_ID = "ca-app-pub-3737758644488199/8073617269";
  private AdView adView;
  public Button btnReenviar;
  TextView empty;
  public ImageView image;
  public ImageView imageDelete;
  private ViewGroup mContainerView;
  List<String> nomesSorteio = new ArrayList();
  SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
  SimpleDateFormat sdfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR"));
  SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm", new Locale("pt-BR"));
  public TextView text;
  public TextView text1;

  private void addItem(SorteioVO paramSorteioVO)
    throws ParseException
  {
    this.empty.setVisibility(8);
    ViewGroup localViewGroup = (ViewGroup)LayoutInflater.from(this).inflate(2130903100, this.mContainerView, false);
    ((LinearLayout)localViewGroup.findViewById(2131034269)).setOnClickListener(new View.OnClickListener(paramSorteioVO)
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ActivityListarSorteioCriados.this, ActivityListarParticipantes.class);
        localIntent.putExtra("id", this.val$sorteioVO.getId());
        ActivityListarSorteioCriados.this.startActivity(localIntent);
      }
    });
    this.text = ((TextView)localViewGroup.findViewById(2131034288));
    this.text1 = ((TextView)localViewGroup.findViewById(2131034289));
    this.image = ((ImageView)localViewGroup.findViewById(2131034180));
    this.imageDelete = ((ImageView)localViewGroup.findViewById(2131034292));
    ImageView localImageView = (ImageView)localViewGroup.findViewById(2131034293);
    if (paramSorteioVO.getComFamilia().intValue() != 1)
      localImageView.setVisibility(0);
    localImageView.setOnClickListener(new View.OnClickListener(paramSorteioVO)
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ActivityListarSorteioCriados.this, ActivityNovoSorteio.class);
        localIntent.putExtra("tituloSorteio", this.val$sorteioVO.getTitulo());
        localIntent.putExtra("dataSorteio", this.val$sorteioVO.getDataSorteio());
        localIntent.putExtra("valorPresente", this.val$sorteioVO.getValorPresente());
        localIntent.putExtra("sorteioId", this.val$sorteioVO.getId());
        ActivityListarSorteioCriados.this.startActivity(localIntent);
      }
    });
    this.imageDelete.setOnClickListener(new View.OnClickListener(paramSorteioVO, localViewGroup)
    {
      public void onClick(View paramView)
      {
        this.val$sorteioVO.getIdSimpleDB();
        ParticipanteDAO localParticipanteDAO = new ParticipanteDAO(ActivityListarSorteioCriados.this.getApplicationContext());
        SorteioDAO localSorteioDAO = new SorteioDAO(ActivityListarSorteioCriados.this.getApplicationContext());
        localParticipanteDAO.deletarParticipantesSorteio(this.val$sorteioVO.getId().intValue());
        localSorteioDAO.delete(this.val$sorteioVO);
        ActivityListarSorteioCriados.ExlcuirSorteioSimpleDB localExlcuirSorteioSimpleDB = new ActivityListarSorteioCriados.ExlcuirSorteioSimpleDB(ActivityListarSorteioCriados.this, null);
        String[] arrayOfString = new String[1];
        arrayOfString[0] = this.val$sorteioVO.getIdSimpleDB();
        localExlcuirSorteioSimpleDB.execute(arrayOfString);
        ActivityListarSorteioCriados.this.mContainerView.removeView(this.val$newView);
        if (ActivityListarSorteioCriados.this.mContainerView.getChildCount() == 0)
          ActivityListarSorteioCriados.this.empty.setVisibility(0);
      }
    });
    this.btnReenviar = ((Button)localViewGroup.findViewById(2131034291));
    this.text.setText(getString(2131230824) + " " + paramSorteioVO.getTitulo());
    if (paramSorteioVO.getDataSorteio().contains("às"))
      this.text1.setText(getString(2131230823) + " " + paramSorteioVO.getDataSorteio());
    while (true)
    {
      this.image.setImageResource(2130837687);
      this.imageDelete.setVisibility(0);
      this.mContainerView.addView(localViewGroup, 0);
      return;
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(this.sdfDiaHora.parse(paramSorteioVO.getDataSorteio()));
      this.text1.setText(getString(2131230823) + " " + this.sdfDia.format(localCalendar.getTime()) + " " + getString(2131230862) + " " + this.sdfHora.format(localCalendar.getTime()));
    }
  }

  public void editarSorteio(View paramView)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903066);
    getSupportActionBar().setSubtitle(getString(2131230886));
    SorteioDAO localSorteioDAO = new SorteioDAO(this);
    getResources();
    this.mContainerView = ((ViewGroup)findViewById(2131034208));
    this.empty = ((TextView)findViewById(2131034207));
    Iterator localIterator = localSorteioDAO.listar().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.adView = new AdView(this);
        this.adView.setAdUnitId("ca-app-pub-3737758644488199/8073617269");
        this.adView.setAdSize(AdSize.BANNER);
        ((LinearLayout)findViewById(2131034212)).addView(this.adView, 0);
        AdRequest localAdRequest = new AdRequest.Builder().build();
        this.adView.loadAd(localAdRequest);
        return;
      }
      SorteioVO localSorteioVO = (SorteioVO)localIterator.next();
      try
      {
        addItem(localSorteioVO);
      }
      catch (ParseException localParseException)
      {
        localParseException.printStackTrace();
      }
    }
  }

  public void onDestroy()
  {
    this.adView.destroy();
    super.onDestroy();
  }

  public void onItemClick(int paramInt, View paramView)
  {
    int i = new SorteioDAO(getApplicationContext()).buscaIdPorTitulo((String)this.nomesSorteio.get(paramInt));
    Intent localIntent = new Intent(this, ActivityListarParticipantes.class);
    localIntent.putExtra("id", i);
    startActivity(localIntent);
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

  public void setListData(List<SorteioVO> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      SorteioVO localSorteioVO = (SorteioVO)localIterator.next();
      ListModel localListModel = new ListModel();
      localListModel.setCompanyName(getString(2131230824) + " " + localSorteioVO.getTitulo());
      localListModel.setTelefone(getString(2131230823) + " " + localSorteioVO.getDataSorteio());
      localListModel.setIdImagem(Integer.valueOf(2130837687));
      localListModel.setSorteioVO(localSorteioVO);
      this.nomesSorteio.add(localSorteioVO.getTitulo());
    }
  }

  public void teste(View paramView)
  {
    TextView localTextView = (TextView)((LinearLayout)paramView.getParent()).findViewById(2131034272);
    new SorteioDAO(getApplicationContext()).buscaPorId(Integer.valueOf(Integer.parseInt(localTextView.getText().toString())));
    new ParticipanteDAO(getApplicationContext());
  }

  private class ExlcuirSorteioSimpleDB extends AsyncTask<String, Void, Void>
  {
    private ExlcuirSorteioSimpleDB()
    {
    }

    protected Void doInBackground(String[] paramArrayOfString)
    {
      new SimpleDB().deletePorSorteio(paramArrayOfString[0]);
      return null;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.ActivityListarSorteioCriados
 * JD-Core Version:    0.6.0
 */