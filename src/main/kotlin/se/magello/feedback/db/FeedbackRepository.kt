package se.magello.feedback.db

import org.springframework.data.repository.CrudRepository
import se.magello.feedback.model.internal.Feedback

interface FeedbackRepository : CrudRepository<Feedback, String> {

}