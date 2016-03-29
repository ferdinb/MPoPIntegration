package com.example.moka.mokampopsample;

/**
 * Created by Andreas Susilo on 2/23/2016.
 */
public class ReportRefund {

  /*  public List<com.mokapos.model.Refund> getRefund() {
        return refund;
    }

    public void setRefund(List<com.mokapos.model.Refund> refund) {
        this.refund = refund;
    }

    private List<com.mokapos.model.Refund> refund;
    public class Refund{*/
        private	String refunded_reason;
        private	String refunded_at;
        private	boolean partial_refund_completed;
        private	long parent_payment_id;
        private	long amount_change;
        private	long amount_pay;
        private	long business_id;
        private	long refund_amount;
        private	long created_by;
        private	long total_tax_amount;
        private	long id;
        private	long gratuity_id;
        private	long server_id;
        private	long total_item_price_amount;
        private	long discount_id;
        private	long customer_id;
        private	long tax_id;
        private	long total_checkouts_amount;
        private	long total_collected_amount;
        private	long total_custom_price_amount;
        private	long total_discount_amount;
        private	long total_gratuity_amount;
        private	boolean full_refund_completed;
        private	boolean include_gratuity_tax;
        private	boolean customer_feedback;
        private	boolean is_deleted;
        private	boolean is_receipt_emailed;
        private	boolean is_receipt_printed;
        private	boolean is_refunded;

        public String getRefunded_reason() {
            return refunded_reason;
        }

        public void setRefunded_reason(String refunded_reason) {
            this.refunded_reason = refunded_reason;
        }

        public String getRefunded_at() {
            return refunded_at;
        }

        public void setRefunded_at(String refunded_at) {
            this.refunded_at = refunded_at;
        }

        public boolean isPartial_refund_completed() {
            return partial_refund_completed;
        }

        public void setPartial_refund_completed(boolean partial_refund_completed) {
            this.partial_refund_completed = partial_refund_completed;
        }

        public long getParent_payment_id() {
            return parent_payment_id;
        }

        public void setParent_payment_id(long parent_payment_id) {
            this.parent_payment_id = parent_payment_id;
        }

        public long getAmount_change() {
            return amount_change;
        }

        public void setAmount_change(long amount_change) {
            this.amount_change = amount_change;
        }

        public long getAmount_pay() {
            return amount_pay;
        }

        public void setAmount_pay(long amount_pay) {
            this.amount_pay = amount_pay;
        }

        public long getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(long business_id) {
            this.business_id = business_id;
        }

        public long getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(long refund_amount) {
            this.refund_amount = refund_amount;
        }

        public long getCreated_by() {
            return created_by;
        }

        public void setCreated_by(long created_by) {
            this.created_by = created_by;
        }

        public long getTotal_tax_amount() {
            return total_tax_amount;
        }

        public void setTotal_tax_amount(long total_tax_amount) {
            this.total_tax_amount = total_tax_amount;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getGratuity_id() {
            return gratuity_id;
        }

        public void setGratuity_id(long gratuity_id) {
            this.gratuity_id = gratuity_id;
        }

        public long getServer_id() {
            return server_id;
        }

        public void setServer_id(long server_id) {
            this.server_id = server_id;
        }

        public long getTotal_item_price_amount() {
            return total_item_price_amount;
        }

        public void setTotal_item_price_amount(long total_item_price_amount) {
            this.total_item_price_amount = total_item_price_amount;
        }

        public long getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(long discount_id) {
            this.discount_id = discount_id;
        }

        public long getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(long customer_id) {
            this.customer_id = customer_id;
        }

        public long getTax_id() {
            return tax_id;
        }

        public void setTax_id(long tax_id) {
            this.tax_id = tax_id;
        }

        public long getTotal_checkouts_amount() {
            return total_checkouts_amount;
        }

        public void setTotal_checkouts_amount(long total_checkouts_amount) {
            this.total_checkouts_amount = total_checkouts_amount;
        }

        public long getTotal_collected_amount() {
            return total_collected_amount;
        }

        public void setTotal_collected_amount(long total_collected_amount) {
            this.total_collected_amount = total_collected_amount;
        }

        public long getTotal_custom_price_amount() {
            return total_custom_price_amount;
        }

        public void setTotal_custom_price_amount(long total_custom_price_amount) {
            this.total_custom_price_amount = total_custom_price_amount;
        }

        public long getTotal_discount_amount() {
            return total_discount_amount;
        }

        public void setTotal_discount_amount(long total_discount_amount) {
            this.total_discount_amount = total_discount_amount;
        }

        public long getTotal_gratuity_amount() {
            return total_gratuity_amount;
        }

        public void setTotal_gratuity_amount(long total_gratuity_amount) {
            this.total_gratuity_amount = total_gratuity_amount;
        }

        public boolean isFull_refund_completed() {
            return full_refund_completed;
        }

        public void setFull_refund_completed(boolean full_refund_completed) {
            this.full_refund_completed = full_refund_completed;
        }

        public boolean isInclude_gratuity_tax() {
            return include_gratuity_tax;
        }

        public void setInclude_gratuity_tax(boolean include_gratuity_tax) {
            this.include_gratuity_tax = include_gratuity_tax;
        }

        public boolean isCustomer_feedback() {
            return customer_feedback;
        }

        public void setCustomer_feedback(boolean customer_feedback) {
            this.customer_feedback = customer_feedback;
        }

        public boolean is_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(boolean is_deleted) {
            this.is_deleted = is_deleted;
        }

        public boolean is_receipt_emailed() {
            return is_receipt_emailed;
        }

        public void setIs_receipt_emailed(boolean is_receipt_emailed) {
            this.is_receipt_emailed = is_receipt_emailed;
        }

        public boolean is_receipt_printed() {
            return is_receipt_printed;
        }

        public void setIs_receipt_printed(boolean is_receipt_printed) {
            this.is_receipt_printed = is_receipt_printed;
        }

        public boolean is_refunded() {
            return is_refunded;
        }

        public void setIs_refunded(boolean is_refunded) {
            this.is_refunded = is_refunded;
        }
    //}
}
