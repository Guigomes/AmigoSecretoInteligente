package br.gomes.amigosecreto.simpledb;

import android.content.Context;
import android.util.Log;
import br.com.curso.bd.FamiliaDAO;
import br.com.curso.bd.FamiliaVO;
import br.com.curso.bd.ParticipanteDAO;
import br.com.curso.bd.ParticipanteVO;
import br.com.curso.bd.SorteioDAO;
import br.com.curso.bd.SorteioVO;
import br.gomes.amigosecreto.MainActivity;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.DeleteAttributesRequest;
import com.amazonaws.services.simpledb.model.GetAttributesRequest;
import com.amazonaws.services.simpledb.model.GetAttributesResult;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleDB
{
  public static final String DOMAIN_FAMILIA = "FAMILIA";
  public static final String DOMAIN_PARTICIPANTE = "PARTICIPANTE";
  public static final String DOMAIN_SORTEIO = "SORTEIO";
  AWSCredentials credentials = new BasicAWSCredentials("", "");

  public String buscarNomePorId(String paramString)
  {
    GetAttributesResult localGetAttributesResult = new AmazonSimpleDBClient(this.credentials).getAttributes(new GetAttributesRequest("PARTICIPANTE", paramString));
    Iterator localIterator;
    if (localGetAttributesResult != null)
    {
      localIterator = localGetAttributesResult.getAttributes().iterator();
      if (localIterator.hasNext())
        break label54;
    }
    label54: Attribute localAttribute;
    for (String str = null; ; str = localAttribute.getValue())
    {
      return str;
      localAttribute = (Attribute)localIterator.next();
      if (!localAttribute.getName().equals("nome"))
        break;
    }
  }

  public String buscarNumeroPorId(String paramString)
  {
    GetAttributesResult localGetAttributesResult = new AmazonSimpleDBClient(this.credentials).getAttributes(new GetAttributesRequest("FAMILIA", paramString));
    if (localGetAttributesResult != null);
    for (String str = ((Attribute)localGetAttributesResult.getAttributes().get(0)).getValue(); ; str = null)
      return str;
  }

  public SelectResult buscarQuemMeSorteou(String paramString)
  {
    return new AmazonSimpleDBClient(this.credentials).select(new SelectRequest("select * from PARTICIPANTE WHERE participante_sorteado_simpledb = '" + paramString + "'").withConsistentRead(Boolean.valueOf(true)));
  }

  public void cadastrarDomains()
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    CreateDomainRequest localCreateDomainRequest1 = new CreateDomainRequest("SORTEIO");
    CreateDomainRequest localCreateDomainRequest2 = new CreateDomainRequest("PARTICIPANTE");
    CreateDomainRequest localCreateDomainRequest3 = new CreateDomainRequest("FAMILIA");
    localAmazonSimpleDBClient.createDomain(localCreateDomainRequest1);
    localAmazonSimpleDBClient.createDomain(localCreateDomainRequest2);
    localAmazonSimpleDBClient.createDomain(localCreateDomainRequest3);
  }

  public void delete(String paramString)
  {
    new AmazonSimpleDBClient(this.credentials).deleteAttributes(new DeleteAttributesRequest("PARTICIPANTE", paramString));
  }

  public void deletePorSorteio(String paramString)
  {
    Iterator localIterator = listarParticipantes(paramString).getItems().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      delete(((Item)localIterator.next()).getName());
    }
  }

  public SelectResult listar()
  {
    return new AmazonSimpleDBClient(this.credentials).select(new SelectRequest("select * from PARTICIPANTE WHERE telefone = '" + MainActivity.getNumber() + "'").withConsistentRead(Boolean.valueOf(true)));
  }

  public SelectResult listar2()
  {
    return new AmazonSimpleDBClient(this.credentials).select(new SelectRequest("select * from PARTICIPANTE WHERE sorteio = 'NATAL 2015'").withConsistentRead(Boolean.valueOf(true)));
  }

  public SelectResult listarParticipantes(String paramString)
  {
    return new AmazonSimpleDBClient(this.credentials).select(new SelectRequest("select * from PARTICIPANTE WHERE sorteio_simpledb = '" + paramString + "'").withConsistentRead(Boolean.valueOf(true)));
  }

  public void participanteSorteado(ParticipanteVO paramParticipanteVO, Context paramContext)
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    ParticipanteDAO localParticipanteDAO = new ParticipanteDAO(paramContext);
    ParticipanteVO localParticipanteVO = localParticipanteDAO.buscaPorId(paramParticipanteVO.getParticipanteSorteado());
    String str1 = localParticipanteVO.getNome();
    paramParticipanteVO.setParticipanteSorteadoSimpleDB(localParticipanteVO.getIdSimpleDB());
    ReplaceableAttribute localReplaceableAttribute1 = new ReplaceableAttribute("id", paramParticipanteVO.getId().toString(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute2 = new ReplaceableAttribute("nome", paramParticipanteVO.getNome(), Boolean.TRUE);
    SorteioDAO localSorteioDAO1 = new SorteioDAO(paramContext);
    ReplaceableAttribute localReplaceableAttribute3 = new ReplaceableAttribute("sorteio", localSorteioDAO1.buscaPorId(paramParticipanteVO.getSorteio()).getTitulo(), Boolean.TRUE);
    FamiliaDAO localFamiliaDAO = new FamiliaDAO(paramContext);
    FamiliaVO localFamiliaVO = localFamiliaDAO.buscaPorId(paramParticipanteVO.getFamilia());
    String str2 = "";
    if ((localFamiliaVO != null) && (localFamiliaVO.getNome() != null))
      str2 = localFamiliaVO.getNome();
    ReplaceableAttribute localReplaceableAttribute4 = new ReplaceableAttribute("familia", str2, Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute5 = new ReplaceableAttribute("telefone", paramParticipanteVO.getTelefone(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute6 = new ReplaceableAttribute("sorteado", paramParticipanteVO.getSorteado().toString(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute7 = new ReplaceableAttribute("participante_sorteado", str1, Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute8 = new ReplaceableAttribute("participante_sorteado_simpledb", paramParticipanteVO.getParticipanteSorteadoSimpleDB(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute9 = new ReplaceableAttribute("meu_presente", paramParticipanteVO.getMeuPresente(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute10 = new ReplaceableAttribute("presente_sorteado", paramParticipanteVO.getPresenteSorteado(), Boolean.TRUE);
    SorteioDAO localSorteioDAO2 = new SorteioDAO(paramContext);
    ReplaceableAttribute localReplaceableAttribute11 = new ReplaceableAttribute("data", localSorteioDAO2.buscaPorId(paramParticipanteVO.getSorteio()).getDataSorteio(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute12 = new ReplaceableAttribute("sorteio_simpledb", paramParticipanteVO.getSimpleIDSorteio(), Boolean.TRUE);
    ArrayList localArrayList = new ArrayList(11);
    localArrayList.add(localReplaceableAttribute1);
    localArrayList.add(localReplaceableAttribute2);
    localArrayList.add(localReplaceableAttribute3);
    localArrayList.add(localReplaceableAttribute4);
    localArrayList.add(localReplaceableAttribute5);
    localArrayList.add(localReplaceableAttribute6);
    localArrayList.add(localReplaceableAttribute7);
    localArrayList.add(localReplaceableAttribute8);
    localArrayList.add(localReplaceableAttribute9);
    localArrayList.add(localReplaceableAttribute10);
    localArrayList.add(localReplaceableAttribute11);
    localArrayList.add(localReplaceableAttribute12);
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("PARTICIPANTE", paramParticipanteVO.getIdSimpleDB(), localArrayList));
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("SORTEIO", paramParticipanteVO.getIdSimpleDB(), localArrayList));
  }

  public void salvarFamilia(String paramString1, String paramString2)
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    ReplaceableAttribute localReplaceableAttribute = new ReplaceableAttribute("numero", paramString1, Boolean.TRUE);
    ArrayList localArrayList = new ArrayList(2);
    localArrayList.add(localReplaceableAttribute);
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("FAMILIA", paramString2, localArrayList));
  }

  public void salvarParticipante(ParticipanteVO paramParticipanteVO, Context paramContext)
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    ReplaceableAttribute localReplaceableAttribute1 = new ReplaceableAttribute("id", paramParticipanteVO.getId().toString(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute2 = new ReplaceableAttribute("nome", paramParticipanteVO.getNome(), Boolean.TRUE);
    SorteioDAO localSorteioDAO1 = new SorteioDAO(paramContext);
    ReplaceableAttribute localReplaceableAttribute3 = new ReplaceableAttribute("sorteio", localSorteioDAO1.buscaPorId(paramParticipanteVO.getSorteio()).getTitulo(), Boolean.TRUE);
    FamiliaDAO localFamiliaDAO = new FamiliaDAO(paramContext);
    FamiliaVO localFamiliaVO = localFamiliaDAO.buscaPorId(paramParticipanteVO.getFamilia());
    String str = "";
    if ((localFamiliaVO != null) && (localFamiliaVO.getNome() != null))
      str = localFamiliaVO.getNome();
    ReplaceableAttribute localReplaceableAttribute4 = new ReplaceableAttribute("familia", str, Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute5 = new ReplaceableAttribute("telefone", paramParticipanteVO.getTelefone(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute6 = new ReplaceableAttribute("sorteado", paramParticipanteVO.getSorteado().toString(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute7 = new ReplaceableAttribute("participante_sorteado", paramParticipanteVO.getParticipanteSorteado().toString(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute8 = new ReplaceableAttribute("participante_sorteado_simpledb", paramParticipanteVO.getParticipanteSorteadoSimpleDB(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute9 = new ReplaceableAttribute("meu_presente", paramParticipanteVO.getMeuPresente(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute10 = new ReplaceableAttribute("presente_sorteado", paramParticipanteVO.getPresenteSorteado(), Boolean.TRUE);
    SorteioDAO localSorteioDAO2 = new SorteioDAO(paramContext);
    ReplaceableAttribute localReplaceableAttribute11 = new ReplaceableAttribute("data", localSorteioDAO2.buscaPorId(paramParticipanteVO.getSorteio()).getDataSorteio(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute12 = new ReplaceableAttribute("sorteio_simpledb", paramParticipanteVO.getSimpleIDSorteio(), Boolean.TRUE);
    ArrayList localArrayList = new ArrayList(11);
    localArrayList.add(localReplaceableAttribute1);
    localArrayList.add(localReplaceableAttribute2);
    localArrayList.add(localReplaceableAttribute3);
    localArrayList.add(localReplaceableAttribute4);
    localArrayList.add(localReplaceableAttribute5);
    localArrayList.add(localReplaceableAttribute6);
    localArrayList.add(localReplaceableAttribute7);
    localArrayList.add(localReplaceableAttribute8);
    localArrayList.add(localReplaceableAttribute9);
    localArrayList.add(localReplaceableAttribute10);
    localArrayList.add(localReplaceableAttribute11);
    localArrayList.add(localReplaceableAttribute12);
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("PARTICIPANTE", paramParticipanteVO.getIdSimpleDB(), localArrayList));
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("SORTEIO", paramParticipanteVO.getIdSimpleDB(), localArrayList));
  }

  public void salvarParticipante(List<ReplaceableAttribute> paramList, String paramString1, String paramString2)
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    PutAttributesRequest localPutAttributesRequest = new PutAttributesRequest("PARTICIPANTE", paramString1, paramList);
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("SORTEIO", paramString1, paramList));
    localAmazonSimpleDBClient.putAttributes(localPutAttributesRequest);
    ArrayList localArrayList = new ArrayList(11);
    SelectResult localSelectResult = localAmazonSimpleDBClient.select(new SelectRequest("select * from PARTICIPANTE WHERE participante_sorteado_simpledb = '" + paramString1 + "'").withConsistentRead(Boolean.valueOf(true)));
    Iterator localIterator = localAmazonSimpleDBClient.getAttributes(new GetAttributesRequest("PARTICIPANTE", ((Item)localSelectResult.getItems().get(0)).getName())).getAttributes().iterator();
    if (!localIterator.hasNext())
    {
      localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("PARTICIPANTE", ((Item)localSelectResult.getItems().get(0)).getName(), localArrayList));
      localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("SORTEIO", ((Item)localSelectResult.getItems().get(0)).getName(), localArrayList));
      return;
    }
    Attribute localAttribute = (Attribute)localIterator.next();
    if (localAttribute.getName().equals("presente_sorteado"));
    for (ReplaceableAttribute localReplaceableAttribute = new ReplaceableAttribute(localAttribute.getName(), paramString2, Boolean.TRUE); ; localReplaceableAttribute = new ReplaceableAttribute(localAttribute.getName(), localAttribute.getValue(), Boolean.TRUE))
    {
      localArrayList.add(localReplaceableAttribute);
      break;
    }
  }

  public void salvarParticipantes(List<ParticipanteVO> paramList, Context paramContext)
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ParticipanteVO localParticipanteVO = (ParticipanteVO)localIterator.next();
      ReplaceableAttribute localReplaceableAttribute1 = new ReplaceableAttribute("id", localParticipanteVO.getId().toString(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute2 = new ReplaceableAttribute("nome", localParticipanteVO.getNome(), Boolean.TRUE);
      SorteioDAO localSorteioDAO1 = new SorteioDAO(paramContext);
      localSorteioDAO1.listar();
      Log.i("SORTEIOVO", localParticipanteVO.toString());
      String str1 = localSorteioDAO1.buscaPorId(localParticipanteVO.getSorteio()).getTitulo();
      ReplaceableAttribute localReplaceableAttribute3 = new ReplaceableAttribute("sorteio", str1, Boolean.TRUE);
      FamiliaDAO localFamiliaDAO = new FamiliaDAO(paramContext);
      FamiliaVO localFamiliaVO = localFamiliaDAO.buscaPorId(localParticipanteVO.getFamilia());
      String str2 = "";
      if ((localFamiliaVO != null) && (localFamiliaVO.getNome() != null))
        str2 = localFamiliaVO.getNome();
      ReplaceableAttribute localReplaceableAttribute4 = new ReplaceableAttribute("familia", str2, Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute5 = new ReplaceableAttribute("telefone", localParticipanteVO.getTelefone(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute6 = new ReplaceableAttribute("sorteado", localParticipanteVO.getSorteado().toString(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute7 = new ReplaceableAttribute("participante_sorteado", localParticipanteVO.getParticipanteSorteado().toString(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute8 = new ReplaceableAttribute("participante_sorteado_simpledb", localParticipanteVO.getParticipanteSorteadoSimpleDB(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute9 = new ReplaceableAttribute("meu_presente", localParticipanteVO.getMeuPresente(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute10 = new ReplaceableAttribute("presente_sorteado", localParticipanteVO.getPresenteSorteado(), Boolean.TRUE);
      SorteioDAO localSorteioDAO2 = new SorteioDAO(paramContext);
      ReplaceableAttribute localReplaceableAttribute11 = new ReplaceableAttribute("data", localSorteioDAO2.buscaPorId(localParticipanteVO.getSorteio()).getDataSorteio(), Boolean.TRUE);
      ReplaceableAttribute localReplaceableAttribute12 = new ReplaceableAttribute("sorteio_simpledb", localParticipanteVO.getSimpleIDSorteio(), Boolean.TRUE);
      ArrayList localArrayList = new ArrayList(11);
      localArrayList.add(localReplaceableAttribute1);
      localArrayList.add(localReplaceableAttribute2);
      localArrayList.add(localReplaceableAttribute3);
      localArrayList.add(localReplaceableAttribute4);
      localArrayList.add(localReplaceableAttribute5);
      localArrayList.add(localReplaceableAttribute6);
      localArrayList.add(localReplaceableAttribute7);
      localArrayList.add(localReplaceableAttribute8);
      localArrayList.add(localReplaceableAttribute9);
      localArrayList.add(localReplaceableAttribute10);
      localArrayList.add(localReplaceableAttribute11);
      localArrayList.add(localReplaceableAttribute12);
      localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("PARTICIPANTE", localParticipanteVO.getIdSimpleDB(), localArrayList));
      localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("SORTEIO", localParticipanteVO.getIdSimpleDB(), localArrayList));
    }
  }

  public void salvarSorteio(SorteioVO paramSorteioVO)
  {
    AmazonSimpleDBClient localAmazonSimpleDBClient = new AmazonSimpleDBClient(this.credentials);
    ReplaceableAttribute localReplaceableAttribute1 = new ReplaceableAttribute("id", paramSorteioVO.getId().toString(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute2 = new ReplaceableAttribute("data_sorteio", paramSorteioVO.getDataSorteio(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute3 = new ReplaceableAttribute("titulo", paramSorteioVO.getTitulo(), Boolean.TRUE);
    ReplaceableAttribute localReplaceableAttribute4 = new ReplaceableAttribute("com_familia", paramSorteioVO.getComFamilia().toString(), Boolean.TRUE);
    ArrayList localArrayList = new ArrayList(4);
    localArrayList.add(localReplaceableAttribute1);
    localArrayList.add(localReplaceableAttribute2);
    localArrayList.add(localReplaceableAttribute3);
    localArrayList.add(localReplaceableAttribute4);
    localAmazonSimpleDBClient.putAttributes(new PutAttributesRequest("SORTEIO", paramSorteioVO.getIdSimpleDB(), localArrayList));
  }
}

/* Location:           C:\Users\guilh\Downloads\Nova pasta\tools\classes-dex2jar.jar
 * Qualified Name:     br.gomes.amigosecreto.simpledb.SimpleDB
 * JD-Core Version:    0.6.0
 */
