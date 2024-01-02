package se.magello.feedback.model.internal

import org.springframework.data.annotation.Id

data class Feedback(@Id val userId: String?) {}
