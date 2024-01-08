package se.magello.feedback.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class InputController {

  @GetMapping("/edit")
  fun getExistingFeedbackForId(@RequestParam("consultantId") consultantId : String) = "Yeah this was in DB for $consultantId"


  @GetMapping("/hello")
  fun greet() = "Hello man!"

  @GetMapping("/external/{uuid}/edit")
  fun redirectExternalToEdit(@PathVariable uuid: String) : String {
    return "Redirecting to ${uuid}, hold on!"
  }
}