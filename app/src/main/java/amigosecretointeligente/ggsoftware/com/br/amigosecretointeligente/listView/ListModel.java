package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.listView;


import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.ParticipanteVO;
import amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd.SorteioVO;

public class ListModel
{
  private String Amigo = "";
  private String CompanyName = "";
  private String Image = "";
  int id;
  private Integer idImagem;
  private ParticipanteVO participanteVO;
  private SorteioVO sorteioVO;
  private String telefone = "";

  public String getAmigo()
  {
    return this.Amigo;
  }

  public String getCompanyName()
  {
    return this.CompanyName;
  }

  public int getId()
  {
    return this.id;
  }

  public Integer getIdImagem()
  {
    return this.idImagem;
  }

  public String getImage()
  {
    return this.Image;
  }

  public ParticipanteVO getParticipanteVO()
  {
    return this.participanteVO;
  }

  public SorteioVO getSorteioVO()
  {
    return this.sorteioVO;
  }

  public String getTelefone()
  {
    return this.telefone;
  }

  public void setAmigo(String paramString)
  {
    this.Amigo = paramString;
  }

  public void setCompanyName(String paramString)
  {
    this.CompanyName = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIdImagem(Integer paramInteger)
  {
    this.idImagem = paramInteger;
  }

  public void setImage(String paramString)
  {
    this.Image = paramString;
  }

  public void setParticipanteVO(ParticipanteVO paramParticipanteVO)
  {
    this.participanteVO = paramParticipanteVO;
  }

  public void setSorteioVO(SorteioVO paramSorteioVO)
  {
    this.sorteioVO = paramSorteioVO;
  }

  public void setTelefone(String paramString)
  {
    this.telefone = paramString;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.listView.ListModel
 * JD-Core Version:    0.6.0
 */