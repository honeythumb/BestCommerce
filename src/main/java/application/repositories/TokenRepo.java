package application.repositories;

import application.entities.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends CrudRepository<Token, Long> {

    Token getByEmail(String email);

    @Override
    <S extends Token> S save(S s);

    Token getByRegisterToken(String registerToken);

}
