package com.midas.app.workflows;

import com.midas.app.models.Account;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CreateAccountWorkflow {
  String QUEUE_NAME = "create-account-workflow";

  @WorkflowMethod
  Account createAccount(String email);
}

public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {
  @Override
  public Account createAccount(String email) {
    // Implement workflow logic here
    // This method will be invoked asynchronously by Temporal
    // Use the provided email to perform any necessary actions
    // For example, send confirmation emails, perform additional verifications, etc.
    return null;
  }
}
