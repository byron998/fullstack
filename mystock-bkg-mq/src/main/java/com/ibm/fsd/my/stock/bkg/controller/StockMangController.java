package com.ibm.fsd.my.stock.bkg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.my.stock.bkg.bean.bo.StockBaseBo;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResponse;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;
import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;
import com.ibm.fsd.my.stock.bkg.domain.StockBase;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.schedule.MyStockTradeTask;
import com.ibm.fsd.my.stock.bkg.service.StockMangService;
import com.mysql.cj.util.StringUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/stock")
public class StockMangController {
	
	@Autowired
    ApplicationContext applicationContext;
	
	@Autowired
	StockMangService stockMangService;
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @ApiOperation(value="获取全部股票")
	@GetMapping("all")
    public ReturnResult<?> getAllStocks(@RequestParam(name = "market", required=false) String market,
    		@RequestParam(name = "industry",required=false) String industry){
    	Map<String, String> condition = new HashMap<String, String>();
    	if (!StringUtils.isNullOrEmpty(market)) {
    		condition.put("market", market);
    	}
    	if (!StringUtils.isNullOrEmpty(industry)) {
    		condition.put("industry", industry);
    	}
    	stockMangService.getAllStock(condition);
        return ReturnResponse.makeOKResp(null);
    }

    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="股票停牌")
	@PostMapping("suspend/{id}")
    public ReturnResult<StockBase> issueNewStock(@PathVariable long id, 
    		@RequestParam(name = "days", required=true) @Validated @Range(min=1,max=100,message="停牌天数为1天到100天") int days){
    	StockBase result = stockMangService.getStockById(id);
    	if (result== null || result.getId()== 0l) {
    		return ReturnResponse.makeErrResp("ID is not exist.");
    	}
    	if (result.getSuspend().equals(VarcharBoolean.BOOL_TRUE.code)) {
    		return ReturnResponse.makeErrResp("Stock has all ready suspended.");
    	}
    	result.setSuspend(VarcharBoolean.BOOL_TRUE.code);
    	result.setSuspendDays(days);
    	stockMangService.suspendStock(id, days);
        return ReturnResponse.makeOKResp(result);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="发行新股，交易中禁止发行。")
	@PostMapping("issue")
    public ReturnResult<List<UserBase>> issueNewStock(StockBaseBo input){
    	MyStockTradeTask task = applicationContext.getBean(MyStockTradeTask.class);
    	if (task.selfTradeFlg) {
    		return ReturnResponse.makeErrResp("Stock market is trading, please issue when market trade closed.");
    	}
    	StockBase insert = new StockBase();
    	BeanUtils.copyProperties(input, insert);
    	stockMangService.issueANewStock(insert);
        return ReturnResponse.makeOKResp(null);
    }
}
