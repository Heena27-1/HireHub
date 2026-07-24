package com.hirehub.hirehub_backend.controller;

import com.hirehub.hirehub_backend.dto.CompanyRequest;
import com.hirehub.hirehub_backend.dto.CompanyResponse;
import com.hirehub.hirehub_backend.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyResponse createCompany(
            @Valid @RequestBody CompanyRequest request) {

        return companyService.createCompany(request);
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {

        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {

        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {

        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);

        return "Company deleted successfully";
    }
}