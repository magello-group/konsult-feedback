package se.magello.feedback.model.internal

import jakarta.persistence.*

@Entity
class Feedback(
  @Column(nullable = false)
  val userId: String? = null,

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int? = null
  ) {}

