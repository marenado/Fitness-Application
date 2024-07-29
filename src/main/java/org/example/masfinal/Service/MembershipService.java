package org.example.masfinal.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.masfinal.Model.BasicMembership;
import org.example.masfinal.Model.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void upgradeMemberships() {
        TypedQuery<BasicMembership> query = entityManager.createQuery(
                "SELECT b FROM BasicMembership b", BasicMembership.class);
        List<BasicMembership> basicMemberships = query.getResultList();

        for (BasicMembership basicMembership : basicMemberships) {
            if (basicMembership.shouldBeUpgraded()) {
                Membership upgradedMembership = basicMembership.upgrade();
                entityManager.remove(basicMembership); // Remove basic membership
                entityManager.persist(upgradedMembership); // Persist upgraded membership
            }
        }
    }
}

