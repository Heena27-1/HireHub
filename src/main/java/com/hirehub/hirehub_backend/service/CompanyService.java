package com.hirehub.hirehub_backend.service;

import com.hirehub.hirehub_backend.dto.CompanyRequest;
import com.hirehub.hirehub_backend.dto.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest request);

    List<CompanyResponse> getAllCompanies();

    CompanyResponse getCompanyById(Long id);

    CompanyResponse updateCompany(Long id, CompanyRequest request);

    void deleteCompany(Long id);
}