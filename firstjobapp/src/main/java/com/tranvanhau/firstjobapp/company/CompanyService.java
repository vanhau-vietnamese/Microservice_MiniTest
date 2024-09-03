package com.tranvanhau.firstjobapp.company;

import com.tranvanhau.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    boolean updateCompany(Long id, Company company);
    Company getCompanyById(Long id);
    boolean deleteCompany(Long jobId);
}
