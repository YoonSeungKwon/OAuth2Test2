package yoon.test.oAuthTest2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest2.domain.Accounts;
import yoon.test.oAuthTest2.enums.Role;
import yoon.test.oAuthTest2.repository.AccountRepository;
import yoon.test.oAuthTest2.vo.response.OAuthAttribute;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Accounts saveOAuth(OAuthAttribute attribute){

        Accounts account = accountRepository.findByEmail(attribute.getEmail())
                .map(entity -> update(entity, attribute.getName()))
                .orElse(toEntity(attribute));

        return accountRepository.save(account);
    }

    public Accounts update(Accounts account, String name){
        account.setName(name);
        return account;
    }

    public Accounts toEntity(OAuthAttribute attribute){
        return Accounts.builder()
                .email(attribute.getEmail())
                .name(attribute.getName())
                .provider(attribute.getProvider())
                .role(Role.USER)
                .build();
    }

}
