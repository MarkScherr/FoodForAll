/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.data;


import com.sg.foodforall.models.DinnerGuest;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mrsch
 */
@Repository
public interface DinnerGuestRepository extends JpaRepository<DinnerGuest, Integer>{
    
    DinnerGuest findByDinnerGuestId(int dinnerGuestId);
    
    @Query("select d from DinnerGuest d WHERE (:firstName = '' OR d.firstName LIKE CONCAT('%', UPPER( :firstName), '%')) and " +
                "(:lastName = '' OR d.lastName LIKE CONCAT('%', UPPER( :lastName), '%'))")
     List<DinnerGuest> findNames(@Param("firstName") String firstName, 
                                                    @Param("lastName") String lastName);
     @Transactional
     @Modifying
     @Query("DELETE FROM DinnerGuest d WHERE d.dinnerGuestId = :dinnerGuestId")
     void deleteById(@Param("dinnerGuestId") String dinnerGuestId);
}
