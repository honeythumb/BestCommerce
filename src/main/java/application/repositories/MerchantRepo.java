package application.repositories;

import application.entities.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepo extends CrudRepository<Merchant, Long> {

    Optional<Merchant> findMerchantByEmail(String email);

    @Override
    <S extends Merchant> S save(S s);

    @Override
    Iterable<Merchant> findAll();
}
