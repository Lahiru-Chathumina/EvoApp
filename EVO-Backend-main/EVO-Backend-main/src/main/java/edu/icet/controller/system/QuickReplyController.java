package edu.icet.controller.system;

import edu.icet.dto.system.QuickReplies;
import edu.icet.service.system.QuickReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/supplier/quick-reply")
@CrossOrigin
public class QuickReplyController {
    final QuickReplyService service;

    @PostMapping("/add")
    public Boolean addReply(@RequestBody QuickReplies quickReplies) {
        return service.save(quickReplies);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteReply(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/update")
    public Boolean updateReply(@RequestBody QuickReplies quickReplies) {
        return service.update(quickReplies);
    }

    @GetMapping("/get-all")
    public List<QuickReplies> getAll() {
        return service.getAll();
    }
}
