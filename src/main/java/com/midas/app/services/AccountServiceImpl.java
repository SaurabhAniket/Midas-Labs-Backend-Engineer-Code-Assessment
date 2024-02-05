package com.midas.app.services;

import com.midas.app.models.Account;
import com.midas.app.providers.external.stripe.StripeConfiguration;
import com.midas.app.repositories.AccountRepository;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

  private final WorkflowClient workflowClient;
  private final AccountRepository accountRepository;
  private final StripeConfiguration stripeConfiguration;

  @Override
  public Account createAccount(Account details) {
    String email = details.getEmail();
    String name = details.getName();
    String stripeCustomerId = createStripeCustomer(email, name);
    details.setProviderType(ProviderType.STRIPE);
    details.setProviderType(stripeCustomerId);
    Account savedAccount = accountRepository.save(details);
    initiateWorkflow(savedAccount.getEmail());
    return savedAccount;
  }

  private String createStripeCustomer(String email, String name) {
    Stripe.apiKey = stripeConfiguration.getApiKey();
    Map<String, Object> customerParams = new HashMap<>();
    customerParams.put("email", email);
    customerParams.put("name", name);
    try {
      Customer customer = Customer.create(customerParams);
      return customer.getId();
    } catch (StripeException e) {
      logger.error("Failed to create Stripe customer: {}", e.getMessage());
      throw new RuntimeException("Failed to create Stripe customer");
    }
  }

  private void initiateWorkflow(String email) {
    var options =
        WorkflowOptions.newBuilder()
            .setTaskQueue(CreateAccountWorkflow.QUEUE_NAME)
            .setWorkflowId(email)
            .build();
    var workflow = workflowClient.newWorkflowStub(CreateAccountWorkflow.class, options);
    workflow.createAccount(email);
  }

  @Override
  public List<Account> getAccounts() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAccounts'");
  }
}
