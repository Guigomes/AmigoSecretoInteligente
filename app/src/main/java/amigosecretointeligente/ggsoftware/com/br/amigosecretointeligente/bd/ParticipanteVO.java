package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

public class ParticipanteVO
{
  private Integer familia;
  private Integer id;
  private String idSimpleDB;
  private String meuPresente;
  private String nome;
  private Integer participanteSorteado;
  private String participanteSorteadoSimpleDB;
  private String presenteSorteado;
  private String simpleIDSorteio;
  private Integer sorteado = Integer.valueOf(0);
  private Integer sorteio;
  private String telefone;

  public ParticipanteVO()
  {
  }

  public ParticipanteVO(String paramString1, Integer paramInteger, String paramString2)
  {
    this.nome = paramString1;
    this.familia = paramInteger;
    this.telefone = paramString2;
    this.participanteSorteado = Integer.valueOf(0);
    this.participanteSorteadoSimpleDB = "";
    this.meuPresente = "";
    this.presenteSorteado = "";
    this.sorteado = Integer.valueOf(0);
  }

  public Integer getFamilia()
  {
    return this.familia;
  }

  public Integer getId()
  {
    return this.id;
  }

  public String getIdSimpleDB()
  {
    return this.idSimpleDB;
  }

  public String getMeuPresente()
  {
    return this.meuPresente;
  }

  public String getNome()
  {
    return this.nome;
  }

  public Integer getParticipanteSorteado()
  {
    return this.participanteSorteado;
  }

  public String getParticipanteSorteadoSimpleDB()
  {
    return this.participanteSorteadoSimpleDB;
  }

  public String getPresenteSorteado()
  {
    return this.presenteSorteado;
  }

  public String getSimpleIDSorteio()
  {
    return this.simpleIDSorteio;
  }

  public Integer getSorteado()
  {
    return this.sorteado;
  }

  public Integer getSorteio()
  {
    return this.sorteio;
  }

  public String getTelefone()
  {
    return this.telefone;
  }

  public void setFamilia(Integer paramInteger)
  {
    this.familia = paramInteger;
  }

  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }

  public void setIdSimpleDB(String paramString)
  {
    this.idSimpleDB = paramString;
  }

  public void setMeuPresente(String paramString)
  {
    this.meuPresente = paramString;
  }

  public void setNome(String paramString)
  {
    this.nome = paramString;
  }

  public void setParticipanteSorteado(Integer paramInteger)
  {
    this.participanteSorteado = paramInteger;
  }

  public void setParticipanteSorteadoSimpleDB(String paramString)
  {
    this.participanteSorteadoSimpleDB = paramString;
  }

  public void setPresenteSorteado(String paramString)
  {
    this.presenteSorteado = paramString;
  }

  public void setSimpleIDSorteio(String paramString)
  {
    this.simpleIDSorteio = paramString;
  }

  public void setSorteado(Integer paramInteger)
  {
    this.sorteado = paramInteger;
  }

  public void setSorteio(Integer paramInteger)
  {
    this.sorteio = paramInteger;
  }

  public void setTelefone(String paramString)
  {
    this.telefone = paramString;
  }

  public String toString()
  {
    return "ParticipanteVO [id=" + this.id + ", idSimpleDB=" + this.idSimpleDB + ", nome=" + this.nome + ", sorteio=" + this.sorteio + ", familia=" + this.familia + ", telefone=" + this.telefone + ", sorteado=" + this.sorteado + ", participanteSorteado=" + this.participanteSorteado + ", participanteSorteadoSimpleDB=" + this.participanteSorteadoSimpleDB + ", meuPresente=" + this.meuPresente + ", presenteSorteado=" + this.presenteSorteado + ", simpleIDSorteio=" + this.simpleIDSorteio + "]";
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.ParticipanteVO
 * JD-Core Version:    0.6.0
 */