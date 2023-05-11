package com.list.WChatProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Member implements UserDetails {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;
    @Column
    private String nickName;
    @Column
    private String name;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column
    private boolean isBan;
    @Column
    private LocalDateTime changeAt;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Session> sessions = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isBan;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isBan;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isBan;
    }

    @Override
    public boolean isEnabled() {
        return this.isBan;
    }
}
