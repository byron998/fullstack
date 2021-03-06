package com.ibm.fsd.my.stock.bkg.mapper;

import com.ibm.fsd.my.stock.bkg.domain.StockTradeRecord;

public interface StockTradeRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_trade_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_trade_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int insert(StockTradeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_trade_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int insertSelective(StockTradeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_trade_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    StockTradeRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_trade_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int updateByPrimaryKeySelective(StockTradeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_trade_record
     *
     * @mbggenerated Sun May 10 22:26:47 CST 2020
     */
    int updateByPrimaryKey(StockTradeRecord record);
}