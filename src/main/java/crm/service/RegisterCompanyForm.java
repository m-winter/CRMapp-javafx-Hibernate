package crm.service;

import static java.util.Objects.*;

public class RegisterCompanyForm {

    private final String companyName;
    private final String nip;

    public RegisterCompanyForm(String companyName, String nip) {
        this.companyName = requireNonNull(companyName);
        this.nip = requireNonNull(nip);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNip() {
        return nip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterCompanyForm that = (RegisterCompanyForm) o;
        return companyName.equals(that.companyName) && nip.equals(that.nip);
    }

    @Override
    public int hashCode() {
        return hash(companyName, nip);
    }

    @Override
    public String toString() {
        return "RegisterCompanyForm{" +
                "companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                '}';
    }
}
