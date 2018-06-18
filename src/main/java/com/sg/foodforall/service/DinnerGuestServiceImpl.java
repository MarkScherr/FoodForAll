/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.DinnerGuestRepository;
import com.sg.foodforall.models.Allergy;
import com.sg.foodforall.models.DinnerGuest;
import com.sg.foodforall.models.DinnerGuestIssues;
import com.sg.foodforall.models.FoodAversion;
import com.sg.foodforall.models.Ingredient;
import com.sg.foodforall.models.Intolerance;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mrsch
 */
@Service
public class DinnerGuestServiceImpl implements DinnerGuestService {

    @Autowired
    private DinnerGuestRepository dgRepo;

    @Override
    public Result<DinnerGuest> save(DinnerGuest dg) {
        Result<DinnerGuest> result = validate(dg);
        if (dg.getFirstName() == null || dg.getLastName() == null) {
            result.addMessage("First and last name are required fields.");
        }
        if (result.isSuccess()) {
            dg = dgRepo.save(dg);
            result.setPayload(dg);
        }
        return result;
    }

    @Transactional
    @Override
    public Result<List<DinnerGuest>> getAllGuests() {
        Result<List<DinnerGuest>> resultList = new Result<>();
        resultList.setPayload(dgRepo.findAll());
        List<DinnerGuest> guests = resultList.getPayload();
        for (DinnerGuest guest : guests) {
            guest.getAllergies().size();
            guest.getIntolerances().size();
            guest.getAllergies().size();
        }
        return resultList;
    }

    @Override
    public Result<List<DinnerGuest>> searchGuest(String firstName, String lastName) {
        if (lastName == null) {
            lastName = "";
        }
        if (firstName == null) {
            firstName = "";
        }
        Result<List<DinnerGuest>> resultList = new Result<>();
        resultList.setPayload(dgRepo.findNames(firstName, lastName));
        return resultList;

    }

    @Override
    public Result<DinnerGuest> delete(int id) {
        Result<DinnerGuest> result = new Result<>();
        dgRepo.deleteById(id);
        return result;
    }

    public DinnerGuest editDinnerGuest(int id) {
        Result<DinnerGuest> result = new Result<>();
        result.setPayload(dgRepo.findByDinnerGuestId(id));
        return result.getPayload();
    }

    public Result<List<DinnerGuestIssues>> checkForIssues(List<String> ingredientArray) {
        List<DinnerGuest> dgList = dgRepo.findAll();
        Result<List<DinnerGuestIssues>> returnList = new Result<>();
        DinnerGuestIssues dgi = new DinnerGuestIssues();
        List<DinnerGuestIssues> dgwiList = new ArrayList<>();
        int breakWhile = 0;

        for (DinnerGuest dinnerGuest : dgList) {
            dgi = new DinnerGuestIssues();
            breakWhile = 0;
            while (breakWhile == 0) {
                for (Allergy allergy : dinnerGuest.getAllergies()) {
                    if (breakWhile == 1) {
                        break;
                    }
                    for (String ing : ingredientArray) {
                        if (breakWhile == 1) {
                            break;
                        }
                        if (ing.toLowerCase().contains(allergy.getName().toLowerCase())) {
                            dgi.setFirstName(dinnerGuest.getFirstName());
                            dgi.setLastName(dinnerGuest.getLastName());
                            dgi.setEmailAddress(dinnerGuest.getEmailAddress());
                            dgi.setPhone(dinnerGuest.getPhone());
                            dgi.setConflictWithRecipe("Allergy: " + allergy.getName());
                            dgwiList.add(dgi);
                            breakWhile = 1;
                            break;
                        }
                        for (Ingredient ingredient : allergy.getIngredients()) {
                            if (ing.toLowerCase().contains(ingredient.getName().toLowerCase())) {
                                dgi.setFirstName(dinnerGuest.getFirstName());
                                dgi.setLastName(dinnerGuest.getLastName());
                                dgi.setEmailAddress(dinnerGuest.getEmailAddress());
                                dgi.setPhone(dinnerGuest.getPhone());
                                dgi.setConflictWithRecipe("Allergy: " + allergy.getName());
                                dgwiList.add(dgi);
                                breakWhile = 1;
                                break;
                            }

                        }

                    }
                }
                for (Intolerance intolerance : dinnerGuest.getIntolerances()) {
                    if (breakWhile == 1) {
                        break;
                    }
                    for (String ing : ingredientArray) {
                        if (ing.toLowerCase().contains(intolerance.getName().toLowerCase())) {
                            dgi.setFirstName(dinnerGuest.getFirstName());
                            dgi.setLastName(dinnerGuest.getLastName());
                            dgi.setEmailAddress(dinnerGuest.getEmailAddress());
                            dgi.setPhone(dinnerGuest.getPhone());
                            dgi.setConflictWithRecipe("Intolerance: " + intolerance.getName());
                            dgwiList.add(dgi);
                            breakWhile = 1;
                            break;
                        }
                    }
                }
                for (FoodAversion foodAversion : dinnerGuest.getFoodAversions()) {
                    if (breakWhile == 1) {
                        break;
                    }
                    for (String ing : ingredientArray) {
                        if (ing.toLowerCase().contains(foodAversion.getName().toLowerCase())) {
                            dgi.setFirstName(dinnerGuest.getFirstName());
                            dgi.setLastName(dinnerGuest.getLastName());
                            dgi.setEmailAddress(dinnerGuest.getEmailAddress());
                            dgi.setPhone(dinnerGuest.getPhone());
                            dgi.setConflictWithRecipe("Dislikes: " + foodAversion.getName());
                            dgwiList.add(dgi);
                            breakWhile = 1;
                            break;

                        }
                    }
                }
                breakWhile = 1;
            }
        }
        returnList.setPayload(dgwiList);
        return returnList;
    }

    @Override
    public Result<DinnerGuest> validate(DinnerGuest dg) {
        Result<DinnerGuest> result = new Result<>();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DinnerGuest>> errs = validator.validate(dg);

        for (ConstraintViolation<DinnerGuest> err : errs) {
            result.addMessage(err.getMessage());
        }

        return result;

    }

}
