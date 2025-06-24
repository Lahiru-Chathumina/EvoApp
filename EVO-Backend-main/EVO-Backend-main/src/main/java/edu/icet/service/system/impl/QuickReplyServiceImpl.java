package edu.icet.service.system.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.system.QuickReplies;
import edu.icet.entity.system.QuickRepliesEntity;
import edu.icet.repository.system.QuickReplyRepository;
import edu.icet.service.system.QuickReplyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuickReplyServiceImpl implements QuickReplyService {
    List<QuickReplies> repliesList = new ArrayList<>();

    private final QuickReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Boolean save(QuickReplies quickReplies) {
        if (quickReplies==null) {
            return false;
        }
        if(filterProfanity(quickReplies.getContent())){
            return false;
        }
        QuickRepliesEntity quickRepliesEntity = replyRepository.save(modelMapper.map(quickReplies, QuickRepliesEntity.class));
        return replyRepository.existsById(quickRepliesEntity.getReplyID());
    }

    @Override
    public Boolean delete(Long id) {
        if (id==null) {
            return false;
        }
        replyRepository.deleteById(id);
        return !replyRepository.existsById(id);
    }

    @Override
    public Boolean update(QuickReplies quickReplies) {
        if (quickReplies==null) {
            return false;
        }
        QuickRepliesEntity quickRepliesEntity = replyRepository.save(modelMapper.map(quickReplies, QuickRepliesEntity.class));
        return replyRepository.existsById(quickRepliesEntity.getReplyID());
    }

    @Override
    public List<QuickReplies> getAll() {
        List<QuickRepliesEntity> allEntities = replyRepository.findAll();
        List<QuickReplies> repliesList = new ArrayList<>();
        allEntities.forEach(entity -> repliesList.add(modelMapper.map(entity, QuickReplies.class)));
        return repliesList;
    }

    public Boolean filterProfanity(String content) {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.apiverve.com/v1/profanityfilter"))
                    .header("x-api-key", "9939ffef-8f41-4c02-b6a9-714d670a3237")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(
                            "{ \"text\": \"" + content + "\", \"mask\": \"*\" }"
                    ))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(response.body(), Map.class);

            Map<String, Object> data = (Map<String,Object>) responseMap.get("data");
            return (boolean) data.get("isProfane");
        }catch (IOException e) {
            throw new IllegalArgumentException("Error while calling profanity API", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException("Thread was interrupted", e);
        }
    }
}
