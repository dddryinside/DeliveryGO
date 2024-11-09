package bsuir.chernikov.main.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request DTO for an Order")
public class OrderRequest {

    @Schema(description = "Unique identifier of the order", example = "123")
    private Long id;

    @Schema(description = "Name of the order", example = "Package Delivery")
    private String name;

    @Schema(description = "Starting point of the order", example = "Warehouse A")
    private String startPoint;

    @Schema(description = "Ending point of the order", example = "Client's Address")
    private String endPoint;

    @Schema(description = "Distance for the order", example = "15km")
    private String distance;

    @Schema(description = "Size of the package", example = "Medium")
    private String size;

    @Schema(description = "Current status of the order", example = "Pending")
    private String status;

    @Schema(description = "Creation date of the order", example = "2024-11-02T10:00:00Z")
    private String createdAt;

    @Schema(description = "Assigned courier for the order", example = "John Doe")
    private String courier;
}
