package org.nicecharity.crowdfunding.comment;


import java.sql.Timestamp;

import org.nicecharity.crowdfunding.project.Project;
import org.nicecharity.crowdfunding.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name="comment_text",columnDefinition = "TEXT")
    private String commentText;

    @Column(name="timestamp")
    private Timestamp timestamp;

    
}
