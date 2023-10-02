package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Table;
import com.mssecurity.mssecurity.Repositories.TableRepository;
@CrossOrigin
@RestController
@RequestMapping("private/tables")
public class TablesController {
    @Autowired
    private TableRepository theTableRepository;

    @GetMapping("")
    public List<Table> index() {
        return this.theTableRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Table store(@RequestBody Table newTable) {
        return this.theTableRepository.save(newTable);
    }

    @GetMapping("{id}")
    public Table show(@PathVariable String id) {
        Table theTable = this.theTableRepository.findById(id).orElse(null);
        return theTable;
    }

    @PutMapping("{id}")
    public Table update(@PathVariable String id, @RequestBody Table newTable) {
        Table theTable = this.theTableRepository.findById(id).orElse(null);
        if (theTable != null) {
            theTable.setCapacity(newTable.getCapacity());
            theTable.setAvailability(newTable.isAvailability());
            return this.theTableRepository.save(theTable);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Table theTable = this.theTableRepository.findById(id).orElse(null);
        if (theTable != null) {
            this.theTableRepository.delete(theTable);
        }
    }

}
