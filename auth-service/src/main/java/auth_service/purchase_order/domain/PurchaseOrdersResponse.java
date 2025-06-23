package auth_service.purchase_order.domain;

import java.util.List;

public record PurchaseOrdersResponse(List<PurchaseOrderResponse> order){}
