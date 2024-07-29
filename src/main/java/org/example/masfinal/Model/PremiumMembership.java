package org.example.masfinal.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Premium")
public class PremiumMembership extends Membership {
    private boolean accessToPremiumContent;

    public PremiumMembership(Membership oldMembership, boolean accessToPremiumContent) {
        super(oldMembership);
        this.accessToPremiumContent = accessToPremiumContent;
    }

    @Override
    public double calculateFee() {
        return getMonthlyFee() * getDuration();
    }

    @Override
    public Membership upgrade() {
        return this;
    }
}
