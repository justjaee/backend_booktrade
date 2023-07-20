package repositories;

import com.example.booktrade_backend.user_form.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    // You can add custom query methods here if needed

}
