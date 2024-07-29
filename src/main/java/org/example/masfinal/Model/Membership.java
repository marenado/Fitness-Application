package org.example.masfinal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "membership_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date startDate;
    private int duration; // in months
    private double monthlyFee;

    public Membership(Membership oldMembership) {
        this.startDate = oldMembership.getStartDate();
        this.duration = oldMembership.getDuration();
        this.monthlyFee = oldMembership.getMonthlyFee();
    }

    public boolean shouldBeUpgraded() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, duration);

        Date currentDate = new Date();

        return currentDate.after(calendar.getTime());
    }

    public abstract Membership upgrade();

    public abstract double calculateFee();
}
