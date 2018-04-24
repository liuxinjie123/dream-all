package com.dream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * 打开首页
	 */
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}


	@GetMapping("/{page}")
	public String showPage(@PathVariable("page") String page) {
		return page;
	}
}
