package br.gomes.amigosecreto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.com.curso.bd.ContatoDAO;
import br.com.curso.bd.FamiliaDAO;
import br.com.curso.bd.FamiliaVO;
import br.com.curso.bd.ParticipanteDAO;
import br.com.curso.bd.ParticipanteVO;
import br.com.curso.bd.SorteioDAO;
import br.com.curso.bd.SorteioVO;
import br.gomes.amigosecreto.simpledb.SimpleDB;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ActivityNovoSorteio extends Activity
{
  AlertDialog alertDialog;
  CheckBox chkBoxComFamilias;
  ViewGroup container;
  final Context context = this;
  String dataSorteio;
  boolean editarDividido = false;
  EditText edtNome;
  EditText edtTel;
  FamiliaDAO familiaDAO = new FamiliaDAO(this.context);
  int indice = 1;
  List<ParticipanteVO> listaParticipantes;
  String m_Text;
  ParticipanteDAO participanteDAO;
  String simpleIDSorteio;
  Integer sorteioId;
  Spinner spiner;
  String tituloSorteio;
  EditText userInput;
  Double valorPresente;

  private void addView()
  {
    ViewGroup localViewGroup = (ViewGroup)LayoutInflater.from(this).inflate(2130903091, this.container, false);
    Spinner localSpinner = (Spinner)localViewGroup.findViewById(2131034265);
    ((TextView)localViewGroup.findViewById(2131034261)).setText(getString(2131230867) + " " + this.indice);
    EditText localEditText = (EditText)localViewGroup.findViewById(2131034267);
    ArrayList localArrayList;
    Iterator localIterator;
    label140: ArrayAdapter localArrayAdapter;
    if (Locale.getDefault().getLanguage().equals("pt"))
    {
      localEditText.addTextChangedListener(Util.insertBR(localEditText));
      this.indice = (1 + this.indice);
      List localList = this.familiaDAO.listar();
      localArrayList = new ArrayList();
      localIterator = localList.iterator();
      if (localIterator.hasNext())
        break label320;
      localArrayAdapter = new ArrayAdapter(this, 17367048);
      if (!localArrayList.isEmpty())
        break label344;
      localArrayList.add(getString(2131230868));
    }
    while (true)
    {
      localArrayAdapter.addAll(localArrayList);
      localArrayAdapter.setDropDownViewResource(17367049);
      localSpinner.setAdapter(localArrayAdapter);
      localSpinner.setSelection(0);
      LinearLayout localLinearLayout = (LinearLayout)localViewGroup.findViewById(2131034264);
      if (!this.chkBoxComFamilias.isChecked())
        localLinearLayout.setVisibility(8);
      ((ImageButton)localViewGroup.findViewById(2131034268)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          View localView = (View)paramView.getParent();
          ActivityNovoSorteio.this.edtTel = ((EditText)localView.findViewById(2131034267));
          LinearLayout localLinearLayout = (LinearLayout)localView.getParent();
          ActivityNovoSorteio.this.edtNome = ((EditText)localLinearLayout.findViewById(2131034263));
          ActivityNovoSorteio.this.selecionar(paramView);
        }
      });
      ((ImageView)localViewGroup.findViewById(2131034262)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          LinearLayout localLinearLayout = (LinearLayout)paramView.getParent().getParent();
          int i;
          if (ActivityNovoSorteio.this.container.getChildCount() > 6)
          {
            ActivityNovoSorteio.this.container.removeView(localLinearLayout);
            ActivityNovoSorteio.this.indice = 1;
            i = -1 + ActivityNovoSorteio.this.container.getChildCount();
            if (i >= 3);
          }
          while (true)
          {
            return;
            ((TextView)ActivityNovoSorteio.this.container.getChildAt(i).findViewById(2131034261)).setText(ActivityNovoSorteio.this.getString(2131230867) + " " + ActivityNovoSorteio.this.indice);
            ActivityNovoSorteio localActivityNovoSorteio = ActivityNovoSorteio.this;
            localActivityNovoSorteio.indice = (1 + localActivityNovoSorteio.indice);
            i--;
            break;
            Toast.makeText(ActivityNovoSorteio.this.getApplicationContext(), ActivityNovoSorteio.this.getString(2131230848), 0).show();
          }
        }
      });
      ((ImageButton)localViewGroup.findViewById(2131034266)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          LayoutInflater localLayoutInflater = LayoutInflater.from(ActivityNovoSorteio.this.context);
          View localView1 = localLayoutInflater.inflate(2130903095, null);
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(ActivityNovoSorteio.this.context);
          View localView2 = localLayoutInflater.inflate(2130903086, null);
          ((TextView)localView2.findViewById(2131034248)).setText(ActivityNovoSorteio.this.getString(2131230878));
          LinearLayout localLinearLayout = (LinearLayout)paramView.getParent();
          ActivityNovoSorteio.this.spiner = ((Spinner)localLinearLayout.findViewById(2131034265));
          localBuilder.setCustomTitle(localView2);
          localBuilder.setView(localView1);
          ActivityNovoSorteio.this.userInput = ((EditText)localView1.findViewById(2131034281));
          localBuilder.setCancelable(true);
          ActivityNovoSorteio.this.alertDialog = localBuilder.create();
          ActivityNovoSorteio.this.alertDialog.show();
        }
      });
      this.container.addView(localViewGroup, 3);
      return;
      localEditText.addTextChangedListener(Util.insertUS(localEditText));
      break;
      label320: localArrayList.add(((FamiliaVO)localIterator.next()).getNome());
      break label140;
      label344: localArrayList.add(0, getString(2131230869));
    }
  }

  private void addView(ParticipanteVO paramParticipanteVO)
  {
    String str = null;
    int i = -1;
    ViewGroup localViewGroup = (ViewGroup)LayoutInflater.from(this).inflate(2130903091, this.container, false);
    Spinner localSpinner = (Spinner)localViewGroup.findViewById(2131034265);
    ((TextView)localViewGroup.findViewById(2131034261)).setText(getString(2131230867) + " " + this.indice);
    ArrayList localArrayList;
    Iterator localIterator;
    label211: EditText localEditText;
    if (Locale.getDefault().getLanguage().equals("pt"))
    {
      ((EditText)localViewGroup.findViewById(2131034267)).setText(Util.formataTelefoneBR(paramParticipanteVO.getTelefone()));
      ((EditText)localViewGroup.findViewById(2131034263)).setText(paramParticipanteVO.getNome());
      this.indice = (1 + this.indice);
      List localList = this.familiaDAO.listar();
      localArrayList = new ArrayList();
      if ((paramParticipanteVO.getFamilia().intValue() != 0) && (paramParticipanteVO.getFamilia().intValue() != -1))
      {
        str = this.familiaDAO.buscaPorId(paramParticipanteVO.getFamilia()).getNome();
        this.editarDividido = true;
      }
      localIterator = localList.iterator();
      if (localIterator.hasNext())
        break label460;
      ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367048);
      if (!localArrayList.isEmpty())
        break label517;
      localArrayList.add(getString(2131230868));
      label257: localArrayAdapter.addAll(localArrayList);
      localArrayAdapter.setDropDownViewResource(17367049);
      localSpinner.setAdapter(localArrayAdapter);
      localSpinner.setSelection(0);
      if (i != -1)
        localSpinner.setSelection(i + 1);
      LinearLayout localLinearLayout = (LinearLayout)localViewGroup.findViewById(2131034264);
      if (!this.chkBoxComFamilias.isChecked())
        localLinearLayout.setVisibility(8);
      ((ImageButton)localViewGroup.findViewById(2131034268)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          View localView = (View)paramView.getParent();
          ActivityNovoSorteio.this.edtTel = ((EditText)localView.findViewById(2131034267));
          LinearLayout localLinearLayout = (LinearLayout)localView.getParent();
          ActivityNovoSorteio.this.edtNome = ((EditText)localLinearLayout.findViewById(2131034263));
          ActivityNovoSorteio.this.selecionar(paramView);
        }
      });
      ((ImageView)localViewGroup.findViewById(2131034262)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          LinearLayout localLinearLayout = (LinearLayout)paramView.getParent().getParent();
          int i;
          if (ActivityNovoSorteio.this.container.getChildCount() > 6)
          {
            ActivityNovoSorteio.this.container.removeView(localLinearLayout);
            ActivityNovoSorteio.this.indice = 1;
            i = -1 + ActivityNovoSorteio.this.container.getChildCount();
            if (i >= 3);
          }
          while (true)
          {
            return;
            ((TextView)ActivityNovoSorteio.this.container.getChildAt(i).findViewById(2131034261)).setText(ActivityNovoSorteio.this.getString(2131230867) + " " + ActivityNovoSorteio.this.indice);
            ActivityNovoSorteio localActivityNovoSorteio = ActivityNovoSorteio.this;
            localActivityNovoSorteio.indice = (1 + localActivityNovoSorteio.indice);
            i--;
            break;
            Toast.makeText(ActivityNovoSorteio.this.getApplicationContext(), ActivityNovoSorteio.this.getString(2131230848), 0).show();
          }
        }
      });
      ((ImageButton)localViewGroup.findViewById(2131034266)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          LayoutInflater localLayoutInflater = LayoutInflater.from(ActivityNovoSorteio.this.context);
          View localView1 = localLayoutInflater.inflate(2130903095, null);
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(ActivityNovoSorteio.this.context);
          View localView2 = localLayoutInflater.inflate(2130903086, null);
          ((TextView)localView2.findViewById(2131034248)).setText(ActivityNovoSorteio.this.getString(2131230878));
          LinearLayout localLinearLayout = (LinearLayout)paramView.getParent();
          ActivityNovoSorteio.this.spiner = ((Spinner)localLinearLayout.findViewById(2131034265));
          localBuilder.setCustomTitle(localView2);
          localBuilder.setView(localView1);
          ActivityNovoSorteio.this.userInput = ((EditText)localView1.findViewById(2131034281));
          localBuilder.setCancelable(true);
          ActivityNovoSorteio.this.alertDialog = localBuilder.create();
          ActivityNovoSorteio.this.alertDialog.show();
        }
      });
      localEditText = (EditText)localViewGroup.findViewById(2131034267);
      if (!Locale.getDefault().getLanguage().equals("pt"))
        break label535;
      localEditText.addTextChangedListener(Util.insertBR(localEditText));
    }
    while (true)
    {
      this.container.addView(localViewGroup, 3);
      return;
      ((EditText)localViewGroup.findViewById(2131034267)).setText(Util.formataTelefoneUS(paramParticipanteVO.getTelefone()));
      break;
      label460: FamiliaVO localFamiliaVO = (FamiliaVO)localIterator.next();
      localArrayList.add(localFamiliaVO.getNome());
      if ((str == null) || (!localFamiliaVO.getNome().equals(str)))
        break label211;
      i = localArrayList.indexOf(localFamiliaVO.getNome());
      break label211;
      label517: localArrayList.add(0, getString(2131230869));
      break label257;
      label535: localEditText.addTextChangedListener(Util.insertUS(localEditText));
    }
  }

  private void atualizaSpinner()
  {
    List localList = this.familiaDAO.listar();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(0, getString(2131230869));
    Iterator localIterator = localList.iterator();
    ArrayAdapter localArrayAdapter;
    if (!localIterator.hasNext())
    {
      localArrayAdapter = new ArrayAdapter(this, 17367048);
      localArrayAdapter.addAll(localArrayList);
      localArrayAdapter.setDropDownViewResource(17367049);
    }
    for (int i = 3; ; i++)
    {
      if (i >= this.container.getChildCount())
      {
        return;
        localArrayList.add(((FamiliaVO)localIterator.next()).getNome());
        break;
      }
      Spinner localSpinner = (Spinner)((LinearLayout)this.container.getChildAt(i)).findViewById(2131034265);
      int j = localSpinner.getSelectedItemPosition();
      localSpinner.setAdapter(localArrayAdapter);
      localSpinner.setSelection(j);
    }
  }

  public void addView(View paramView)
  {
    addView();
  }

  public void cancelar(View paramView)
  {
    this.alertDialog.dismiss();
  }

  public void criar(View paramView)
  {
    this.m_Text = this.userInput.getText().toString();
    if (!this.m_Text.isEmpty())
      if (this.familiaDAO.buscaIdPorNome(this.m_Text) == -1)
      {
        new FamiliaVO(this.m_Text).setIdSimpleDB(String.valueOf(System.currentTimeMillis()));
        if (this.familiaDAO.insert(new FamiliaVO(this.m_Text)))
        {
          Toast.makeText(this.context, getString(2131230870) + " " + this.m_Text + " " + getString(2131230871), 0).show();
          this.alertDialog.dismiss();
          atualizaSpinner();
          this.spiner.setSelection(-1 + this.spiner.getCount());
        }
      }
    while (true)
    {
      return;
      Toast.makeText(this.context, getString(2131230858), 0).show();
      continue;
      Toast.makeText(this.context, getString(2131230849), 0).show();
    }
  }

  public void criarSorteio(View paramView)
  {
    SorteioDAO localSorteioDAO = new SorteioDAO(this.context);
    SorteioVO localSorteioVO1 = new SorteioVO(this.tituloSorteio, this.dataSorteio, this.valorPresente);
    ArrayList localArrayList1 = new ArrayList();
    int i = 1;
    int j = 3;
    label59: int k;
    ArrayList localArrayList2;
    Iterator localIterator;
    if (j >= this.container.getChildCount())
      if (i != 0)
      {
        if ((this.sorteioId != null) && (this.sorteioId.intValue() != 0))
        {
          SorteioVO localSorteioVO2 = localSorteioDAO.buscaPorId(this.sorteioId);
          this.participanteDAO.deletarParticipantesSorteio(localSorteioVO2.getId().intValue());
          localSorteioDAO.delete(localSorteioVO2);
          ExlcuirSorteioSimpleDB localExlcuirSorteioSimpleDB = new ExlcuirSorteioSimpleDB(null);
          String[] arrayOfString = new String[1];
          arrayOfString[0] = localSorteioVO2.getIdSimpleDB();
          localExlcuirSorteioSimpleDB.execute(arrayOfString);
        }
        this.simpleIDSorteio = String.valueOf(System.currentTimeMillis());
        localSorteioVO1.setIdSimpleDB(this.simpleIDSorteio);
        localSorteioDAO.insert(localSorteioVO1);
        k = localSorteioDAO.proximoId();
        localArrayList2 = new ArrayList();
        new ContatoDAO(getApplicationContext());
        localIterator = localArrayList1.iterator();
      }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        SalvarParticipantesSimpleDB localSalvarParticipantesSimpleDB = new SalvarParticipantesSimpleDB(null);
        List[] arrayOfList = new List[1];
        arrayOfList[0] = localArrayList2;
        localSalvarParticipantesSimpleDB.execute(arrayOfList);
        finish();
        if ((this.sorteioId != null) && (this.sorteioId.intValue() != 0))
          startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(this.context, getString(2131230850), 0).show();
        return;
        LinearLayout localLinearLayout = (LinearLayout)this.container.getChildAt(j);
        Spinner localSpinner = (Spinner)localLinearLayout.findViewById(2131034265);
        EditText localEditText1 = (EditText)localLinearLayout.findViewById(2131034263);
        EditText localEditText2 = (EditText)localLinearLayout.findViewById(2131034267);
        i = 1;
        String str1 = localEditText1.getText().toString();
        String str2 = Util.unmask(localEditText2.getText().toString());
        String str3 = localSpinner.getSelectedItem().toString();
        String str4 = ((TextView)localLinearLayout.findViewById(2131034261)).getText().toString();
        if ((str1 == null) || (str1.isEmpty()))
        {
          Toast.makeText(this, getString(2131230872) + " " + str4 + " " + getString(2131230873) + ".", 0).show();
          i = 0;
          break label59;
        }
        if ((str2 == null) || (str2.isEmpty()))
        {
          Toast.makeText(this, getString(2131230874) + " " + str4 + " " + getString(2131230873) + ".", 0).show();
          i = 0;
          break label59;
        }
        ParticipanteVO localParticipanteVO2 = null;
        if (str3.equals(getString(2131230869)))
        {
          Integer localInteger2 = Integer.valueOf(0);
          localParticipanteVO2 = new ParticipanteVO(str1, localInteger2, str2);
        }
        while (true)
        {
          localArrayList1.add(localParticipanteVO2);
          j++;
          break;
          if (str3.isEmpty())
            continue;
          int m = this.familiaDAO.buscaIdPorNome(str3);
          Integer localInteger1 = Integer.valueOf(m);
          localParticipanteVO2 = new ParticipanteVO(str1, localInteger1, str2);
        }
      }
      ParticipanteVO localParticipanteVO1 = (ParticipanteVO)localIterator.next();
      localParticipanteVO1.setIdSimpleDB(String.valueOf(System.currentTimeMillis()));
      localParticipanteVO1.setSimpleIDSorteio(this.simpleIDSorteio);
      localParticipanteVO1.setSorteio(Integer.valueOf(k));
      this.participanteDAO.insert(localParticipanteVO1);
      localParticipanteVO1.setId(Integer.valueOf(this.participanteDAO.proximoId()));
      localArrayList2.add(localParticipanteVO1);
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramIntent != null)
      try
      {
        Uri localUri = paramIntent.getData();
        ContentResolver localContentResolver = getContentResolver();
        String[] arrayOfString = new String[2];
        arrayOfString[0] = "display_name";
        arrayOfString[1] = "data1";
        Cursor localCursor = localContentResolver.query(localUri, arrayOfString, null, null, null);
        if ((localCursor != null) && (localCursor.moveToFirst()))
        {
          String str1 = localCursor.getString(0);
          this.edtNome.setText(str1);
          String str2 = localCursor.getString(1);
          if (str2.startsWith("0"))
            str2 = str2.substring(1);
          String str3 = str2.replace("+55", "");
          if (Locale.getDefault().getLanguage().equals("pt"))
            this.edtTel.setText(Util.formataTelefoneBR(str3));
          else
            this.edtTel.setText(Util.formataTelefoneUS(str3));
        }
      }
      catch (Exception localException)
      {
      }
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.participanteDAO = new ParticipanteDAO(this.context);
    super.onCreate(paramBundle);
    setContentView(2130903067);
    this.listaParticipantes = new ArrayList();
    this.container = ((ViewGroup)findViewById(2131034208));
    Intent localIntent = getIntent();
    this.tituloSorteio = localIntent.getExtras().getString("tituloSorteio");
    this.dataSorteio = localIntent.getExtras().getString("dataSorteio");
    this.valorPresente = Double.valueOf(localIntent.getExtras().getDouble("valorPresente"));
    this.chkBoxComFamilias = ((CheckBox)findViewById(2131034215));
    this.chkBoxComFamilias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
        int i = 3;
        if (i >= ActivityNovoSorteio.this.container.getChildCount())
          return;
        LinearLayout localLinearLayout1 = (LinearLayout)ActivityNovoSorteio.this.container.getChildAt(i);
        LinearLayout localLinearLayout2 = (LinearLayout)localLinearLayout1.findViewById(2131034264);
        if (paramBoolean)
        {
          Toast.makeText(ActivityNovoSorteio.this.getApplicationContext(), ActivityNovoSorteio.this.getString(2131230847), 0).show();
          localLinearLayout2.setVisibility(0);
        }
        while (true)
        {
          i++;
          break;
          ((Spinner)localLinearLayout1.findViewById(2131034265)).setSelection(0);
          localLinearLayout2.setVisibility(8);
        }
      }
    });
    this.sorteioId = Integer.valueOf(localIntent.getExtras().getInt("sorteioId"));
    Iterator localIterator;
    if ((this.sorteioId != null) && (this.sorteioId.intValue() != 0))
    {
      this.chkBoxComFamilias.setChecked(true);
      localIterator = this.participanteDAO.listarPorSorteio(this.sorteioId.intValue()).iterator();
      if (!localIterator.hasNext())
        if (!this.editarDividido)
          this.chkBoxComFamilias.setChecked(false);
    }
    while (true)
    {
      return;
      addView((ParticipanteVO)localIterator.next());
      break;
      addView();
      addView();
      addView();
    }
  }

  public void selecionar(View paramView)
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("vnd.android.cursor.item/phone_v2");
    startActivityForResult(localIntent, 1);
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

  private class SalvarParticipantesSimpleDB extends AsyncTask<List<ParticipanteVO>, Void, Void>
  {
    private SalvarParticipantesSimpleDB()
    {
    }

    protected Void doInBackground(List<ParticipanteVO>[] paramArrayOfList)
    {
      new SimpleDB().salvarParticipantes(paramArrayOfList[0], ActivityNovoSorteio.this.getApplicationContext());
      return null;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.ActivityNovoSorteio
 * JD-Core Version:    0.6.0
 */