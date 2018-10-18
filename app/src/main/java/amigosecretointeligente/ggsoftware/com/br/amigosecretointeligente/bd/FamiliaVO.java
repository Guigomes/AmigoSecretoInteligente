package amigosecretointeligente.ggsoftware.com.br.amigosecretointeligente.bd;

public class FamiliaVO
{
  private Integer id;
  private String idSimpleDB;
  private String nome;

  public FamiliaVO()
  {
  }

  public FamiliaVO(String paramString)
  {
    this.nome = paramString;
  }

  public Integer getId()
  {
    return this.id;
  }

  public String getIdSimpleDB()
  {
    return this.idSimpleDB;
  }

  public String getNome()
  {
    return this.nome;
  }

  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }

  public void setIdSimpleDB(String paramString)
  {
    this.idSimpleDB = paramString;
  }

  public void setNome(String paramString)
  {
    this.nome = paramString;
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.com.curso.bd.FamiliaVO
 * JD-Core Version:    0.6.0
 */