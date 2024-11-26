package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import org.lafresca.lafrescabackend.DTO.Request.ChangePasswordDTO;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.ForgotPassword;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.ForgotPasswordRepository;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class ForgotPasswordService {
    @Autowired
    private JavaMailSender javaMailSender;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final UserRepository userRepository;

    public ForgotPasswordService(ForgotPasswordRepository forgotPasswordRepository, UserRepository userRepository) {
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.userRepository = userRepository;
    }

    public String sendEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setEmail(email);
        forgotPassword.setRandomNo((int) (Math.random() * 9000) + 1000);

        forgotPasswordRepository.save(forgotPassword);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Recovery");
        message.setText("Hello " + user.get().getLastName() + ",\n" +
                "\n" +
                "We received a request to reset your password. If you made this request, please click the link below to reset your password:\n" +
                "\n" +
                "http://localhost:8080/api/lafresca/forgotpassword/" + forgotPassword.getRandomNo() + "\n" +
                "\n" +
                "This link will expire in [time duration, e.g., 24 hours]. If you did not request to reset your password, please ignore this email, and your account will remain secure.\n" +
                "\n" +
                "If you have any questions or need assistance, feel free to contact us at thecafe.lafresca@gmail.com.\n" +
                "\n" +
                "Thank you,  \n" +
                "Lafresca  \n");
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);

        return "Email sent sucessfully";
    }

    public String changePassword(@Valid ChangePasswordDTO changePasswordDTO) {
        return "Password Changed";
    }
}
