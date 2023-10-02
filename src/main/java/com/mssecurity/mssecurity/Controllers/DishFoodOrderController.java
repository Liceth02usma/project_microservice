package com.mssecurity.mssecurity.Controllers;

import com.mssecurity.mssecurity.Models.*;
import com.mssecurity.mssecurity.Repositories.BillRepository;
import com.mssecurity.mssecurity.Repositories.DishFoodOrderRepository;
import com.mssecurity.mssecurity.Repositories.DishFoodRepository;
import com.mssecurity.mssecurity.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("private/DishFood-Order")
public class DishFoodOrderController {
    @Autowired
    private DishFoodOrderRepository theDishFoodOrderRepository;
    @Autowired
    private DishFoodRepository theDishFoodRepository;
    @Autowired
    private OrderRepository theOrderRepository;
    @Autowired
    private BillRepository theBillRepository;

    @GetMapping("")
    public List<DishFoodOrder> index() {
        return this.theDishFoodOrderRepository.findAll();
    }


    @GetMapping("{id}")
    public DishFoodOrder show(@PathVariable String id) {
        DishFoodOrder theDishFoodOrder = this.theDishFoodOrderRepository.findById(id).orElse(null);
        return theDishFoodOrder;
    }

    @PutMapping("{id}")
    public DishFoodOrder update(@PathVariable String id, @RequestBody DishFoodOrder theNewDishFoodOrder) {
        DishFoodOrder theActualDishFoodOrder = this.theDishFoodOrderRepository.findById(id).orElse(null);
        if (theActualDishFoodOrder != null) {
            theActualDishFoodOrder.setDishFood(theNewDishFoodOrder.getDishFood());
            theActualDishFoodOrder.setAmount(theNewDishFoodOrder.getAmount());
            theActualDishFoodOrder.setOrder(theNewDishFoodOrder.getOrder());
            theActualDishFoodOrder.setBill(theNewDishFoodOrder.getBill());
            return this.theDishFoodOrderRepository.save(theActualDishFoodOrder);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("DFood/{dfood_id}/order/{order_id}/amount/{amount}")
    public DishFoodOrder store(@PathVariable String order_id,
            @PathVariable String dfood_id, @PathVariable String amount) {
        try {
            int intAmount = Integer.parseInt(amount);
            DishFood theDishFood = this.theDishFoodRepository.findById(dfood_id)
                    .orElse(null);
            Order theOrder = this.theOrderRepository.findById(order_id)
                    .orElse(null);
            if (theDishFood != null && theOrder != null && intAmount != 0) {
                DishFoodOrder newDishFoodOrder = new DishFoodOrder();
                newDishFoodOrder.setDishFood(theDishFood);
                newDishFoodOrder.setOrder(theOrder);
                newDishFoodOrder.setAmount(intAmount);
                return this.theDishFoodOrderRepository.save(newDishFoodOrder);
            } else {
                return null;
            }
        }catch (NumberFormatException e){
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        DishFoodOrder theDishFoodOrder = this.theDishFoodOrderRepository
                .findById(id)
                .orElse(null);
        if (theDishFoodOrder != null) {
            this.theDishFoodOrderRepository.delete(theDishFoodOrder);
        }
    }

    @PutMapping("{dfoodOrder_id}/bill/{bill_id}")
    public DishFoodOrder matchfoodOrderBill(@PathVariable String dfoodOrder_id, @PathVariable String bill_id) {
        DishFoodOrder theActualDishFoodOrder = this.theDishFoodOrderRepository.findById(dfoodOrder_id).orElse(null);
        Bill theActualBill = this.theBillRepository.findById(bill_id).orElse(null);

        if (theActualDishFoodOrder != null && theActualBill != null) {
            theActualDishFoodOrder.setBill(theActualBill);
            return this.theDishFoodOrderRepository.save(theActualDishFoodOrder);
        } else {
            return null;
        }
    }

    @PutMapping("{dfoodOrder_id}/bill")
    public DishFoodOrder unMatchfoodOrderBill(@PathVariable String dfoodOrder_id) {
        DishFoodOrder theActualDishFoodOrder = this.theDishFoodOrderRepository.findById(dfoodOrder_id).orElse(null);

        if (theActualDishFoodOrder != null) {
            theActualDishFoodOrder.setBill(null);
            return this.theDishFoodOrderRepository.save(theActualDishFoodOrder);
        } else {
            return null;
        }
    }
}
