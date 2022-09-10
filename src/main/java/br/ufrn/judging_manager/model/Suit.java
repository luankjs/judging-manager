package br.ufrn.judging_manager.model;

import java.sql.Array;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

import lombok.Data;

@TypeDefs({
  @TypeDef(
      name = "string-array",
      typeClass = StringArrayType.class
  )
})
@Data
@Entity
@Table(name = "suits")
public class Suit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String number;
  
  private String excerpt;

  @Type(type = "string-array")
  @Column(columnDefinition = "text[]")
  private List<String> parts;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "judge_id", nullable = false)
  @JsonIgnore
  private Judge judge;
}
