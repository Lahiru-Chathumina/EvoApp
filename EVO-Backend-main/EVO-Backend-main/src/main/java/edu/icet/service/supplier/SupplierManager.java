package edu.icet.service.supplier;

import edu.icet.dto.supplier.*;
import edu.icet.util.MealType;
import edu.icet.util.SupplierCategoryType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface SupplierManager {

    // Supplier
    Supplier addSupplier(Supplier supplier);
    List<Supplier> getAllSuppliers();
    List<Supplier> getSupplierByCategory(SupplierCategoryType supplierCategoryType);
    Supplier searchSupplier(Long supplerID);
    Supplier updateSupplier(Supplier supplier);
    Boolean deleteSupplier(Long supplerID);

    // BeautySalon
    List<BeautySaloon> getAllBeautySalon();
    Supplier addBeautySalonSupplier(BeautySaloon beautySaloon, Long supplierID);
    Boolean deleteBeautySalonSupplier(Long supplierID);
    Supplier updateBeautySalonSupplier(BeautySaloon beautySaloon, Long supplierID);
    BeautySaloon getBeautySalon(Long salonID);

    // Catering
    Catering addCateringSupplier(Catering catering, Long supplierID);
    Boolean updateCateringSupplier(Catering catering, Long supplierID);
    Catering getCateringById(Long cateringID);
    List<Catering> getAllCatering();
    Boolean deleteCateringSupplier(Long supplierID);

    // Music
    List<Music> getAllMusic();
    Supplier addMusicSupplier(Music music, Long supplierID);
    Music getMusicByID(Long musicID);
    Boolean updateMusicSupplier(Music music, Long supplierID);
    Boolean deleteMusicSupplier(Long supplierID);

    // Meal
    List<Meal> getAllCateringMeals();
    Catering addCateringMeal(Meal meal, Long cateringID);
    Boolean deleteCateringMeal(Long id);
    Catering updateCateringMeal(Meal meal, Long cateringID);
    List<Catering> searchCateringByMealType(MealType type);
    List<Catering> searchCateringByMealName(String name);
    Meal searchMealByID(Long id);

    // Inventory
    List<Inventory> getAllInventory();
    Boolean addInventory(Inventory inventory, Long supplierID);
    Inventory searchInventoryByID(Long id);
    Boolean updateInventory(Inventory inventory, Long supplierID);
    Boolean deleteInventory(Long id);

    // ProfilePackage
    Boolean addProfilePackage(ProfilePackage profilePackage, Long supplierID);
    List<ProfilePackage> getAllProfilePackages();
    Boolean updateProfilePackage(ProfilePackage profilePackage, Long supplierID);
    Boolean deleteProfilePackage(Long packageId);
    ProfilePackage searchProfilePackageSupplierByID(Long packageId);
    ProfilePackage searchProfilePackageSupplierByName(String packageName);

//    // PackageFeature
//    List<PackageFeature> getAllPackageFeatures();
//    ProfilePackage addPackageFeature(PackageFeature packageFeature, Long packageID);
//    Boolean deletePackageFeature(Long id);
//    ProfilePackage updatePackageFeature(PackageFeature packageFeature, Long packageID);
//    ProfilePackage searchPackageFeatureById(Long id);

    // ProfilePreviousWork
    List<ProfilePreviousWork> getAllProfilePreviousWork();
    Boolean addProfilePreviousWork(ProfilePreviousWork profilePreviousWork, Long supplierID);
    Boolean deleteProfilePreviousWork(Long id);
    List<ProfilePreviousWork> searchProfilePreviousWorkByID(Long profilePreviousWorkID);
    Boolean updateProfilePreviousWork(ProfilePreviousWork profilePreviousWork, Long supplierID);


    // SupplierRequest
    Boolean addSupplierRequest(SupplierRequest supplierRequest, Long supplierID);
    List<SupplierRequest> getAllSupplierRequests();
    SupplierRequest getSupplierRequestByID(Long id);
    Supplier updateSupplierRequest(SupplierRequest supplierRequest, Long supplierID);
    Boolean deleteSupplierRequest(Long id);

    boolean existsByEmail(@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email);
    Supplier getSupplierByEmail (String email);

}
