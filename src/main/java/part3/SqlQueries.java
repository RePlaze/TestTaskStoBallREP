package part3;

public class SqlQueries {
    
    public static class Queries {
        public static final String ORDERS_WITH_PAID_PAYMENTS_BUT_NOT_PAID = """
            SELECT o.id, o.amount, o.status
            FROM orders o
            JOIN payments p ON o.id = p.order_id
            WHERE o.status != 'paid'
            AND p.status = 'paid'
            GROUP BY o.id, o.amount, o.status
            HAVING COUNT(p.id) > 0;
            """;

        public static final String ORDERS_WITH_AMOUNT_MISMATCH = """
            SELECT o.id, o.amount, SUM(p.amount) as total_payments
            FROM orders o
            JOIN payments p ON o.id = p.order_id
            WHERE o.status = 'paid'
            AND p.status = 'paid'
            GROUP BY o.id, o.amount
            HAVING o.amount != SUM(p.amount);
            """;

        public static final String PAID_ORDERS_WITHOUT_PAID_PAYMENTS = """
            SELECT o.id, o.amount, o.status
            FROM orders o
            LEFT JOIN payments p ON o.id = p.order_id AND p.status = 'paid'
            WHERE o.status = 'paid'
            AND p.id IS NULL;
            """;

        public static final String PENDING_ORDERS_WITHOUT_PAYMENTS = """
            SELECT o.id, o.amount, o.status
            FROM orders o
            LEFT JOIN payments p ON o.id = p.order_id
            WHERE o.status = 'pending'
            AND p.id IS NULL;
            """;
    }
} 