package edu.icet.controller.system;

import edu.icet.dto.supplier.Supplier;
import edu.icet.dto.system.*;
import edu.icet.service.admin.TransactionHistoryService;
import edu.icet.service.system.*;
import edu.icet.service.system.impl.ExcelReaderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/system")
public class SystemController {
    final TransactionHistoryService service;
    private final NotificationService notificationService;

    private ExcelReaderService excelReaderService;
    final ReviewService reviewService;
    final SearchHistoryService searchHistoryService;
    final TermsService termsService;

    @PostMapping("/transaction-history/save-transaction-history")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> saveTransactionHistory(@Valid @RequestBody TransactionHistory history){
        Boolean result = service.saveTransactionHistory(history);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/transaction-history/getAll-transaction-history")
    public ResponseEntity<List<TransactionHistory>> getAllTransactionHistory(){
        List<TransactionHistory> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/transaction-history/get-transaction-byId/{id}")
    public ResponseEntity<TransactionHistory>getTransactionHistoryById(@PathVariable Long id){
        TransactionHistory transactionHistoryById = service.getTransactionHistoryById(id);
        return ResponseEntity.ok(transactionHistoryById);
    }

    @GetMapping("/transaction-history/get-transaction-history-byDate/{date}")
    public ResponseEntity<List<TransactionHistory>> getTransactionHistoryByDate(@PathVariable LocalDate date){
        List<TransactionHistory> transactionByDate = service.getTransactionByDate(date);
        return ResponseEntity.ok(transactionByDate);
    }

    @GetMapping("/transaction-history/get-transaction-history-byUserId/{id}")
    public ResponseEntity<List<TransactionHistory>> getTransactionHistoryByUserId(@PathVariable Long id){
        List<TransactionHistory> transactionByUserId = service.getTransactionByUserId(id);
        return ResponseEntity.ok(transactionByUserId);
    }

    @DeleteMapping("/transaction-history/delete-transaction-history-ById/{id}")
    public ResponseEntity<Boolean> deleteTransactionHistoryById(@PathVariable Long id){
        boolean b = service.deleteTransactionHistoryById(id);
        return ResponseEntity.ok(b);
    }

    @PostMapping("/notification/create-notification")
    public ResponseEntity<Notification> createNotification(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody Notification notification) {
        if(notificationService.createNotification(notification) !=null){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notification/get-all-notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotification();
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/notification/delete-notification/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long notificationId) {
        if(notificationService.deleteNotification(notificationId)) {
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/notification/update-notification")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Notification> updateNotification(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody Notification notification) {
        if (notificationService.updateNotification(notification.getNotificationId(),notification) !=null) {
            return new ResponseEntity<>(notification,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notification/search-by-notificationId/{notificationId}")
    public ResponseEntity<Notification> getNotificationId(@PathVariable Long notificationId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        if (notification != null) {
            return  new ResponseEntity<>(notification,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notification/search-notification-by-type/{type}")
    public ResponseEntity<List<Notification>> getNotificationByType(@PathVariable String type) {
        List<Notification> notifications = notificationService.getNotificationByType(type);
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notification/search-notification-by-status/{status}")
    public ResponseEntity<List<Notification>> getNotificationByStatus(@PathVariable String status) {
        List<Notification> notifications = notificationService.getNotificationByStatus(status);
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notification/search-by-delivery/{deliveryMethod}")
    public ResponseEntity<List<Notification>> getNotificationByDeliveryMethod(@PathVariable String deliveryMethod) {
        List<Notification> notifications = notificationService.getNotificationByDeliveryMethod(deliveryMethod);
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notification/search-by-user/{usertype}/{userId}")
    public ResponseEntity<List<Notification>> getNotificationByUser(@PathVariable String userType, @PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationByUser(userType, userId);
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/notification/mark-as-read/{notificationId}")
    public ResponseEntity<String> markAsRead(@PathVariable Long notificationId) {
        if (notificationService.markAsRead(notificationId)) {
            return ResponseEntity.ok("Notification marked as read");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }
    }

    @GetMapping("/notification/get-unread-notification")
    public ResponseEntity<List<Notification>> getUnreadNotifications() {
        List<Notification> notifications = notificationService.getUnreadNotifications();
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/category/add-category")
//    public Boolean addCategory(@RequestBody Category category){
//        return categoryService.save(category);
//    }
//
//    @DeleteMapping("/category/delete-category-by-id/{id}")
//    public Boolean deleteCategoryById(@PathVariable Long id){
//        return categoryService.delete(id);
//    }
//
//    @DeleteMapping("/category/delete-category")
//    public Boolean deleteCategory(@RequestBody Category category){
//        return categoryService.delete(category);
//    }
//
//    @PutMapping("/category/update-category")
//    public Boolean updateCategory(@RequestBody Category category){
//        return categoryService.update(category);
//    }
//
//    @GetMapping("/category/search-category")
//    public Category searchCategory(@RequestBody String query){
//        return categoryService.search(query);
//    }
//
//    @GetMapping("/category/get-all-categories")
//    public List<Category> getAllCategory(){
//        return categoryService.getAll();
//    }

    @PostMapping("/excel/upload-excel")
    public ResponseEntity<List<String>> uploadExcel() {
        try {
            List<String> words = excelReaderService.readWordsFromExcel();
            return ResponseEntity.ok(words);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/review/addReview")
    public ResponseEntity<Review> addByReview(@Valid @RequestBody Review review) {

        Review savedReview = reviewService.addReview(review);
        return (savedReview != null)
                ? ResponseEntity.status(HttpStatus.CREATED).body(savedReview)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/review/searchByReviewId/{id}")
    public ResponseEntity<Review> searchByReview(@PathVariable long id) {
        Review review = reviewService.SearchByReviewID(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/review/getReviewsBySupplierId/{id}")
    public ResponseEntity<List<Review>> getReviewsBySupplierId(@PathVariable long id) {
        List<Review> reviews = reviewService.getReviewsBySupplierId(id);
        if (!reviews.isEmpty()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/review/updateByReviewId/{id}")
    public ResponseEntity<String> updateByReviewId(@PathVariable Long id, @RequestBody Review review) {

        if (reviewService.updateReviewById(id, review)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/review/get-all-reviews")
    public ResponseEntity<List<Review>> getAllByReview() {
        List<Review> reviews = reviewService.getAll();

        return reviews.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/review/deleteByReviewId/{id}")
    public ResponseEntity<String> deleteByReview(@PathVariable long id) {
        boolean isDeleted = reviewService.deleteByReview(id);

        return isDeleted
                ? ResponseEntity.ok("Review deleted successfully!")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found!");
    }

    @PostMapping("/search-history/save-search-history")
    public ResponseEntity<String> saveSearchHistory(@Valid @RequestBody SearchHistory searchHistory, HttpServletRequest request){
        if(searchHistoryService.saveSearchHistory(searchHistory)){
            String os = request.getRemoteAddr();
            log.info("Request Received IP: {} | Add SearchHistory detail: {}",os,searchHistory);
            return ResponseEntity.ok("search history saved successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("try again!, Can't added search history");
        }
    }

    @DeleteMapping("/search-history/delete-search-history/{id}")
    public ResponseEntity<String> deleteSearchHistoryById(@PathVariable Long id){
        if (searchHistoryService.deleteSearchHistoryById(id)){
            return ResponseEntity.ok("search history delete successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("try again!, Can't delete search history");
        }
    }
    @GetMapping("/search-history/get-search-history-by-id/{id}")
    public ResponseEntity<SearchHistory> searchSearchHistoryById(@PathVariable Long id){
        SearchHistory searchHistory = searchHistoryService.searchSearchHistory(id);
        if(searchHistory != null){
            return new ResponseEntity<>(searchHistory, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-history/get-all-search-history")
    public ResponseEntity<List<SearchHistory>> getAllSearchHistory(){
        List<SearchHistory> allSearchHistory = searchHistoryService.getAllSearchHistory();
        if(allSearchHistory != null){
            return new ResponseEntity<>(allSearchHistory, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-history/get-all-search-history-by-title/{title}")
    public ResponseEntity<List<SearchHistory>> getAllSearchHistoryByTitle(@PathVariable String title){
        List<SearchHistory> allSearchHistoryByTitle = searchHistoryService.getFilterSearchHistory(title);
        if(allSearchHistoryByTitle != null){
            return new ResponseEntity<>(allSearchHistoryByTitle, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/terms/add-terms")
    public ResponseEntity<Terms> addTerms(@Valid @RequestBody Terms terms) {
        Terms createdTerms = termsService.addTerms(terms);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTerms);
    }

    @PutMapping("/terms/update-terms")
    public ResponseEntity<Terms> updateTerms(@Valid @RequestBody Terms terms) {
        Terms updatedTerms = termsService.updateTerms(terms);
        return ResponseEntity.ok(updatedTerms);
    }

    @GetMapping("/terms/get-terms-by-id/{id}")
    public ResponseEntity<Terms> getTermsById(@PathVariable Integer id) {
        return termsService.getTermsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/terms/profile/{profileId}")
    public ResponseEntity<List<Terms>> getTermsBySupplier(@PathVariable Supplier supplier) {
        List<Terms> termsList = termsService.getTermsBySupplier(supplier);
        return ResponseEntity.ok(termsList);
    }

    @DeleteMapping("/terms/delete-term-by-id/{id}")
    public ResponseEntity<Void> deleteTermsById(@PathVariable Integer id) {
        termsService.deleteTerms(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/term/active/{profileId}")
    public ResponseEntity<Terms> getActiveTerms(@PathVariable Supplier supplier) {
        return termsService.getActiveTerms(supplier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
