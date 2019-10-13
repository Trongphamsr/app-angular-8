//package com.phamtrong.app.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.phamtrong.app.entity.User;
//import com.phamtrong.app.service.JwtService;
//import com.phamtrong.app.service.UserService;
//
//@RestController
//@RequestMapping(value="/api")
//public class LoginController {
//
//	@Autowired
//	JwtService jwtService;
//	
//	@Autowired
//	UserService userService;
//	
//	@PostMapping(value="/login")
//	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody User user) {
//		String result="";
//		HttpStatus httpStatus= null;
//		try {
//			if(!userService.checkLogin(user).isEmpty()) {
//				result = jwtService.generateTokenLogin(user.getUsername());
//				httpStatus = HttpStatus.OK;
//			}else {
//				result = "pass or username is wrong";
//				httpStatus = HttpStatus.BAD_REQUEST;
//			}
//		} catch (Exception e) {
//			result = "Server error";
//			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		
//		return new ResponseEntity<String>(result, httpStatus);
//	}
//	
//	
//	@GetMapping("/users")
//	public ResponseEntity<?> findAll(Pageable pageable) {
//		return ResponseEntity.ok(userService.findAll(null, pageable));
//		
//	}
//	
//	
//}
