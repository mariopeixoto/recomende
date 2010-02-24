package br.recomende.model.profiling.engine.api;

import br.recomende.model.curriculum.CurriculumVitae;
import br.recomende.model.profile.Profile;

public interface ProfileEngine {

	Profile generate(CurriculumVitae curriculumVitae);
	
}
