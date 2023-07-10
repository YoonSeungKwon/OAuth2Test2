package yoon.test.oAuthTest2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest2.domain.Accounts;
import yoon.test.oAuthTest2.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountRepository.findAccountsByEmail(username);

        if(accounts == null)
            throw new UsernameNotFoundException(username);

        return accounts;
    }

}
