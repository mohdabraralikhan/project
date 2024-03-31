package org.nicecharity.crowdfunding.project;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Locale.Category;

import org.nicecharity.crowdfunding.project.categories.Categories;
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
public class Project  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;
    
    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;
    
    @Column(name="goal_amount")
    private BigDecimal goalAmount;

    @Column(name="current_amount_funded")
    private BigDecimal currentAmountFunded;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="status")
    private String status;


        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

}
