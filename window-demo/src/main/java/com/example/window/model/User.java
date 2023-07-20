package com.example.window.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Embedded
    @AttributeOverride(name = "streetName", column = @Column(name = "street_name"))
    @AttributeOverride(name = "zipCode", column = @Column(name = "z_code"))
    private Address address;
    @CreatedDate
    @Column(name = "creation_time")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime creationDate;


    public User(Address address) {
        this.address = address;
    }

    User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder().append(id, user.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
