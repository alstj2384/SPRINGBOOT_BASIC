package spring.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.blog.domain.RefreshToken;
import spring.blog.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
