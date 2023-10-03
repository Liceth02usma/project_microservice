package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mssecurity.mssecurity.Models.Bill;
import com.mssecurity.mssecurity.Repositories.BillRepository;
// import com.mssecurity.mssecurity.Repositories.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping("private/bill")
public class BillController {
    @Autowired
    private BillRepository theBillRepository;



    @GetMapping("")
    public List<Bill> index() {
        return this.theBillRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Bill store(@RequestBody Bill newBill) {
        return this.theBillRepository.save(newBill);
    }

    @GetMapping("{id}")
    public Bill show(@PathVariable String id) {
        Bill theBill = this.theBillRepository.findById(id).orElse(null);
        return theBill;
    }

    @PutMapping("{id}")
    public Bill update(@PathVariable String id, @RequestBody Bill theNewBill) {
        Bill theActualBill = this.theBillRepository.findById(id).orElse(null);
        if (theActualBill != null) {
            theActualBill.setDatetime(theNewBill.getDatetime());
            theActualBill.setTotal(theNewBill.getTotal());
            theActualBill.setDishFoodOrder(theNewBill.getDishFoodOrder());
            return this.theBillRepository.save(theActualBill);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Bill theBill = this.theBillRepository
                .findById(id)
                .orElse(null);
        if (theBill != null) {
            this.theBillRepository.delete(theBill);
        }
    }


}
