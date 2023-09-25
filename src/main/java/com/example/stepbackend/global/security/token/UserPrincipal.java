package com.example.stepbackend.global.security.token;

import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {

    @Getter
    private final Long id;
    private final String nickname;
    private final String role;
    private final String profileImage;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

    private UserPrincipal(Builder builder) {
        this.id = builder.id;
        this.nickname = builder.nickname;
        this.role = builder.role;
        this.profileImage = builder.profileImage;
        this.authorities = builder.authorities;
        this.attributes = builder.attributes;
    }

    public static Builder builder(Long id, String nickname, String role,String profileImage, Map<String, Object> attributes) {
        return new Builder(id, nickname, role, profileImage, attributes);
    }

    public static Builder builder(Long id, String nickname, String role, String profileImage) {
        return new Builder(id, nickname, role, profileImage);
    }

    public static class Builder {
        private final Long id;
        private final String nickname;
        private final String role;
        private final String profileImage;
        private final Collection<? extends GrantedAuthority> authorities;
        private Map<String, Object> attributes;

        public Builder(Long id, String nickname, String role,String profileImage, Map<String, Object> attributes) {
            this.id = id;
            this.nickname = nickname;
            this.role = role;
            this.profileImage = profileImage;
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            this.attributes = attributes;

        }

        public Builder(Long id, String nickname, String role, String profileImage) {
            this.id = id;
            this.nickname = nickname;
            this.role = role;
            this.profileImage = profileImage;
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        }

        /**
         * 위 생성자에서 만약 입력 값 중 옵셔널 값일 경우 Setter를 통해 값을 초기화
         * 위 경우 Builder 클래스의 옵셔널 필드는 final 키워드 사용 불가
         * 이러한 경우 Builder 패턴을 사용하는 의미가 크게 낮아짐.
         */

        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        return UserPrincipal.builder(user.getId(), user.getNickname(), Role.valueOf(user.getRole().name()).getKey(), user.getProfileImage(),attributes).build();
    }

    public static UserPrincipal create(FindUserDTO user, Map<String, Object> attributes) {
        return UserPrincipal.builder(user.getId(), user.getNickname(), Role.valueOf(user.getRole()).getKey(),user.getProfileImage(), attributes).build();
    }

    public static UserPrincipal create(FindUserDTO user) {
        return UserPrincipal.builder(user.getId(), user.getNickname(),user.getProfileImage(), Role.valueOf(user.getRole()).getKey()).build();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return nickname;
    }

}
