package model.bo;
import model.dao.*;

import java.util.List;

import model.bean.*;

public class LanguageBo {
	private LanguageDao languageDao = new LanguageDao();
	
	public Language getById(int id) {
		return languageDao.getById(id);
	}
	public List<Language> getAll() {
		return languageDao.getAll();
	}
	

}
