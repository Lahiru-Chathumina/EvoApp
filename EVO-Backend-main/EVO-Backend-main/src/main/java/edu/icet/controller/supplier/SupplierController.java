package edu.icet.controller.supplier;

import edu.icet.dto.customer.User;
import edu.icet.dto.supplier.*;
import edu.icet.service.supplier.SupplierManager;
import edu.icet.util.MealType;
import edu.icet.util.SupplierCategoryType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierManager service;

    // Supplier endpoint
    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/add")
    @Operation(summary = "Creates a supplier")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(service.addSupplier(supplier));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/get-all")
    @Operation(summary = "Returns supplier objects of all the suppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers(@RequestParam(required = false) SupplierCategoryType category) {
        List<Supplier> suppliers = category == null
                ? service.getAllSuppliers()
                : service.getSupplierByCategory(category);


        return suppliers.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(suppliers);
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/{supplierID}")
    @Operation(summary = "Returns a supplier based on ID")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long supplierID) {
        return ResponseEntity.ok(service.searchSupplier(supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(service.updateSupplier(supplier));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/{supplierID}")
    public ResponseEntity<Boolean> deleteSupplier(@PathVariable Long supplierID) {
        return ResponseEntity.ok(service.deleteSupplier(supplierID));
    }


    // BeautySalon endpoints

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/beauty-salon")
    public ResponseEntity<List<BeautySaloon>> getAllBeautySalon() {
        return ResponseEntity.ok(service.getAllBeautySalon());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/beauty-salon/{beautySalonID}")
    public ResponseEntity<BeautySaloon> getBeautySalon(@PathVariable Long beautySalonID) {
        return ResponseEntity.ok(service.getBeautySalon(beautySalonID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/beauty-salon")
    public ResponseEntity<Supplier> addBeautySalonSupplier(@RequestBody BeautySaloon beautySaloon, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addBeautySalonSupplier(beautySaloon, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/beauty-salon")
    public ResponseEntity<Supplier> updateBeautySalonSupplier(@RequestBody BeautySaloon beautySaloon, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateBeautySalonSupplier(beautySaloon, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/{supplierID}/beauty-salon")
    public ResponseEntity<Boolean> deleteBeautySalonSupplier(@PathVariable Long supplierID) {
        return ResponseEntity.ok(service.deleteBeautySalonSupplier(supplierID));
    }


    // Catering endpoints

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/catering")
    public ResponseEntity<List<Catering>> getAllCatering() {
        return ResponseEntity.ok(service.getAllCatering());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/catering/{cateringID}")
    public ResponseEntity<Catering> getCateringByID(@PathVariable Long cateringID) {
        return ResponseEntity.ok(service.getCateringById(cateringID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/catering")
    public ResponseEntity<Catering> addCateringSupplier(@RequestBody Catering catering, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addCateringSupplier(catering, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/catering")
    public ResponseEntity<Boolean> updateCateringSupplier(@RequestBody Catering catering, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateCateringSupplier(catering, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/{supplierID}/catering")
    public ResponseEntity<Boolean> deleteCateringSupplier(@PathVariable Long supplierID) {
        return ResponseEntity.ok(service.deleteCateringSupplier(supplierID));
    }


    // Music endpoints

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/music")
    public ResponseEntity<List<Music>> getAllMusic() {
        return ResponseEntity.ok(service.getAllMusic());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/music/{musicID}")
    public ResponseEntity<Music> getMusicByID(@PathVariable Long musicID) {
        return ResponseEntity.ok(service.getMusicByID(musicID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/music")
    public ResponseEntity<Supplier> addMusicSupplier(@RequestBody Music music, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addMusicSupplier(music, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/music")
    public ResponseEntity<Boolean> updateMusicSupplier(@RequestBody Music music, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateMusicSupplier(music, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/{supplierID}/music")
    public ResponseEntity<Boolean> deleteMusicSupplier(@PathVariable Long supplierID) {
        return ResponseEntity.ok(service.deleteMusicSupplier(supplierID));
    }


    // Meal

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/meal")
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(service.getAllCateringMeals());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/meal/{mealID}")
    public ResponseEntity<Meal> getMealByID(@PathVariable Long mealID) {
        return ResponseEntity.ok(service.searchMealByID(mealID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/meal/catering/search")
    public ResponseEntity<List<Catering>> searchCateringByMeal(@RequestParam(required = false) MealType mealType, @RequestParam(required = false) String name) {
        List<Catering> cateringList;
        if (mealType != null) {
            cateringList = service.searchCateringByMealType(mealType);

        } else {
            if (name != null) cateringList = service.searchCateringByMealName(name);
            else cateringList = service.getAllCatering();
        }

        return ResponseEntity.ok(cateringList);

    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{cateringID}/meal")
    public ResponseEntity<Catering> addCateringMeal (@RequestBody Meal meal, @PathVariable Long cateringID) {
        return ResponseEntity.ok(service.addCateringMeal(meal, cateringID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{cateringID}/meal")
    public ResponseEntity<Catering> updateCateringMeal(@RequestBody Meal meal, @PathVariable Long cateringID) {
        return ResponseEntity.ok(service.updateCateringMeal(meal, cateringID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/meal/{mealID}")
    public ResponseEntity<Boolean> deleteCateringMeal(@PathVariable Long mealID) {
        return ResponseEntity.ok(service.deleteCateringMeal(mealID));
    }


    // Inventory Endpoints

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(service.getAllInventory());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/inventory/{id}")
    public ResponseEntity<Inventory> getInventoryByID(@PathVariable Long id) {
        return ResponseEntity.ok(service.searchInventoryByID(id));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/inventory")
    public ResponseEntity<Boolean> addInventory(@RequestBody Inventory inventory, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addInventory(inventory, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/inventory")
    public ResponseEntity<Boolean> updateInventory(@RequestBody Inventory inventory, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateInventory(inventory, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<Boolean> deleteInventory(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteInventory(id));
    }

    // ProfilePackage Endpoints
    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/profile-package")
    public ResponseEntity<List<ProfilePackage>> getAllProfilePackages() {
        return ResponseEntity.ok(service.getAllProfilePackages());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/profile-package")
    public ResponseEntity<Boolean> addProfilePackage(@RequestBody ProfilePackage profilePackage, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addProfilePackage(profilePackage, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/profile-package")
    public ResponseEntity<Boolean> updateProfilePackage(@RequestBody ProfilePackage profilePackage, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateProfilePackage(profilePackage, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/profile-package/{packageId}")
    public ResponseEntity<Boolean> deleteProfilePackage(@PathVariable Long packageId) {
        return ResponseEntity.ok(service.deleteProfilePackage(packageId));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/profile-package/{packageId}")
    public ResponseEntity<ProfilePackage> getProfilePackageSupplierByID(@PathVariable Long packageId) {
        return ResponseEntity.ok(service.searchProfilePackageSupplierByID(packageId));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/profile-package/search")
    public ResponseEntity<ProfilePackage> searchProfilePackageByName(@RequestParam String packageName) {
        return ResponseEntity.ok(service.searchProfilePackageSupplierByName(packageName));
    }

//    // PackageFeature Endpoints
//    @GetMapping("/package-feature")
//    public ResponseEntity<List<PackageFeature>> getAllPackageFeatures() {
//        return ResponseEntity.ok(service.getAllPackageFeatures());
//    }
//
//    @PostMapping("/{packageID}/package-feature")
//    public ResponseEntity<ProfilePackage> addPackageFeature(@RequestBody PackageFeature packageFeature, @PathVariable Long packageID) {
//        return ResponseEntity.ok(service.addPackageFeature(packageFeature, packageID));
//    }
//
//    @PutMapping("/{packageID}/package-feature")
//    public ResponseEntity<ProfilePackage> updatePackageFeature(@RequestBody PackageFeature packageFeature, @PathVariable Long packageID) {
//        return ResponseEntity.ok(service.updatePackageFeature(packageFeature, packageID));
//    }
//
//    @DeleteMapping("/package-feature/{id}")
//    public ResponseEntity<Boolean> deletePackageFeature(@PathVariable Long id) {
//        return ResponseEntity.ok(service.deletePackageFeature(id));
//    }
//
//    @GetMapping("/package-feature/{id}")
//    public ResponseEntity<ProfilePackage> searchPackageFeatureById(@PathVariable Long id) {
//        return ResponseEntity.ok(service.searchPackageFeatureById(id));
//    }

    // ProfilePreviousWork Endpoints
    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/profile-previous-work")
    public ResponseEntity<List<ProfilePreviousWork>> getAllProfilePreviousWork() {
        return ResponseEntity.ok(service.getAllProfilePreviousWork());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/profile-previous-work")
    public ResponseEntity<Boolean> addProfilePreviousWork(@RequestBody ProfilePreviousWork profilePreviousWork, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addProfilePreviousWork(profilePreviousWork, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/profile-previous-work")
    public ResponseEntity<Boolean> updateProfilePreviousWork(@RequestBody ProfilePreviousWork profilePreviousWork, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateProfilePreviousWork(profilePreviousWork, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/profile-previous-work/{id}")
    public ResponseEntity<Boolean> deleteProfilePreviousWork(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteProfilePreviousWork(id));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/profile-previous-work/{id}")
    public ResponseEntity<List<ProfilePreviousWork>> searchProfilePreviousWorkByID(@PathVariable Long id) {
        return ResponseEntity.ok(service.searchProfilePreviousWorkByID(id));
    }


    // Supplier Request

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/request")
    public ResponseEntity<List<SupplierRequest>> getAllSupplierRequests() {
        return ResponseEntity.ok(service.getAllSupplierRequests());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/request/{supplierRequestID}")
    public ResponseEntity<SupplierRequest> getSupplierRequestByID(@PathVariable Long supplierRequestID) {
        return ResponseEntity.ok(service.getSupplierRequestByID(supplierRequestID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{supplierID}/request")
    public ResponseEntity<Boolean> addSupplierRequest(@RequestBody SupplierRequest supplierRequest, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addSupplierRequest(supplierRequest, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping("/{supplierID}/request")
    public ResponseEntity<Supplier> updateSupplierRequest(@RequestBody SupplierRequest supplierRequest, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.updateSupplierRequest(supplierRequest, supplierID));
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/request/{supplierRequestID}")
    public ResponseEntity<Boolean> deleteSupplierRequest(@PathVariable Long supplierRequestID) {
        return ResponseEntity.ok(service.deleteSupplierRequest(supplierRequestID));
    }



}
