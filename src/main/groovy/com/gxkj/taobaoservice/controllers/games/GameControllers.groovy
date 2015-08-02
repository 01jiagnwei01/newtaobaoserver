package com.gxkj.taobaoservice.controllers.games

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/games")
class GameControllers {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String findbackpassword_GET(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String mv = "site/games/BlockBlaster/BlockBlaster";
		return mv;
	}
}
