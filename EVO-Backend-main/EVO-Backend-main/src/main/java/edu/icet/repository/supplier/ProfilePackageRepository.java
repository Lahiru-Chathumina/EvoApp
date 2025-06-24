package edu.icet.repository.supplier;

import edu.icet.entity.supplier.ProfilePackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProfilePackageRepository extends JpaRepository<ProfilePackageEntity,Long> {
    List<ProfilePackageEntity> getAllByPackageId(Long id);
    ProfilePackageEntity findByPackageName(String name);
    boolean existsByPackageName(String name);

}
