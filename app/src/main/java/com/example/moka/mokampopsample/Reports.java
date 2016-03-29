package com.example.moka.mokampopsample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ferdi on 7/22/2015.
 */
public class Reports {
    private List<Details> reports;

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public Boolean getComplted() {
        return completed;
    }

    public void setComplted(Boolean complted) {
        this.completed = complted;
    }

    private String next_url;
    private Boolean completed;

    public List<Details> getResults() {
        return results;
    }

    public void setResults(List<Details> results) {
        this.results = results;
    }

    private List<Details> results;

    public List<Details> getReports() {
        return reports;
    }

    public void setReports(List<Details> reports) {
        this.reports = reports;
    }

    public static class Checkouts {
        private String item_name;
        private String item_variant_name;
        private long price;
        private int quantity;
        //private long revenue;
        private String note;
        private long item_price_library;
        private long gross_sales;
        private List<Modifiers> modifiers;
        private List<Discounts> discounts;


        public List<Discounts> getDiscounts() {
            return discounts;
        }

        public void setDiscounts(List<Discounts> discounts) {
            this.discounts = discounts;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_variant_name() {
            return item_variant_name;
        }

        public void setItem_variant_name(String item_variant_name) {
            this.item_variant_name = item_variant_name;
        }

        /*public long getRevenue() {
            return revenue;
        }*/

        /* public void setRevenue(long revenue) {
            this.revenue = revenue;
        }
        */
        public long getItem_price_library() {
            return item_price_library;
        }

        public void setItem_price_library(long item_price_library) {
            this.item_price_library = item_price_library;
        }

        public long getGross_sales() {
            return gross_sales;
        }

        public void setGross_sales(long gross_sales) {
            this.gross_sales = gross_sales;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public List<Modifiers> getModifiers() {
            return modifiers;
        }

        public void setModifiers(List<Modifiers> modifiers) {
            this.modifiers = modifiers;
        }
    }

    public static class Discounts {
        private long id;
        private long discount_id;
        private long checkout_id;
        private long discount_amount;
        private String discount_name;
        private String discount_type;
        private double discount_percentage;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(long discount_id) {
            this.discount_id = discount_id;
        }

        public long getCheckout_id() {
            return checkout_id;
        }

        public void setCheckout_id(long checkout_id) {
            this.checkout_id = checkout_id;
        }

        public long getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(long discount_amount) {
            this.discount_amount = discount_amount;
        }

        public String getDiscount_name() {
            return discount_name;
        }

        public void setDiscount_name(String discount_name) {
            this.discount_name = discount_name;
        }

        public String getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(String discount_type) {
            this.discount_type = discount_type;
        }

        public double getDiscount_percentage() {
            return discount_percentage;
        }

        public void setDiscount_percentage(double discount_percentage) {
            this.discount_percentage = discount_percentage;
        }
    }

    public static class Modifiers {
        private String modifier_name;
        private String modifier_option_name;
        private long price;
        private long checkout_id;
        private long modifier_id;
        private long modifier_option_id;


        public String getModifier_name() {
            return modifier_name;
        }

        public void setModifier_name(String modifier_name) {
            this.modifier_name = modifier_name;
        }

        public String getModifier_option_name() {
            return modifier_option_name;
        }

        public void setModifier_option_name(String modifier_option_name) {
            this.modifier_option_name = modifier_option_name;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public long getCheckout_id() {
            return checkout_id;
        }

        public void setCheckout_id(long checkout_id) {
            this.checkout_id = checkout_id;
        }

        public long getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(long modifier_id) {
            this.modifier_id = modifier_id;
        }

        public long getModifier_option_id() {
            return modifier_option_id;
        }

        public void setModifier_option_id(long modifier_option_id) {
            this.modifier_option_id = modifier_option_id;
        }
    }

    public static class Refunds {
        private long id;
        private String status;
        private String payment_no;
        private String payment_type;
        private long amount_pay;
        private long amount_change;
        private long total_discount_amount;
        private long total_gratuity_amount;
        private long total_item_price_amount;
        private long total_tax_amount;
        private long total_checkouts_amount;
        private boolean is_receipt_printed;
        private boolean is_receipt_emailed;
        private boolean is_refunded;
        private String refunded_reason;
        private String refunded_date;
        private String refunded_at;
        private String pg_mid;
        private String transaction_certificate;
        private String transaction_status_info;
        private String mpos_transaction_date;
        private String transaction_reference;
        private String transaction_number;
        private String order_info;
        private String pii;

        public boolean is_receipt_printed() {
            return is_receipt_printed;
        }

        public boolean is_receipt_emailed() {
            return is_receipt_emailed;
        }

        public boolean is_refunded() {
            return is_refunded;
        }

        public String getPg_mid() {
            return pg_mid;
        }

        public void setPg_mid(String pg_mid) {
            this.pg_mid = pg_mid;
        }

        public String getTransaction_certificate() {
            return transaction_certificate;
        }

        public void setTransaction_certificate(String transaction_certificate) {
            this.transaction_certificate = transaction_certificate;
        }

        public String getTransaction_status_info() {
            return transaction_status_info;
        }

        public void setTransaction_status_info(String transaction_status_info) {
            this.transaction_status_info = transaction_status_info;
        }

        public String getMpos_transaction_date() {
            return mpos_transaction_date;
        }

        public void setMpos_transaction_date(String mpos_transaction_date) {
            this.mpos_transaction_date = mpos_transaction_date;
        }

        public String getTransaction_reference() {
            return transaction_reference;
        }

        public void setTransaction_reference(String transaction_reference) {
            this.transaction_reference = transaction_reference;
        }

        public String getTransaction_number() {
            return transaction_number;
        }

        public void setTransaction_number(String transaction_number) {
            this.transaction_number = transaction_number;
        }

        public String getOrder_info() {
            return order_info;
        }

        public void setOrder_info(String order_info) {
            this.order_info = order_info;
        }

        public String getPii() {
            return pii;
        }

        public void setPii(String pii) {
            this.pii = pii;
        }

        public boolean is_deleted() {
            return is_deleted;
        }

        public String getRefunded_at() {
            return refunded_at;
        }

        public void setRefunded_at(String refunded_at) {
            this.refunded_at = refunded_at;
        }

        private boolean customer_feedback;
        private long business_id;
        private long customer_id;
        private long discount_id;
        private boolean is_deleted;

        private String created_at;
        private String updated_at;
        private long total_custom_price_amount;
        private long gratuity_id;
        private long tax_id;
        private boolean include_gratuity_tax;
        private long parent_payment_id;
        private long refund_amount;
        private String refund_type;
        private String card_no;
        private boolean partial_refund_completed;
        private boolean full_refund_completed;
        private long created_by;
        private String note;
        private long total_collected_amount;
        private long server_id;
        private String server_name;
        private String server_title;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayment_no() {
            return payment_no;
        }

        public void setPayment_no(String payment_no) {
            this.payment_no = payment_no;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public long getAmount_pay() {
            return amount_pay;
        }

        public void setAmount_pay(long amount_pay) {
            this.amount_pay = amount_pay;
        }

        public long getAmount_change() {
            return amount_change;
        }

        public void setAmount_change(long amount_change) {
            this.amount_change = amount_change;
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

        public long getTotal_item_price_amount() {
            return total_item_price_amount;
        }

        public void setTotal_item_price_amount(long total_item_price_amount) {
            this.total_item_price_amount = total_item_price_amount;
        }

        public long getTotal_tax_amount() {
            return total_tax_amount;
        }

        public void setTotal_tax_amount(long total_tax_amount) {
            this.total_tax_amount = total_tax_amount;
        }

        public long getTotal_checkouts_amount() {
            return total_checkouts_amount;
        }

        public void setTotal_checkouts_amount(long total_checkouts_amount) {
            this.total_checkouts_amount = total_checkouts_amount;
        }

        public boolean isIs_receipt_printed() {
            return is_receipt_printed;
        }

        public void setIs_receipt_printed(boolean is_receipt_printed) {
            this.is_receipt_printed = is_receipt_printed;
        }

        public boolean isIs_receipt_emailed() {
            return is_receipt_emailed;
        }

        public void setIs_receipt_emailed(boolean is_receipt_emailed) {
            this.is_receipt_emailed = is_receipt_emailed;
        }

        public boolean isIs_refunded() {
            return is_refunded;
        }

        public void setIs_refunded(boolean is_refunded) {
            this.is_refunded = is_refunded;
        }

        public String getRefunded_reason() {
            return refunded_reason;
        }

        public void setRefunded_reason(String refunded_reason) {
            this.refunded_reason = refunded_reason;
        }

        public String getRefunded_date() {
            return refunded_date;
        }

        public void setRefunded_date(String refunded_date) {
            this.refunded_date = refunded_date;
        }

        public boolean isCustomer_feedback() {
            return customer_feedback;
        }

        public void setCustomer_feedback(boolean customer_feedback) {
            this.customer_feedback = customer_feedback;
        }

        public long getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(long business_id) {
            this.business_id = business_id;
        }

        public long getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(long customer_id) {
            this.customer_id = customer_id;
        }

        public long getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(long discount_id) {
            this.discount_id = discount_id;
        }

        public boolean isIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(boolean is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public long getTotal_custom_price_amount() {
            return total_custom_price_amount;
        }

        public void setTotal_custom_price_amount(long total_custom_price_amount) {
            this.total_custom_price_amount = total_custom_price_amount;
        }

        public long getGratuity_id() {
            return gratuity_id;
        }

        public void setGratuity_id(long gratuity_id) {
            this.gratuity_id = gratuity_id;
        }

        public long getTax_id() {
            return tax_id;
        }

        public void setTax_id(long tax_id) {
            this.tax_id = tax_id;
        }

        public boolean isInclude_gratuity_tax() {
            return include_gratuity_tax;
        }

        public void setInclude_gratuity_tax(boolean include_gratuity_tax) {
            this.include_gratuity_tax = include_gratuity_tax;
        }

        public long getParent_payment_id() {
            return parent_payment_id;
        }

        public void setParent_payment_id(long parent_payment_id) {
            this.parent_payment_id = parent_payment_id;
        }

        public long getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(long refund_amount) {
            this.refund_amount = refund_amount;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public boolean isPartial_refund_completed() {
            return partial_refund_completed;
        }

        public void setPartial_refund_completed(boolean partial_refund_completed) {
            this.partial_refund_completed = partial_refund_completed;
        }

        public boolean isFull_refund_completed() {
            return full_refund_completed;
        }

        public void setFull_refund_completed(boolean full_refund_completed) {
            this.full_refund_completed = full_refund_completed;
        }

        public long getCreated_by() {
            return created_by;
        }

        public void setCreated_by(long created_by) {
            this.created_by = created_by;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public long getTotal_collected_amount() {
            return total_collected_amount;
        }

        public void setTotal_collected_amount(long total_collected_amount) {
            this.total_collected_amount = total_collected_amount;
        }

        public long getServer_id() {
            return server_id;
        }

        public void setServer_id(long server_id) {
            this.server_id = server_id;
        }

        public String getServer_name() {
            return server_name;
        }

        public void setServer_name(String server_name) {
            this.server_name = server_name;
        }

        public String getServer_title() {
            return server_title;
        }

        public void setServer_title(String server_title) {
            this.server_title = server_title;
        }
    }

    public static class Details {
        private long id;
        private String payment_no;
        private String created_at;
        private String updated_at;

        public String getServed_by() {
            return served_by;
        }

        public void setServed_by(String served_by) {
            this.served_by = served_by;
        }

        private String served_by;

        @SerializedName("collected_by")
        private String created_by;
        private String parent_payment_created_at;
        private long total_collected;
        private long total_item_price_amount;
        private String name;
        private List<Checkouts> checkouts;
        private long parent_payment_id;
        private String payment_type;
        private String payment_type_label;
        private String payment_note;
        private long discounts;
        private long subtotal;
        private long gratuities;
        private long taxes;
        private long tendered;
        private long change;
        private long total_refund;
        private boolean is_refunded;
        private boolean refundable;
        private long refund_amount;
        private List<Refunds> payment_refunds;
        private String business_name;
        private String business_logo;
        private boolean include_gratuity_tax;
        private String email;
        private String transaction_date;
        private String transaction_time;

        private boolean enable_tax;
        private boolean enable_gratuity;
        private String jsonCheckout;
        private String jsonRefunds;

        public String getJsonRefunds() {
            return jsonRefunds;
        }

        public void setJsonRefunds(String jsonRefunds) {
            this.jsonRefunds = jsonRefunds;
        }

        private String card_no;
        private String auth_code;
        private String card_type;
        private String cc_name;
        private String transaction_number;
        private String transaction_reference;
        private String transaction_certificate;
        private String transaction_status_info;
        private String merchant_id;
        private String mpos_device_id;
        private String pg_mid;
        private String pg_setting;
        private String order_info;
        private String tvr;
        private String cvm_result;
        private String aid;
        private String mpos_transaction_date;
        private String pii;

        private String voided;
        private String voidedReason;
        private String voidedAmount;
        private long outlet_id;

        public long getOutlet_id() {
            return outlet_id;
        }

        public void setOutlet_id(long outlet_id) {
            this.outlet_id = outlet_id;
        }

        public String getPii() {
            return pii;
        }

        public void setPii(String pii) {
            this.pii = pii;
        }

        public String getVoidedReason() {
            return voidedReason;
        }

        public void setVoidedReason(String voidedReason) {
            this.voidedReason = voidedReason;
        }

        public String getVoidedAmount() {
            return voidedAmount;
        }

        public void setVoidedAmount(String voidedAmount) {
            this.voidedAmount = voidedAmount;
        }

        public String getVoided() {
            return voided;
        }

        public void setVoided(String voided) {
            this.voided = voided;
        }


        public String getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(String auth_code) {
            this.auth_code = auth_code;
        }

        public String getCc_name() {
            return cc_name;
        }

        public void setCc_name(String cc_name) {
            this.cc_name = cc_name;
        }

        public String getTransaction_number() {
            return transaction_number;
        }

        public void setTransaction_number(String transaction_number) {
            this.transaction_number = transaction_number;
        }

        public String getTransaction_reference() {
            return transaction_reference;
        }

        public void setTransaction_reference(String transaction_reference) {
            this.transaction_reference = transaction_reference;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getPg_setting() {
            return pg_setting;
        }

        public void setPg_setting(String pg_setting) {
            this.pg_setting = pg_setting;
        }

        public String getOrder_info() {
            return order_info;
        }

        public void setOrder_info(String order_info) {
            this.order_info = order_info;
        }

        public String getTvr() {
            return tvr;
        }

        public void setTvr(String tvr) {
            this.tvr = tvr;
        }

        public String getCvm_result() {
            return cvm_result;
        }

        public void setCvm_result(String cvm_result) {
            this.cvm_result = cvm_result;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getPg_mid() {
            return pg_mid;
        }

        public void setPg_mid(String pg_mid) {
            this.pg_mid = pg_mid;
        }

        public String getMpos_device_id() {
            return mpos_device_id;
        }

        public void setMpos_device_id(String mpos_device_id) {
            this.mpos_device_id = mpos_device_id;
        }

        public String getTransaction_certificate() {
            return transaction_certificate;
        }

        public void setTransaction_certificate(String transaction_certificate) {
            this.transaction_certificate = transaction_certificate;
        }

        public String getTransaction_status_info() {
            return transaction_status_info;
        }

        public void setTransaction_status_info(String transaction_status_info) {
            this.transaction_status_info = transaction_status_info;
        }

        public String getMpos_transaction_date() {
            return mpos_transaction_date;
        }

        public void setMpos_transaction_date(String mpos_transaction_date) {
            this.mpos_transaction_date = mpos_transaction_date;
        }

        public String getTransaction_date() {
            return transaction_date;
        }

        public void setTransaction_date(String transaction_date) {
            this.transaction_date = transaction_date;
        }

        public String getTransaction_time() {
            return transaction_time;
        }

        public void setTransaction_time(String transaction_time) {
            this.transaction_time = transaction_time;
        }

        public long getTotal_item_price_amount() {
            return total_item_price_amount;
        }

        public void setTotal_item_price_amount(long total_item_price_amount) {
            this.total_item_price_amount = total_item_price_amount;
        }

        private List<ReportDiscount> payment_discounts;
        private String jsonCashDiscount;

        private List<ReportTax> payment_taxes;
        private String jsonTax;

        private List<ReportGratuity> payment_gratuities;
        private String jsonGratuities;


        public List<ReportDiscount> getPayment_discounts() {
            return payment_discounts;
        }

        public void setPayment_discounts(List<ReportDiscount> payment_discounts) {
            this.payment_discounts = payment_discounts;
        }

        public String getJsonCashDiscount() {
            return jsonCashDiscount;
        }

        public void setJsonCashDiscount(String jsonCashDiscount) {
            this.jsonCashDiscount = jsonCashDiscount;
        }

        public List<ReportTax> getPayment_taxes() {
            return payment_taxes;
        }

        public void setPayment_taxes(List<ReportTax> payment_taxes) {
            this.payment_taxes = payment_taxes;
        }

        public String getJsonTax() {
            return jsonTax;
        }

        public void setJsonTax(String jsonTax) {
            this.jsonTax = jsonTax;
        }

        public List<ReportGratuity> getPayment_gratuities() {
            return payment_gratuities;
        }

        public void setPayment_gratuities(List<ReportGratuity> payment_gratuities) {
            this.payment_gratuities = payment_gratuities;
        }

        public String getJsonGratuities() {
            return jsonGratuities;
        }

        public void setJsonGratuities(String jsonGratuities) {
            this.jsonGratuities = jsonGratuities;
        }

        public String getJsonCheckout() {
            return jsonCheckout;
        }

        public void setJsonCheckout(String jsonCheckout) {
            this.jsonCheckout = jsonCheckout;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPayment_no() {
            return payment_no;
        }

        public void setPayment_no(String payment_no) {
            this.payment_no = payment_no;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getParent_payment_created_at() {
            return parent_payment_created_at;
        }

        public void setParent_payment_created_at(String parent_payment_created_at) {
            this.parent_payment_created_at = parent_payment_created_at;
        }

        public long getTotal_collected() {
            return total_collected;
        }

        public void setTotal_collected(long total_collected) {
            this.total_collected = total_collected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Checkouts> getCheckouts() {
            return checkouts;
        }

        public void setCheckouts(List<Checkouts> checkouts) {
            this.checkouts = checkouts;
        }

        public long getParent_payment_id() {
            return parent_payment_id;
        }

        public void setParent_payment_id(long parent_payment_id) {
            this.parent_payment_id = parent_payment_id;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getPayment_type_label() {
            return payment_type_label;
        }

        public void setPayment_type_label(String payment_type_label) {
            this.payment_type_label = payment_type_label;
        }

        public String getPayment_note() {
            return payment_note;
        }

        public void setPayment_note(String payment_note) {
            this.payment_note = payment_note;
        }

        public long getDiscounts() {
            return discounts;
        }

        public void setDiscounts(long discounts) {
            this.discounts = discounts;
        }

        public long getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(long subtotal) {
            this.subtotal = subtotal;
        }

        public long getGratuities() {
            return gratuities;
        }

        public void setGratuities(long gratuities) {
            this.gratuities = gratuities;
        }

        public long getTaxes() {
            return taxes;
        }

        public void setTaxes(long taxes) {
            this.taxes = taxes;
        }

        public long getTendered() {
            return tendered;
        }

        public void setTendered(long tendered) {
            this.tendered = tendered;
        }

        public long getChange() {
            return change;
        }

        public void setChange(long change) {
            this.change = change;
        }

        public long getTotal_refund() {
            return total_refund;
        }

        public void setTotal_refund(long total_refund) {
            this.total_refund = total_refund;
        }

        public boolean isIs_refunded() {
            return is_refunded;
        }

        public void setIs_refunded(boolean is_refunded) {
            this.is_refunded = is_refunded;
        }

        public boolean isRefundable() {
            return refundable;
        }

        public void setRefundable(boolean refundable) {
            this.refundable = refundable;
        }

        public long getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(long refund_amount) {
            this.refund_amount = refund_amount;
        }

        public List<Refunds> getPayment_refunds() {
            return payment_refunds;
        }

        public void setPayment_refunds(List<Refunds> payment_refunds) {
            this.payment_refunds = payment_refunds;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getBusiness_logo() {
            return business_logo;
        }

        public void setBusiness_logo(String business_logo) {
            this.business_logo = business_logo;
        }

        public boolean isInclude_gratuity_tax() {
            return include_gratuity_tax;
        }

        public void setInclude_gratuity_tax(boolean include_gratuity_tax) {
            this.include_gratuity_tax = include_gratuity_tax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isEnable_tax() {
            return enable_tax;
        }

        public void setEnable_tax(boolean enable_tax) {
            this.enable_tax = enable_tax;
        }

        public boolean isEnable_gratuity() {
            return enable_gratuity;
        }

        public void setEnable_gratuity(boolean enable_gratuity) {
            this.enable_gratuity = enable_gratuity;
        }
    }
}

