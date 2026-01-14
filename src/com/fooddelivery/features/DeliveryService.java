package com.fooddelivery.features;

import java.util.List;
import com.fooddelivery.model.*;
import com.fooddelivery.model.DeliveryPerson;

public class DeliveryService {

    public DeliveryPerson assignDeliveryPerson(List<DeliveryPerson> deliveryPersons) {
        for (DeliveryPerson person : deliveryPersons) {
            if (person.isAvailable()) {
                person.setAvailable(false);
                System.out.println("Delivery Person Assigned: " + person.getName());
                return person;
            }
        }
        System.out.println("No Delivery Person Available!");
        return null;
    }

    public int getEstimatedDeliveryTime() {
        return 30;
    }
}
