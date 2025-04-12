package io.ratel.stocktrader.api.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ratel.stocktrader.business.order.api.controller.OrderController;
import io.ratel.stocktrader.business.order.api.request.BuyOrderRequest;
import io.ratel.stocktrader.business.order.api.request.SellOrderRequest;
import io.ratel.stocktrader.business.order.domain.entity.Order;
import io.ratel.stocktrader.business.order.domain.entity.OrderStatus;
import io.ratel.stocktrader.business.order.domain.entity.OrderType;
import io.ratel.stocktrader.business.order.domain.service.OrderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(OrderController.class)
@Import(OrderControllerTest.TestMockConfig.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @TestConfiguration
    static class TestMockConfig {
        @Bean
        public OrderService orderService() {
            return Mockito.mock(OrderService.class);
        }
    }

    @Nested
    @DisplayName("매수 주문 등록 (POST /api/v1/order/buy)")
    @Disabled
    class BuyOrder {

        @Test
        @DisplayName("성공 - 올바른 요청일 경우 201 반환")
        void placeBuyOrder_success() throws Exception {
            BuyOrderRequest request = createRequest(101, 10, 1500.0, 1001, "123-4567-8901");

            Order savedOrder = Order.ofBuyOrder(
                    1001,  // userId
                    101,   // itemId
                    123456789, // accountId (숫자 변환된 값)
                    10,    // quantity
                    OrderType.LIMIT // 주문 방식은 임시 하드코딩
            );

            Mockito.when(orderService.buyStock(any(Order.class))).thenReturn(savedOrder);

            mockMvc.perform(post("/api/v1/order/buy")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.order_id").value(1))
                    .andExpect(jsonPath("$.status").value("NEW"));
        }

        @ParameterizedTest(name = "[{index}] 유효하지 않은 요청 - {0}")
        @MethodSource("invalidBuyOrderProvider")
        @DisplayName("실패 - 유효하지 않은 요청 시 400 반환")
        void placeBuyOrder_fail_invalidInput(String description, BuyOrderRequest request) throws Exception {
            mockMvc.perform(post("/api/v1/order/buy")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 - 잘못된 타입 JSON (item_id에 문자열)")
        void placeBuyOrder_fail_invalidJsonType() throws Exception {
            String invalidJson = """
                {
                    "item_id": "ABC",
                    "quantity": 10,
                    "price": 1500.0,
                    "user_id": 1001,
                    "account_id": "123-4567-8901"
                }
                """;

            mockMvc.perform(post("/api/v1/order/buy")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(invalidJson))
                    .andExpect(status().isBadRequest());
        }

        private static Stream<Arguments> invalidBuyOrderProvider() {
            return Stream.of(
                    Arguments.of("item_id 누락", createRequest(null, 10, 1500.0, 1001, "123-4567-8901")),
                    Arguments.of("quantity 0", createRequest(101, 0, 1500.0, 1001, "123-4567-8901")),
                    Arguments.of("quantity 음수", createRequest(101, -5, 1500.0, 1001, "123-4567-8901")),
                    Arguments.of("price 0.0", createRequest(101, 10, 0.0, 1001, "123-4567-8901")),
                    Arguments.of("price 음수", createRequest(101, 10, -100.0, 1001, "123-4567-8901")),
                    Arguments.of("user_id 누락", createRequest(101, 10, 1500.0, null, "123-4567-8901")),
                    Arguments.of("account_id 빈 문자열", createRequest(101, 10, 1500.0, 1001, "")),
                    Arguments.of("account_id 공백만", createRequest(101, 10, 1500.0, 1001, "   "))
            );
        }

        private static BuyOrderRequest createRequest(Integer itemId, Integer quantity, Double price, Integer userId, String accountId) {
            BuyOrderRequest request = new BuyOrderRequest();
            request.setItemId(itemId);
            request.setQuantity(quantity);
            request.setPrice(price);
            request.setUserId(userId);
            request.setAccountId(accountId);
            return request;
        }
    }

    @Nested
    @DisplayName("매도 주문 등록 (POST /api/v1/order/sell)")
    class SellOrder {

        @Test
        @DisplayName("성공 - 올바른 요청일 경우 201 반환")
        void placeSellOrder_success() throws Exception {
            SellOrderRequest request = createSellRequest(101, 5, 1200.0, 1001, "123-4567-8901");

            Order savedOrder = Order.ofSellOrder(
                    1001,
                    101,
                    123456789,
                    5,
                    OrderType.LIMIT
            );
            // 테스트용 ID, 상태 설정 (필요 시)
            ReflectionTestUtils.setField(savedOrder, "id", 2L);
            ReflectionTestUtils.setField(savedOrder, "orderStatus", OrderStatus.NEW);

            Mockito.when(orderService.sellStock(any(Order.class))).thenReturn(savedOrder);

            mockMvc.perform(post("/api/v1/order/sell")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.order_id").value(2))
                    .andExpect(jsonPath("$.status").value("NEW"));
        }

        @ParameterizedTest(name = "[{index}] 유효하지 않은 요청 - {0}")
        @MethodSource("invalidSellOrderProvider")
        @DisplayName("실패 - 유효하지 않은 요청 시 400 반환")
        void placeSellOrder_fail_invalidInput(String description, SellOrderRequest request) throws Exception {
            mockMvc.perform(post("/api/v1/order/sell")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        private static Stream<Arguments> invalidSellOrderProvider() {
            return Stream.of(
                    Arguments.of("item_id 누락", createSellRequest(null, 10, 1500.0, 1001, "123-4567-8901")),
                    Arguments.of("quantity 0", createSellRequest(101, 0, 1500.0, 1001, "123-4567-8901")),
                    Arguments.of("quantity 음수", createSellRequest(101, -5, 1500.0, 1001, "123-4567-8901")),
                    Arguments.of("price 0.0", createSellRequest(101, 10, 0.0, 1001, "123-4567-8901")),
                    Arguments.of("price 음수", createSellRequest(101, 10, -100.0, 1001, "123-4567-8901")),
                    Arguments.of("user_id 누락", createSellRequest(101, 10, 1500.0, null, "123-4567-8901")),
                    Arguments.of("account_id 빈 문자열", createSellRequest(101, 10, 1500.0, 1001, "")),
                    Arguments.of("account_id 공백만", createSellRequest(101, 10, 1500.0, 1001, "   "))
            );
        }

        private static SellOrderRequest createSellRequest(Integer itemId, Integer quantity, Double price, Integer userId, String accountId) {
            SellOrderRequest request = new SellOrderRequest();
            request.setItemId(itemId);
            request.setQuantity(quantity);
            request.setPrice(price);
            request.setUserId(userId);
            request.setAccountId(accountId);
            return request;
        }
}