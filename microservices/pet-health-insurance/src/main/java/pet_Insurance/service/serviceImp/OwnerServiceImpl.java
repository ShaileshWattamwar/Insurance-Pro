package pet_Insurance.service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_Insurance.entities.OwnerDeteail;
import pet_Insurance.repository.OwnerRepository;
import pet_Insurance.service.OwnerDetailService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerDetailService {

    private final OwnerRepository ownerRepository;

    @Override
    public OwnerDeteail createOwner(OwnerDeteail owner) {

        return ownerRepository.save(owner);
    }

    @Override
    public OwnerDeteail getOwnerById(String id) {

        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public List<OwnerDeteail> getAllOwners() {

        return ownerRepository.findAll();
    }

    @Override
    public OwnerDeteail updateOwner(String id, OwnerDeteail owner) {

        Optional<OwnerDeteail> existing = ownerRepository.findById(id);
        if (existing.isPresent()) {
            OwnerDeteail updated  = existing.get();
            updated.setOwnerName(owner.getOwnerName());
            updated.setEmail(owner.getEmail());
            updated.setOwnerName(owner.getOwnerName());
            updated.setOwnerAddress(owner.getOwnerAddress());
            return ownerRepository.save(updated);
        }
        return null;
    }

    @Override
    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }
}

