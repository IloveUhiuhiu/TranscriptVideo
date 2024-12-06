package Model.BO;
import Model.DAO.*;

import java.util.List;

import Model.Bean.*;

public class LanguageBo {
	private LanguageDao languageDao = new LanguageDao();
	
	public Language getById(int id) {
		return languageDao.getById(id);
	}
	public List<Language> getAll() {
		return languageDao.getAll();
	}
	

}
