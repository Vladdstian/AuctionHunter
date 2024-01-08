package com.vladnichifor.auctionhunter.entities;

import com.vladnichifor.auctionhunter.utils.AccountRole;
import com.vladnichifor.auctionhunter.utils.AccountStatus;
import com.vladnichifor.auctionhunter.utils.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "user")
@Table(name = "_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity addressEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_role", nullable = false)
    private AccountRole accountRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @OneToMany
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private List<AuctionEntity> createdAuctions;

    @ManyToMany
    @JoinTable(
            name = "favourite_auctions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id")
    )
    private List<AuctionEntity> favouriteAuctions;

    @ManyToMany
    @JoinTable(
            name = "participated_auctions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id")
    )
    private List<AuctionEntity> participatedAuctions;
}
