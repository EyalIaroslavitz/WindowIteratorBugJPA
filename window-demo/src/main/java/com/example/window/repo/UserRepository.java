package com.example.window.repo;

import com.example.window.model.AddressView;
import com.example.window.model.User;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

    Window<AddressView> findFirst20By(ScrollPosition scrollPosition);
}