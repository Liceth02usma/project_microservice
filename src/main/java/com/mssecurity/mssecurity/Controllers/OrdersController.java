package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Order;
import com.mssecurity.mssecurity.Models.Table;
import com.mssecurity.mssecurity.Repositories.OrderRepository;
import com.mssecurity.mssecurity.Repositories.TableRepository;

@CrossOrigin
@RestController
@RequestMapping("private/order")
public class OrdersController {
    @Autowired
    private OrderRepository theOrderRepository;
    @Autowired
    private TableRepository theTableRepository;

    @GetMapping("")
    public List<Order> index() {
        return this.theOrderRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Order store(@RequestBody Order newOrder) {
        return this.theOrderRepository.save(newOrder);
    }

    @GetMapping("{id}")
    public Order show(@PathVariable String id, @RequestBody Order newOrder) {
        Order theOrder = this.theOrderRepository.findById(id).orElse(null);
        return theOrder;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Order update(@PathVariable String id, @RequestBody Order newOrder) {
        Order theOrder = this.theOrderRepository.findById(id).orElse(null);

        if (theOrder != null) {
            theOrder.setDatetime(newOrder.getDatetime());
            theOrder.setStatus(newOrder.getStatus());
            theOrder.setTable(newOrder.getTable());
            return this.theOrderRepository.save(theOrder);
        } else {
            return null;
        }

    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Order theOrder = this.theOrderRepository.findById(id).orElse(null);
        if (theOrder != null) {
            this.theOrderRepository.delete(theOrder);
        }
    }

    @PutMapping("{order_id}/table/{table_id}")
    public Order matchOrderTable(@PathVariable String order_id, @PathVariable String table_id) {

        Order theOrder = this.theOrderRepository.findById(order_id).orElse(null);
        Table theTable = this.theTableRepository.findById(table_id).orElse(null);

        if (theOrder != null && theTable != null) {
            theOrder.setTable(theTable);
            return this.theOrderRepository.save(theOrder);
        } else {
            return null;
        }
    }

    @PutMapping("{order_id}/table")
    public Order unMatchOrderTable(@PathVariable String order_id) {

        Order theOrder = this.theOrderRepository.findById(order_id).orElse(null);

        if (theOrder != null) {
            theOrder.setTable(null);
            return this.theOrderRepository.save(theOrder);
        } else {
            return null;
        }
    }

}
