package bsuir.chernikov.main.service;

import bsuir.chernikov.main.entities.*;
import bsuir.chernikov.main.enums.ORDER_STATUS;
import bsuir.chernikov.main.repository.OrderRepository;
import bsuir.chernikov.main.repository.ReplyRepository;
import bsuir.chernikov.main.dto.OrderDto;
import bsuir.chernikov.main.dto.ReplyRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ReplyRepository replyRepository;
    private final AddressService addressService;
    private final UserService userService;
    private final RouteService routeService;

    @Transactional
    public Integer createOrder(OrderDto orderRequest) {
        if (userService.getUserFromContext() instanceof Client client) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setName(orderRequest.getName());

            Address startAddress = addressService.getAddressById(Long.valueOf(orderRequest.getStartPointId()));
            Address endAddress = addressService.getAddressById(Long.valueOf(orderRequest.getEndPointId()));
            deliveryOrder.setStartPoint(startAddress);
            deliveryOrder.setEndPoint(endAddress);

            routeService.calculateFullOrderData(orderRequest);
            deliveryOrder.setDistance(orderRequest.getDistance());
            deliveryOrder.setWeight(orderRequest.getWeight());
            deliveryOrder.setCalculatedTime(orderRequest.getCalculatedTime());
            deliveryOrder.setCo2Emission(orderRequest.getCo2Emission());

            deliveryOrder.setStatus(ORDER_STATUS.CREATED);
            deliveryOrder.setClient(client);
            deliveryOrder.setCreated(LocalDateTime.now());

            System.out.println(deliveryOrder);

            return Math.toIntExact(orderRepository.save(deliveryOrder).getId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public void deleteOrder(Long orderId) {
        Optional<DeliveryOrder> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(orderId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<DeliveryOrder> getClientOrders(ORDER_STATUS orderStatus) {
        User user = userService.getUserFromContext();
        if (user instanceof Client client) {
            return orderRepository.findAllByClientAndStatus(client, orderStatus);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public DeliveryOrder getOrder(Long id) {
        Optional<DeliveryOrder> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not found by id = " + id);
        }
    }

    @Transactional
    public void replyToOrder(ReplyRequest replyRequest) {
        if (userService.getUserFromContext() instanceof Courier courier) {
            try {
                Reply reply = new Reply();
                reply.setCourier(courier);
                reply.setOrder(getOrder(Long.valueOf(replyRequest.getOrderId())));
                reply.setPrice(Integer.valueOf(replyRequest.getPrice()));
                reply.setDateTime(LocalDateTime.now());
                replyRepository.save(reply);
            } catch (RuntimeException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Transactional
    public void acceptReply(Long replyId) {
        Optional<Reply> replyOptional = replyRepository.findById(replyId);
        if (replyOptional.isPresent()) {
            Reply reply = replyOptional.get();
            DeliveryOrder order = reply.getOrder();
            order.setCourier(reply.getCourier());
            order.setStatus(ORDER_STATUS.IN_PROGRESS);
            orderRepository.save(order);
            replyRepository.delete(reply);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
