package dev.houssenaly.smartsub.services;

import dev.houssenaly.smartsub.models.Subscription;
import dev.houssenaly.smartsub.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, Subscription updatedSubscription) {
        return subscriptionRepository.findById(id)
                .map(subscription -> {
                    subscription.setName(updatedSubscription.getName());
                    subscription.setWebsite(updatedSubscription.getWebsite());
                    subscription.setPrice(updatedSubscription.getPrice());
                    subscription.setStartDate(updatedSubscription.getStartDate());
                    subscription.setEndDate(updatedSubscription.getEndDate());
                    subscription.setState(updatedSubscription.getState());
                    subscription.setImageUrl(updatedSubscription.getImageUrl());
                    subscription.setState(updatedSubscription.getState());
                    subscription.setUserId(updatedSubscription.getUserId());
                    return subscriptionRepository.save(subscription);
                })
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}