package com.userfront.unit.Service;

import com.userfront.controller.AccountController;
import com.userfront.dao.PrimaryAccountDao;
import com.userfront.dao.SavingsAccountDao;
import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.User;
import com.userfront.service.AccountService;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;
import com.userfront.service.UserServiceImpl.AccountServiceImpl;
import com.userfront.service.UserServiceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)


public class AccountServiceImplTest {
    @MockBean
    private AccountServiceImpl accountServiceImpl;
    @Mock
    private PrimaryAccount primaryAccount;

    @Mock
    private SavingsAccountDao savingsAccountDao;

    @InjectMocks
    private UserServiceImpl userService;


    @MockBean
    private TransactionService transactionService;
    @MockBean
    private Principal principle ;
    @Test
    // @WithMockUser(username = "User",password = "password")
    //@AutoConfigureMockMvc(addFilters = false)
    public void primaryAccountTest() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("User");
        //PrimaryAccount primaryAccount = new PrimaryAccount();

      primaryAccount = userService.findByUsername("user").getPrimaryAccount();

              accountServiceImpl.deposit("Primary",300,mockPrincipal);
        Assert.assertEquals("Checking user balance", 2000,primaryAccount.getAccountBalance());

    }

}
