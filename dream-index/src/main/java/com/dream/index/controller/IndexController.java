package com.dream.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("indexController")
public class IndexController {
    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request, Map<String, Object> model) {
        //首页图片
        model.put("indexBannerList", null);
        //总成交吨数, 总金额, 昨日更新总吨数
        model.put("totalAmount", "100000");
        model.put("totalMoney", "10000000");
        model.put("totalAmountYesterday", "1000000");
        //成交记录
        model.put("orderTransactionList", null);
        //煤矿专区 店铺列表
        model.put("shopList", null);
        //需求专区
        model.put("demandList", null);
        //煤炭商城
        model.put("sellInfoList", null);
        //资讯模块
        model.put("newsFirstArea", null);
        model.put("newsSecondArea", null);
        model.put("newsThirdArea", null);
        model.put("ymalertsList", null);
        //最新易煤指数
        model.put("index", null);
        //最新指数报告
        model.put("newestIndexArticle", null);
        //最新动态
        model.put("latestNews", null);
        //最新公告
        model.put("latestNotice", null);
        //合作伙伴list
        model.put("partnerList", null);
        //友情链接
        model.put("FriendlyLinkList", null);
        model.put("title", "");
        model.put("keywords", "");
        model.put("description", "");
        return "index/index";
    }


}
