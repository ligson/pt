package gui.desktop.searchtool.service;

import java.io.File;
import java.util.List;

import gui.desktop.searchtool.model.PageModel;

public interface SearchService {
	void index(List<File> dirs);

	PageModel<File> search(String key, int offset, int max);
}
