package br.recomende.model.searching.engine;


//@Searcher
public class TagsBasedDocumentSearcher {

//	private DocumentRepository documentRepository;
//	
//	private QueryGenerator queryGenerator;
//	
//	private TagSetMiner tagSetMiner;
//	
//	@Autowired
//	private TagsBasedDocumentSearcher(DocumentRepository documentRepository, QueryGenerator queryGenerator,
//			TagSetMiner tagSetMiner) {
//		this.documentRepository = documentRepository;
//		this.queryGenerator = queryGenerator;
//		this.tagSetMiner = tagSetMiner;
//	}
//	
//	private String getIndexedText(Document document) {
//		Class<?> documentClass = document.getClass();
//		List<String> fields = this.queryGenerator.getSearcheableFields(documentClass);
//		StringBuilder builder = new StringBuilder();
//		for (String field : fields) {
//			try {
//				Field objField = documentClass.getDeclaredField(field);
//				boolean accessible = objField.isAccessible();
//				if (!accessible) {
//					objField.setAccessible(true);
//				}
//				Object obj = objField.get(document);
//				builder.append(" ").append(obj);
//				objField.setAccessible(accessible);//Returns to original access level
//			} catch (SecurityException e) {
//				throw new RuntimeException(e);
//			} catch (NoSuchFieldException e) {
//				throw new RuntimeException(e);
//			} catch (IllegalArgumentException e) {
//				throw new RuntimeException(e);
//			} catch (IllegalAccessException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		return builder.toString();
//	}
//	
//	@BeginMethod
//	public DocumentList search(TagSet tags, Class<?> documentClass) throws MineException {
//		Map<Document, Double> documents = new HashMap<Document, Double>();
//		Query query = this.queryGenerator.generate(tags, documentClass);
//		List<ScoredDocument> scoredDocuments = this.documentRepository.search(query, documentClass);
//		for (ScoredDocument scoredDocument : scoredDocuments) {
//			Document document = scoredDocument.getDocument();
//			String text = this.getIndexedText(document);
//			TagSet textTags = this.tagSetMiner.mine(text);
//			Double score = scoredDocument.getScore();
//			for (Tag textTag : textTags) {
//				if (tags.getTag(textTag.getTag()) != null) {
//					score += textTag.getWeight()*tags.getTag(textTag.getTag()).getWeight();
//				}
//			}
//			if (documents.containsKey(document)) {
//				documents.put(document, documents.get(document) + score);
//			} else {
//				documents.put(document, score);
//			}
//		}
//		scoredDocuments = new ArrayList<ScoredDocument>();
//		for (Entry<Document,Double> element : documents.entrySet()) {
//			scoredDocuments.add(new ScoredDocument(element.getKey(), element.getValue()));
//		}
//		Collections.sort(scoredDocuments, new ScoredDocumentComparator());
//		List<Document> finalDocs = new ArrayList<Document>();
//		for (ScoredDocument scoredDocument : scoredDocuments) {
//			finalDocs.add(scoredDocument.getDocument());
//		}
//		return new DocumentList(finalDocs);
//	}
	
}
