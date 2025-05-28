package pet_Insurance.service;

import pet_Insurance.entities.OwnerDeteail;

import java.util.List;

public interface OwnerDetailService {

    OwnerDeteail getOwnerById(String id);

    List<OwnerDeteail> getAllOwners();

    OwnerDeteail createOwner(OwnerDeteail owner);

     OwnerDeteail updateOwner(String id, OwnerDeteail owner);

    void deleteOwner(String id);
}
