package com.nayab.resource;

import com.nayab.dto.SalesOrderDTO;
import com.nayab.service.SalesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orders")
@RestControllerAdvice
@RestController
@RefreshScope
public class SalesOrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(SalesOrderResource.class);

    @Autowired
    private SalesOrderService salesOrderService;

    @Value("${developer}")
    private String developer;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<SalesOrderDTO> all() {
        System.out.println(developer);
        return salesOrderService.all();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public SalesOrderDTO get(@PathVariable long id) {
        return salesOrderService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public SalesOrderDTO put(@PathVariable long id, @RequestBody SalesOrderDTO salesOrderDTO) {
        salesOrderDTO.setId(id);
        return salesOrderService.save(salesOrderDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        salesOrderService.delete(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesOrderDTO add(@RequestBody SalesOrderDTO salesOrderDTO) {
        return salesOrderService.save(salesOrderDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Throwable ex) {
        LOG.error("There was an error: ", ex);
        // Add conditional logic to show differnt status on different exceptions
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
