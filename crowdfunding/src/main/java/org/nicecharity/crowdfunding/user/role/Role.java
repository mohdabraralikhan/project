package org.nicecharity.crowdfunding.user.role;

import java.util.HashSet;
import java.util.Set;

import org.nicecharity.crowdfunding.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;    
    @JsonIgnore
     @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

   public  Role(String name){
    this.name = name;
   }
    
}
