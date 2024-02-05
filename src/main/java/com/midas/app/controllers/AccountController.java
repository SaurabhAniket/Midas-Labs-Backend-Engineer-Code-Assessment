package com.midas.app.controllers;

import com.midas.app.mappers.Mapper;
import com.midas.app.models.Account;
import com.midas.app.services.AccountService;
import com.midas.app.services.ProviderType;
import com.midas.generated.api.AccountsApi;
import com.midas.generated.model.AccountDto;
import com.midas.generated.model.CreateAccountDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountController implements AccountsApi {
  private final AccountService accountService;
  private final Logger logger = LoggerFactory.getLogger(AccountController.class);

  /**
   * POST /accounts : Create a new user account Creates a new user account with the given details
   * and attaches a supported payment provider such as 'stripe'.
   *
   * @param createAccountDto User account details (required)
   * @return User account created (status code 201)
   */
  @Override
  public ResponseEntity<AccountDto> createUserAccount(CreateAccountDto createAccountDto) {
    logger.info("Creating account for user with email: {}", createAccountDto.getEmail());

    // Create an Account object with the new fields
    Account account =
        Account.builder()
            .firstName(createAccountDto.getFirstName())
            .lastName(createAccountDto.getLastName())
            .email(createAccountDto.getEmail())
            .providerType(ProviderType.STRIPE) // Set provider type to stripe
            .build();

    // Create the account and obtain the generated provider ID
    Account createdAccount = accountService.createAccount(account);

    // Map the created account to DTO and return
    AccountDto accountDto = Mapper.toAccountDto(createdAccount);
    return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
  }

  /**
   * GET /accounts : Get list of user accounts Returns a list of user accounts.
   *
   * @return List of user accounts (status code 200)
   */
  @Override
  public ResponseEntity<List<AccountDto>> getUserAccounts() {
    logger.info("Retrieving all accounts");

    // Retrieve all accounts and map them to DTOs
    List<Account> accounts = accountService.getAccounts();
    List<AccountDto> accountsDto = accounts.stream().map(Mapper::toAccountDto).toList();

    return new ResponseEntity<>(accountsDto, HttpStatus.OK);
  }
}