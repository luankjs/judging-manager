package br.ufrn.judging_manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "judging_suits")
public class JudgingSuit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "judging_id")
  private Judging judging;

  @ManyToOne
  @JoinColumn(name = "suit_id")
  private Suit suit;

  @Column(nullable = false)
  private Number order;
}
