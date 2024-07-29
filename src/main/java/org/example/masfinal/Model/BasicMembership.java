package org.example.masfinal.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Basic")
public class BasicMembership extends Membership {

    @Min(value = 0, message = "Discount rate cannot be less than 0")
    @Max(value = 1, message = "Discount rate cannot be more than 1")
    private double discountRate;

    public BasicMembership(Membership oldMembership, double discountRate) {
        super(oldMembership);
        this.discountRate = discountRate;
    }

    @Override
    public boolean shouldBeUpgraded() {
        return super.shouldBeUpgraded();
    }

    @Override
    public Membership upgrade() {
        if (shouldBeUpgraded()) {
            return new PremiumMembership(this, true);
        } else {
            return this;
        }
    }

    @Override
    public double calculateFee() {
        return getMonthlyFee() * getDuration() * (1 - discountRate);
    }
}
