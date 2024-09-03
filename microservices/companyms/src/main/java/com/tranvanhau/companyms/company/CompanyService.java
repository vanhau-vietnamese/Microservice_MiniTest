package com.tranvanhau.companyms.company;

import com.tranvanhau.companyms.company.dto.ReviewMessageDTO;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    boolean updateCompany(Long id, Company company);
    Company getCompanyById(Long id);
    boolean deleteCompany(Long jobId);
    public void updateCompanyRating(ReviewMessageDTO reviewMessageDTO);
}
