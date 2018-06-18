/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.DinnerGuestRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mrsch
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DinnerGuestServiceImplTest {
    @Mock
    @Autowired
    private DinnerGuestRepository repo;
    public DinnerGuestServiceImplTest() {
    }
    

    @Test
    public void testSave() {
    }

    /**
     * Test of getAllGuests method, of class DinnerGuestServiceImpl.
     */
    @Test
    public void testGetAllGuests() {
    }

    /**
     * Test of searchGuest method, of class DinnerGuestServiceImpl.
     */
    @Test
    public void testSearchGuest() {
    }

    /**
     * Test of delete method, of class DinnerGuestServiceImpl.
     */
    @Test
    public void testDelete() {
    }

    /**
     * Test of editDinnerGuest method, of class DinnerGuestServiceImpl.
     */
    @Test
    public void testEditDinnerGuest() {
    }

    /**
     * Test of checkForIssues method, of class DinnerGuestServiceImpl.
     */
    @Test
    public void testCheckForIssues() {
    }

    /**
     * Test of validate method, of class DinnerGuestServiceImpl.
     */
    @Test
    public void testValidate() {
    }
    
}
