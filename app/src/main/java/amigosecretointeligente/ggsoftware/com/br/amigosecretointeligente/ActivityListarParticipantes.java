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
import android.widget.Toast;
import br.com.curso.bd.FamiliaDAO;
import br.com.curso.bd.FamiliaVO;
import br.com.curso.bd.ParticipanteDAO;
import br.com.curso.bd.ParticipanteVO;
import br.com.curso.bd.SorteioDAO;
import br.com.curso.bd.SorteioVO;
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
import java.util.Random;

public class ActivityListarParticipantes extends ActionBarActivity
{
  private final String ANÚNCIO_ID = "ca-app-pub-3737758644488199/4840949260";
  private AdView adView;
  Button btnSortear;
  int cont = 0;
  TextView empty;
  FamiliaDAO familiDAO;
  int idSorteio;
  private ViewGroup mContainerView;
  ParticipanteDAO participanteDAO;
  SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
  SimpleDateFormat sdfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR"));
  SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm", new Locale("pt-BR"));
  SorteioDAO sorteioDAO;
  SorteioVO sorteioVO;

  private void addItem(ParticipanteVO paramParticipanteVO)
    throws ParseException
  {
    this.empty.setVisibility(8);
    ViewGroup localViewGroup = (ViewGroup)LayoutInflater.from(this).inflate(2130903100, this.mContainerView, false);
    TextView localTextView1 = (TextView)localViewGroup.findViewById(2131034290);
    Button localButton = (Button)localViewGroup.findViewById(2131034291);
    TextView localTextView2 = (TextView)localViewGroup.findViewById(2131034288);
    TextView localTextView3 = (TextView)localViewGroup.findViewById(2131034289);
    ImageView localImageView1 = (ImageView)localViewGroup.findViewById(2131034180);
    TextView localTextView4 = (TextView)localViewGroup.findViewById(2131034272);
    ImageView localImageView2 = (ImageView)localViewGroup.findViewById(2131034292);
    String str1 = "";
    if ((paramParticipanteVO.getFamilia().intValue() != 0) && (paramParticipanteVO.getFamilia().intValue() != -1))
      str1 = this.familiDAO.buscaPorId(paramParticipanteVO.getFamilia()).getNome();
    localTextView2.setText(getString(2131230821) + " " + paramParticipanteVO.getNome() + " " + str1);
    if (Locale.getDefault().getLanguage().equals("pt"))
      localTextView3.setText(getString(2131230822) + " " + Util.formataTelefoneBR(paramParticipanteVO.getTelefone()));
    while (true)
    {
      localImageView1.setImageResource(2130837681);
      localTextView4.setText(paramParticipanteVO.getId().toString());
      SimpleDateFormat localSimpleDateFormat;
      Calendar localCalendar;
      if (this.sorteioVO.getComFamilia().intValue() == 1)
      {
        localButton.setVisibility(0);
        localSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR"));
        localCalendar = Calendar.getInstance();
      }
      try
      {
        localCalendar.setTime(localSimpleDateFormat.parse(this.sorteioVO.getDataSorteio()));
        label325: 1 local1 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            int i = Integer.parseInt(((TextView)((LinearLayout)paramView.getParent()).findViewById(2131034272)).getText().toString());
            ParticipanteVO localParticipanteVO = ActivityListarParticipantes.this.participanteDAO.buscaPorId(Integer.valueOf(i));
            try
            {
              ActivityListarParticipantes.this.enviarSMS(localParticipanteVO);
              label50: Toast.makeText(ActivityListarParticipantes.this.getApplicationContext(), ActivityListarParticipantes.this.getString(2131230831) + " " + localParticipanteVO.getNome(), 0).show();
              return;
            }
            catch (ParseException localParseException)
            {
              break label50;
            }
          }
        };
        localButton.setOnClickListener(local1);
        ParticipanteVO localParticipanteVO;
        if (Calendar.getInstance().after(localCalendar))
        {
          if ((paramParticipanteVO.getParticipanteSorteado() == null) || (paramParticipanteVO.getParticipanteSorteado().intValue() == 0))
            break label587;
          localParticipanteVO = this.participanteDAO.buscaPorId(paramParticipanteVO.getParticipanteSorteado());
          FamiliaVO localFamiliaVO = new FamiliaDAO(getApplicationContext()).buscaPorId(localParticipanteVO.getFamilia());
          String str2 = null;
          if (localFamiliaVO != null)
            str2 = localFamiliaVO.getNome();
          localButton.setVisibility(8);
          localTextView1.setVisibility(0);
          if (str2 == null)
            break label548;
          localTextView1.setText(getString(2131230826) + " " + localParticipanteVO.getNome() + " " + str2);
        }
        while (true)
        {
          localImageView1.setImageResource(2130837681);
          localImageView2.setVisibility(8);
          this.mContainerView.addView(localViewGroup, 0);
          return;
          localTextView3.setText(getString(2131230822) + " " + Util.formataTelefoneUS(paramParticipanteVO.getTelefone()));
          break;
          label548: localTextView1.setText(getString(2131230826) + " " + localParticipanteVO.getNome());
          continue;
          label587: localTextView1.setText("");
        }
      }
      catch (ParseException localParseException)
      {
        break label325;
      }
    }
  }

  private void enviarSMS(ParticipanteVO paramParticipanteVO)
    throws ParseException
  {
    Sms localSms = new Sms();
    String str1 = paramParticipanteVO.getTelefone();
    if (this.sorteioVO.getDataSorteio().contains("às"));
    Calendar localCalendar;
    for (String str2 = getString(2131230824) + " " + this.sorteioVO.getTitulo() + "\n" + paramParticipanteVO.getNome() + ", " + getString(2131230865) + " " + this.participanteDAO.buscaPorId(paramParticipanteVO.getParticipanteSorteado()).getNome() + " " + getString(2131230828) + " " + this.sorteioVO.getDataSorteio() + "h."; ; str2 = getString(2131230824) + " " + this.sorteioVO.getTitulo() + "\n" + paramParticipanteVO.getNome() + ", " + getString(2131230865) + " " + this.participanteDAO.buscaPorId(paramParticipanteVO.getParticipanteSorteado()).getNome() + " " + getString(2131230828) + " " + this.sdfDia.format(localCalendar.getTime()) + " " + getString(2131230862) + " " + this.sdfHora.format(localCalendar.getTime()) + "h.")
    {
      String str3 = str2 + "\n" + getString(2131230825) + " " + getString(2131230863) + this.sorteioVO.getValorPresente() + ".";
      localSms.enviarSms(getApplicationContext(), str1, str3);
      return;
      localCalendar = Calendar.getInstance();
      localCalendar.setTime(this.sdfDiaHora.parse(this.sorteioVO.getDataSorteio()));
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.familiDAO = new FamiliaDAO(getApplicationContext());
    this.participanteDAO = new ParticipanteDAO(getApplicationContext());
    super.onCreate(paramBundle);
    setContentView(2130903064);
    this.idSorteio = getIntent().getExtras().getInt("id");
    this.sorteioDAO = new SorteioDAO(this);
    this.sorteioVO = this.sorteioDAO.buscaPorId(Integer.valueOf(this.idSorteio));
    this.btnSortear = ((Button)findViewById(2131034206));
    getSupportActionBar().setTitle(this.sorteioVO.getTitulo());
    if (this.sorteioVO.getDataSorteio().contains("às"))
      getSupportActionBar().setSubtitle(getString(2131230823) + " " + this.sorteioVO.getDataSorteio());
    while (true)
    {
      getResources();
      LinearLayout localLinearLayout = (LinearLayout)findViewById(2131034203);
      Button localButton = (Button)findViewById(2131034205);
      Calendar localCalendar2;
      if (this.sorteioVO.getComFamilia().intValue() == 1)
      {
        this.btnSortear.setVisibility(8);
        localCalendar2 = Calendar.getInstance();
      }
      try
      {
        if (this.sorteioVO.getDataSorteio().contains("às"))
          localCalendar2.setTime(new SimpleDateFormat("dd/MM/yyyy ' " + getString(2131230862) + " ' HH:mm", new Locale("pt-BR")).parse(this.sorteioVO.getDataSorteio()));
        while (true)
        {
          while (true)
          {
            if (!Calendar.getInstance().after(localCalendar2))
              break label639;
            TextView localTextView = (TextView)localLinearLayout.findViewById(2131034204);
            localTextView.setText(getString(2131230857));
            localTextView.setTextSize(24.0F);
            localButton.setVisibility(8);
            localLinearLayout.setVisibility(0);
            this.mContainerView = ((ViewGroup)findViewById(2131034208));
            this.empty = ((TextView)findViewById(2131034207));
            localIterator = new ParticipanteDAO(getApplicationContext()).listarPorSorteio(this.idSorteio).iterator();
            if (localIterator.hasNext())
              break label648;
            this.adView = new AdView(this);
            this.adView.setAdUnitId("ca-app-pub-3737758644488199/4840949260");
            this.adView.setAdSize(AdSize.BANNER);
            ((LinearLayout)findViewById(2131034202)).addView(this.adView, 0);
            AdRequest localAdRequest = new AdRequest.Builder().build();
            this.adView.loadAd(localAdRequest);
            return;
            Calendar localCalendar1 = Calendar.getInstance();
            try
            {
              localCalendar1.setTime(this.sdfDiaHora.parse(this.sorteioVO.getDataSorteio()));
              getSupportActionBar().setSubtitle(getString(2131230823) + " " + this.sdfDia.format(localCalendar1.getTime()) + " " + getString(2131230862) + " " + this.sdfHora.format(localCalendar1.getTime()));
            }
            catch (ParseException localParseException1)
            {
              while (true)
                localParseException1.printStackTrace();
            }
          }
          localCalendar2.setTime(this.sdfDiaHora.parse(this.sorteioVO.getDataSorteio()));
        }
      }
      catch (ParseException localParseException3)
      {
        while (true)
        {
          Iterator localIterator;
          continue;
          label639: localButton.setVisibility(0);
          continue;
          label648: ParticipanteVO localParticipanteVO = (ParticipanteVO)localIterator.next();
          try
          {
            addItem(localParticipanteVO);
          }
          catch (ParseException localParseException2)
          {
            localParseException2.printStackTrace();
          }
        }
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

  public void reenviarTodos(View paramView)
    throws ParseException
  {
    Iterator localIterator = new ParticipanteDAO(getApplicationContext()).listarPorSorteio(this.sorteioVO.getId().intValue()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        Toast.makeText(getApplicationContext(), getString(2131230829), 0).show();
        return;
      }
      enviarSMS((ParticipanteVO)localIterator.next());
    }
  }

  public void sortear(View paramView)
    throws ParseException
  {
    ParticipanteDAO localParticipanteDAO = new ParticipanteDAO(getApplicationContext());
    Random localRandom = new Random();
    ((LinearLayout)findViewById(2131034202));
    List localList = localParticipanteDAO.listarPorSorteio(this.idSorteio);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Object localObject = (ParticipanteVO)localList.get(0);
    int i = 0;
    int j;
    Iterator localIterator8;
    if (i >= localList.size())
    {
      j = 0;
      localIterator8 = localList.iterator();
    }
    while (true)
    {
      if (!localIterator8.hasNext())
      {
        if ((j == 0) || (this.cont >= 10))
          break label755;
        this.cont = (1 + this.cont);
        sortear(paramView);
        return;
        localArrayList1.clear();
        localArrayList2.clear();
        localArrayList3.clear();
        Iterator localIterator1 = localList.iterator();
        label178: Iterator localIterator2;
        label197: Iterator localIterator3;
        label216: ParticipanteVO localParticipanteVO9;
        Iterator localIterator7;
        label288: label298: Iterator localIterator5;
        if (!localIterator1.hasNext())
        {
          localIterator2 = localArrayList1.iterator();
          if (localIterator2.hasNext())
            break label372;
          localIterator3 = localArrayList3.iterator();
          if (localIterator3.hasNext())
            break label408;
          if (localArrayList3.isEmpty())
            break label572;
          if (localArrayList2.isEmpty())
            break label483;
          localParticipanteVO9 = (ParticipanteVO)localArrayList2.get(localRandom.nextInt(localArrayList2.size()));
          ((ParticipanteVO)localObject).setParticipanteSorteado(localParticipanteVO9.getId());
          localIterator7 = localList.iterator();
          if (localIterator7.hasNext())
            break label446;
          localIterator5 = localList.iterator();
          label307: if (localIterator5.hasNext())
            break label661;
        }
        while (true)
        {
          i++;
          break;
          ParticipanteVO localParticipanteVO1 = (ParticipanteVO)localIterator1.next();
          if ((localParticipanteVO1.getSorteado().intValue() != 0) || (localParticipanteVO1.getId() == ((ParticipanteVO)localObject).getId()))
            break label178;
          localArrayList1.add(localParticipanteVO1);
          break label178;
          label372: ParticipanteVO localParticipanteVO2 = (ParticipanteVO)localIterator2.next();
          if (localParticipanteVO2.getParticipanteSorteado().intValue() != 0)
            break label197;
          localArrayList3.add(localParticipanteVO2);
          break label197;
          label408: ParticipanteVO localParticipanteVO3 = (ParticipanteVO)localIterator3.next();
          if (((ParticipanteVO)localObject).getFamilia() == localParticipanteVO3.getFamilia())
            break label216;
          localArrayList2.add(localParticipanteVO3);
          break label216;
          label446: ParticipanteVO localParticipanteVO10 = (ParticipanteVO)localIterator7.next();
          if (localParticipanteVO10.getId() != localParticipanteVO9.getId())
            break label288;
          localParticipanteVO10.setSorteado(Integer.valueOf(1));
          break label288;
          label483: ParticipanteVO localParticipanteVO7 = (ParticipanteVO)localArrayList3.get(localRandom.nextInt(localArrayList3.size()));
          ((ParticipanteVO)localObject).setParticipanteSorteado(localParticipanteVO7.getId());
          Iterator localIterator6 = localList.iterator();
          while (localIterator6.hasNext())
          {
            ParticipanteVO localParticipanteVO8 = (ParticipanteVO)localIterator6.next();
            if (localParticipanteVO8.getId() != localParticipanteVO7.getId())
              continue;
            localParticipanteVO8.setSorteado(Integer.valueOf(1));
          }
          break label298;
          label572: ParticipanteVO localParticipanteVO4 = (ParticipanteVO)localArrayList1.get(localRandom.nextInt(localArrayList1.size()));
          ((ParticipanteVO)localObject).setParticipanteSorteado(localParticipanteVO4.getId());
          Iterator localIterator4 = localList.iterator();
          while (localIterator4.hasNext())
          {
            ParticipanteVO localParticipanteVO5 = (ParticipanteVO)localIterator4.next();
            if (localParticipanteVO5.getId() != localParticipanteVO4.getId())
              continue;
            localParticipanteVO5.setSorteado(Integer.valueOf(1));
          }
          break label298;
          label661: ParticipanteVO localParticipanteVO6 = (ParticipanteVO)localIterator5.next();
          if (localParticipanteVO6.getId() != ((ParticipanteVO)localObject).getParticipanteSorteado())
            break label307;
          localObject = localParticipanteVO6;
        }
      }
      ParticipanteVO localParticipanteVO11 = (ParticipanteVO)localIterator8.next();
      if ((localParticipanteVO11.getFamilia() != localParticipanteDAO.buscaPorId(localParticipanteVO11.getParticipanteSorteado()).getFamilia()) || (localParticipanteVO11.getFamilia().intValue() == 0) || (localParticipanteVO11.getFamilia().intValue() == -1))
        continue;
      j = 1;
    }
    label755: Iterator localIterator9 = localList.iterator();
    while (true)
    {
      if (!localIterator9.hasNext())
      {
        this.sorteioVO.setComFamilia(Integer.valueOf(1));
        this.sorteioDAO.update(this.sorteioVO);
        finish();
        Toast.makeText(getApplicationContext(), getString(2131230830), 0).show();
        startActivity(new Intent(this, ActivityListarSorteioCriados.class));
        break;
      }
      ParticipanteVO localParticipanteVO12 = (ParticipanteVO)localIterator9.next();
      SorteioDAO localSorteioDAO = new SorteioDAO(getApplicationContext());
      localSorteioDAO.buscaPorId(localParticipanteVO12.getSorteio());
      localParticipanteDAO.update(localParticipanteVO12);
      enviarSMS(localParticipanteVO12);
      SalvarParticipantesSimpleDB localSalvarParticipantesSimpleDB = new SalvarParticipantesSimpleDB(null);
      ParticipanteVO[] arrayOfParticipanteVO = new ParticipanteVO[1];
      arrayOfParticipanteVO[0] = localParticipanteVO12;
      localSalvarParticipantesSimpleDB.execute(arrayOfParticipanteVO);
    }
  }

  private class SalvarParticipantesSimpleDB extends AsyncTask<ParticipanteVO, Void, Void>
  {
    private SalvarParticipantesSimpleDB()
    {
    }

    protected Void doInBackground(ParticipanteVO[] paramArrayOfParticipanteVO)
    {
      new SimpleDB().participanteSorteado(paramArrayOfParticipanteVO[0], ActivityListarParticipantes.this.getApplicationContext());
      return null;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.ActivityListarParticipantes
 * JD-Core Version:    0.6.0
 */