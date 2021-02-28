package bc.repository;

import bc.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepo extends JpaRepository <AuthorModel, Long>{

    Set<AuthorModel> findAllByIdIn(Set<Long> authorsId);
    void deleteById(Long id);
}
