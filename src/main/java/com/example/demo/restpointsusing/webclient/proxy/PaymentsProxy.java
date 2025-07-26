package com.example.demo.restpointsusing.webclient.proxy;

import com.example.demo.restpointsusing.webclient.models.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class PaymentsProxy {

    private final WebClient webClient;

    /* Получаем из файла свойств URL сервиса платежей */
    @Value("${name.service.url}")
    private String paymentsServiceUrl;

    /* С помощью DI внедряем в конструкторе WebClient из контекста Spring */
    public PaymentsProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(String requestId, Payment payment) {
        /* Определяем HTTP-метод, который будет использоваться при вызове */
        return webClient.post()
                /* Определяем URI вызова */
                .uri(paymentsServiceUrl + "/payment")
                /* Добавляем к запросу HTTP-заголовок.
                 Для создания нескольких заголовков можно вызвать метод header() несколько раз. */
                .header("requestId", requestId)
                /* Формируем тело HTTP-запроса */
                .body(Mono.just(payment), Payment.class)
                /* Отправляем HTTP-запрос и получаем HTTP-ответ */
                .retrieve()
                /* Извлекаем тело HTTP-запроса */
                .bodyToMono(Payment.class);
    }
}
