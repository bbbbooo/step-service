package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import com.example.stepbackend.aggregate.entity.Token;
import com.example.stepbackend.aggregate.entity.vo.UserVO;
import com.example.stepbackend.global.exception.TokenNotFoundException;
import com.example.stepbackend.global.security.token.UserPrincipal;
import com.example.stepbackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IssueTokenService {

    private final CustomTokenService customTokenService;
    private final TokenRepository tokenRepository;
    private final RequestUser requestUser;

    @Autowired
    public IssueTokenService(CustomTokenService customTokenService, TokenRepository tokenRepository, RequestUser requestUser) {
        this.customTokenService = customTokenService;
        this.tokenRepository = tokenRepository;
        this.requestUser = requestUser;
    }

    @Transactional
    public String issueTokenByUserPrincipal(UserPrincipal userPrincipal) {
        long userId = userPrincipal.getId();
        String userRole = userPrincipal.getRole();
        Optional<Token> findToken = tokenRepository.findTokenByUser_Id(userId);
        String issuedToken = customTokenService.createToken(userId, userRole);
        if(findToken.isPresent()) {
            Token updateToken = findToken.get();
            updateToken.setAccessToken(issuedToken);
            tokenRepository.save(updateToken);
        } else {
            Token createdToken = new Token(new UserVO(userId), issuedToken);
            tokenRepository.save(createdToken);
        }

        return issuedToken;
    }

    @Transactional
    public String issueTokenByAccessToken(String accessToken) {
        Token findToken = tokenRepository.findTokenByAccessToken(accessToken).orElseThrow(
                () -> new TokenNotFoundException("해당 AccessToken은 폐기되었습니다.")
        );
        long userId = findToken.getUser().getId();

        FindUserDTO findUser = requestUser.getUserById(userId);

        String issuedToken = customTokenService.createToken(findUser.getId(), findUser.getRole());
        findToken.setAccessToken(issuedToken);
        tokenRepository.save(findToken);

        return issuedToken;
    }

}
