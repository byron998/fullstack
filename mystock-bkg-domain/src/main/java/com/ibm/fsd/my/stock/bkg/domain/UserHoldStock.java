package com.ibm.fsd.my.stock.bkg.domain;

import java.util.Date;

public class UserHoldStock {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.user_id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.stock_id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private Long stockId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.hold_type
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private String holdType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.hold_price
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private Double holdPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.hold_amount
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private Long holdAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.completed
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private String completed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_hold_stock.last_update_dt
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    private Date lastUpdateDt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.id
     *
     * @return the value of user_hold_stock.id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.id
     *
     * @param id the value for user_hold_stock.id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.user_id
     *
     * @return the value of user_hold_stock.user_id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.user_id
     *
     * @param userId the value for user_hold_stock.user_id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.stock_id
     *
     * @return the value of user_hold_stock.stock_id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public Long getStockId() {
        return stockId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.stock_id
     *
     * @param stockId the value for user_hold_stock.stock_id
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.hold_type
     *
     * @return the value of user_hold_stock.hold_type
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public String getHoldType() {
        return holdType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.hold_type
     *
     * @param holdType the value for user_hold_stock.hold_type
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setHoldType(String holdType) {
        this.holdType = holdType == null ? null : holdType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.hold_price
     *
     * @return the value of user_hold_stock.hold_price
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public Double getHoldPrice() {
        return holdPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.hold_price
     *
     * @param holdPrice the value for user_hold_stock.hold_price
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setHoldPrice(Double holdPrice) {
        this.holdPrice = holdPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.hold_amount
     *
     * @return the value of user_hold_stock.hold_amount
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public Long getHoldAmount() {
        return holdAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.hold_amount
     *
     * @param holdAmount the value for user_hold_stock.hold_amount
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setHoldAmount(Long holdAmount) {
        this.holdAmount = holdAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.completed
     *
     * @return the value of user_hold_stock.completed
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public String getCompleted() {
        return completed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.completed
     *
     * @param completed the value for user_hold_stock.completed
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setCompleted(String completed) {
        this.completed = completed == null ? null : completed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_hold_stock.last_update_dt
     *
     * @return the value of user_hold_stock.last_update_dt
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public Date getLastUpdateDt() {
        return lastUpdateDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_hold_stock.last_update_dt
     *
     * @param lastUpdateDt the value for user_hold_stock.last_update_dt
     *
     * @mbggenerated Sat Apr 25 23:44:18 CST 2020
     */
    public void setLastUpdateDt(Date lastUpdateDt) {
        this.lastUpdateDt = lastUpdateDt;
    }
}