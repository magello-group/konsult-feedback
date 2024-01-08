package se.magello.feedback.model.internal

import jakarta.persistence.*

@Entity
@Table(name = "Feedback")
data class Feedback(
  @Column(nullable = false)
  val userId: String? = null,

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0
  ) {}

