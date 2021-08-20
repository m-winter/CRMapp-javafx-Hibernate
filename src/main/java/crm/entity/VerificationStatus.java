package crm.entity;

import crm.util.ArgumentValidator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static crm.util.ArgumentValidator.*;

@Entity
@Table(name = "verification")
public class VerificationStatus {
    @Id
    private UUID id;

    private boolean isVerified;
    private LocalDate verifiedAt;
    private String verifiedBy;

    private VerificationStatus(){};

    public VerificationStatus(boolean isVerified, LocalDate verifiedAt, String verifiedBy) {
        validate(isVerified || !isVerified, "Invalid verification status");
        validate(verifiedAt.isBefore(LocalDate.now()), "Invalid verification date");
        validate(verifiedBy != null && !verifiedBy.isBlank(), "Invalid name of the verifier");

        this.id = UUID.randomUUID();
        this.isVerified = isVerified;
        this.verifiedAt = verifiedAt;
        this.verifiedBy = verifiedBy;
    }

    public UUID getId() {
        return id;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public LocalDate getVerifiedAt() {
        return verifiedAt;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationStatus that = (VerificationStatus) o;
        return isVerified == that.isVerified && id.equals(that.id) && verifiedAt.equals(that.verifiedAt) && verifiedBy.equals(that.verifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isVerified, verifiedAt, verifiedBy);
    }

    @Override
    public String toString() {
        return "VerificationStatus{" +
                "id=" + id +
                ", isVerified=" + isVerified +
                ", verifiedAt=" + verifiedAt +
                ", verifiedBy='" + verifiedBy + '\'' +
                '}';
    }
}
