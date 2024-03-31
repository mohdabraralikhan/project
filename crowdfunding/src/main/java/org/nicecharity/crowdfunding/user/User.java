package org.nicecharity.crowdfunding.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nicecharity.crowdfunding.comment.Comment;
import org.nicecharity.crowdfunding.preferences.Preferences;
import org.nicecharity.crowdfunding.profile.Profile;
import org.nicecharity.crowdfunding.user.role.Role;
import org.nicecharity.crowdfunding.verification.VerificationStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)

    @Column(name="password")
    private String password; 

    @Column(name="email")
    private String email;

    @Column(name="bio")
    private String bio;

    @Column(name="location")
    private String location;

    @Column(name="preferences_id")
    private String preferencesId;

    @Column(name="verification_id")
    private Long VerificationId;

    @Column(name="profile_id")
    private String profileId;
    // @Column(name="project_history")
    // private String projectHistory;
    // @Column(name="funding_history")
    // private String fundingHistory;
  
   
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferences_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Preferences preferences;

   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verification_id", referencedColumnName = "id", insertable = false, updatable = false)
    private VerificationStatus verificationStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Profile profile;

     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

   @ManyToMany
    @JoinTable(name = "users_roles",
                 joinColumns = @JoinColumn(name = "user_id"),
                 inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>(); 
}
