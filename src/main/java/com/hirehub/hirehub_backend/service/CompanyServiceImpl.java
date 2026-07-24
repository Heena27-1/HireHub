package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.CompanyRequest;
import com.hirehub.hirehub_backend.dto.CompanyResponse;
import com.hirehub.hirehub_backend.entity.Company;
import com.hirehub.hirehub_backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse createCompany(CompanyRequest request) {

        Company company = new Company();

        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setWebsite(request.getWebsite());
        company.setLocation(request.getLocation());
        company.setCreatedAt(LocalDateTime.now());

        Company saved = companyRepository.save(company);

        return mapToResponse(saved);
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToResponse(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setWebsite(request.getWebsite());
        company.setLocation(request.getLocation());

        Company updated = companyRepository.save(company);

        return mapToResponse(updated);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private CompanyResponse mapToResponse(Company company) {

        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getWebsite(),
                company.getLocation(),
                company.getCreatedAt()
        );
    }
}