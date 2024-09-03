package com.tranvanhau.companyms.company.impl;

import com.tranvanhau.companyms.company.Company;
import com.tranvanhau.companyms.company.CompanyRepository;
import com.tranvanhau.companyms.company.CompanyService;
import com.tranvanhau.companyms.company.clients.ReviewClients;
import com.tranvanhau.companyms.company.dto.ReviewMessageDTO;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewClients reviewClients;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            companyRepository.delete(company);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessageDTO reviewMessageDTO) {
        System.out.println("Description: " + reviewMessageDTO.getDescription());
        Company company = companyRepository.findById(reviewMessageDTO.getCompanyId()).orElseThrow(() -> new NotFoundException("Company not found" + reviewMessageDTO.getCompanyId()));

        double averageRating = reviewClients.getAverageRatingForCompany(reviewMessageDTO.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
