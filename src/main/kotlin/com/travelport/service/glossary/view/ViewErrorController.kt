package com.travelport.service.glossary.view

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class ViewErrorController: ErrorController {
  override fun getErrorPath() = "/error"

  @RequestMapping("/error")
  fun handleError(request: HttpServletRequest): String {
    val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

    if (status != null) {
      val statusCode = Integer.valueOf(status.toString())

      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "error-404.html"
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "error-500.html"
      }
    }
    return "error.html"
  }
}
