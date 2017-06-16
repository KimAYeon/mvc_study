package com.spring.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class errorPageController {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView errorPage404(NoHandlerFoundException ex) {
		ModelAndView mav = getModelAndView(ex);
		System.out.println("404");
		mav.addObject("msg", "404");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView errorPage500(Exception ex) {
		ModelAndView mav = getModelAndView(ex);
		System.out.println("500");
		mav.addObject("msg", "500");
		return mav;
	}

	/**
	 * @return
	 */
	private ModelAndView getModelAndView(Exception ex) {
		ModelAndView mav = new ModelAndView("/errorPage");
		mav.addObject("msg", ex.getMessage().toString());
		System.out.println(ex.getStackTrace().toString());
		ex.printStackTrace();
		return mav;
	}
	
}
