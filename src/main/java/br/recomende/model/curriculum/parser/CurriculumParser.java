package br.recomende.model.curriculum.parser;

import java.io.InputStream;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.digester.Digester;

import br.recomende.model.curriculum.BibliographicProduction;
import br.recomende.model.curriculum.CurriculumVitae;
import br.recomende.model.curriculum.Language;
import br.recomende.model.curriculum.LanguageSkill;
import br.recomende.model.curriculum.Languages;
import br.recomende.model.curriculum.ScienceArea;
import br.recomende.model.curriculum.TecnicalProduction;

public class CurriculumParser {
	
	private Digester digester;
	
	public CurriculumParser() {
		this.digester = new Digester();
		this.setUp();
	}
	
	private void setUp() {
		this.addConverters();
		this.addCurriculumCreation();
		this.addScienceAreas("CURRICULO-VITAE/DADOS-GERAIS/AREAS-DE-ATUACAO/AREA-DE-ATUACAO","addScienceArea");
		this.addBibliographicProduction("CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/TRABALHOS-EM-EVENTOS/TRABALHO-EM-EVENTOS","TRABALHO","TRABALHO");
		this.addBibliographicProduction("CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/ARTIGOS-PUBLICADOS/ARTIGO-PUBLICADO","ARTIGO","ARTIGO");
		this.addBibliographicProduction("CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/LIVROS-E-CAPITULOS/LIVROS-PUBLICADOS-OU-ORGANIZADOS/LIVRO-PUBLICADO-OU-ORGANIZADO","LIVRO","LIVRO");
		this.addBibliographicProduction("CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/LIVROS-E-CAPITULOS/CAPITULOS-DE-LIVROS-PUBLICADOS/CAPITULO-DE-LIVRO-PUBLICADO","CAPITULO","CAPITULO-DO-LIVRO","TITULO-DO-LIVRO");
		this.addBibliographicProduction("CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/ARTIGOS-ACEITOS-PARA-PUBLICACAO/ARTIGO-ACEITO-PARA-PUBLICACAO","ARTIGO","ARTIGO");
		this.addTecnicalProduction("CURRICULO-VITAE/PRODUCAO-TECNICA/SOFTWARE", "DO-SOFTWARE", "TITULO-DO-SOFTWARE", "FINALIDADE");
		this.addTecnicalProduction("CURRICULO-VITAE/PRODUCAO-TECNICA/DEMAIS-TIPOS-DE-PRODUCAO-TECNICA/APRESENTACAO-DE-TRABALHO", "DA-APRESENTACAO-DE-TRABALHO", "TITULO");
		this.addLanguages();
	}
	
	private void addConverters() {
		ConvertUtils.register(new StringConverter(), String.class);
		ConvertUtils.register(new DateConverter(), Date.class);
		ConvertUtils.register(new BooleanConverter(), Boolean.class);
		ConvertUtils.register(new LanguageSkillConverter(), LanguageSkill.class);
		ConvertUtils.register(new LanguagesConverter(), Languages.class);
	}

	private void addLanguages() {
		String pattern = "CURRICULO-VITAE/DADOS-GERAIS/IDIOMAS/IDIOMA";
		this.digester.addObjectCreate(pattern, Language.class);
		this.digester.addSetProperties(pattern);
		this.digester.addSetProperties(pattern, "DESCRICAO-DO-IDIOMA", "type");
		this.digester.addSetProperties(pattern, "PROFICIENCIA-DE-LEITURA", "readSkill");
		this.digester.addSetNext(pattern, "addLanguage");
	}
	
	private void addTecnicalProduction(String pattern, String type, String titleElementName) {
		this.addTecnicalProduction(pattern, type, titleElementName, null);
	} 
	
	private void addTecnicalProduction(String pattern, String type, String titleElementName, String purposeElementName) {
		this.digester.addObjectCreate(pattern, TecnicalProduction.class);
		this.digester.addSetProperties(pattern);
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-"+type, titleElementName, "title");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-"+type, titleElementName+"-INGLES", "englishTitle");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-"+type, "FLAG-RELEVANCIA", "relevant");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-"+type, "ANO", "year");
		if (purposeElementName != null) {
			this.digester.addSetProperties(pattern+"/DETALHAMENTO-"+type, purposeElementName, "purpose");
		}
		this.digester.addSetProperties(pattern+"/INFORMACOES-ADICIONAIS","DESCRICAO-INFORMACOES-ADICIONAIS","aditionalInformation");
		this.digester.addSetProperties(pattern+"/INFORMACOES-ADICIONAIS","DESCRICAO-INFORMACOES-ADICIONAIS-INGLES","englishAditionalInformation");
		this.digester.addSetNext(pattern, "addTecnicalProduction");
	}
	
	private void addCurriculumCreation() {
		this.digester.addObjectCreate("CURRICULO-VITAE", CurriculumVitae.class);
		this.digester.addSetProperties("CURRICULO-VITAE");
		this.digester.addSetProperties("CURRICULO-VITAE", "DATA-ATUALIZACAO", "atualizationDate");
		this.digester.addSetProperties("CURRICULO-VITAE/DADOS-GERAIS/RESUMO-CV", "TEXTO-RESUMO-CV-RH", "summary");
		this.digester.addSetProperties("CURRICULO-VITAE/DADOS-GERAIS/OUTRAS-INFORMACOES-RELEVANTES", "OUTRAS-INFORMACOES-RELEVANTES", "otherRelevantInformation");
	}
	
	private void addScienceAreas(String pattern, String methodName) {
		this.digester.addObjectCreate(pattern, ScienceArea.class);
		this.digester.addSetProperties(pattern);
		this.digester.addSetProperties(pattern, "NOME-GRANDE-AREA-DO-CONHECIMENTO", "greatArea");
		this.digester.addSetProperties(pattern, "NOME-DA-AREA-DO-CONHECIMENTO", "area");
		this.digester.addSetProperties(pattern, "NOME-DA-SUB-AREA-DO-CONHECIMENTO", "subArea");
		this.digester.addSetProperties(pattern, "NOME-DA-ESPECIALIDADE", "specialization");
		this.digester.addSetNext(pattern, methodName);
	}
	
	private void addCallMethod(String pattern, String methodName, String... parameters) {
		this.digester.addCallMethod(pattern, methodName, parameters.length);
		int paramIndex = 0;
		for (String parameter : parameters) {
			this.digester.addCallParam(pattern, paramIndex++,parameter);
		}
	}
	
	private void addBibliographicProduction(String pattern, String type, String titleElementName) {
		this.addBibliographicProduction(pattern, type, titleElementName,null);
	}
	
	private void addBibliographicProduction(String pattern, String type, String titleElementName, String publicationExtraInformationName) {
		this.digester.addObjectCreate(pattern, BibliographicProduction.class);
		this.digester.addSetProperties(pattern);
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-DO-"+type, "TITULO-DO-"+titleElementName, "title");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-DO-"+type, "TITULO-DO-"+titleElementName+"-INGLES", "englishTitle");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-DO-"+type, "FLAG-RELEVANCIA", "relevant");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-DO-"+type, "ANO-DO-"+type, "year");
		this.digester.addSetProperties(pattern+"/DADOS-BASICOS-DO-"+type, "IDIOMA", "language");
		if (publicationExtraInformationName != null) {
			this.digester.addSetProperties(pattern+"/DETALHAMENTO-DO-"+type, publicationExtraInformationName, "publicationExtraInformation");
		}
		this.addCallMethod(pattern+"/PALAVRAS-CHAVE", "addKeyword", "PALAVRA-CHAVE-6");
		this.addCallMethod(pattern+"/PALAVRAS-CHAVE", "addKeyword", "PALAVRA-CHAVE-5");
		this.addCallMethod(pattern+"/PALAVRAS-CHAVE", "addKeyword", "PALAVRA-CHAVE-4");
		this.addCallMethod(pattern+"/PALAVRAS-CHAVE", "addKeyword", "PALAVRA-CHAVE-3");
		this.addCallMethod(pattern+"/PALAVRAS-CHAVE", "addKeyword", "PALAVRA-CHAVE-2");
		this.addCallMethod(pattern+"/PALAVRAS-CHAVE", "addKeyword", "PALAVRA-CHAVE-1");
		this.addScienceAreas(pattern+"/AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2", "addArea");
		this.addScienceAreas(pattern+"/AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1", "addArea");
		this.digester.addSetProperties(pattern+"/INFORMACOES-ADICIONAIS","DESCRICAO-INFORMACOES-ADICIONAIS","aditionalInformation");
		this.digester.addSetProperties(pattern+"/INFORMACOES-ADICIONAIS","DESCRICAO-INFORMACOES-ADICIONAIS-INGLES","englishAditionalInformation");
		this.digester.addSetNext(pattern, "addBibliographicProduction");
	}
	
	public CurriculumVitae parse(InputStream stream) {
		try {
			return (CurriculumVitae) digester.parse(stream);
		} catch (Exception e) {
			//FIXME Throw business exception - See exception handling on spring 3
			throw new RuntimeException(e);
		}
	}
	
}
