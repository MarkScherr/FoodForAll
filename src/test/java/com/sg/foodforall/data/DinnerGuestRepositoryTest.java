/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.data;

import com.sg.foodforall.models.DinnerGuest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mrsch
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DinnerGuestRepositoryTest {
    
    @Autowired
    private DinnerGuestRepository repo;
    
    public DinnerGuestRepositoryTest() {
    }
    

    public DinnerGuest findByDinnerGuestId(int dinnerGuestId) {
        return null;
    }
    @Test
    public void findNames() {
        List<DinnerGuest> testList = new ArrayList<>();
        testList = repo.findNames("Catey", "");
        assertTrue(testList.size() > 0);
    }
    
}
