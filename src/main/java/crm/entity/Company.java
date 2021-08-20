package crm.entity;

import crm.service.RegisterCompanyForm;
import crm.service.RegisterPersonForm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import static crm.util.ArgumentValidator.*;
import static java.util.Objects.*;
@Entity
@DiscriminatorValue("COMPANY") // set value in coulumn CUSTOMERTYPE
public class Company extends Customer{

    private String companyName;

    @Embedded
    private NipNumber nipNumber;

    private Company(){};

    public Company(String companyName, NipNumber nipNumber) {
        validate(companyName != null && !companyName.isBlank(), "Invalid company name");

        this.companyName = companyName;
        this.nipNumber = requireNonNull(nipNumber);
    }

    public static Company from(RegisterCompanyForm form){
        return new Company(form.getCompanyName(), new NipNumber(form.getNip()));
    }


    public String getCompanyName() {
        return companyName;
    }

    public NipNumber getNipNumber() {
        return nipNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return companyName.equals(company.companyName) && nipNumber.equals(company.nipNumber);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), companyName, nipNumber);
    }
}
