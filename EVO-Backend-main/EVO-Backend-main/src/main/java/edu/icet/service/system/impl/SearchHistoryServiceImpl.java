package edu.icet.service.system.impl;

import edu.icet.dto.system.SearchHistory;
import edu.icet.entity.system.SearchHistoryEntity;
import edu.icet.repository.customer.CustomerRepository;
import edu.icet.repository.system.SearchHistoryRepository;
import edu.icet.service.system.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchHistoryServiceImpl implements SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

   /* private final List<SearchHistory> searchHistoryList=new ArrayList<>();
    private final List<Long> customerIdList=new ArrayList<>();*/

    @Override
    public boolean saveSearchHistory(SearchHistory searchHistory) {
        if(searchHistory.getSearchId()==null||searchHistory.getTitle()==null||searchHistory.getDate()==null){
            throw new IllegalArgumentException("Search ID,Title,and Date cannot be null.");
        }
        if(searchHistory.getDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Search history date cannot be in the future.");
        }
        searchHistoryRepository.save(mapper.map(searchHistory, SearchHistoryEntity.class));
        return searchHistoryRepository.existsById(searchHistory.getSearchId());
    }

    @Override
    public boolean deleteSearchHistoryById(Long id) {
        if (id==null){
            throw new IllegalArgumentException("ID cannot be null.");
        }
        searchHistoryRepository.deleteById(id);
        return !searchHistoryRepository.existsById(id);
    }

    @Override
    public SearchHistory searchSearchHistory(Long id) {
        if (id==null){
            throw new IllegalArgumentException("ID cannot be null");
        }
       return mapper.map(searchHistoryRepository.findById(id), SearchHistory.class);
    }

    @Override
    public List<SearchHistory> getAllSearchHistory() {
        return searchHistoryRepository.findAll().stream()
                .map(searchHistoryEntity -> mapper.map(searchHistoryEntity, SearchHistory.class))
                .toList();
    }

    @Override
    public List<SearchHistory> getFilterSearchHistory(String title) {
        if (title==null||title.isBlank()){
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        return searchHistoryRepository.findByTitle(title)
                .stream()
                .map(searchHistoryEntity -> mapper.map(searchHistoryEntity, SearchHistory.class))
                .toList();
    }
}
