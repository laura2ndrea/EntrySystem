package campus.u2.entrysystem.company.application;

import campus.u2.entrysystem.utilities.exceptions.GlobalException;
import campus.u2.entrysystem.company.domain.Company;
import campus.u2.entrysystem.people.domain.People;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // to Save company  first time 
    @Transactional

    public Company saveCompany(Company company) {
        if (company == null) {
            throw new GlobalException("Company Objt is not Valid");
        }
        Company companyValidate = new Company(company.getName());
        return companyRepository.save(companyValidate);
    }

    public Company saveCompanyPeople(Company company, List<People> people) {

        Company comp = new Company(company.getName());
        for (People person : people) {
            People p = new People();
            p.setName(person.getName());
            p.setCedula(person.getCedula());
            p.setTelefono(person.getTelefono());
            p.setPersonType(person.getPersonType());
            p.setCompany(comp);
            comp.addPeople(p);
        }
        return companyRepository.save(comp);

    }

    // To delete a company
    @Transactional

    public void deleteCompany(Long id) {

        Optional<Company> companyOpt = companyRepository.getCompanyById(id);
        if (companyOpt.isPresent()) {
            Company companyValidated = companyOpt.get();
            companyRepository.deleteCompany(companyValidated.getId_company());

        } else {
            throw new RuntimeException("Company with ID " + id + " not found.");
        }

    }

    
    
    // To list all companies
    public List<Company> listAllCompanies() {
        return companyRepository.listAllCompanies();
    }

    // To get a Company by ID
    public Company getCompanyById(Long id) {
        return companyRepository.getCompanyById(id)
                .orElseThrow(() -> new RuntimeException("Company with ID " + id + " not found."));
    }

    
    
    
    public List<People> getEmployeesByCompanyId(Long companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

//        @Transactional
//   
//    public Company updateCompany(Company companyToUpdate) {
//        Optional<Company> existingCompanyOpt = companyRepository.findById(companyToUpdate.getId_company());
//        if (existingCompanyOpt.isPresent()) {
//            Company exisitnCompany = existingCompanyOpt.get();
//            exisitnCompany.setName(companyToUpdate.getName());
//            return companyRepository.save(exisitnCompany);
//
//        } else {
//            throw new RuntimeException("Company with ID " + companyToUpdate.getId_company() + " not found.");
//        }
//    }
}
