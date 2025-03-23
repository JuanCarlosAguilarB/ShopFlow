### Use Case: Order Completion

1. **Initial action (Order Completion):**
   - A customer places an order, and upon doing so, an event is triggered in Kafka that starts the workflow. This event is sent to the `order-placed` queue.

2. **Parallel actions:**
    - **Sub-case 1:** **Inventory check. An event is sent to verify if the products are available.
    - **Sub-case 2:** **Payment validation. An event is sent to verify if the payment was successful.
    - **Sub-case 3:** **Customer history update. An event is sent to update the customer's purchase history.
    - **Sub-case 4:** **Sending order confirmation. An event is sent to send an email or notification to the customer.

-----
