package edu.icet.service.system;

import edu.icet.dto.system.SearchHistory;

import java.util.List;

public interface SearchHistoryService {
    boolean saveSearchHistory(SearchHistory searchHistory);
    boolean deleteSearchHistoryById(Long id);
    SearchHistory searchSearchHistory(Long id);
    List<SearchHistory> getAllSearchHistory();
    List<SearchHistory> getFilterSearchHistory(String title);
}
