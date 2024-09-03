package com.tranvanhau.firstjobapp.company;

import com.tranvanhau.firstjobapp.job.Job;
import com.tranvanhau.firstjobapp.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") Long id, @RequestBody Company company){
        boolean isCompany = companyService.updateCompany(id, company);
        if(isCompany){
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found Company to update", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable("id")Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id")Long id){
        boolean isDeletedCompany = companyService.deleteCompany(id);
        if(isDeletedCompany){
            return new ResponseEntity<>("Deleted Company Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Company", HttpStatus.NOT_FOUND);
    }
}
