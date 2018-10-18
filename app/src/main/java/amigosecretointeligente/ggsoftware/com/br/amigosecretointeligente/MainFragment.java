package br.gomes.amigosecreto;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.gomes.amigosecreto.simpledb.SimpleDB;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import java.util.Locale;

public class MainFragment extends Fragment
{
  private static final String TAG = "MainFragment";
  AlertDialog alertDialog2;
  private Session.StatusCallback callback = new Session.StatusCallback()
  {
    public void call(Session paramSession, SessionState paramSessionState, Exception paramException)
    {
      MainFragment.this.onSessionStateChange(paramSession, paramSessionState, paramException);
    }
  };
  Context context;
  String meuNumero;
  int numeroAtivacao;
  private UiLifecycleHelper uiHelper;

  private void onSessionStateChange(Session paramSession, SessionState paramSessionState, Exception paramException)
  {
    if ((paramSessionState.isOpened()) && (paramSession.isOpened()) && (MainActivity.getNumber() == null))
      Request.newMeRequest(paramSession, new Request.GraphUserCallback()
      {
        public void onCompleted(GraphUser paramGraphUser, Response paramResponse)
        {
          View localView1;
          View localView2;
          AlertDialog.Builder localBuilder;
          EditText localEditText;
          if (paramGraphUser != null)
          {
            MainFragment.this.context = MainFragment.this.getActivity();
            LayoutInflater localLayoutInflater = (LayoutInflater)MainFragment.this.context.getSystemService("layout_inflater");
            localView1 = localLayoutInflater.inflate(2130903097, null);
            localView2 = localLayoutInflater.inflate(2130903086, null);
            localBuilder = new AlertDialog.Builder(MainFragment.this.context);
            ((TextView)localView2.findViewById(2131034248)).setText(MainFragment.this.getString(2131230888));
            localEditText = (EditText)localView1.findViewById(2131034281);
            if (!Locale.getDefault().getLanguage().equals("pt"))
              break label241;
            localEditText.addTextChangedListener(Util.insertBR(localEditText));
          }
          while (true)
          {
            ((TextView)localView1.findViewById(2131034277)).setText(MainFragment.this.getString(2131230879) + " " + paramGraphUser.getName() + ", " + MainFragment.this.getString(2131230880));
            ((Button)localView1.findViewById(2131034286)).setOnClickListener(new MainFragment.2.1(this, localEditText, paramGraphUser));
            localBuilder.setView(localView1);
            localBuilder.setCustomTitle(localView2);
            localBuilder.create().show();
            return;
            label241: localEditText.addTextChangedListener(Util.insertUS(localEditText));
          }
        }
      }).executeAsync();
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.uiHelper.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.uiHelper = new UiLifecycleHelper(getActivity(), this.callback);
    this.uiHelper.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903090, null, false);
    ((LoginButton)localView.findViewById(2131034260)).setFragment(this);
    return localView;
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.uiHelper.onDestroy();
  }

  public void onPause()
  {
    super.onPause();
    this.uiHelper.onPause();
  }

  public void onResume()
  {
    super.onResume();
    Session localSession = Session.getActiveSession();
    if ((localSession != null) && ((localSession.isOpened()) || (localSession.isClosed())))
      onSessionStateChange(localSession, localSession.getState(), null);
    this.uiHelper.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.uiHelper.onSaveInstanceState(paramBundle);
  }

  private class CadastrarNovoUsuario extends AsyncTask<String, Void, Void>
  {
    private CadastrarNovoUsuario()
    {
    }

    protected Void doInBackground(String[] paramArrayOfString)
    {
      new SimpleDB().salvarFamilia(paramArrayOfString[0], paramArrayOfString[1]);
      return null;
    }
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.MainFragment
 * JD-Core Version:    0.6.0
 */