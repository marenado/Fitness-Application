package org.example.masfinal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    @Column(name = "client_id", nullable = false)
    private long clientId;

    @Column(name = "plan_id", nullable = false)
    private long planId;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", insertable = false, updatable = false)
    private WorkoutPlan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", insertable=false, updatable = false)
    private User client;


    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progress> progressRecords;


    public Date calculateEndDate() {
        if (plan != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, plan.getDuration());
            return calendar.getTime();
        }
        return null;
    }

    public boolean isCompleted() {
        Date currentDate = new Date();
        return currentDate.after(calculateEndDate());
    }

    public void markCompleted() {
        if (!isCompleted()) {
            plan.markCompleted();
        }
    }

    public void viewDetails() {
        System.out.println("Enrollment Details:");
        System.out.println("Client ID: " + clientId);
        System.out.println("Plan ID: " + planId);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + calculateEndDate());
        System.out.println("Plan Details:");
        if (plan != null) {
            plan.viewDetails();
        }
        System.out.println("Client Details:");
        if (client != null) {
            System.out.println("Name: " + client.getName());
            System.out.println("Email: " + client.getEmail());
        }
    }


}
