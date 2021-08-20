package crm.entity;

import crm.util.ArgumentValidator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static crm.util.ArgumentValidator.*;

@Entity
@Table(name = "premium")
public class PremiumStatus {
    @Id
    private UUID id;
    private boolean isActive;
    private LocalDate expireAt;
    private PremiumType premiumType;

    private PremiumStatus(){};

    public PremiumStatus(boolean isActive, LocalDate expireAt, PremiumType premiumType) {
        validate(isActive || !isActive, "Invalid premium status");
        validate(expireAt.isAfter(LocalDate.now()),"Invalid date");

        this.id = UUID.randomUUID();
        this.isActive = isActive;
        this.expireAt = expireAt;
        this.premiumType = premiumType;
    }

    public UUID getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public PremiumType getPremiumType() {
        return premiumType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremiumStatus that = (PremiumStatus) o;
        return isActive == that.isActive && id.equals(that.id) && expireAt.equals(that.expireAt) && premiumType == that.premiumType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, expireAt, premiumType);
    }


    @Override
    public String toString() {
        return "PremiumStatus{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", expireAt=" + expireAt +
                ", premiumType=" + premiumType +
                '}';
    }
}
