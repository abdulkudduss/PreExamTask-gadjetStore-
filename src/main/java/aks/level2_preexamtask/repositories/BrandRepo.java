package aks.level2_preexamtask.repositories;


import aks.level2_preexamtask.entities.Brand;
import aks.level2_preexamtask.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {

    default Brand getBrandByIdOrIdException (Long id) {
        return findById(id).orElseThrow(
                ()->new NotFoundException("Brand with id '"+id+" ' not found"));
    }

//    @Query("""
//            select new gadgetStore.dto.brandDto.BrandResponse(
//                        g.id, g.name, g.image
//                        )
//                                    from Brand g
//            """)
//    List<BrandResponse> getAllBrands();
}
