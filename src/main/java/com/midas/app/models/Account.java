package com.midas.app.models;

import com.midas.app.services.ProviderType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @Column(name = "id")
  @GeneratedValue
  private UUID id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "provider_type") // New field for provider type
  @Enumerated(EnumType.STRING)
  private ProviderType providerType;

  @Column(name = "provider_id") // New field for provider ID
  private String providerId;

  @Column(name = "created_at")
  @CreationTimestamp
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private OffsetDateTime updatedAt;

  public String getName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getName'");
  }

  public void setProviderType(String stripeCustomerId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setProviderType'");
  }

  public void setProviderType(ProviderType stripe) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setProviderType'");
  }
}
