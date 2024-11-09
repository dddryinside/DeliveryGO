package bsuir.chernikov.main.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        String statusCode = status != null ? status.toString() : "Unknown";

        Object message = request.getAttribute("jakarta.servlet.error.message");
        String errorMessage = message != null ? message.toString() : "No details available";

        model.addAttribute("error", "Error: " + statusCode);
        model.addAttribute("message", errorMessage);

        return "error";
    }
}