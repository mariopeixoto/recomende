File dir = new File("/Users/mario/Documents/documentos/recomendacao/citeseer_adapted/");
for (final File file : dir.listFiles()) {
		OAIListRecordsParser parser = new OAIListRecordsParser();
		List<DublinCoreDocument> documents;
			try {
				log.info(file.getPath() + ": Importando");
				documents = parser.parse(new FileInputStream(file), harvesterDefinitionRepository.get(1));
				log.info(file.getPath() + ": Importado! Inserindo no banco de dados");
				for(DublinCoreDocument doc : documents) {
					//gerar SQL de insert
				}
				log.info(file.getPath() + ": Finalizou");
			} catch (FileNotFoundException e) {
				log.error(file.getPath(), e);
			} catch (IOException e) {
				log.error(file.getPath(), e);
			} catch (SAXException e) {
				log.error(file.getPath(), e);
			}
			
		}