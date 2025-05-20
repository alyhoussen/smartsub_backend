package dev.houssenaly.smartsub.repositories;

import dev.houssenaly.smartsub.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByState(Subscription.State state);
}
