package testing.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import testing.model.LoginProcessor;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
/* Определяем объекты-заглушки и внедряем их в экземпляр, поведение которого хотим протестировать */
    @Mock
    private Model model;

    @Mock
    private LoginProcessor loginProcessor;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginPostLoginSucceedsTest() {
        /* Делаем так, чтобы объект-заглушка, при вызове метода login(), возвращал true */
        given(loginProcessor.login()).willReturn(true);
        /* Вызываем тестируемый метод с заданными предпосылками */
        String result = loginController.loginPost("username", "password", model);
        /* Проверяем значение, которое возвращает тестируемый метод */
        assertEquals("login.html", result);
        /* Проверяем значение аттрибута сообщения, который был добавлен к объекту модели */
        verify(model).addAttribute("message", "You are now logged in.");
    }

    @Test
    public void loginPostLoginFailsTest() {
        given(loginProcessor.login()).willReturn(false);

        String result = loginController.loginPost("username", "password", model);

        assertEquals("login.html", result);

        verify(model).addAttribute("message", "Login failed!");
    }
}