package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

public class SorteioVO
{
  private Integer comFamilia = Integer.valueOf(0);
  private String dataSorteio;
  private Integer id;
  private String idSimpleDB;
  private String titulo;
  private Double valorPresente;

  public SorteioVO()
  {
    this.comFamilia = Integer.valueOf(0);
  }

  public SorteioVO(String paramString)
  {
    this.titulo = paramString;
    this.comFamilia = Integer.valueOf(0);
  }

  public SorteioVO(String paramString1, String paramString2, Double paramDouble)
  {
    this.dataSorteio = paramString2;
    this.titulo = paramString1;
    this.valorPresente = paramDouble;
  }

  public Integer getComFamilia()
  {
    return this.comFamilia;
  }

  public String getDataSorteio()
  {
    return this.dataSorteio;
  }

  public Integer getId()
  {
    return this.id;
  }

  public String getIdSimpleDB()
  {
    return this.idSimpleDB;
  }

  public String getTitulo()
  {
    return this.titulo;
  }

  public Double getValorPresente()
  {
    return this.valorPresente;
  }

  public void setComFamilia(Integer paramInteger)
  {
    this.comFamilia = paramInteger;
  }

  public void setDataSorteio(String paramString)
  {
    this.dataSorteio = paramString;
  }

  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }

  public void setIdSimpleDB(String paramString)
  {
    this.idSimpleDB = paramString;
  }

  public void setTitulo(String paramString)
  {
    this.titulo = paramString;
  }

  public void setValorPresente(Double paramDouble)
  {
    this.valorPresente = paramDouble;
  }

  public String toString()
  {
    return "SorteioVO [id=" + this.id + ", idSimpleDB=" + this.idSimpleDB + ", dataSorteio=" + this.dataSorteio + ", titulo=" + this.titulo + ", comFamilia=" + this.comFamilia + ", valorPresente=" + this.valorPresente + "]";
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.SorteioVO
 * JD-Core Version:    0.6.0
 */