package ru.storeMVC.storeMVC.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.storeMVC.storeMVC.dto.UserDto;
import ru.storeMVC.storeMVC.exceptions.NoExistsUser;
import ru.storeMVC.storeMVC.models.User;

import java.util.List;
import java.util.UUID;

@Repository
public class UsersRepository {
    @PersistenceContext
    private EntityManager manager;

    public UserDto getUserById(UUID uuid) throws NoExistsUser {
        UserDto user = new UserDto(manager.find(User.class, uuid));
        manager.close();
        return user;
    }

    @Transactional
    public void createUser(UserDto user) {
        System.out.println(user.getUser());
        manager.persist(user.getUser());
        manager.flush();
        manager.close();
    }

    public List<UserDto> getAllUser() {
        // Criteria API
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        Query query = manager.createQuery(criteriaQuery);
        List<UserDto> listDto = UserDto.allUser(query.getResultList());
        manager.close();
        return listDto;
    }

    @Transactional
    public UserDto updateUser(UserDto user) {
        User oldUser = manager.find(User.class, user.getUuid());
        oldUser.setEmail(user.getEmail());
        oldUser.setName(user.getName());
        if(user.getOrders() != null) {
            oldUser.setOrders(user.getOrders());
        }
        manager.persist(oldUser);
        manager.close();
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(UUID id) {
        User userDelete = manager.find(User.class, id);
        manager.remove(userDelete);
        manager.close();
    }
}
