package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.listView;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.FamiliaDAO;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.FamiliaVO;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.ParticipanteDAO;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.ParticipanteVO;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.SorteioVO;

public class CustomAdapter extends BaseAdapter
  implements View.OnClickListener
{
  private static LayoutInflater inflater = null;
  private Activity activity;
  private ArrayList data;
  int i = 0;
  ParticipanteDAO participanteDAO = null;
  int pos;
  public Resources res;
  boolean sorteado = false;
  SorteioVO sorteioVO = new SorteioVO();
  ListModel tempValues = null;

  public CustomAdapter(Activity paramActivity, ArrayList paramArrayList, Resources paramResources)
  {
    this.activity = paramActivity;
    this.data = paramArrayList;
    this.res = paramResources;
    this.participanteDAO = new ParticipanteDAO(paramActivity.getApplicationContext());
    inflater = (LayoutInflater)this.activity.getSystemService("layout_inflater");
  }

  public CustomAdapter(Activity paramActivity, ArrayList paramArrayList, Resources paramResources, boolean paramBoolean, SorteioVO paramSorteioVO)
  {
    this.activity = paramActivity;
    this.data = paramArrayList;
    this.res = paramResources;
    this.sorteado = paramBoolean;
    this.sorteioVO = paramSorteioVO;
    inflater = (LayoutInflater)this.activity.getSystemService("layout_inflater");
    this.participanteDAO = new ParticipanteDAO(paramActivity.getApplicationContext());
  }

  public int getCount()
  {
    if (this.data.size() <= 0);
    for (int j = 1; ; j = this.data.size())
      return j;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      localView = inflater.inflate(2130903100, null);
      localViewHolder = new ViewHolder();
      localViewHolder.text = ((TextView)localView.findViewById(2131034288));
      localViewHolder.text1 = ((TextView)localView.findViewById(2131034289));
      localViewHolder.image = ((ImageView)localView.findViewById(2131034180));
      localViewHolder.imageDelete = ((ImageView)localView.findViewById(2131034292));
      localViewHolder.id = ((TextView)localView.findViewById(2131034272));
      localViewHolder.textAmigo = ((TextView)localView.findViewById(2131034290));
      localViewHolder.btnReenviar = ((Button)localView.findViewById(2131034291));
      localView.setTag(localViewHolder);
      if (this.data.size() > 0)
        break label198;
      if (!(this.activity instanceof ActivityListarSorteioParticipados))
        break label185;
      localViewHolder.text.setText("Você não está participando de nenhum sorteio");
    }
    while (true)
    {
      return localView;
      localViewHolder = (ViewHolder)localView.getTag();
      break;
      label185: localViewHolder.text.setText("Você não tem nenhum sorteio criado");
      continue;
      label198: this.tempValues = null;
      this.tempValues = ((ListModel)this.data.get(paramInt));
      localViewHolder.text.setText(this.tempValues.getCompanyName());
      localViewHolder.text1.setText(this.tempValues.getTelefone());
      label276: SimpleDateFormat localSimpleDateFormat;
      Calendar localCalendar;
      if (this.tempValues.getIdImagem() != null)
      {
        localViewHolder.image.setImageResource(this.tempValues.getIdImagem().intValue());
        localViewHolder.id.setText(String.valueOf(this.tempValues.getId()));
        if (this.sorteado)
        {
          localViewHolder.btnReenviar.setVisibility(0);
          localSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ' às ' HH:mm");
          localCalendar = Calendar.getInstance();
        }
      }
      try
      {
        localCalendar.setTime(localSimpleDateFormat.parse(this.sorteioVO.getDataSorteio()));
        label343: localViewHolder.btnReenviar.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            int i = Integer.parseInt(((TextView)((LinearLayout)paramView.getParent()).findViewById(2131034272)).getText().toString());
            ParticipanteVO localParticipanteVO = CustomAdapter.this.participanteDAO.buscaPorId(Integer.valueOf(i));
            Sms localSms = new Sms();
            String str = new StringBuilder("Titulo: ").append(CustomAdapter.this.sorteioVO.getTitulo()).append("\n").append(localParticipanteVO.getNome()).append(", você tirou o(a) ").append(CustomAdapter.this.participanteDAO.buscaPorId(localParticipanteVO.getParticipanteSorteado()).getNome()).append(" no sorteio do amigo secreto que será revelado no dia ").append(CustomAdapter.this.sorteioVO.getDataSorteio()).append("h.").toString() + "\nValor máximo do presente: R$ " + CustomAdapter.this.sorteioVO.getValorPresente() + ".";
            localSms.enviarSms(CustomAdapter.this.activity.getApplicationContext(), localParticipanteVO.getTelefone(), str);
            Toast.makeText(CustomAdapter.this.activity.getApplicationContext(), "Uma mensagem foi enviada para o(a) participante " + localParticipanteVO.getNome(), 0).show();
          }
        });
        ParticipanteVO localParticipanteVO;
        if ((Calendar.getInstance().after(localCalendar)) && (this.tempValues.getParticipanteVO().getParticipanteSorteado() != null) && (this.tempValues.getParticipanteVO().getParticipanteSorteado().intValue() != 0))
        {
          localParticipanteVO = this.participanteDAO.buscaPorId(this.tempValues.getParticipanteVO().getParticipanteSorteado());
          FamiliaVO localFamiliaVO = new FamiliaDAO(this.activity.getApplicationContext()).buscaPorId(localParticipanteVO.getFamilia());
          String str = null;
          if (localFamiliaVO != null)
            str = localFamiliaVO.getNome();
          localViewHolder.btnReenviar.setVisibility(8);
          localViewHolder.textAmigo.setVisibility(0);
          if (str == null)
            break label571;
          localViewHolder.textAmigo.setText("Tirou: " + localParticipanteVO.getNome() + " - " + str);
        }
        while (true)
        {
          if (this.tempValues.getSorteioVO() != null)
            localViewHolder.imageDelete.setVisibility(0);
          localView.setOnClickListener(new OnItemClickListener(paramInt));
          break;
          localViewHolder.image.setImageResource(2130837681);
          break label276;
          label571: localViewHolder.textAmigo.setText("Tirou: " + localParticipanteVO.getNome());
        }
      }
      catch (ParseException localParseException)
      {
        break label343;
      }
    }
  }

  public void onClick(View paramView)
  {
    Log.v("CustomAdapter", "=====Row button clicked");
  }

  private class OnItemClickListener
    implements View.OnClickListener
  {
    private int mPosition;

    OnItemClickListener(int arg2)
    {
      int i;
      this.mPosition = i;
    }

    public void onClick(View paramView)
    {
      if ((CustomAdapter.this.activity instanceof ActivityListarParticipantes))
        ((ActivityListarParticipantes)CustomAdapter.this.activity).onItemClick(this.mPosition, paramView);
      while (true)
      {
        return;
        if ((CustomAdapter.this.activity instanceof ActivityListarSorteioCriados))
        {
          ((ActivityListarSorteioCriados)CustomAdapter.this.activity).onItemClick(this.mPosition, paramView);
          continue;
        }
        if ((CustomAdapter.this.activity instanceof ActivityListarParticipantesSorteiosParticipados))
        {
          ((ActivityListarParticipantesSorteiosParticipados)CustomAdapter.this.activity).onItemClick(this.mPosition, paramView);
          continue;
        }
        if (!(CustomAdapter.this.activity instanceof ActivityListarSorteioParticipados))
          continue;
        ((ActivityListarSorteioParticipados)CustomAdapter.this.activity).onItemClick(this.mPosition, paramView);
      }
    }
  }

  public static class ViewHolder
  {
    public Button btnReenviar;
    public TextView id;
    public ImageView image;
    public ImageView imageDelete;
    public TextView text;
    public TextView text1;
    public TextView textAmigo;
    public TextView textWide;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.listView.CustomAdapter
 * JD-Core Version:    0.6.0
 */