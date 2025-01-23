
package campus.u2.entrysystem.company.application;


import campus.u2.entrysystem.company.domain.Company;
import campus.u2.entrysystem.people.domain.People;
import java.util.List;
import java.util.Optional;


public interface CompanyRepository {
    
    
    Company save(Company company);
    Company saveCompanyPeople(Company company, List<People> people);
//    Company updateCompany(Company companyToUpdate);
    void deleteCompany(Long id);
    List<Company> listAllCompanies();
    Optional<Company> getCompanyById(Long id);
    List<People> getEmployeesByCompanyId(Long companyId);
                
            

     
    
    
    
    
    
}
