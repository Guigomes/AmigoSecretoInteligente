package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;
public class ContatoVO
{
  private Integer id;
  private Integer idFamilia;
  private String nomeContato;
  private String numeroTelefone;

  public ContatoVO()
  {
  }

  public ContatoVO(ParticipanteVO paramParticipanteVO)
  {
    this.nomeContato = paramParticipanteVO.getNome();
    this.idFamilia = paramParticipanteVO.getFamilia();
    this.numeroTelefone = paramParticipanteVO.getTelefone();
  }

  public ContatoVO(String paramString1, Integer paramInteger, String paramString2)
  {
    this.nomeContato = paramString1;
    this.idFamilia = paramInteger;
    this.numeroTelefone = paramString2;
  }

  public Integer getId()
  {
    return this.id;
  }

  public Integer getIdFamilia()
  {
    return this.idFamilia;
  }

  public String getNomeContato()
  {
    return this.nomeContato;
  }

  public String getNumeroTelefone()
  {
    return this.numeroTelefone;
  }

  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }

  public void setIdFamilia(Integer paramInteger)
  {
    this.idFamilia = paramInteger;
  }

  public void setNomeContato(String paramString)
  {
    this.nomeContato = paramString;
  }

  public void setNumeroTelefone(String paramString)
  {
    this.numeroTelefone = paramString;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.ContatoVO
 * JD-Core Version:    0.6.0
 */